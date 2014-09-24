package competitions;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Competitions {


    public static void main(String[] args)
    {
        Connection conn = null;
        Statement statement = null;
        ResultSet res = null;

        try {
            conn = MySqliteConn.getConnection();
            String sql = "select * from commands;";
            statement = conn.createStatement();
            res = statement.executeQuery(sql);
            while (res.next()){
                System.out.println(res.getObject("name"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Competitions.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
              if (statement != null) statement.close();
              if (res != null) res.close();
            } catch (SQLException ex) {
              Logger.getLogger(Competitions.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
