<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>회원 정보 수정</title>
</head>
<body>
    <h1>회원 정보 수정</h1>
    <h2><%= request.getAttribute("username") %>님의 정보</h2>
    <p><%= request.getAttribute("userInfo") %></p>
    <form action="UpdateMemberServlet" method="post">
        <input type="hidden" name="username" value="<%= request.getAttribute("username") %>">
        <label>아이디: <%= request.getAttribute("username") %></label><br>
        <label>비밀번호: </label><input type="password" name="password" required><br>
        <label>이름: </label><input type="text" name="name" required><br>
        <label>이메일: </label><input type="email" name="email" required><br>
        <!-- 필요한 수정 항목들을 추가 -->
        <button type="submit">제출</button>
    </form>
</body>
</html>
