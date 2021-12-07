package sistema.presentation.facturas;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import sistema.logic.Factura;

public class FacturasTableModel extends AbstractTableModel implements TableModel {
    String[] cols ={"Numero","Descripcion","Monto","Cliente" };
    List<Factura> rows;

    public  FacturasTableModel(List<Factura> rows){
        this.rows=rows;
    }

    public int getColumnCount() {
        return 4;
    }

    public String getColumnName(int col){
        return cols[col];
    }

    public int getRowCount() {
        return rows.size();
    }
    
    public Object getValueAt(int row, int col) {
        Factura f = rows.get(row);
        switch (col){
            case 0: return f.getNumero();
            case 1:return f.getDescripción();
            case 2:return f.getMonto();
            case 3:return f.getCliente().getNombre();
            default: return "";
        }
    }    
}

