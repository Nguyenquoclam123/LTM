<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registration Form</title>
    <style>
        body {
    font-family: Arial, sans-serif;
    background: linear-gradient(135deg, #89CFF0, #67B7D1);
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
    margin: 0;
    color: #fff;
}

.registration-container {
    background-color: #fff;
    padding: 30px;
    box-shadow: 0 4px 20px rgba(0, 0, 0, 0.2);
    border-radius: 12px;
    width: 400px;
    text-align: center;
    color: #333;
}

h2 {
    margin-bottom: 20px;
    color: #0077b6;
    font-weight: bold;
}

label {
    font-size: 14px;
    font-weight: bold;
    display: block;
    margin-bottom: 8px;
    text-align: left;
}

.input-field,
select {
    width: 100%;
    padding: 12px;
    margin: 8px 0 16px 0;
    border: 1px solid #ddd;
    border-radius: 8px;
    box-sizing: border-box;
    font-size: 14px;
    transition: all 0.3s ease;
}

.input-field:focus,
select:focus {
    border-color: #0077b6;
    outline: none;
    box-shadow: 0 0 5px rgba(0, 119, 182, 0.5);
}

select {
    background-color: #fff;
    cursor: pointer;
}

select option {
    font-size: 14px;
    padding: 10px;
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

.error-message {
    color: #d9534f;
    background-color: #f8d7da;
    border: 1px solid #f5c6cb;
    border-radius: 8px;
    padding: 10px;
    margin-top: 15px;
    font-size: 14px;
}

@media (max-width: 768px) {
    .registration-container {
        width: 90%;
        padding: 20px;
    }

    .btn {
        font-size: 14px;
        padding: 10px;
        margin: 8px 0;
    }
}

    </style>
</head>
<body>
    <div class="registration-container">
        <h2>Register</h2>
        <form action="AuthController" method="POST">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" class="input-field" placeholder="Enter your username" required>

            <label for="password">Password:</label>
            <input type="password" id="password" name="password" class="input-field" placeholder="Enter your password" required>

            <label for="fullname">Full Name:</label>
            <input type="text" id="fullname" name="fullName" class="input-field" placeholder="Enter your full name" required>

            <label for="phone">Phone Number:</label>
            <input type="tel" id="phone" name="phonenumber" class="input-field" placeholder="Enter your phone number" required>

            <label for="address">Address:</label>
            <input type="text" id="address" name="address" class="input-field" placeholder="Enter your address" required>

            <label for="age">Age:</label>
            <input type="number" id="age" name="age" class="input-field" min="1" max="120" required>
            
            <label for="gender">Gender:</label>
            <select name="gender" id="gender">
            	<option value="" selected="selected" hidden></option>
            	<option value="female">Female</option>
            	<option value="male">Male</option>
            </select>

			<input type="hidden" name="action" value="signup" class="input-field" required>
            <div class="form-buttons">
			    <button type="submit" class="btn">Register</button>
			    <button type="reset" class="btn">Reset</button>
			</div>
        </form>
        <% 
            String errorMessage = (String) request.getAttribute("errorMessage");

            if (errorMessage != null) {
        %>
            <div class="error-message"><%= errorMessage %></div>
        <% } %>
        <a href="Login.jsp">-----Login-----</a>
    </div>
</body>
</html>
