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
import sistema.logic.Cliente;

public class ClienteDao {
    Database db;
    
    public ClienteDao(){
        db= Database.instance();
    }

    public void create(Cliente c) throws Exception{
        String sql="insert into cliente (cedula, nombre, telefono) "+
                "values(?,?,?)";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, c.getCedula());
        stm.setString(2, c.getNombre());
        stm.setString(3, c.getTelefono());
      
        int count=db.executeUpdate(stm);
        if (count==0){
            throw new Exception("Cliente ya existe");
        }
    }
    
    public Cliente read(String cedula) throws Exception{
        String sql="select * from Cliente c where cedula=?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, cedula);
        ResultSet rs =  db.executeQuery(stm);
        if (rs.next()) {
            Cliente c = from(rs, "c"); 
            return c;
        }
        else{
            throw new Exception ("Cliente no Existe");
        }
    }
    public void update(Cliente c) throws Exception{
        String sql="update cliebte set nombre=?, telefono=? "+
                "where cedula=?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, c.getNombre());
        stm.setString(2, c.getTelefono());
        stm.setString(3,c.getCedula());       
        int count=db.executeUpdate(stm);
        if (count==0){
            throw new Exception("Cliente no existe");
        }        
    }

    public void delete(Cliente c) throws Exception{
        String sql="delete from cliente where cedula=?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, c.getCedula());
        int count=db.executeUpdate(stm);
        if (count==0){
            throw new Exception("Cliebte no existe");
        }
    }
    
    
    public List<Cliente> findAll(){
        List<Cliente> resultado=new ArrayList<>();
        try {
            String sql="select * from Cliente c";
            PreparedStatement stm = db.prepareStatement(sql);
            ResultSet rs =  db.executeQuery(stm);
            Cliente c;
            while (rs.next()) {
                c = from(rs, "c"); 
                resultado.add(c);
            }
        } catch (SQLException ex) { }
        return resultado;        
    }

    public List<Cliente> findByCedula(String cedula){
        List<Cliente> resultado = new ArrayList<>();
        try {
            String sql="select * from cliente c "+
                    "where c.cedula like ?";            
            PreparedStatement stm = db.prepareStatement(sql);
            stm.setString(1, cedula+"%");
            ResultSet rs =  db.executeQuery(stm); 
            Cliente c;
            while (rs.next()) {
                c = from(rs, "c"); 
                resultado.add(c);
            }
        } catch (SQLException ex) {  }
        return resultado;
    }
    
    public Cliente from(ResultSet rs, String alias){
        try {
            Cliente c= new Cliente();
            c.setCedula(rs.getString(alias+".cedula"));
            c.setNombre(rs.getString(alias+".nombre"));
            c.setTelefono(rs.getString(alias+".telefono"));
            return c;
        } catch (SQLException ex) {
            return null;
        }
    }    
}
