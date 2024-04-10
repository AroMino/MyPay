

import jakarta.servlet.ServletException;
import java.io.PrintWriter;
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

/**
 * Servlet implementation class Wallet
 */
public class Wallet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Wallet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int cur_pay = 0;
		String login = (String) request.getParameter("login");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mypay","mit","123456");
			Statement statement = connection.createStatement();
			
			String r = "SELECT SUM(value) as value from transactions where login='"+login+"' and field='wallet'";
            ResultSet result = statement.executeQuery(r);
            result.next();
            
            String tmp = result.getString("value");
            if(tmp != null)cur_pay = Integer.parseInt(tmp); 
            else cur_pay = 0;
            
            connection.close();
            statement.close();
            result.close();
		}
		catch (ClassNotFoundException e) {
		    e.printStackTrace();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		Transaction  transaction = new Transaction();
		
		transaction.setType(request.getParameter("type"));
		if(transaction.getType() != null) {
			if(transaction.getType().equals("outgoing")) transaction.setDestination(request.getParameter("src_des"));
			else if(transaction.getType().equals("incoming")) transaction.setSource(request.getParameter("src_des"));
			transaction.setMotive(request.getParameter("motive"));
			
			int value = Integer.parseInt(request.getParameter("value"));
			if(transaction.getType().equals("outgoing") || transaction.getType().equals("bank_deposit") || transaction.getType().equals("saving_deposit")) value *=-1;
			transaction.setValue(value);
			transaction.setField("wallet");
			transaction.setLogin(login);
			
			try {
				transaction.execute(cur_pay);
			}
			catch(NotEnoughPay e) {
				response.sendRedirect("wallet.jsp");
			}
		}
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mypay","mit","123456");
			Statement statement = connection.createStatement();
			
			String r = "SELECT SUM(value) as value from transactions where login='"+login+"' and field='wallet'";
            ResultSet result = statement.executeQuery(r);
            result.next();
            String tmp = result.getString("value");
            if(tmp != null)cur_pay = Integer.parseInt(tmp); 
            else cur_pay = 0;
            
            r = "SELECT * from transactions where login='"+login+"' and field='wallet'";
            result = statement.executeQuery(r);
            result.next();
            
            connection.close();
            statement.close();
            result.close();
		}
		catch (ClassNotFoundException e) {
		    e.printStackTrace();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("cur_pay", cur_pay);
		request.setAttribute("login", login);
		request.getRequestDispatcher("wallet.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
