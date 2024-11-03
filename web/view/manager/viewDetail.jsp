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
        <h5 class="text-center">${plan.plname}</h5>
        <b>General Plan: </b> ${plan.plid} <br>
        <b>Workshop:</b> ${plan.department.dname} <br>
        <b>Workshop Manager:</b> ${plan.manager.ename}<br>
        <b>General Plan:</b>
        
        <div class="d-flex justify-content-center mb-3">
            <table class="table table-bordered text-center" style="width: 50%;">
                <thead class="table-light">
                    <tr class="table-danger">
                        <th scope="col">Product ID</th>
                        <th scope="col">Product Name</th>
                        <th scope="col">Quantity (unit: piece)</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${planHeaders}" var="pd">
                        <tr>
                            <td>${pd.product.pid}</td>
                            <td>${pd.product.pname}</td>
                            <td>${pd.quantity}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <b>The detailed production plan is listed in the table below:</b> <br>
        
        <!-- Detailed production plan table with merged date and shift cells -->
        <table class="table table-bordered">
            <thead class="table-light">
                <tr class="table-info">
                    <th scope="col">Date</th>
                    <th scope="col">Shift</th>
                    <th scope="col">Product ID</th>
                    <th scope="col">Product Name</th>
                    <th scope="col">Quantity (unit: piece)</th>
                    <th scope="col">Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${productDetails}" var="pd" varStatus="loop">
                    <tr>
                        <!-- Merge Date cell every 9 rows -->
                        <c:if test="${loop.index % 9 == 0}">
                            <td rowspan="9" >${pd.date}</td>
                        </c:if>
                        
                        <!-- Merge Shift cell every 3 rows -->
                        <c:if test="${loop.index % 3 == 0}">
                            <td rowspan="3">${pd.shift.sname}</td>
                        </c:if>

                        <td>${pd.product.pid}</td>
                        <td>${pd.product.pname}</td>
                        <td>${pd.quantity}</td>
                       <c:if test="${loop.index % 9 == 0}">
                           <td rowspan="9" >
                               <a class="btn btn-info" href="view-day?id=${plan.plid}&date=${pd.date}">Xem</a> <br> <br> <br>
                               <c:if test="${pd.plan.status.statusId != 3}">
                                   <a class="btn btn-danger" href="att?id=${plan.plid}&date=${pd.date}">Chấm công</a>
                               </c:if>
                               
                           </td>
                        </c:if>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
