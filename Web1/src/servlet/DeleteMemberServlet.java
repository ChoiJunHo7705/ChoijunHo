package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DeleteMemberServlet")
public class DeleteMemberServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 특정 회원을 삭제하는 로직을 구현해야 합니다. (이 예제에서는 더미 데이터 사용)
        String username = request.getParameter("username");
        deleteMember(username);

        // 삭제된 후, 해당 회원 목록을 다시 보여주기 위해 AdminPageServlet로 리다이렉트
        response.sendRedirect("AdminPageServlet");
    }

    private void deleteMember(String username) {
        // 특정 회원을 삭제하는 로직을 구현해야 합니다. (이 예제에서는 더미 데이터 사용)
        // 여기서는 아무 동작도 하지 않습니다.
    }
}
