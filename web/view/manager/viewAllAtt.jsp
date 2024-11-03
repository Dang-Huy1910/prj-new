<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <c:choose>
        <c:when test="${empty att}">
        <div class="alert alert-danger text-center mt-5 mb-5" role="alert">
            Chưa có ai order sản xuất
        </div>
        </c:when>
        <c:otherwise>
        <table class="table table-bordered">
                <thead class="table-light">
                    <tr class="table-info">
                        <th scope="col" >Employee ID</th>
                        <th scope="col">Employee Name</th>
                        <c:forEach items="${list}" var="day">
                            <th scope="col"> ${day}</th>
                        </c:forEach>
                        <th scope="col">Salary(VND)</th>
                    </tr>
                </thead>
                <tbody>
                    <c:set var="salary" value="0"></c:set>
                    <c:forEach items="${emps}" var="emp">
                        <tr>
                            <td scope="col"> ${emp.eid}</td>
                            <td scope="col"> ${emp.ename}</td>
                            <c:forEach items="${list}" var="day">
                            <td>
                                <c:forEach items="${att}" var="att">
                                    <c:if test="${att.date == day and att.employee.eid == emp.eid}">
                                        ${att.alpha}
                                    <c:set var="salary" value="${salary+att.alpha*emp.salary.salary*8}"></c:set>
                                    </c:if>
                                </c:forEach>
                            </td>
                            </c:forEach>
                            <td><fmt:formatNumber value="${salary}" type="currency" currencySymbol="₫"/></td>
                        </tr>
                        <c:set var="salary" value="0"></c:set>
                    </c:forEach>
                </tbody>
            </table>
        </c:otherwise>
    </c:choose>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
