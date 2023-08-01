package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/PurchaseCourseServlet")
public class PurchaseCourseServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 세션에서 사용자 정보 가져오기
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            // 로그인되지 않은 상태라면 에러 응답
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        String courseName = request.getParameter("courseName");
        int price = Integer.parseInt(request.getParameter("price"));

        // 포인트를 서버에서 관리한다고 가정하여 사용자의 포인트를 가져옴
        int userPoints = getUserPoints(session.getAttribute("username").toString());

        if (userPoints >= price) {
            // 포인트가 충분한 경우 구매 처리
            updateUserPoints(session.getAttribute("username").toString(), userPoints - price);
            // 구매 성공을 클라이언트에게 알려주기 위해 JSON 응답
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{\"success\": true}");
        } else {
            // 포인트가 부족한 경우 에러 응답
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    private int getUserPoints(String username) {
        // 해당 사용자의 포인트를 DB에서 가져오는 로직을 구현해야 합니다.
        // 필요에 따라 DAO나 데이터베이스 연결 등을 사용합니다.
        // 이 예제에서는 임시로 200,000 포인트를 리턴합니다.
        return 200000;
    }

    private void updateUserPoints(String username, int points) {
        // 해당 사용자의 포인트를 DB에 업데이트하는 로직을 구현해야 합니다.
        // 필요에 따라 DAO나 데이터베이스 연결 등을 사용합니다.
        // 이 예제에서는 아무 동작도 하지 않습니다.
    }
}

