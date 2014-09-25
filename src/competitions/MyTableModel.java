package competitions;

import javax.swing.table.*;
import java.sql.*;
import java.util.*;

public class MyTableModel extends AbstractTableModel {
    
    private static Connection conn;

    private Object[][] contents;
    private String[] columnNames;
    private Class[] columnClasses;

    public MyTableModel(Connection conn,
            String tableName)
            throws SQLException {
        super();
        MyTableModel.conn = conn;
        getTableContents(tableName);
    }

    private void getTableContents(String tableName)
            throws SQLException {

        DatabaseMetaData meta = conn.getMetaData();

        ResultSet rs = meta.getColumns(null, null, tableName, null);

        ArrayList colNamesList = new ArrayList();
        ArrayList colTypesList = new ArrayList();

        while (rs.next()) {
            colNamesList.add(rs.getString("COLUMN_NAME"));
            int dbType = rs.getInt("DATA_TYPE");

            switch (dbType) {
                case Types.INTEGER:
                    colTypesList.add(Integer.class);
                    break;
                case Types.FLOAT:
                    colTypesList.add(Float.class);
                    break;
                case Types.DOUBLE:
                case Types.REAL:
                    colTypesList.add(Double.class);
                    break;
                case Types.DATE:
                case Types.TIME:
                case Types.TIMESTAMP:
                    colTypesList.add(java.sql.Date.class);
                    break;
                default:
                    colTypesList.add(String.class);
                    break;
            };
        }

        columnNames = new String[colNamesList.size()];
        colNamesList.toArray(columnNames);

        columnClasses = new Class[colTypesList.size()];
        colTypesList.toArray(columnClasses);

        Statement statement = conn.createStatement();
        rs = statement.executeQuery("SELECT * FROM " + tableName);
        ArrayList rowList = new ArrayList();

        while (rs.next()) {
            ArrayList cellList = new ArrayList();
            for (int i = 0; i < columnClasses.length; i++) {
                Object cellValue = null;

                //TODO: pattern should be here
                if (columnClasses[i] == String.class) { cellValue = rs.getString(columnNames[i]);
                } else if (columnClasses[i] == Integer.class) { cellValue = new Integer(rs.getInt(columnNames[i]));
                } else if (columnClasses[i] == Float.class) { cellValue = new Float(rs.getInt(columnNames[i]));
                } else if (columnClasses[i] == Double.class) { cellValue = new Double(rs.getDouble(columnNames[i]));
                } else if (columnClasses[i] == java.sql.Date.class) { cellValue = rs.getDate(columnNames[i]);
                } else {System.out.println("Undefined type " + columnNames[i]);
                }

                cellList.add(cellValue);
            }

            Object[] cells = cellList.toArray();
            rowList.add(cells);
        }

        contents = new Object[rowList.size()][];
        for (int i = 0; i < contents.length; i++) {
            contents[i] = (Object[]) rowList.get(i);
        }
        
        if (rs!=null ) rs.close();
        if (statement!=null ) statement.close();
    }
    
    @Override
    public int getRowCount() {
        return contents.length;
    }

    @Override
    public int getColumnCount() {
        if (contents.length == 0) {
            return 0;
        } else {
            return contents[0].length;
        }
    }

    @Override
    public Object getValueAt(int row, int column) {
        return contents[row][column];
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        contents[rowIndex][columnIndex] = aValue;

        fireTableCellUpdated(rowIndex, columnIndex);
    }

    @Override
    public Class getColumnClass(int col) {
        return columnClasses[col];
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (columnIndex == 0) {
            return false;
        }
        return true;
    }

   
}