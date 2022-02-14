package trn.Welcome;

import java.sql.Connection;
import java.sql.DriverManager;

public class WelcomeDAO {
	
	private Connection con;
	
	public WelcomeDAO() {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "KGGYM";
		String password = "oracle1";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
