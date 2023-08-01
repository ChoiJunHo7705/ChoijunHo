package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 세션 무효화 (로그아웃)
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        // 로그인 화면으로 이동
        response.sendRedirect("로그인 화면.jsp");
    }
}

