/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.cure.estacion.de.trabajo;

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import uy.edu.cure.servidor.central.soap.client.ReservaWS;
import uy.edu.cure.servidor.central.soap.client.ReservaWSImplService;


/**
 *
 * @author juan
 */
public class EstadoReserva extends javax.swing.JFrame {

    /**
     * Creates new form EstadoReserva
     */
    private ReservaWS portReserva;
    private ReservaWSImplService reservaWSImplService;
    DefaultListModel listReserva;
    DefaultListModel listServicio;
    DefaultListModel listPromocion;
    
    
    
    public EstadoReserva() {
        listReserva = new DefaultListModel();
        listServicio = new DefaultListModel();
        listPromocion = new DefaultListModel();
        initComponents();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        
          try {
             reservaWSImplService = new ReservaWSImplService(new URL("http://localhost:8080/servidor-central-webapp/soap/ReservaWSImplService?wsdl"));
        } catch (MalformedURLException e) {
            Logger.getLogger(EstadoReserva.class.getName()).log(Level.SEVERE,null,e);
        }
        portReserva = reservaWSImplService.getReservaWSImplPort();
        
        for (int i = 0; i < portReserva.obtenerTodasReservasWS().size(); i++) {
            listReserva.add(i, portReserva.obtenerTodasReservasWS().get(i).getNumero());
        }
        listaReservas.setModel(listReserva);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        listaReservas = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        Numero = new javax.swing.JLabel();
        Fecha = new javax.swing.JLabel();
        Precio = new javax.swing.JLabel();
        Estado = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listaServicios = new javax.swing.JList<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        listaPromocion = new javax.swing.JList<>();
        cancelar = new javax.swing.JButton();
        pagar = new javax.swing.JButton();
        facturar = new javax.swing.JButton();
        salir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        listaReservas.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        listaReservas.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listaReservasValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(listaReservas);

        jLabel1.setText("Numero");

        jLabel2.setText("Fecha");

        jLabel3.setText("Precio");

        jLabel4.setText("Estado");

        listaServicios.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(listaServicios);

        listaPromocion.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(listaPromocion);

        cancelar.setText("Cancelar");
        cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarActionPerformed(evt);
            }
        });

        pagar.setText("Pagar");
        pagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pagarActionPerformed(evt);
            }
        });

        facturar.setText("Facturar");
        facturar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                facturarActionPerformed(evt);
            }
        });

        salir.setText("Salir");
        salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Numero))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Fecha))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Precio))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Estado))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(pagar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(facturar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancelar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(salir)))
                .addContainerGap(55, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(Numero))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(Fecha))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(Precio))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(Estado))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelar)
                    .addComponent(pagar)
                    .addComponent(facturar)
                    .addComponent(salir))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void pagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pagarActionPerformed
         if (!listaReservas.getSelectedValuesList().isEmpty()) {
            int index = listaReservas.getSelectedIndex();
            /*if (reservaController.cambiarEstado(reservaController.obtenerTodasReservas().get(index), "Paga")) {
                JOptionPane.showMessageDialog(null, "Paga");
                this.dispose();
            }
            else {
                JOptionPane.showMessageDialog(null, "Error");
            }*/
        }
        else {
            JOptionPane.showMessageDialog(null, "Error: seleccione una reserva");
        }
    }//GEN-LAST:event_pagarActionPerformed

    private void listaReservasValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listaReservasValueChanged
        int indice = listaReservas.getSelectedIndex();
        Numero.setText(Integer.toString(portReserva.obtenerTodasReservasWS().get(indice).getNumero()));
        int mes = portReserva.obtenerTodasReservasWS().get(indice).getFechaCreacion().getMonth() + 1;
        int año = portReserva.obtenerTodasReservasWS().get(indice).getFechaCreacion().getYear() + 1900;
        Fecha.setText(String.valueOf(portReserva.obtenerTodasReservasWS().get(indice).getFechaCreacion().getDay()) + "/" + String.valueOf(mes) + "/" + String.valueOf(año));
        Precio.setText(Double.toString(portReserva.obtenerTodasReservasWS().get(indice).getPrecio()));
        Estado.setText(portReserva.obtenerTodasReservasWS().get(indice).getEstado());
        listPromocion.clear();
      /*  for (int i = 0; i < portReserva.obtenerTodasReservasWS().get(indice).getPromociones().size(); i++) {
            listPromocion.add(i, portReserva.obtenerTodasReservasWS().get(indice).getPromociones().get(i).getNombre());
        }
        listaPromocion.setModel(listPromocion);
        listServicio.clear();
        for (int i = 0; i < portReserva.obtenerTodasReservasWS().get(indice).getServicios().size(); i++) {
            listServicio.add(i, portReserva.obtenerTodasReservasWS().get(indice).getServicios().get(i).getNombre());
        }*/
        listaServicios.setModel(listServicio);
    }//GEN-LAST:event_listaReservasValueChanged

    private void facturarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_facturarActionPerformed
         if (!listaReservas.getSelectedValuesList().isEmpty()) {
            int index = listaReservas.getSelectedIndex();
            if (portReserva.cambiarEstadoWS(portReserva.obtenerTodasReservasWS().get(index), "Facturada")) {
                JOptionPane.showMessageDialog(null, "Facturada");
                this.dispose();
            }
            else {
                JOptionPane.showMessageDialog(null, "Error");
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "Error: seleccione una reserva");
        }
    }//GEN-LAST:event_facturarActionPerformed

    private void cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarActionPerformed
        if (!listaReservas.getSelectedValuesList().isEmpty()) {
            int index = listaReservas.getSelectedIndex();
            if (portReserva.cambiarEstadoWS(portReserva.obtenerTodasReservasWS().get(index), "Cancelar")) {
                JOptionPane.showMessageDialog(null, "Cancelada");
                this.dispose();
            }
            else {
                JOptionPane.showMessageDialog(null, "Error");
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "Error: seleccione una reserva");
        }
    }//GEN-LAST:event_cancelarActionPerformed

    private void salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirActionPerformed
        this.dispose();
    }//GEN-LAST:event_salirActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(EstadoReserva.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EstadoReserva.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EstadoReserva.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EstadoReserva.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EstadoReserva().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Estado;
    private javax.swing.JLabel Fecha;
    private javax.swing.JLabel Numero;
    private javax.swing.JLabel Precio;
    private javax.swing.JButton cancelar;
    private javax.swing.JButton facturar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JList<String> listaPromocion;
    private javax.swing.JList<String> listaReservas;
    private javax.swing.JList<String> listaServicios;
    private javax.swing.JButton pagar;
    private javax.swing.JButton salir;
    // End of variables declaration//GEN-END:variables
}
