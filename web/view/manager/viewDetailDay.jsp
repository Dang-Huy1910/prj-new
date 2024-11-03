<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Create Work Plan</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/5.3.0/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <jsp:include page="header.jsp"></jsp:include>
    <div class="container mt-5">
        <h5 class="text-center">${planDetail.plan.plname} - ${planDetail.date}</h5>
<b>List of Personnel Available for Deployment at ${planDetail.plan.department.dname}</b> <br>    <br>   
        <div class="d-flex justify-content-center mb-3">
            <table class="table table-bordered text-center" style="width: 50%;">
                <thead class="table-light">
                    <tr class="table-danger">
                        <th scope="col">Employee ID</th>
                        <th scope="col">Full Name</th>
                        <th scope="col">Salary Level</th>
                        <th scope="col">Hourly rate</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${emps}" var="emp">
                        <tr>
                            <td>${emp.eid}</td>
                            <td>${emp.ename}</td>
                            <td>${emp.salary.slevel}</td>
                            <td>${emp.salary.salary} K</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

<b>Assigned Production Plan for ${planDetail.plan.department.dname} - ${planDetail.date}</b> <br>          <br>   
        <!-- Detailed production plan table with merged date and shift cells -->
        <table class="table table-bordered">
            <thead class="table-light">
                <tr class="table-info">
                    <th scope="col">Date</th>
                    <th scope="col">Shift</th>
                    <th scope="col">Product ID</th>
                    <th scope="col">Product Name</th>
                    <th scope="col">Quantity (unit: piece)</th>
                    <th scope="col">Note</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${planDetails}" var="pd">
                    <tr>
                        <td >${pd.date}</td>
                        <td>${pd.shift.sname}</td>
                        <td>${pd.product.pid}</td>
                        <td>${pd.product.pname}</td>
                        <td>${pd.quantity}</td>
                        <td></td>

                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <b>Detailed Personnel Deployment Plan for ${planDetail.date}</b> <br>  <br>  
        <c:choose>
    <c:when test="${empty work}">
        <div class="alert alert-danger text-center mt-5 mb-5" role="alert">
            Chưa có ai order sản xuất
        </div>
    </c:when>
    <c:otherwise>
        <table class="table table-bordered mb-5">
            <thead class="table-light">
                <tr class="table-warning">
                    <th scope="col">Employee ID</th>
                    <th scope="col">Employee Name</th>
                    <th scope="col">Product ID</th>
                    <th scope="col">Product Name</th>
                    <th scope="col">Shift</th>
                    <th scope="col">Order Quantity</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${work}" var="work">
                    <tr>
                        <td>${work.employee.eid}</td>
                        <td>${work.employee.ename}</td>
                        <td>${work.details.product.pid}</td>
                        <td>${work.details.product.pname}</td>
                        <td>${work.details.shift.sname}</td>
                        <td>${work.quantity}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:otherwise>
</c:choose>
        
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
