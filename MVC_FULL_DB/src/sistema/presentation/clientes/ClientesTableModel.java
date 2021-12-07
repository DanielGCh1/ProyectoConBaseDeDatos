package sistema.presentation.clientes;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import sistema.logic.Cliente;

public class ClientesTableModel extends AbstractTableModel implements TableModel {
    String[] cols ={"Cedula","Nombre","Telefono" };
    List<Cliente> rows;

    public  ClientesTableModel(List<Cliente> rows){
        this.rows=rows;
    }

    public int getColumnCount() {
        return 3;
    }

    public String getColumnName(int col){
        return cols[col];
    }

    public int getRowCount() {
        return rows.size();
    }
    
    public Object getValueAt(int row, int col) {
        Cliente c = rows.get(row);
        switch (col){
            case 0: return c.getCedula();
            case 1:return c.getNombre();
            case 2:return c.getTelefono();
            default: return "";
        }
    }    
}

