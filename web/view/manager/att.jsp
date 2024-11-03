<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
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
        <c:choose>
            <c:when test="${empty work}">
                <div class="alert alert-danger text-center mt-5 mb-5" role="alert">
                    Chưa có ai order sản xuất
                </div>
            </c:when>
            <c:otherwise>
                <form method="POST">
                    <input type="hidden" value="${id}" name="id">
                    <input type="hidden" value="${date}" name="date">
                    <table class="table table-bordered mb-5">
                        <thead class="table-light">
                            <tr class="table-warning">
                                <th scope="col">Employee ID</th>
                                <th scope="col">Employee Name</th>
                                <th scope="col">Product ID</th>
                                <th scope="col">Product Name</th>
                                <th scope="col">Shift</th>
                                <th scope="col">Order Quantity</th>
                                <th scope="col">Actual Production</th>
                                <th scope="col">Anpha</th>
                                <th scope="col">Notes</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:set var="prevEmployeeId" value="" />
                            <c:set var="currentEmployeeId" value="" />
                            <c:set var="rowspanCount" value="0" />
                            <c:forEach items="${work}" var="work">
                                <c:set var="prevEmployeeId" value="${work.employee.eid}" />
                                <c:if test="${currentEmployeeId != prevEmployeeId}">
                                    <c:forEach items="${requestScope.work}" var="work1">
                                        <c:if test="${prevEmployeeId == work1.employee.eid}">
                                            <c:set var="rowspanCount" value="${rowspanCount + 1}" />
                                        </c:if>
                                    </c:forEach>
                                </c:if>
                                <tr>
                                     <c:if test="${currentEmployeeId != prevEmployeeId}">
                                        <td rowspan="${rowspanCount}">${work.employee.eid}</td>
                                        <td rowspan="${rowspanCount}">${work.employee.ename}</td>
                                    </c:if>
                                    <td>${work.details.product.pid}</td>
                                    <td>${work.details.product.pname}</td>
                                    <td>${work.details.shift.sname}</td>
                                    <td>${work.quantity}</td>
                                    <!-- Input fields for Actual Production, Attendance, and Notes -->
                                    <td><input type="number" class="form-control" placeholder="Actual Production" name="w_${work.waid}"></td>
                                    <c:if test="${currentEmployeeId != prevEmployeeId}">
                                        <td rowspan="${rowspanCount}" ><input type="number" step=0.1 class="form-control" name="a_${work.waid}"></td>
                                        <td rowspan="${rowspanCount}" ><textarea type="text" class="form-control" name="n_${work.waid}" ></textarea></td>
                                        <c:set var="currentEmployeeId" value="${work.employee.eid}" />
                                        <c:set var="rowspanCount" value="0" />
                                    </c:if>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <button type="submit" class="btn btn-primary mb-5" >Submit</button>
                </form>
            </c:otherwise>
        </c:choose>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
