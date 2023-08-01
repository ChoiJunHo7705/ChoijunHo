package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AdminPageServlet")
public class AdminPageServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 관리자 아이디로 로그인했는지 확인
        String adminId = (String) request.getSession().getAttribute("adminId");
        if (adminId == null || !adminId.equals("admin")) {
            response.sendRedirect("admin_login.jsp");
            return;
        }

        // 회원 목록을 가져오는 로직을 구현해야 합니다. (이 예제에서는 더미 데이터 사용)
        List<String> memberList = getMemberList();

        request.setAttribute("memberList", memberList);
        request.getRequestDispatcher("admin_page.jsp").forward(request, response);
    }

    private List<String> getMemberList() {
        // 회원 목록을 가져오는 로직을 구현해야 합니다. (이 예제에서는 더미 데이터 사용)
        List<String> memberList = new ArrayList<>();
        memberList.add("User1");
        memberList.add("User2");
        memberList.add("User3");
        return memberList;
    }
}
