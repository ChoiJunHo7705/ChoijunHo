<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.DriverManager"%>
<%@ page import="java.sql.PreparedStatement"%>
<%@ page import="java.sql.SQLException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String driver = "oracle.jdbc.driver.OracleDriver";
String url = "jdbc:oracle:thin:@localhost:1521:xe";
String dbId = "user0112";
String dbPw = "user1234";

Connection conn = null;
try {
    Class.forName(driver);
    conn = DriverManager.getConnection(url, dbId, dbPw);
} catch(Exception e) {
    e.printStackTrace();
}
%>

<!DOCTYPE html>
<html>
<head>
    <title>로그인 페이지</title>
</head>
<body>
    <h1>로그인</h1>
    <form id="loginForm">
        <label for="username">아이디:</label>
        <input type="text" id="username" required><br>
        <label for="password">비밀번호:</label>
        <input type="password" id="password" required><br>
        <button type="submit">로그인</button>
        <button type="button" onclick="goToSignupPage()">회원가입</button>
    </form>
    <script src="login.js"></script>
<script>
    function goToSignupPage() {
        window.location.href = "회원가입 화면.jsp"; // 회원가입 페이지로 이동
    }

    document.getElementById("loginForm").onsubmit = function(event) {
        event.preventDefault(); // 폼 전송 막기

        // 아이디와 비밀번호 값을 가져옴
        var username = document.getElementById("username").value;
        var password = document.getElementById("password").value;

        // 로그인 시도
        var xhr = new XMLHttpRequest();
        xhr.open("POST", "LoginServlet", true);
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4 && xhr.status === 200) {
                var response = JSON.parse(xhr.responseText);
                if (response.success) {
                    if (response.isAdmin) {
                        window.location.href = "admin_login.jsp"; // 관리자 계정이면 관리자 페이지로 이동
                    } else {
                        window.location.href = "메인페이지.jsp"; // 일반 계정이면 메인 페이지로 이동
                    }
                } else {
                    alert("아이디/비밀번호를 다시 확인하세요"); // 잘못된 입력일 경우 경고창 띄우기
                }
            }
        };
        xhr.send("username=" + encodeURIComponent(username) + "&password=" + encodeURIComponent(password));
    };
</script>

</body>
</html>
