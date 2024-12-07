<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Form</title>
    <style>
        body {
    font-family: Arial, sans-serif;
    background: linear-gradient(135deg, #89CFF0, #67B7D1); /* Màu xanh nước biển nhạt */
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
    margin: 0;
    color: #fff;
}

.login-container {
    background-color: #fff;
    padding: 25px;
    box-shadow: 0 4px 20px rgba(0, 0, 0, 0.2);
    border-radius: 12px;
    width: 350px;
    text-align: center;
    color: #333;
}

h2 {
    margin-bottom: 15px;
    color: #0077b6; /* Xanh nước biển đậm */
    font-weight: bold;
}

label {
    font-size: 14px;
    font-weight: bold;
    display: block;
    margin-bottom: 8px;
    text-align: left;
}

.input-field {
    width: 100%;
    padding: 12px;
    margin: 8px 0 16px 0;
    border: 1px solid #ddd;
    border-radius: 8px;
    box-sizing: border-box;
    font-size: 14px;
    transition: all 0.3s ease;
}

.input-field:focus {
    border-color: #0077b6;
    outline: none;
    box-shadow: 0 0 5px rgba(0, 119, 182, 0.5);
}

.form-buttons {
    display: flex;
    justify-content: space-between;
    margin-top: 20px;
}

.btn {
    width: 48%;
    padding: 12px;
    background: linear-gradient(135deg, #89CFF0, #0077b6);
    color: white;
    border: none;
    border-radius: 8px;
    font-size: 16px;
    cursor: pointer;
    font-weight: bold;
    transition: all 0.3s ease;
    text-align: center;
}

.btn:hover {
    background: linear-gradient(135deg, #0077b6, #005f99);
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
    transform: translateY(-2px);
}

.error-message {
    color: #d9534f;
    background-color: #f8d7da;
    border: 1px solid #f5c6cb;
    border-radius: 8px;
    padding: 10px;
    margin-top: 15px;
    font-size: 14px;
}

.success-message {
    color: #28a745;
    background-color: #d4edda;
    border: 1px solid #c3e6cb;
    border-radius: 8px;
    padding: 10px;
    margin-top: 15px;
    font-size: 14px;
}

.form-container {
    margin-top: 15px;
}

.form-container label {
    text-align: left;
    display: block;
    font-size: 14px;
    margin-bottom: 5px;
}

.form-container .input-field {
    margin-bottom: 20px;
}

::placeholder {
    color: #999;
    font-style: italic;
}

input[type="text"]:focus, input[type="password"]:focus {
    background-color: #f9f9f9;
    outline: none;
    border: 1px solid #0077b6;
    box-shadow: 0 0 3px rgba(0, 119, 182, 0.4);
}

@media (max-width: 768px) {
    .login-container {
        width: 90%;
        padding: 15px;
    }

    .btn {
        font-size: 14px;
        padding: 10px;
    }
    
}
    a {
    display: block;
    margin-top: 15px;
    font-size: 14px;
    text-decoration: none;
    color: #0077b6;
    font-weight: bold;
    transition: color 0.3s ease;
}

a:hover {
    color: #005f99;
    text-decoration: underline;
}

    </style>
</head>
<body>
    <div class="login-container">
        <h2>Login</h2>
		<form action="AuthController" method="POST">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" class="input-field" required>

            <label for="password">Password:</label>
            <input type="password" id="password" name="password" class="input-field" required>

			<input type="hidden" name="action" value="login" class="input-field" required>
            <div class="form-buttons">
			    <button type="submit" class="btn">Login</button>
			    <button type="reset" class="btn">Reset</button>
			</div>
        </form>
        <% 
            String errorMessage = (String) request.getAttribute("errorMessage");

            if (errorMessage != null) {
        %>
            <div class="error-message"><%= errorMessage %></div>
        <% } %>


        <a href="Register.jsp">-----Sign up-----</a>
    </div>
</body>
</html>
