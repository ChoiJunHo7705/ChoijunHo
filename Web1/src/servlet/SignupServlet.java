package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MemberDao;
import model.Member;

@WebServlet("/SignupServlet")
public class SignupServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Update these values with your database information
    private final String driver = "oracle.jdbc.driver.OracleDriver";
    private final String url = "jdbc:oracle:thin:@localhost:1521:xe";
    private final String dbId = "user0112";
    private final String dbPw = "user1234";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        // Get the user data from the request body
        StringBuilder requestBody = new StringBuilder();
        String line;
        while ((line = request.getReader().readLine()) != null) {
            requestBody.append(line);
        }

        // Extract the user data manually from the JSON string
        String username = null;
        String password = null;
        String name = null;
        int point = 0;

        try {
            // Parse the JSON manually
            String json = requestBody.toString();
            String[] pairs = json.substring(1, json.length() - 1).split(",");
            for (String pair : pairs) {
                String[] keyValue = pair.split(":");
                String key = keyValue[0].trim().replaceAll("\"", "");
                String value = keyValue[1].trim().replaceAll("\"", "");
                switch (key) {
                    case "username":
                        username = value;
                        break;
                    case "password":
                        password = value;
                        break;
                    case "name":
                        name = value;
                        break;
                    case "point":
                        point = Integer.parseInt(value);
                        break;
                }
            }

            // Create a new Member object with the extracted data
            Member member = new Member(username, password, name, point, false);

            // Insert the member into the database using the MemberDao
            boolean success = MemberDao.insertMember(member);

            // Prepare the JSON response manually
            StringBuilder jsonResponse = new StringBuilder();
            jsonResponse.append("{\"success\":");
            jsonResponse.append(success ? "true" : "false");
            jsonResponse.append("}");

            // Send the JSON response back to the client
            out.println(jsonResponse.toString());

        } catch (Exception e) {
            e.printStackTrace();
            // Prepare the JSON response in case of an error
            StringBuilder errorResponse = new StringBuilder();
            errorResponse.append("{\"success\":false}");
            out.println(errorResponse.toString());
        }
    }
}
