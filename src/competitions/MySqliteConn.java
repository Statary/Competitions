package competitions;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MySqliteConn {

  private static Connection conn;
  
  public static Connection getConnection()
  {
//      conn = null;
      
      try {
          Driver driver = (Driver) Class.forName("org.sqlite.JDBC").newInstance();
          String url = "jdbc:sqlite:./db/competitions.db";

          if( conn == null) conn = DriverManager.getConnection(url);
          return conn;
          
      } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
          Logger.getLogger(MySqliteConn.class.getName()).log(Level.SEVERE, null, ex);
//      } finally {
//        try {
//          if (conn != null) conn.close();
//        } catch (SQLException ex) {
//          Logger.getLogger(Competitions.class.getName()).log(Level.SEVERE, null, ex);
//        }
      }
      return null;
  }
}
