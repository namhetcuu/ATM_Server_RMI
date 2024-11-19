import java.sql.Connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class MyConnection {
	public static Connection connection;
	public static void ketnoi() {
		try {
	        // Edit URL , username, password to authenticate with your MS SQL Server


	        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	        connection = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-8NNPD81\\\\MSSQLSERVER:1433;"
	        		+ "encrypt=false;trustServerCertificate=true;database=Bank;User=sa;password=123456");
	        System.out.println("ok");
	    } catch (ClassNotFoundException | SQLException ex) {
	        System.out.println(ex);
	    }
	}
	
}
