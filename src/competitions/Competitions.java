package competitions;

import java.sql.Connection;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class Competitions {


    public static void main(String[] args)
    {
        try {
            Connection conn = MySqliteConn.getConnection();
            TableModel mod = new MyTableModel(conn, "commands");
            JTable jtable = new JTable(mod);
            
            TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(mod);
            jtable.setRowSorter(sorter);
            
            jtable.setDefaultRenderer(Object.class, new MyTableRenderer());
            JScrollPane scroll = new JScrollPane(jtable, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            
            JFrame frame = new JFrame("Competitions");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(scroll);
            frame.pack();
            frame.setVisible(true);
            
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
