
package les12015.core.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class Conexao {
	public static Connection getConnection() 
			throws ClassNotFoundException, SQLException
	{
		Connection conn = null;
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost/les_loco";
        String user = "root";
		String password = "123456";
		conn = DriverManager.getConnection(url, user, password);
		return conn;
	}
}
