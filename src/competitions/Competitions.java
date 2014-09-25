package competitions;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.TableModel;

public class Competitions {


    public static void main(String[] args)
    {
        Connection conn = null;
        Statement statement = null;
        ResultSet res = null;

        try {
            conn = MySqliteConn.getConnection();
            TableModel mod = new MyTableModel(conn, "commands");
            
            JTable jtable = new JTable(mod);
            jtable.setDefaultRenderer(Object.class, new MyTableRenderer());
            JScrollPane scroll = new JScrollPane(jtable, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            
            JFrame frame = new JFrame("Competitions");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(scroll);
            frame.pack();
            frame.setVisible(true);
            
            conn.close();
                    
//            String sql = "select * from commands;";
//            statement = conn.createStatement();
//            res = statement.executeQuery(sql);
//            while (res.next()){
//                System.out.println(res.getObject("name"));
//            }
        } catch (Exception e) {
            e.printStackTrace();
//        } finally {
//            try {
//              if (statement != null) statement.close();
//              if (res != null) res.close();
//            } catch (SQLException ex) {
//              Logger.getLogger(Competitions.class.getName()).log(Level.SEVERE, null, ex);
//            }
        }
    }
}
