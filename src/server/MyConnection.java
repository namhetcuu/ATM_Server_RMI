package server;
import java.sql.Connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class MyConnection {
	public static Connection connection;
	public static void ketnoi() {
		try {


//	        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//	        connection = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-8NNPD81\\\\MSSQLSERVER:1433;"
//	        		+ "encrypt=false;trustServerCertificate=true;database=Bank;User=sa;password=123456");
			
			String url = "jdbc:mysql://localhost:3306/banking?useSSL=false&serverTimezone=UTC";
			String user = "root";
			String password = "123456";
			connection = DriverManager.getConnection(url,user,password);
	        System.out.println("ok");
	    } catch (SQLException ex) {
	        System.out.println(ex);
	    }
	}
	
}
