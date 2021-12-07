/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sistema.logic.Factura;

/**
 *
 * @author jsanchez
 */
public class FacturaDao {
    Database db;
    
    public FacturaDao(){
        db= Database.instance();
    }
    public void create(Factura f) throws Exception{
        String sql="insert into factura (numero, descripcion, monto, cliente) "+
                "values(?,?,?,?)";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, f.getNumero());
        stm.setString(2, f.getDescripción());
        stm.setInt(3, f.getMonto());
        stm.setString(4,f.getCliente().getCedula());
      
        int count=db.executeUpdate(stm);
        if (count==0){
            throw new Exception("Factura ya existe");
        }
    }

    public Factura read(String numero) throws Exception{
        String sql="select * from "+
                "factura f inner join cliente c on f.cliente=c.cedula "+
                "where f.numero=?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, numero);
        ResultSet rs =  db.executeQuery(stm);        
        if (rs.next()) {
            Factura f = from(rs, "f");         
            f.setCliente(new ClienteDao().from(rs, "c"));
            return f;
        }
        else{
            throw new Exception ("Factura no Existe");
        }
    }
    
    public void update(Factura f) throws Exception{
        String sql="update Factura set descripcion=?, monto=?, cliente=? "+
                "where numero=?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, f.getDescripción());
        stm.setInt(2, f.getMonto());
        stm.setString(3,f.getCliente().getCedula());
        stm.setString(4, f.getNumero());        
        int count=db.executeUpdate(stm);
        if (count==0){
            throw new Exception("Factura no existe");
        }        
    }

    public void delete(Factura f) throws Exception{
        String sql="delete from factura where numero=?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, f.getNumero());
        int count=db.executeUpdate(stm);
        if (count==0){
            throw new Exception("Factura no existe");
        }
    }

    public List<Factura> findByNumero(String numero){
        List<Factura> resultado = new ArrayList<>();
        try {
            String sql="select * from "+
                    "factura f inner join cliente c on f.cliente=c.cedula "+
                    "where f.numero like ?";            
            PreparedStatement stm = db.prepareStatement(sql);
            stm.setString(1, numero+"%");
            ResultSet rs =  db.executeQuery(stm); 
            Factura f;
            ClienteDao cDao= new ClienteDao();
            while (rs.next()) {
                f = from(rs, "f");          
                f.setCliente(cDao.from(rs, "c"));
                resultado.add(f);
            }
        } catch (SQLException ex) {  }
        return resultado;
    }
    
    public List<Factura> findByDescripcion(String descripcion){
        List<Factura> resultado = new ArrayList<>();
        try {
            String sql="select * from "+
                    "factura f inner join cliente c on f.cliente=c.cedula "+
                    "where f.descripcion like ?";            
            PreparedStatement stm = db.prepareStatement(sql);
            stm.setString(1, "%"+descripcion+"%");
            ResultSet rs =  db.executeQuery(stm); 
            Factura f;
            ClienteDao cDao= new ClienteDao();
            while (rs.next()) {
                f = from(rs, "f");          
                f.setCliente(cDao.from(rs, "c"));
                resultado.add(f);
            }
        } catch (SQLException ex) {  }
        return resultado;
    }
    
    private Factura from(ResultSet rs, String alias){    
        try {
            ClienteDao cDao = new ClienteDao();
            Factura f= new Factura();
            f.setNumero(rs.getString(alias+".numero"));
            f.setDescripción(rs.getString(alias+".descripcion"));
            f.setMonto(rs.getInt(alias+".monto"));
            return f;
        } catch (SQLException ex) {
            return null;
        }
    }
}
