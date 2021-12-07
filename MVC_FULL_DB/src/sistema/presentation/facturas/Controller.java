package sistema.presentation.facturas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import sistema.Application;
import sistema.logic.Factura;
import sistema.logic.Service;

public class Controller {
    Model model;
    View view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
        // invoke Model sets for initialization before linking to the view
        // problably get the data from Service
        model.setFactura(new Factura("","",0));
        model.setFacturas(new ArrayList<>());
        model.setClientes(Service.instance().clienteAll());
        
        view.setModel(model);
        view.setController(this);
    }
    
    public void show(){
        model.setClientes(Service.instance().clienteAll());
        model.commit();
        this.view.setVisible(true);
    }
    
    public void hide(){
        this.view.setVisible(false);
        Application.PRINCIPAL.show();
    }    
    
    // Controller methods that respond to View events
    // probably invoke methods from Service,
    // and set data to Model, which in turn causes the View to update 
    
    public void facturaGet(String numero){
        try {
            Factura factura = Service.instance().facturaGet(numero);
            model.setFactura(factura);
            model.setFacturas(Arrays.asList(factura));
            model.commit();
        } catch (Exception ex) {
            model.setFactura(new Factura());
            model.setFacturas(new ArrayList<>());
            model.commit();
        }
    }
    
    public void  facturaSearch(String numero){
        List<Factura> facturas= Service.instance().facturaSearch(numero);
        model.setFactura(new Factura(numero,"",0));
        model.setFacturas(facturas);
        model.commit();
    }
    
    public void facturaEdit(int row){
        Factura factura = model.getFacturas().get(row);
        model.setFactura(factura);
        model.commit();
    }
    
    public void facturaAdd(Factura factura){
        try {
            Service.instance().facturaAdd(factura);
            model.setFactura(new Factura("","",0));
            model.setFacturas(Arrays.asList(factura));
            model.commit();
        } catch (Exception ex) {
            
        }
        
    }
    
    
}
