<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>관리자 로그인</title>
</head>
<body>
    <h1>회원 관리</h1>
    <form action="AdminLoginServlet" method="post">
        <button type="submit" style="position: absolute; left: 340px; top:40px">로그인</button>
    </form>

    <table border="1">
        <tr>
            <th>ID</th>
            <th>PW</th>
            <th>name</th>
            <th>point</th>
            <th>수정/삭제</th>
        </tr>
        <tr>
            <td>John Doe</td>
            <td>1234</td>
            <td>johndoe</td>
            <td>5000</td>
            <td>
                <a href="#" onclick="editMember('johndoe')">수정</a> |
                <a href="#" onclick="deleteMember('johndoe')">삭제</a>
            </td>
        </tr>
        <tr>
            <td>Jane Smith</td>
            <td>5678</td>
            <td>janesmith</td>
            <td>10000</td>
            <td>
                <a href="#" onclick="editMember('janesmith')">수정</a> |
                <a href="#" onclick="deleteMember('janesmith')">삭제</a>
            </td>
        </tr>
        <tr>
            <td>Mike Johnson</td>
            <td>9012</td>
            <td>mikejohnson</td>
            <td>7500</td>
            <td>
                <a href="#" onclick="editMember('mikejohnson')">수정</a> |
                <a href="#" onclick="deleteMember('mikejohnson')">삭제</a>
            </td>
        </tr>
        <tr>
            <td>Susan Lee</td>
            <td>2345</td>
            <td>susanlee</td>
            <td>3000</td>
            <td>
                <a href="#" onclick="editMember('susanlee')">수정</a> |
                <a href="#" onclick="deleteMember('susanlee')">삭제</a>
            </td>
        </tr>
        <tr>
            <td>Robert Brown</td>
            <td>5678</td>
            <td>robertbrown</td>
            <td>2000</td>
            <td>
                <a href="#" onclick="editMember('robertbrown')">수정</a> |
                <a href="#" onclick="deleteMember('robertbrown')">삭제</a>
            </td>
        </tr>
    </table>

    <script>
        function editMember(username) {
            // Redirect to the member information update page with the corresponding username
            window.location.href = "EditMemberServlet?username=" + encodeURIComponent(username);
        }

        function deleteMember(username) {
            if (confirm("정말로 회원을 삭제하시겠습니까?")) {
                // Call the DeleteMemberServlet using AJAX to delete the member without page reload
                var xhr = new XMLHttpRequest();
                xhr.open("GET", "DeleteMemberServlet?username=" + encodeURIComponent(username), true);
                xhr.onreadystatechange = function() {
                    if (xhr.readyState === 4 && xhr.status === 200) {
                        alert("삭제되었습니다.");
                        // Remove the deleted row from the table
                        var row = document.querySelector("tr[data-username='" + username + "']");
                        if (row) {
                            row.remove();
                        }
                    }
                };
                xhr.send();
            }
        }
    </script>
    
<h1>스케줄러 관리</h1>
<button onclick="startScheduler()">스케줄러(20초마다 포인트 1증가) 실행 시작</button>
<button onclick="stopScheduler()">스케줄러 실행 종료</button>
<script>
    var schedulerInterval;

    function startScheduler() {
        if (schedulerInterval) {
            clearInterval(schedulerInterval);
        }
        schedulerInterval = setInterval(increasePoint, 20000); // 20 seconds
    }

    function stopScheduler() {
        if (schedulerInterval) {
            clearInterval(schedulerInterval);
            schedulerInterval = null;
        }
    }

    function increasePoint() {
        // Call the server-side method to increase points for all members
        // using AJAX to avoid page reload
        var xhr = new XMLHttpRequest();
        xhr.open("GET", "IncreasePointsServlet", true);
        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4 && xhr.status === 200) {
                alert("스케줄러 실행: 포인트 1증가");
            }
        };
        xhr.send();
    }
</script>

</body>
</html>
