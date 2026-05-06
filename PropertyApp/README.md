# LuxeRealEstate – Property Management System

A full-stack Java web application for managing real estate property listings, built with Java Servlets, file-based persistence, and a modern HTML/CSS/JS frontend.

---

## Project Structure

```
PropertyApp/
├── pom.xml                          ← Maven build file
└── src/main/
    ├── java/com/property/
    │   ├── model/
    │   │   ├── Property.java        ← Property data model
    │   │   └── Agent.java           ← Agent data model
    │   ├── service/
    │   │   └── PropertyService.java ← Core business logic (file I/O)
    │   └── servlet/
    │       ├── AddPropertyServlet.java
    │       ├── UpdatePropertyServlet.java
    │       └── DeletePropertyServlet.java
    └── webapp/
        ├── index.html               ← Home page (entry point)
        ├── addProperty.html
        ├── viewProperties.html
        ├── updateProperty.html
        ├── deleteProperty.html
        ├── css/style.css
        └── js/main.js               ← Frontend logic (localStorage CRUD)
```

---

## How to Run

**Prerequisites:** Java 8+, Maven installed

```bash
cd PropertyApp
mvn jetty:run
```

Then open: `http://localhost:8080/PropertyApp/`

To stop: press `Ctrl + C`

---

## Architecture Overview

### Design Pattern: MVC (Model – View – Controller)

| Layer | Role | Files |
|---|---|---|
| **Model** | Holds data | `Property.java`, `Agent.java` |
| **View** | UI shown to user | All `.html` files |
| **Controller** | Handles requests | All `*Servlet.java` files |
| **Service** | Business logic | `PropertyService.java` |

### Request Flow

```
Browser (HTML Form)
      ↓  HTTP POST
Servlet (Controller)
      ↓  calls
PropertyService (Business Logic)
      ↓  reads/writes
properties.txt (Data Store)
      ↓  redirect
HTML Page (View)
```

---

## Backend – Java Layer

### 1. Models (`com.property.model`)

#### `Property.java`
Represents a real estate listing.

| Field | Type | Description |
|---|---|---|
| `id` | String | Unique property identifier (e.g. P1001) |
| `title` | String | Property name (e.g. Modern Villa) |
| `location` | String | Address or city |
| `price` | double | Listing price in USD |
| `agent` | Agent | Nested agent object |

Key methods:
- `toFileString()` — serializes the object to a comma-separated string for file storage
- `fromFileString(String line)` — deserializes a line from the file back into a `Property` object

#### `Agent.java`
Represents the real estate agent for a listing.

| Field | Type |
|---|---|
| `agentName` | String |
| `phoneNumber` | String |
| `agencyName` | String |
| `licenseNumber` | String |

---

### 2. Service Layer (`com.property.service`)

#### `PropertyService.java`
Central class for all data operations. Uses a flat text file (`properties.txt`) as the database — no SQL or external database required.

| Method | Description |
|---|---|
| `addProperty(Property p)` | Appends a new property to the file |
| `getAllProperties()` | Reads all lines from the file and returns a list |
| `getPropertyById(String id)` | Scans the file and returns a matching property |
| `updateProperty(Property p)` | Rewrites the file, replacing the matching record |
| `deleteProperty(String id)` | Rewrites the file, omitting the matching record |

**Storage format** (`properties.txt`):
```
P1001,Modern Villa,Beverly Hills CA,1500000.0,John Doe,555-0198,Luxe Estates LLC,AG-7890
```
Each line = one property, fields separated by commas.

---

### 3. Servlets (`com.property.servlet`)

Servlets are Java classes that handle HTTP requests. They are the **controller** layer — they receive form data, call the service, and redirect the browser.

#### `AddPropertyServlet.java`
- **URL:** `/AddPropertyServlet`
- **Method:** `HTTP POST`
- Reads form parameters → creates `Agent` and `Property` objects → calls `PropertyService.addProperty()` → redirects

#### `UpdatePropertyServlet.java`
- **URL:** `/UpdatePropertyServlet`
- **Method:** `HTTP POST`
- Reads form parameters → builds updated `Property` object → calls `PropertyService.updateProperty()` → redirects

