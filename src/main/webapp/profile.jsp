<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.realestate.realestateportal.model.User" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My Profile | Real Estate Portal</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body { background-color: #f8f9fa; }
        .navbar { background-color: #1a3c5e; }
        .profile-card {
            max-width: 560px;
            margin: 60px auto;
            border-radius: 12px;
            box-shadow: 0 4px 20px rgba(0,0,0,0.1);
        }
        .card-header {
            background-color: #1a3c5e;
            color: white;
            border-radius: 12px 12px 0 0 !important;
            padding: 24px;
        }
        .avatar {
            width: 72px; height: 72px;
            background-color: #e8f0fe;
            border-radius: 50%;
            display: flex; align-items: center; justify-content: center;
            font-size: 28px; font-weight: bold; color: #1a3c5e;
            margin: 0 auto 12px;
        }
        .btn-primary { background-color: #1a3c5e; border-color: #1a3c5e; }
        .btn-primary:hover { background-color: #16324f; }
    </style>
</head>
<body>

<%
    User loggedIn = (User) session.getAttribute("loggedInUser");
    if (loggedIn == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<!-- Navbar -->
<nav class="navbar navbar-dark px-4 py-3">
    <a class="navbar-brand fw-bold" href="index.jsp">🏠 Real Estate Portal</a>
    <div>
        <span class="text-white me-3">Hello, <%= loggedIn.getName() %></span>
        <a href="user?action=logout" class="btn btn-outline-light btn-sm">Logout</a>
    </div>
</nav>

<div class="container">
    <div class="card profile-card">
        <div class="card-header text-center">
            <div class="avatar"><%= loggedIn.getName().substring(0,1).toUpperCase() %></div>
            <h4 class="mb-0"><%= loggedIn.getName() %></h4>
            <small class="opacity-75"><%= loggedIn.getRole().toUpperCase() %></small>
        </div>
        <div class="card-body p-4">

            <% if ("updated".equals(request.getParameter("success"))) { %>
            <div class="alert alert-success">Profile updated successfully!</div>
            <% } %>

            <form action="user" method="post">
                <input type="hidden" name="action" value="update">

                <div class="mb-3">
                    <label class="form-label fw-semibold">Full Name</label>
                    <input type="text" name="name" class="form-control"
                           value="<%= loggedIn.getName() %>" required>
                </div>

                <div class="mb-3">
                    <label class="form-label fw-semibold">Email Address</label>
                    <input type="email" name="email" class="form-control"
                           value="<%= loggedIn.getEmail() %>" required>
                </div>

                <div class="mb-3">
                    <label class="form-label fw-semibold">New Password</label>
                    <input type="password" name="password" class="form-control"
                           placeholder="Enter new password">
                </div>

                <div class="d-grid mt-4">
                    <button type="submit" class="btn btn-primary btn-lg">Update Profile</button>
                </div>
            </form>

            <hr class="my-4">

            <!-- Delete Account -->
            <form action="user" method="post"
                  onsubmit="return confirm('Are you sure you want to delete your account? This cannot be undone.');">
                <input type="hidden" name="action" value="delete">
                <div class="d-grid">
                    <button type="submit" class="btn btn-outline-danger">Delete My Account</button>
                </div>
            </form>
        </div>
    </div>
</div>

</body>
</html>