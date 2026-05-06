async function getProperties() {
    try {
        const response = await fetch('/PropertyApp/GetPropertiesServlet');
        return await response.json();
    } catch (e) {
        console.error("Failed to fetch properties", e);
        return [];
    }
}

function getQueryParam(name) {
    return new URLSearchParams(window.location.search).get(name);
}

function formatPrice(price) {
    return Number(price).toLocaleString('en-US', { minimumFractionDigits: 2, maximumFractionDigits: 2 });
}

// Add Property
function initAddProperty() {
    // We let the HTML form submit naturally to the servlet
    console.log("Add Property page initialized");
}

// View Properties
async function initViewProperties() {
    const listContainer = document.getElementById('propertyList');
    if (!listContainer) return;

    const properties = await getProperties();

    if (properties.length === 0) {
        listContainer.innerHTML = `<div class="empty-state"><h3>No properties available</h3><p>Be the first to add a property listing!</p></div>`;
        return;
    }

    listContainer.innerHTML = properties.map((p, i) => `
        <div class="property-card" style="opacity:0;animation:fadeIn 0.5s ease-out ${i * 0.1}s forwards">
            <div class="header">
                <div class="title">${p.title}</div>
                <div class="price">$${formatPrice(p.price)}</div>
            </div>
            <div class="details">
                <div class="detail-item"><strong>ID:</strong> ${p.id}</div>
                <div class="detail-item"><strong>Location:</strong> ${p.location}</div>
                <div class="detail-item"><strong>Listed on:</strong> ${p.date || 'N/A'}</div>
            </div>
            <div class="agent-info">
                <div><strong>Agent Contact:</strong></div>
                <span>👤 ${p.agent.agentName}</span>
                <span>📞 ${p.agent.phoneNumber}</span>
                <span>🏢 ${p.agent.agencyName}</span>
                <span>🏷️ ${p.agent.licenseNumber}</span>
            </div>
            <div style="margin-top:1rem;padding-top:1rem;border-top:1px solid rgba(255,255,255,0.05);text-align:right;">
                <a href="updateProperty.html?id=${encodeURIComponent(p.id)}" style="display:inline-block;padding:0.5rem 1rem;background:var(--accent-color);color:white;text-decoration:none;border-radius:6px;font-size:0.9rem;font-weight:600;transition:background 0.3s;">Edit Property</a>
            </div>
        </div>
    `).join('');
}

// Update Property
async function initUpdateProperty() {
    const form = document.getElementById('updateForm');
    if (!form) return;

    const id = getQueryParam('id');
    const properties = await getProperties();
    const p = properties.find(x => x.id === id);

    if (!p) {
        form.closest('.container').innerHTML = `<div class="empty-state"><p>Property not found.<br><br><a href="viewProperties.html" style="color:var(--accent-color);text-decoration:none;font-weight:600;">Return to listings</a></p></div>`;
        return;
    }

    // Pre-fill the form
    document.getElementById('prop-id').value = p.id;
    form.title.value = p.title;
    form.location.value = p.location;
    form.price.value = p.price;
    form.agentName.value = p.agent.agentName;
    form.phoneNumber.value = p.agent.phoneNumber;
    form.agencyName.value = p.agent.agencyName;
    form.licenseNumber.value = p.agent.licenseNumber;

    // We let the form submit naturally to UpdatePropertyServlet
}

// Delete Property
function initDeleteProperty() {
    // We let the form submit naturally to DeletePropertyServlet
    console.log("Delete Property page initialized");
}

document.addEventListener('DOMContentLoaded', () => {
    initAddProperty();
    initViewProperties();
    initUpdateProperty();
    initDeleteProperty();
});
