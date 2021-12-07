package sistema.presentation.facturas;

import java.util.List;
import java.util.Observable;
import java.util.Observer;
import sistema.logic.Cliente;
import sistema.logic.Factura;

public class Model extends Observable{
    
    // Model attributes here
    // Model gets and sets here
    
    Factura factura;
    List<Factura> facturas;
    List<Cliente> clientes;

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public List<Factura> getFacturas() {
        return facturas;
    }

    public void setFacturas(List<Factura> facturas) {
        this.facturas = facturas;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    
    @Override
    public synchronized void addObserver(Observer o) {
        super.addObserver(o); 
        this.commit();
    }
    
    public void commit(){
        this.setChanged();
        this.notifyObservers();
    }
    
}











