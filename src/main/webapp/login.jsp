<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login | Real Estate Portal</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body { background-color: #f8f9fa; }
        .login-card {
            max-width: 440px;
            margin: 80px auto;
            border-radius: 12px;
            box-shadow: 0 4px 20px rgba(0,0,0,0.1);
        }
        .card-header {
            background-color: #1a3c5e;
            color: white;
            border-radius: 12px 12px 0 0 !important;
            padding: 24px;
        }
        .btn-primary { background-color: #1a3c5e; border-color: #1a3c5e; }
        .btn-primary:hover { background-color: #16324f; }
    </style>
</head>
<body>
<div class="container">
    <div class="card login-card">
        <div class="card-header text-center">
            <h3 class="mb-0">Welcome Back</h3>
            <p class="mb-0 mt-1 opacity-75">Real Estate Portal</p>
        </div>
        <div class="card-body p-4">

            <% if ("invalid".equals(request.getParameter("error"))) { %>
            <div class="alert alert-danger">Invalid email or password. Please try again.</div>
            <% } %>

            <% if ("registered".equals(request.getParameter("success"))) { %>
            <div class="alert alert-success">Account created! You can now log in.</div>
            <% } %>

            <% if ("deleted".equals(request.getParameter("success"))) { %>
            <div class="alert alert-info">Your account has been deleted.</div>
            <% } %>

            <form action="user" method="post">
                <input type="hidden" name="action" value="login">

                <div class="mb-3">
                    <label class="form-label fw-semibold">Email Address</label>
                    <input type="email" name="email" class="form-control" placeholder="john@email.com" required>
                </div>

                <div class="mb-3">
                    <label class="form-label fw-semibold">Password</label>
                    <input type="password" name="password" class="form-control" placeholder="Your password" required>
                </div>

                <div class="d-grid mt-4">
                    <button type="submit" class="btn btn-primary btn-lg">Login</button>
                </div>
            </form>

            <p class="text-center mt-3 mb-0">
                Don't have an account? <a href="register.jsp">Register here</a>
            </p>
        </div>
    </div>
</div>
</body>
</html>