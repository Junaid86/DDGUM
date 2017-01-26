package jdbcconnection;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBCTemplate
{
	public static Connection getConnection()
	{
		Connection conn=null;
		try{
			//JDBCODBC Connection Stream 
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost/ddgum","root","junaid");
			System.out.println("successfully connect to Database");
		   }
		catch(Exception e)
		   {
			  try{
			      conn.close();
			     }
			   catch(Exception ee)
			     { 
				   System.out.println(ee);
			      }
			System.out.println(e);
		   }
		return conn;
	}
        
        public static void main(String args[])

        {
            Connection c=JDBCTemplate.getConnection();
        }
}
