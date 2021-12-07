package sistema;

public class Application {

    
    public static void main(String[] args) {
        sistema.presentation.clientes.Model modelClientes=new sistema.presentation.clientes.Model() ;
        sistema.presentation.clientes.View viewClientes = new sistema.presentation.clientes.View();
        sistema.presentation.clientes.Controller controllerClientes = new sistema.presentation.clientes.Controller(modelClientes,viewClientes);
        CLIENTES = controllerClientes;
        
        sistema.presentation.facturas.Model modelFacturas=new sistema.presentation.facturas.Model() ;
        sistema.presentation.facturas.View viewFacturas = new sistema.presentation.facturas.View();
        sistema.presentation.facturas.Controller controllerFacturas = new sistema.presentation.facturas.Controller(modelFacturas,viewFacturas);
        FACTURAS = controllerFacturas;       
        
        sistema.presentation.principal.Model modelPrincipal=new sistema.presentation.principal.Model() ;
        sistema.presentation.principal.View viewPrincipal = new sistema.presentation.principal.View();
        sistema.presentation.principal.Controller controllerPrincipal = new sistema.presentation.principal.Controller(modelPrincipal,viewPrincipal);       
        PRINCIPAL = controllerPrincipal;
        
        PRINCIPAL.show();
    }

    public static sistema.presentation.clientes.Controller CLIENTES;
    public static sistema.presentation.facturas.Controller FACTURAS;
    public static sistema.presentation.principal.Controller PRINCIPAL;

}
