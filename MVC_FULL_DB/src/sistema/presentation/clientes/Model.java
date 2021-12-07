package sistema.presentation.clientes;

import java.util.List;
import java.util.Observable;
import java.util.Observer;
import sistema.logic.Cliente;

public class Model extends Observable{
    
    // Model attributes here
    // Model gets and sets here
    
    Cliente cliente;
    List<Cliente> clientes;

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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











