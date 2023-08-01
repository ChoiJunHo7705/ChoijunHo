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
    <title>회원가입</title>
</head>
<body>
    <h1>회원가입</h1>
    <form id="signupForm">
        <label for="username">아이디:</label>
        <input type="text" id="username" required><br>
        <label for="password">비밀번호:</label>
        <input type="password" id="password" required><br>
        <label for="name">이름:</label>
        <input type="text" id="name" required><br>
        <button type="submit">작성완료</button>
    </form>
    <script src="signup.js"></script>
<script>
    document.getElementById("signupForm").onsubmit = function(event) {
        event.preventDefault(); // 폼 전송 막기

        // 아이디, 비밀번호, 이름을 가져옴
        var username = document.getElementById("username").value;
        var password = document.getElementById("password").value;
        var name = document.getElementById("name").value;

        // 회원가입 완료 처리
        var userData = {
            username: username,
            password: password,
            name: name,
            point: 1000
        };

        // Send the user data to the server for signup
        var xhr = new XMLHttpRequest();
        xhr.open("POST", "SignupServlet", true);
        xhr.setRequestHeader("Content-Type", "application/json");
        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4 && xhr.status === 200) {
                var response = JSON.parse(xhr.responseText);
                if (response.success) {
                    alert("가입되었습니다. 로그인 해주세요.");
                    window.location.href = "로그인 화면.jsp"; // 로그인 화면으로 이동
                } else {
                    alert("회원가입에 실패하였습니다. 다시 시도해주세요.");
                }
            }
        };
        xhr.send(JSON.stringify(userData));
    };
</script>

</body>
</html>
