function validateForm() {

    let studentId = document.getElementsByName("studentId")[0].value;
    let name = document.getElementsByName("studentName")[0].value;
    let email = document.getElementsByName("email")[0].value;
    let mobile = document.getElementsByName("mobile")[0].value;
    let course = document.getElementsByName("course")[0].value;
    let password = document.getElementsByName("password")[0].value;

    if (studentId === "" || isNaN(studentId)) {
        alert("Enter valid Student ID");
        return false;
    }

    let namePattern = /^[A-Za-z ]+$/;
    if (!namePattern.test(name)) {
        alert("Invalid Name");
        return false;
    }

    let emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailPattern.test(email)) {
        alert("Invalid Email");
        return false;
    }

    let mobilePattern = /^[0-9]{10}$/;
    if (!mobilePattern.test(mobile)) {
        alert("Invalid Mobile Number");
        return false;
    }

    if (course === "") {
        alert("Enter Course");
        return false;
    }

    if (password.length < 6) {
        alert("Password must be 6+ characters");
        return false;
    }
	

	
    return true;
}

// 2. SUCCESS / FAIL MESSAGE HANDLER
document.addEventListener("DOMContentLoaded", function () {

    const params = new URLSearchParams(window.location.search);
    const msg = params.get("msg");

    if (msg === "success") {
        alert("Student registered successfully!");
    }

    if (msg === "fail") {
        alert("Registration failed!");
    }
});