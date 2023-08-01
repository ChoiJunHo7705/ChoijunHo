package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.MemberDao;
import model.Member;

@WebServlet("/UpdateMemberServlet")
public class UpdateMemberServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String point = request.getParameter("point");
        // 필요한 수정 항목들을 추가

        // 여기서 회원 정보를 DB에 업데이트하는 코드를 작성
        MemberDao memberDao = new MemberDao();
        Member member = new Member(username, password, name, 0, false);
        boolean updateSuccess = memberDao.updateMember(member);

        if (updateSuccess) {
            // 수정 성공 시 "수정되었습니다." 알림을 띄우고 관리자 페이지(회원 목록)으로 이동
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write("<script>alert('수정되었습니다.');location.href='AdminPage.jsp';</script>");
        } else {
            // 수정 실패 시 어떻게 처리할지에 대한 로직 추가 (예: 오류 페이지로 이동)
            // 예시: response.sendRedirect("ErrorPage.jsp");
        }
    }
}
