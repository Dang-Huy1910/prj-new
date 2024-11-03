<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
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
        <h2 class="mb-4 text-center" style="color: red">${plan.plname}</h2>
        <form method="POST"  onsubmit="validateForm(event)">
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
                        <td>
                            <input type="radio" name="${day}_shift" value="1" onchange="toggleShift(this)" /> Shift 1 (6:00 AM - 2:00 PM)
                        </td>
                        <td><input type="number" class="form-control" name="${day}_1_1" value="0" disabled oninput="calculateTotals()"></td>
                        <td><input type="number" class="form-control" name="${day}_1_2" value="0" disabled oninput="calculateTotals()"></td>
                        <td><input type="number" class="form-control" name="${day}_1_3" value="0" disabled oninput="calculateTotals()"></td>
                    </tr>
                    <tr>
                        <td>
                            <input type="radio" name="${day}_shift" value="2" onchange="toggleShift(this)" /> Shift 2 (2:00 PM - 10:00 PM)
                        </td>
                        <td><input type="number" class="form-control" name="${day}_2_1" value="0" disabled oninput="calculateTotals()"></td>
                        <td><input type="number" class="form-control" name="${day}_2_2" value="0" disabled oninput="calculateTotals()"></td>
                        <td><input type="number" class="form-control" name="${day}_2_3" value="0" disabled oninput="calculateTotals()"></td>
                    </tr>
                    <tr>
                        <td>
                            <input type="radio" name="${day}_shift" value="3" onchange="toggleShift(this)" /> Shift 3 (10:00 PM - 6:00 AM)
                        </td>
                        <td><input type="number" class="form-control" name="${day}_3_1" value="0" disabled oninput="calculateTotals()"></td>
                        <td><input type="number" class="form-control" name="${day}_3_2" value="0" disabled oninput="calculateTotals()"></td>
                        <td><input type="number" class="form-control" name="${day}_3_3" value="0" disabled oninput="calculateTotals()"></td>
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
            <button class="btn btn-primary mb-5 justify-content-center text-end" type="submit">Save Schedule</button>
        </form>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        function toggleShift(radio) {
            const selectedShift = radio.value;
            const row = radio.closest('tr');

                const inputs = row.querySelectorAll('input[type="number"]');
    console.log("Inputs found:", inputs); // Kiểm tra các input tìm thấy

    inputs.forEach(input => {
        input.disabled = true; // Đặt tất cả thành disabled
        console.log("Disabled input:", input); // Kiểm tra từng input
    });

            // Enable inputs for the selected shift
            if (selectedShift === '1') {
                console.log("Enable 1");
                row.querySelectorAll('input[name*="_1_"]').forEach(input => input.disabled = false);
            } else if (selectedShift === '2') {
                console.log("Enable 2");
                row.querySelectorAll('input[name*="_2_"]').forEach(input => input.disabled = false);
            } else if (selectedShift === '3') {
                console.log("Enable 3");
                row.querySelectorAll('input[name*="_3_"]').forEach(input => input.disabled = false);
            }

            // Recalculate totals after a shift selection change
            calculateTotals();
        }

        function calculateTotals() {
            const inputsA = document.querySelectorAll('input[name$="_1"]');
            const inputsB = document.querySelectorAll('input[name$="_2"]');
            const inputsC = document.querySelectorAll('input[name$="_3"]');

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
        
function validateForm(event) {
    const days = new Set(); // Sử dụng Set để lưu trữ tên các ngày
    const missingDays = []; // Mảng lưu trữ các ngày chưa được chọn

    // Lấy tất cả các nhóm radio
    const shiftGroups = document.querySelectorAll('input[type="radio"]');

    // Duyệt qua tất cả các nhóm radio
    shiftGroups.forEach(shift => {
        const day = shift.name.split('_')[0]; // Tách tên để lấy ngày

        // Nếu radio được chọn, thêm ngày vào Set
        if (shift.checked) {
            days.add(day);
        }
    });

    // Kiểm tra xem có ngày nào chưa được chọn
    const uniqueDays = new Set([...shiftGroups].map(shift => shift.name.split('_')[0])); // Lấy tất cả các ngày
    uniqueDays.forEach(day => {
        if (!days.has(day)) {
            missingDays.push(day); // Thêm ngày vào danh sách nếu chưa được chọn
        }
    });

    // Nếu có ngày chưa được chọn, hiển thị thông báo
    if (missingDays.length > 0) {
        const missingDaysString = missingDays.join('\n '); // Chuyển danh sách thành chuỗi
        const confirmResult = confirm('Các ngày sau chưa được điền:\n'+missingDaysString+" \nBạn có muốn tiếp tục gửi không?");
        if (!confirmResult) {
            event.preventDefault(); // Ngăn không cho gửi form
        }
    }
}
    </script>
</body>
</html>
