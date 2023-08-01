package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/EditMemberServlet")
public class EditMemberServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 특정 회원의 정보를 수정하는 페이지로 이동
        // 이 예제에서는 더미 데이터를 사용하여 회원의 정보를 불러옴
        String username = request.getParameter("username");
        String userInfo = getUserInfo(username);

        request.setAttribute("username", username);
        request.setAttribute("userInfo", userInfo);
        request.getRequestDispatcher("edit_member.jsp").forward(request, response);
    }

    private String getUserInfo(String username) {
        // 특정 회원의 정보를 가져오는 로직을 구현해야 합니다. (이 예제에서는 더미 데이터 사용)
        // 여기서는 회원의 정보를 문자열로 표현하여 리턴합니다.
        return "회원 정보 - 이름: " + username + ", 이메일: user@example.com";
    }
}
