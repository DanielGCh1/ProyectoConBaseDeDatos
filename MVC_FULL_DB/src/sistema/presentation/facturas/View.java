package sistema.presentation.facturas;

import java.io.IOException;
import java.util.Observable;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import sistema.logic.Cliente;
import sistema.logic.Factura;

public class View extends javax.swing.JFrame implements java.util.Observer {

//**************  MVC ***********
    Controller controller;
    Model model;
    
    public void setController(Controller controller){
        this.controller=controller;
    }

    public Controller getController() {
        return controller;
    }
    
    public void setModel(Model model){
        this.model=model;
         model.addObserver(this);
    }

    public Model getModel() {
        return model;
    }
    
    @Override
    public void update(Observable o, Object arg) {
        Factura factura = model.getFactura();
        numero.setText(factura.getNumero());
        descripcion.setText(factura.getDescripción());
        monto.setText(""+factura.getMonto());
        cliente.setModel(new DefaultComboBoxModel(model.getClientes().toArray()));
        cliente.setSelectedItem(factura.getCliente());
        facturas.setModel(new FacturasTableModel(model.getFacturas()));
        
    }
//************** END MVC ***********
    
    public View() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        numero = new javax.swing.JTextField();
        descripcion = new javax.swing.JTextField();
        monto = new javax.swing.JTextField();
        consultar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        facturas = new javax.swing.JTable();
        agregar = new javax.swing.JButton();
        listar = new javax.swing.JButton();
        regresar = new javax.swing.JButton();
        cliente = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Facturas");
        setMinimumSize(new java.awt.Dimension(600, 540));
        setPreferredSize(null);
        setResizable(false);
        setSize(new java.awt.Dimension(600, 500));
        getContentPane().setLayout(null);

        jLabel1.setText("NUMERO");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(20, 15, 90, 20);

        jLabel2.setText("DESCRIPCION");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(20, 61, 120, 20);

        jLabel3.setText("MONTO");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(20, 105, 57, 20);
        getContentPane().add(numero);
        numero.setBounds(130, 10, 166, 26);
        getContentPane().add(descripcion);
        descripcion.setBounds(130, 60, 207, 26);
        getContentPane().add(monto);
        monto.setBounds(130, 100, 146, 26);

        consultar.setText("Consultar");
        consultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consultarActionPerformed(evt);
            }
        });
        getContentPane().add(consultar);
        consultar.setBounds(320, 10, 99, 29);

        facturas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        facturas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                facturasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(facturas);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(20, 260, 520, 170);

        agregar.setText("Agregar");
        agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarActionPerformed(evt);
            }
        });
        getContentPane().add(agregar);
        agregar.setBounds(130, 210, 93, 29);

        listar.setText("Listar");
        listar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listarActionPerformed(evt);
            }
        });
        getContentPane().add(listar);
        listar.setBounds(460, 10, 71, 29);

        regresar.setText("Regresar");
        regresar.setActionCommand("");
        regresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                regresarActionPerformed(evt);
            }
        });
        getContentPane().add(regresar);
        regresar.setBounds(400, 460, 130, 29);

        getContentPane().add(cliente);
        cliente.setBounds(130, 150, 150, 26);

        jLabel4.setText("CLIENTE");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(20, 160, 80, 20);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void consultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consultarActionPerformed
        controller.facturaGet(numero.getText());
    }//GEN-LAST:event_consultarActionPerformed

    private void listarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listarActionPerformed
        controller.facturaSearch(numero.getText());
    }//GEN-LAST:event_listarActionPerformed

    private void facturasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_facturasMouseClicked
        // TODO add your handling code here:
        if(evt.getClickCount()==2){
            controller.facturaEdit(facturas.getSelectedRow());
        }
    }//GEN-LAST:event_facturasMouseClicked

    private void agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarActionPerformed
        // TODO add your handling code here:
        controller.facturaAdd(new Factura(numero.getText(),descripcion.getText(),Integer.parseInt(monto.getText()),(Cliente)cliente.getSelectedItem())); 
    }//GEN-LAST:event_agregarActionPerformed

    private void regresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_regresarActionPerformed
        // TODO add your handling code here:
        controller.hide();
    }//GEN-LAST:event_regresarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton agregar;
    private javax.swing.JComboBox<Cliente> cliente;
    private javax.swing.JButton consultar;
    private javax.swing.JTextField descripcion;
    private javax.swing.JTable facturas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton listar;
    private javax.swing.JTextField monto;
    private javax.swing.JTextField numero;
    private javax.swing.JButton regresar;
    // End of variables declaration//GEN-END:variables

 
}
