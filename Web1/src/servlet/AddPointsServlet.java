package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/AddPointsServlet")
public class AddPointsServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int points = Integer.parseInt(request.getParameter("points"));
        // 회원의 포인트를 랜덤 포인트 값만큼 증가시킵니다.
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("userPoint") != null) {
            int currentPoints = (int) session.getAttribute("userPoint");
            session.setAttribute("userPoint", currentPoints + points);
        }

        // JSON 형식의 응답을 전송합니다.
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.write("{\"message\": \"" + points + "점이 적립되었습니다.\"}");
    }
}
