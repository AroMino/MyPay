import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.lang.Class;
/**
 * Servlet implementation class Authentification
 */
public class Authentification extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Authentification() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String log = request.getParameter("login");
		String pass = request.getParameter("password");
		
		System.out.println(log);
		System.out.println(pass);
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mypay","mit","123456");
			Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM users");
            PrintWriter p = response.getWriter();
            while(result.next()) {
            	String login = result.getString("login");
            	String password = result.getString("password");
            	if(log.equals(login) && pass.equals(password)) {
            		result.close();
                    statement.close();
                    connection.close();
                    response.sendRedirect("View?login="+login);
            	}
            }
            response.sendRedirect("login.jsp");
          
		} 
		catch (ClassNotFoundException e) {
		    e.printStackTrace();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
