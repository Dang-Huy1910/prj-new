<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
    <!-- Fontawesome CDN Link For Icons -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css" />
    <style>
        
@import url('https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;500;600;700&display=swap');

* {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
    font-family: 'Open Sans', sans-serif;
}

body {
    display: flex;
    align-items: center;
    justify-content: center;
    min-height: 100vh;
    padding: 0 10px;
    background: rgb(176, 237, 215);
}

form {
    padding: 25px;
    background: #fff;
    max-width: 500px;
    width: 100%;
    border-radius: 7px;
    box-shadow: 0 10px 15px rgba(0, 0, 0, 0.25);
}

form h2 {
    font-size: 27px;
    text-align: center;
    margin: 0px 0 30px;
}

form .form-group {
    margin-bottom: 15px;
    position: relative;
}

form label {
    display: block;
    font-size: 15px;
    margin-bottom: 7px;
}

form input,
form select {
    height: 45px;
    padding: 10px;
    width: 100%;
    font-size: 15px;
    outline: none;
    background: #fff;
    border-radius: 3px;
    border: 1px solid #bfbfbf;
}

form input:focus,
form select:focus {
    border-color: #9a9a9a;
}

form input.error,
form select.error {
    border-color: #f91919;
    background: #f9f0f1;
}

form small {
    font-size: 14px;
    margin-top: 5px;
    display: block;
    color: #f91919;
}

form .password i {
    position: absolute;
    right: 0px;
    height: 45px;
    top: 28px;
    font-size: 13px;
    line-height: 45px;
    width: 45px;
    cursor: pointer;
    color: #939393;
    text-align: center;
}

.submit-btn {
    margin-top: 30px;
}

.submit-btn input {
    color: white;
    border: none;
    height: auto;
    font-size: 16px;
    padding: 13px 0;
    border-radius: 5px;
    cursor: pointer;
    font-weight: 500;
    text-align: center;
    background: #1BB295;
    transition: 0.2s ease;
}

.submit-btn input:hover {
    background: #179b81;
}
    </style>
</head>

<body>
<form action="login" method="post">
    <h2>Sign In</h2>
        <div class="form-group username">
            <label for="username">User Name</label>
            <input type="text" id="username" placeholder="Enter your user name" name="username">
        </div>
        <div class="form-group password">
            <label for="password">Password</label>
            <input type="password" id="password" placeholder="Enter your password" name = "password">
            <i id="pass-toggle-btn" class="fa-solid fa-eye pass-toggle-btn"></i>
        </div>
    <div class="form-group submit-btn">
        <input type="submit" value="Submit">
    </div>
</form>

<script src="${pageContext.request.contextPath}/js/login.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script>
    <c:if test="${requestScope.warningLogin!=null}">
    window.onload = function() {
            Swal.fire({
            icon: "error",
            title: "Oops...",
            text: "${requestScope.warningLogin}",
        });
    };
    </c:if>
</script>
<script>
    // Selecting form and input elements
const form = document.querySelector("form");
const passwordInput = document.getElementById("password");
const passToggleBtn = document.getElementById("pass-toggle-btn");

// Function to display error messages
const showError = (field, errorText) => {
field.classList.add("error");
const errorElement = document.createElement("small");
errorElement.classList.add("error-text");
errorElement.innerText = errorText;
field.closest(".form-group").appendChild(errorElement);
}

// Function to handle form submission
const handleFormData = (e) => {
e.preventDefault();

// Retrieving input elements

const usernameInput = document.getElementById("username");

// Getting trimmed values from input fields
const username = usernameInput.value.trim();
const password = passwordInput.value.trim();



// Regular expression pattern for email validation
const emailPattern = /^[^ ]+@[^ ]+\.[a-z]{2,3}$/;

// Clearing previous error messages
document.querySelectorAll(".form-group .error").forEach(field => field.classList.remove("error"));
document.querySelectorAll(".error-text").forEach(errorText => errorText.remove());

// Performing validation checks

if (username === "") {
showError(usernameInput, "Enter your user name");
}

if (password === "") {
showError(passwordInput, "Enter your password");
}else {
if(!combinations.regex.test(password)){
showError(passwordInput,"At least 3 characters long");
}
}


// Checking for any remaining errors before form submission
const errorInputs = document.querySelectorAll(".form-group .error");
if (errorInputs.length > 0) return;

// Submitting the form
form.submit();
}

// Toggling password visibility
passToggleBtn.addEventListener('click', () => {
passToggleBtn.className = passwordInput.type === "password" ? "fa-solid fa-eye-slash" : "fa-solid fa-eye";
passwordInput.type = passwordInput.type === "password" ? "text" : "password";
});

// Handling form submission event
form.addEventListener("submit", handleFormData);

const combinations = { regex: /.{3}/, key: 0 };

</script>
</body>

</html>