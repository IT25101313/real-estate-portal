<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.realestate.realestateportal.model.User" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Real Estate Portal</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body { background-color: #f8f9fa; }
        .navbar { background-color: #1a3c5e; }
        .hero {
            background: linear-gradient(135deg, #1a3c5e 0%, #2d6a9f 100%);
            color: white;
            padding: 100px 0 80px;
            text-align: center;
        }
        .hero h1 { font-size: 3rem; font-weight: 700; }
        .hero p { font-size: 1.2rem; opacity: 0.9; }
        .feature-card {
            border: none;
            border-radius: 12px;
            box-shadow: 0 2px 15px rgba(0,0,0,0.08);
            transition: transform 0.2s;
            height: 100%;
        }
        .feature-card:hover { transform: translateY(-4px); }
        .feature-icon {
            font-size: 2.5rem;
            margin-bottom: 16px;
        }
        .btn-primary { background-color: #1a3c5e; border-color: #1a3c5e; }
        .btn-primary:hover { background-color: #16324f; }
        footer { background-color: #1a3c5e; color: white; padding: 24px 0; }
    </style>
</head>
<body>

<%
    User loggedIn = (User) session.getAttribute("loggedInUser");
%>

<!-- Navbar -->
<nav class="navbar navbar-dark px-4 py-3 d-flex justify-content-between">
    <a class="navbar-brand fw-bold fs-5" href="index.jsp">🏠 Real Estate Portal</a>
    <div>
        <% if (loggedIn != null) { %>
            <span class="text-white me-3">Hello, <%= loggedIn.getName() %></span>
            <a href="profile.jsp" class="btn btn-outline-light btn-sm me-2">My Profile</a>
            <a href="user?action=logout" class="btn btn-outline-light btn-sm">Logout</a>
        <% } else { %>
            <a href="login.jsp" class="btn btn-outline-light btn-sm me-2">Login</a>
            <a href="register.jsp" class="btn btn-light btn-sm">Register</a>
        <% } %>
    </div>
</nav>

<!-- Hero Section -->
<div class="hero">
    <div class="container">
        <h1>Find Your Dream Property</h1>
        <p class="mb-4">Browse thousands of listings across Sri Lanka</p>
        <% if (loggedIn == null) { %>
            <a href="register.jsp" class="btn btn-light btn-lg me-3 px-4">Get Started</a>
            <a href="login.jsp" class="btn btn-outline-light btn-lg px-4">Login</a>
        <% } else { %>
            <a href="#listings" class="btn btn-light btn-lg px-4">Browse Listings</a>
        <% } %>
    </div>
</div>

<!-- Features Section -->
<div class="container my-5">
    <h2 class="text-center fw-bold mb-4">Why Choose Us?</h2>
    <div class="row g-4">
        <div class="col-md-4">
            <div class="card feature-card p-4 text-center">
                <div class="feature-icon">🏡</div>
                <h5 class="fw-bold">Wide Selection</h5>
                <p class="text-muted">Apartments, villas, land and commercial properties across all districts.</p>
            </div>
        </div>
        <div class="col-md-4">
            <div class="card feature-card p-4 text-center">
                <div class="feature-icon">🔍</div>
                <h5 class="fw-bold">Easy Search</h5>
                <p class="text-muted">Filter by location, price, property type and number of bedrooms.</p>
            </div>
        </div>
        <div class="col-md-4">
            <div class="card feature-card p-4 text-center">
                <div class="feature-icon">📞</div>
                <h5 class="fw-bold">Contact Agents</h5>
                <p class="text-muted">Send inquiries directly to property agents with one click.</p>
            </div>
        </div>
    </div>
</div>

<!-- Stats Section -->
<div class="bg-white py-5">
    <div class="container">
        <div class="row text-center">
            <div class="col-md-4">
                <h2 class="fw-bold text-primary">500+</h2>
                <p class="text-muted">Properties Listed</p>
            </div>
            <div class="col-md-4">
                <h2 class="fw-bold text-primary">200+</h2>
                <p class="text-muted">Registered Users</p>
            </div>
            <div class="col-md-4">
                <h2 class="fw-bold text-primary">25+</h2>
                <p class="text-muted">Districts Covered</p>
            </div>
        </div>
    </div>
</div>

<!-- Footer -->
<footer class="text-center mt-5">
    <p class="mb-0">© 2026 Real Estate Portal | SE1020 Project</p>
</footer>

</body>
</html>