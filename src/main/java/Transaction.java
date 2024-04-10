import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Transaction {
	private int number;
	private int value;
	private String field = "";
	private String type = "";
	private String login = "";
	private String motive = "";
	private String source = "";
	private String destination = "";
	private String details = "";
	
	public Transaction() {
		
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public void setField(String field) {
		this.field = field;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setMotive(String motive) {
		this.motive = motive;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public void setValue(int value) {
		this.value = value;
	}
	
	public int getNumber() {
		return this.number;
	}
	public String getType() {
		return this.type;
	}
	public String getField() {
		return this.field;
	}
	public String getLogin() {
		return this.login;
	}
	
	public String getMotive() {
		return this.motive;
	}
	public String getSource() {
		return this.source;
	}
	public String getDestination() {
		return this.destination;
	}
	public String getDetails() {
		return this.details;
	}
	public int getValue() {
		return this.value;
	}
	
	public void execute(int cur_pay) throws NotEnoughPay{
		if(cur_pay + this.value < 0) throw new NotEnoughPay("Not enough");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mypay","mit","123456");
			String sql = "insert into transactions (login,field,value,type,motive,source,destination,details) values (?,?,?,?,?,?,?,?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, this.login);
                preparedStatement.setString(2, this.field);
                preparedStatement.setInt(3, this.value);
                preparedStatement.setString(4, this.type);
                preparedStatement.setString(5, this.motive);
                preparedStatement.setString(6, this.source);
                preparedStatement.setString(7, this.destination);
                preparedStatement.setString(8, this.details);
                
                preparedStatement.executeUpdate();
			
            connection.close();
//            statement.close();
//            result.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
		}
		catch (ClassNotFoundException e) {
		    e.printStackTrace();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
