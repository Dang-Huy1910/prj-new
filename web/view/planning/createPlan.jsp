<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Create Work Plan</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/5.3.0/css/bootstrap.min.css" rel="stylesheet">
    <script>
        function validateDates(event) {
            var startDate = document.getElementById("startDate").value;
            var endDate = document.getElementById("endDate").value;

            if (new Date(endDate) <= new Date(startDate)) {
                alert("End Date must be greater than Start Date.");
                event.preventDefault(); // Prevent form submission
            }
        }
    </script>
</head>
<body>
    <jsp:include page="header.jsp"></jsp:include>
    <div class="container mt-5">
        <h2 class="text-center mb-4">Create Work Plan</h2>
        <form action="add-plan" method="post" onsubmit="validateDates(event)">
            <!-- Work Plan Name -->
            <div class="mb-3">
                <label for="planName" class="form-label">Work Plan Name</label>
                <input type="text" class="form-control" id="planName" name="planName" placeholder="Enter plan name" required>
            </div>

            <!-- Select Workshop -->
            <div class="mb-3">
                <label for="workshop" class="form-label">Select Workshop</label>
                <select class="form-select" id="workshop" name="workshop" required>
                    <option selected disabled>Choose Workshop</option>
                    <c:forEach items="${requestScope.de}" var="de">
                        <option value="${de.did}">${de.dname}</option>
                    </c:forEach>
                </select>
            </div>

            <!-- Select Manager -->
            <div class="mb-3">
                <label for="manager" class="form-label">Select Manager</label>
                <select class="form-select" id="manager" name="manager" required>
                    <option selected disabled>Choose Manager</option>
                    <c:forEach items="${requestScope.manager}" var="manager">
                        <option value="${manager.eid}">${manager.ename}</option>
                    </c:forEach>
                </select>
            </div>

            <!-- Start Date -->
            <div class="mb-3">
                <label for="startDate" class="form-label">Start Date</label>
                <input type="date" class="form-control" id="startDate" name="startDate" required>
            </div>

            <!-- End Date -->
            <div class="mb-3">
                <label for="endDate" class="form-label">End Date</label>
                <input type="date" class="form-control" id="endDate" name="endDate" required>
            </div>

            <!-- Submit Button -->
            <div class="text-center">
                <button type="submit" class="btn btn-primary">Create Work Plan</button>
            </div>
        </form>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
