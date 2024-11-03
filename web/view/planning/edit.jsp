<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Work Plan</title>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/5.3.0/css/bootstrap.min.css" rel="stylesheet">
        <style>
            .shift-row input[type="number"] {
                width: 80px;
                text-align: center;
            }
        </style>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
        <div class="container mt-5">
            <h2 class="mb-4 text-center" style="color: red">EDIT: ${plan.plname}</h2>
            <form method="POST" <c:if test="${plan.status.statusId != 1}">disabled</c:if>>
                <input type="hidden" name="id" value="${plan.plid}">
                <table class="table table-bordered">
                    <thead class="table-light">
                        <tr class="table-info">
                            <th scope="col" rowspan="2">Date</th>
                            <th scope="col">Shift</th>
                            <c:forEach items="${products}" var="p">
                                <th scope="col">${p.pname}</th>
                            </c:forEach>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${list}" var="day">
                        <tr>
                            <td rowspan="3" class="align-middle text-center">
                                <c:out value="${day}"/>
                            </td>
                            <td>Shift 1 (6:00 AM - 2:00 PM)</td>
                            <td><input type="number" class="form-control"
                                       <c:forEach items="${productDetails}" var="pd"> id="1_1"
                                           <c:if test="${pd.date == day && pd.shift.sid == 1 && pd.product.pid == 1}">
                                               value="${pd.quantity}" name="${pd.pdid}" 
                                               <c:if test="${plan.status.statusId != 1}">readonly</c:if>
                                           </c:if>
                                       </c:forEach>
                                       oninput="calculateTotals()"></td>
                            <td><input type="number" class="form-control" id="1_2"
                                                                      <c:forEach items="${productDetails}" var="pd">
                                       <c:if test="${pd.date == day && pd.shift.sid == 1 && pd.product.pid == 2}">
                                           value="${pd.quantity}" name="${pd.pdid}" 
                                           <c:if test="${plan.status.statusId != 1}">readonly</c:if>
                                       </c:if>
                                   </c:forEach>
                                   oninput="calculateTotals()"></td>
                            <td><input type="number" class="form-control" id="1_3"
                                                                      <c:forEach items="${productDetails}" var="pd">
                                       <c:if test="${pd.date == day && pd.shift.sid == 1 && pd.product.pid == 3}">
                                           value="${pd.quantity}" name="${pd.pdid}" 
                                           <c:if test="${plan.status.statusId != 1}">readonly</c:if>
                                       </c:if>
                                   </c:forEach>
                                   oninput="calculateTotals()"></td>
                        </tr>
                        <tr>
                            <td>Shift 2 (2:00 PM - 10:00 PM)</td>
                            <td><input type="number" class="form-control" id="2_1"
                                                                      <c:forEach items="${productDetails}" var="pd">
                                       <c:if test="${pd.date == day && pd.shift.sid == 2 && pd.product.pid == 1}">
                                           value="${pd.quantity}" name="${pd.pdid}" 
                                           <c:if test="${plan.status.statusId != 1}">readonly</c:if>
                                       </c:if>
                                   </c:forEach>
                                   oninput="calculateTotals()"></td>
                            <td><input type="number" class="form-control" id="2_2"
                                                                      <c:forEach items="${productDetails}" var="pd">
                                       <c:if test="${pd.date == day && pd.shift.sid == 2 && pd.product.pid == 2}">
                                           value="${pd.quantity}" name="${pd.pdid}" 
                                           <c:if test="${plan.status.statusId != 1}">readonly</c:if>
                                       </c:if>
                                   </c:forEach>
                                   oninput="calculateTotals()"></td>
                            <td><input type="number" class="form-control" id="2_3"
                                                                      <c:forEach items="${productDetails}" var="pd">
                                       <c:if test="${pd.date == day && pd.shift.sid == 2 && pd.product.pid == 3}">
                                           value="${pd.quantity}" name="${pd.pdid}" 
                                           <c:if test="${plan.status.statusId != 1}">readonly</c:if>
                                       </c:if>
                                   </c:forEach>
                                   oninput="calculateTotals()"></td>
                        </tr>
                        <tr>
                            <td>Shift 3 (10:00 PM - 6:00 AM)</td>
                            <td><input type="number" class="form-control" id="3_1"
                                                                      <c:forEach items="${productDetails}" var="pd">
                                       <c:if test="${pd.date == day && pd.shift.sid == 3 && pd.product.pid == 1}">
                                           value="${pd.quantity}" name="${pd.pdid}" 
                                           <c:if test="${plan.status.statusId != 1}">readonly</c:if>
                                       </c:if>
                                   </c:forEach>
                                   oninput="calculateTotals()"></td>
                            <td><input type="number" class="form-control" id="3_2"
                                                                      <c:forEach items="${productDetails}" var="pd">
                                       <c:if test="${pd.date == day && pd.shift.sid == 3 && pd.product.pid == 2}">
                                           value="${pd.quantity}" name="${pd.pdid}" 
                                           <c:if test="${plan.status.statusId != 1}">readonly</c:if>
                                       </c:if>
                                   </c:forEach>
                                   oninput="calculateTotals()"></td>
                            <td><input type="number" class="form-control" id="3_3"
                                                                      <c:forEach items="${productDetails}" var="pd">
                                       <c:if test="${pd.date == day && pd.shift.sid == 3 && pd.product.pid == 3}">
                                           value="${pd.quantity}" name="${pd.pdid}" 
                                           <c:if test="${plan.status.statusId != 1}">readonly</c:if>
                                       </c:if>
                                   </c:forEach>
                                   oninput="calculateTotals()"></td>
                        </tr>
                    </c:forEach>
                        <!-- Total Row -->
                        <tr class="table-primary">
                            <td colspan="2" class="text-end fw-bold">Total Products:</td>
                            <td><input type="text" id="total1" name="total1" class="form-control text-center" readonly></td>
                            <td><input type="text" id="total2" name="total2" class="form-control text-center" readonly></td>
                            <td><input type="text" id="total3" name="total3" class="form-control text-center" readonly></td>
                        </tr>
                    </tbody>
                </table>
                <c:if test="${plan.status.statusId == 1}">
                    <button class="btn btn-primary mb-5 justify-content-center text-end" type="submit" >Save Schedule</button>
                </c:if>
                    <c:if test="${plan.status.statusId != 1}">
                        <button class="btn btn-danger mb-5" style="width: 100%" type="button" >${plan.status.name}</button>
                </c:if>
                
            </form>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
        <script>
            function calculateTotals() {
                const inputsA = document.querySelectorAll('input[id$="_1"]');
                const inputsB = document.querySelectorAll('input[id$="_2"]');
                const inputsC = document.querySelectorAll('input[id$="_3"]');

                let totalA = 0, totalB = 0, totalC = 0;

                inputsA.forEach(input => totalA += parseInt(input.value) || 0);
                inputsB.forEach(input => totalB += parseInt(input.value) || 0);
                inputsC.forEach(input => totalC += parseInt(input.value) || 0);

                document.getElementById("total1").value = totalA;
                document.getElementById("total2").value = totalB;
                document.getElementById("total3").value = totalC;
            }

            // Initial calculation on page load
            calculateTotals();
        </script>
    </body>
</html>
