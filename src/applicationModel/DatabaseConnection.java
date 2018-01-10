package applicationModel;
import java.sql.*;

import javax.sql.rowset.CachedRowSet;

/*
 * this class take care of database connection
 * 
 */

public class DatabaseConnection
{
	
	private static Connection conn=null;
	
	/*
	 * dbConnect method is creating database connection
	 */
	public static Connection dbConnect()
	{
	 try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			 conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost_or_servername:1521:orcl","username","password");
		
		System.out.println("DB COnnected");}
		catch(Exception e)
		{
			System.out.println(e);
		}
	return conn;
	}

/*
 * this method is closing the database connection
 */
	public static void dbDisconnect() throws SQLException {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (Exception e){
           throw e;
        }
    }


    
}




