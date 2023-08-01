package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DBConnection;
import model.Member;

@WebServlet("/IncreasePointsServlet")
public class IncreasePointsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // DB 연동을 위한 Connection 객체를 가져옵니다.
        Connection connection = null;
		try {
			connection = DBConnection.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        Member memberDAO = new Member(connection);

        try {
            // 모든 회원 정보를 가져옵니다.
            List<Member> members = memberDAO.getAllMembers();

            // 회원들의 포인트를 1씩 증가시킵니다.
            for (Member member : members) {
                member.setPoint(member.getPoint() + 1);
                // 수정된 포인트를 데이터베이스에 반영합니다.
                memberDAO.updateMember(member);
            }

            // 스케줄러가 실행되었음을 콘솔에 출력
            System.out.println("Job이 실행되어, 모든 회원에게 1포인트가 부여되었습니다.");

            // 응답으로 JSON 형식의 데이터를 전송 (Optional: 클라이언트에서 스케줄러 실행 결과를 확인하기 위해)
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            out.write("{\"message\": \"스케줄러 실행: 포인트 1증가\"}");

        } catch (Exception e) {
            e.printStackTrace();
            // DB 연동 중 오류가 발생하였을 경우, 오류 메시지를 응답으로 전송
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "서버 오류");
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