#### `DeletePropertyServlet.java`
- **URL:** `/DeletePropertyServlet`
- **Method:** `HTTP POST`
- Reads property `id` from form → calls `PropertyService.deleteProperty()` → redirects

---

## Frontend – HTML/CSS/JS Layer

### `js/main.js`
Handles all UI logic using the browser's `localStorage` as a client-side data store. Each page calls its own initializer on `DOMContentLoaded`.

| Function | Page | What it does |
|---|---|---|
| `initAddProperty()` | addProperty.html | Saves new property to localStorage, redirects |
| `initViewProperties()` | viewProperties.html | Reads localStorage, renders property cards dynamically |
| `initUpdateProperty()` | updateProperty.html | Reads `?id=` from URL, pre-fills form, saves on submit |
| `initDeleteProperty()` | deleteProperty.html | Finds property by ID, confirms, removes from localStorage |

**localStorage data structure:**
```json
[
  {
    "id": "P1001",
    "title": "Modern Villa",
    "location": "Beverly Hills, CA",
    "price": 1500000,
    "agent": {
      "agentName": "John Doe",
      "phoneNumber": "555-0198",
      "agencyName": "Luxe Estates LLC",
      "licenseNumber": "AG-7890"
    }
  }
]
```

---

## Technologies Used

| Technology | Purpose |
|---|---|
| Java 8 | Backend language |
| Java Servlets (javax.servlet 4.0.1) | HTTP request handling |
| Maven | Build tool and dependency management |
| Jetty (embedded) | Local web server (`mvn jetty:run`) |
| HTML5 / CSS3 | Frontend UI |
| JavaScript (Vanilla) | Frontend CRUD logic |
| localStorage | Client-side data persistence |
| File I/O (`BufferedReader/Writer`) | Server-side data persistence (`properties.txt`) |

---

## Common Viva Questions

**Q: What design pattern does this project use?**
A: MVC (Model-View-Controller). Models are `Property` and `Agent`, Views are the HTML pages, Controllers are the Servlets, and `PropertyService` is the service/business layer.

**Q: Why is there no database?**
A: Data is stored in a plain text file (`properties.txt`) using Java file I/O (`BufferedReader` and `BufferedWriter`). Each property is one comma-separated line.

**Q: What is a Servlet?**
A: A Java class that extends `HttpServlet` and handles HTTP requests (GET/POST). It is registered with the server using the `@WebServlet` annotation and runs inside a servlet container like Jetty or Tomcat.

**Q: What is the role of `PropertyService`?**
A: It is the service layer that contains all the business logic and file operations. Servlets delegate data tasks to this class rather than handling file I/O themselves — this separates concerns.

**Q: How does `toFileString()` / `fromFileString()` work?**
A: `toFileString()` serializes a `Property` object into a comma-separated string for writing to file. `fromFileString()` splits a line by comma and reconstructs the object — this is a simple manual serialization approach.

**Q: What does `mvn jetty:run` do?**
A: Maven uses the Jetty plugin defined in `pom.xml` to compile the project and start an embedded web server on port 8080, making the app accessible at `http://localhost:8080/PropertyApp/`.

**Q: What is localStorage?**
A: A browser API that stores key-value data persistently in the browser (survives page refresh). This project uses it so the HTML pages work as standalone local files without needing the Java server running.

**Q: What is the difference between `doGet` and `doPost`?**
A: `doGet` handles HTTP GET requests (e.g. loading a page via URL). `doPost` handles HTTP POST requests (e.g. form submissions). All servlets in this project use `doPost` because they receive form data.

**Q: What is the `@WebServlet` annotation?**
A: It maps a URL path to a servlet class automatically, without needing a `web.xml` configuration file. For example, `@WebServlet("/AddPropertyServlet")` means the servlet responds to requests at that URL.

**Q: What happens when two properties have the same ID?**
A: The frontend (`main.js`) checks for duplicate IDs before saving and shows an alert. On the backend, `addProperty()` simply appends without checking — so duplicate prevention is a frontend responsibility in this project.
