<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Plan" %>
<%@ page import="DAO.PlanDAO" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View Work Plans</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/5.3.0/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
        }
        .container {
            margin-top: 50px;
        }
    </style>
</head>
<body>
    <jsp:include page="header.jsp" />

    <div class="container">
        <h2 class="text-center mb-4">Work Plans</h2>
        <table class="table table-striped table-bordered">
            <thead class="table-info">
                <tr>
                    <th>Plan ID</th>
                    <th>Plan Name</th>
                    <th>Start Date</th>
                    <th>End Date</th>
                    <th>Department</th>
                    <th>Manager</th>
                    <th>Status</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="plan" items="${plans}">
                    <tr>
                        <td>${plan.plid}</td>
                        <td>${plan.plname}</td>
                        <td>${plan.startDate}</td>
                        <td>${plan.endDate}</td>
                        <td>${plan.department.dname}</td>
                        <td>${plan.manager.ename}</td>
                        <td>
                            <c:choose>
                                <c:when test="${plan.status.statusId == 1}">
                                    <button class="btn btn-secondary">Pending</button>
                                </c:when>
                                <c:when test="${plan.status.statusId == 2}">
                                    <button class="btn btn-info">In Progress</button>
                                </c:when>
                                <c:when test="${plan.status.statusId == 3}">
                                    <button class="btn btn-success">Completed</button>
                                </c:when>
                                <c:when test="${plan.status.statusId == 4}">
                                    <button class="btn btn-danger">Cancelled</button>
                                </c:when>
                                <c:otherwise>
                                    <button class="btn btn-light">Unknown</button>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>
    <c:choose>
        <c:when test="${plan.status.statusId == 1}">
            <a href="edit?id=${plan.plid}" class="btn btn-warning">Edit</a>
        </c:when>
        <c:otherwise>
            <a class="btn btn-secondary" href="edit?id=${plan.plid}">View</a>
        </c:otherwise>
    </c:choose>
</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
