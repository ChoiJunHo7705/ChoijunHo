<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>
<%@ page import="javax.servlet.*" %>
<%@ page import="javax.servlet.http.*" %>
<%@ page import="dao.MemberDao" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>메인페이지</title>
</head>
<body>
    <h1>메인페이지</h1>
    <%-- 세션을 통해 로그인 정보를 가져옴 --%>
    <% HttpSession sessionHttpSession = request.getSession(false); %>
    <div class="id" style="position: absolute;right: 1px;top: 30px;">
        <% if (session != null && session.getAttribute("username") != null) { %>
            <%= session.getAttribute("username") %>님 안녕하세요.
            <form action="LogoutServlet" method="post">
                <button type="submit">로그아웃</button>
            </form>
        <% } else { %>
            로그인이 필요합니다.
            <a href="로그인 화면.jsp">로그인</a>
        <% } %>
        <br>
        <div class="point">포인트: <%= session != null && session.getAttribute("userPoint") != null ? session.getAttribute("userPoint") + "점" : "0점" %></div>
    </div>
    <div class="contents" style="margin-top: 100px; font-size: 25px;">
        구입할 컨텐츠를 선택하세요
    </div>
    <img src="img/Intro_350_408.png">100,000포인트
    <img src="img/Java_350_408.png">500,000포인트	
    <img src="img/Cpp_350_408.png">300,000포인트
    <div class="advertisement" style="float: right; margin-top: 500px;">
        <span style="position: absolute; bottom: 260px;"><광고></span>
        <img src="img/korea_it.png" onclick="generateRandomPoints()">
    </div>
    <script>
        function generateRandomPoints() {
            var minPoints = 1;
            var maxPoints = 1000;
            var points = Math.floor(Math.random() * (maxPoints - minPoints + 1)) + minPoints;
            alert(points + "점이 적립되었습니다.");
            window.location.href = "http://koreaisacademy.com"; // Redirect to the specified URL
            // 서버와의 연동을 위해 추가
            addPointsToUser(points);
        }

        function addPointsToUser(points) {
            // 서버와 연동하여 포인트 적립 처리
            var xhr = new XMLHttpRequest();
            xhr.open("POST", "AddPointsServlet", true);
            xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
            xhr.send("points=" + points);
        }

        function purchaseCourse(courseName, price) {
            // ... 이전 코드 ...
        }
    </script>
</body>
</html>
