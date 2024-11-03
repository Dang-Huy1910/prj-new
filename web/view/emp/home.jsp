<%-- 
    Document   : HomeOfStudent
    Created on : Jan 30, 2024, 5:00:31 PM
    Author     : trung
--%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.time.DayOfWeek"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
        <!-- Font Awesome -->
        <link
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css"
            rel="stylesheet"
            />
        <!-- Google Fonts -->
        <link
            href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap"
            rel="stylesheet"
            />
        <!-- MDB -->
        <link
            href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/7.1.0/mdb.min.css"
            rel="stylesheet"
            />
    </head>
    <body>

        <jsp:include page="header.jsp"></jsp:include>
            <div class="container" style="height: 600px">
                <ul class="list-group list-group-light">
                    
                <li class="list-group-item px-3 border-0 active text-center mt-5 mb-3" aria-current="true">
                    Nhân viên xưởng
                </li>
                <li class="list-group-item px-3 border-0"><a href="emp/view">Đặt trước sản phẩm làm việc trong ngày</a></li>
<!--                <li class="list-group-item px-3 border-0"><a href="manager/att">Điểm danh</a></li>-->
            </ul>  
        </div>
    </body>
</html>
