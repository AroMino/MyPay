

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

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Servlet implementation class View
 */
public class View extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public View() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = today.format(formatter);
        
        String login = (String) request.getParameter("login");
        
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mypay","mit","123456");
			Statement statement = connection.createStatement();
			
			String r = "SELECT SUM(value) as value from transactions where login='"+login+"' and field='wallet'";
            ResultSet result = statement.executeQuery(r);
            result.next();
            String w_value = result.getString("value");   
            if(w_value == null) w_value = "0";
            
            
            r = "SELECT SUM(value) as value from transactions where login='"+login+"' and field='bank'";
            result = statement.executeQuery(r);
            result.next();
            String b_value = result.getString("value"); 
            if(b_value == null) b_value = "0";
            
            r = "SELECT SUM(value) as value from transactions where login='"+login+"' and field='saving'";
            result = statement.executeQuery(r);
            result.next();
            String s_value = result.getString("value");
            if(s_value == null) s_value = "0";
            
            r = "SELECT SUM(value) as value from transactions where value < 0 and login='"+login+"' and date > '" + date + "'";
            result = statement.executeQuery(r);
            result.next();
            String t_expenses = result.getString("value");
            if(t_expenses == null) t_expenses = "0";
            
            r = "SELECT SUM(value) as value from transactions where value > 0 and login='"+login+"' and date > '" + date + "'";
            result = statement.executeQuery(r);
            result.next();
            String t_income = result.getString("value");
            if(t_income == null) t_income = "0";
            
            request.setAttribute("login", login);
            request.setAttribute("w_value", w_value);
            request.setAttribute("b_value", b_value);
            request.setAttribute("s_value", s_value);
            request.setAttribute("t_expenses", t_expenses);
            request.setAttribute("t_income", t_income);
            request.getRequestDispatcher("view.jsp").forward(request, response);
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
