<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register | Real Estate Portal</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body { background-color: #f8f9fa; }
        .register-card {
            max-width: 480px;
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
    <div class="card register-card">
        <div class="card-header text-center">
            <h3 class="mb-0">Create Account</h3>
            <p class="mb-0 mt-1 opacity-75">Join the Real Estate Portal</p>
        </div>
        <div class="card-body p-4">

            <% if ("emailexists".equals(request.getParameter("error"))) { %>
            <div class="alert alert-danger">Email is already registered. Try logging in.</div>
            <% } %>

            <form action="user" method="post">
                <input type="hidden" name="action" value="register">

                <div class="mb-3">
                    <label class="form-label fw-semibold">Full Name</label>
                    <input type="text" name="name" class="form-control" placeholder="John Silva" required>
                </div>

                <div class="mb-3">
                    <label class="form-label fw-semibold">Email Address</label>
                    <input type="email" name="email" class="form-control" placeholder="john@email.com" required>
                </div>

                <div class="mb-3">
                    <label class="form-label fw-semibold">Password</label>
                    <input type="password" name="password" class="form-control" placeholder="Min. 6 characters" required>
                </div>

                <div class="d-grid mt-4">
                    <button type="submit" class="btn btn-primary btn-lg">Create Account</button>
                </div>
            </form>

            <p class="text-center mt-3 mb-0">
                Already have an account? <a href="login.jsp">Login here</a>
            </p>
        </div>
    </div>
</div>
</body>
</html>