package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import dao.LoginDao;

@WebServlet("/LoginServlet")
	public class LoginServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		boolean loginSuccess = LoginDao.checkLogin(username, password) != null;

		// 로그인 성공 여부를 JSON 형태로 응답
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write("{\"success\": " + loginSuccess + "}");

		if (loginSuccess) {
			// 로그인 성공 시 세션에 사용자 정보 저장
			HttpSession session = request.getSession();
			session.setAttribute("username", username);

			// 관리자 계정이면 관리자 페이지로 이동, 일반 계정이면 메인 페이지로 이동
			if (username.equals("admin") && password.equals("adminpassword")) {
				response.sendRedirect("admin_login.jsp");
			} else {
				response.sendRedirect("메인페이지.jsp");
			}
			return; // 중요! 이후 코드가 실행되지 않도록 반환합니다.
		}
	}
}
