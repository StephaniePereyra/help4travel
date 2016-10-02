/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.cure.estacion.de.trabajo;

import java.awt.event.KeyEvent;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import uy.edu.cure.servidor.central.lib.*;
import uy.edu.cure.servidor.central.lib.jeringa.Jeringa;
import uy.edu.cure.servidor.central.lib.jeringa.JeringaInjector;

/**
 *
 * @author Rodrigo "Lobo Plateado" Pérez
 */
public class AltaPromocion extends javax.swing.JFrame {

    @Jeringa (value = "usuariocontroller")
    private UsuarioController usuarioController;
    @Jeringa (value = "serviciocontroller")
    private ServicioController servicioController;
    @Jeringa (value = "promocioncontroller")
    private PromocionController promocionController;

    /**
     * Creates new form AltaPromocion
     */
    public AltaPromocion() {
        initComponents();
        setLocationRelativeTo(null);

          try {
            JeringaInjector.getInstance().inyectar(this);
        } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }
        
        DefaultListModel listaProveedores = new DefaultListModel();
        for (int i = 0; i < usuarioController.obtenerProveedores().size(); i++) {
            listaProveedores.addElement(usuarioController.obtenerProveedores().get(i).getNickName());
        }
        listProveedores.setModel(listaProveedores);
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
        listProveedores = new javax.swing.JList<>();
        labelProveedores = new javax.swing.JLabel();
        labelNombrePromocion = new javax.swing.JLabel();
        textFieldNombrePromocion = new javax.swing.JTextField();
        labelServiciosDisponibles = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listServiciosDisponibles = new javax.swing.JList<>();
        buttonAddServicio = new javax.swing.JButton();
        buttonRemoveServicio = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        listServiciosPromocion = new javax.swing.JList<>();
        labelDescuento = new javax.swing.JLabel();
        textFieldDescuento = new javax.swing.JTextField();
        buttonAtras = new javax.swing.JButton();
        buttonCrear = new javax.swing.JButton();
        labelMessageError = new javax.swing.JLabel();
        buttonPrecioTotal = new javax.swing.JButton();
        labelServiciosPromocion = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        listProveedores.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listProveedoresValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(listProveedores);

        labelProveedores.setText("Proveedores: ");

        labelNombrePromocion.setText("Nombre promocion: ");

        labelServiciosDisponibles.setText("Servicios disponibles: ");

        jScrollPane2.setViewportView(listServiciosDisponibles);

        buttonAddServicio.setText("> >");
        buttonAddServicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddServicioActionPerformed(evt);
            }
        });

        buttonRemoveServicio.setText("< <");
        buttonRemoveServicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRemoveServicioActionPerformed(evt);
            }
        });

        jScrollPane3.setViewportView(listServiciosPromocion);

        labelDescuento.setText("Descuento: ");

        textFieldDescuento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textFieldDescuentoKeyTyped(evt);
            }
        });

        buttonAtras.setText("Atras");
        buttonAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAtrasActionPerformed(evt);
            }
        });

        buttonCrear.setText("Crear");
        buttonCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCrearActionPerformed(evt);
            }
        });

        labelMessageError.setForeground(new java.awt.Color(255, 0, 0));

        buttonPrecioTotal.setText("Calcular Precio Total");
        buttonPrecioTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPrecioTotalActionPerformed(evt);
            }
        });

        labelServiciosPromocion.setText("Servicios promocion: ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelProveedores)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelNombrePromocion)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labelDescuento)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textFieldDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(buttonPrecioTotal)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(buttonAddServicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(buttonRemoveServicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(textFieldNombrePromocion, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labelServiciosDisponibles)
                                .addGap(117, 117, 117)
                                .addComponent(labelServiciosPromocion)))
                        .addGap(0, 46, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelMessageError, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(buttonCrear)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buttonAtras)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelProveedores)
                    .addComponent(labelNombrePromocion))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(textFieldNombrePromocion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelServiciosDisponibles)
                            .addComponent(labelServiciosPromocion))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(buttonAddServicio)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonRemoveServicio)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelDescuento)
                            .addComponent(textFieldDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buttonPrecioTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(buttonAtras)
                        .addComponent(buttonCrear))
                    .addComponent(labelMessageError, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCrearActionPerformed
        labelMessageError.setText(" ");
        String nombrePromocion = textFieldNombrePromocion.getText();
        if (!textFieldDescuento.getText().isEmpty()) {
            int descuento = Integer.parseInt(textFieldDescuento.getText());
            String nickProveedor = listProveedores.getSelectedValue();
            List<String> servicios = new ArrayList<String>();
            for (int i = 0; i < listServiciosPromocion.getModel().getSize(); i++) {
                servicios.add(listServiciosPromocion.getModel().getElementAt(i));
            }
            String resultado = promocionController.crearPromocion(nombrePromocion, descuento, nickProveedor, servicios);
            if (resultado.equals("OK")) {
                javax.swing.JOptionPane.showMessageDialog(null, "Promocion creada exitosamente", "Alta Promocion", 1);
                this.dispose();
            } else {
                javax.swing.JOptionPane.showMessageDialog(null, resultado, "Alta Promocion", 0);
            }
        } else {
            javax.swing.JOptionPane.showMessageDialog(null, "Descuento no puede quedar vacio", "Alta Promocion", 0);
        }
    }//GEN-LAST:event_buttonCrearActionPerformed

    private void buttonAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAtrasActionPerformed
        this.dispose();
    }//GEN-LAST:event_buttonAtrasActionPerformed

    private void textFieldDescuentoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textFieldDescuentoKeyTyped
        char tecla = evt.getKeyChar();
        if (!(Character.isDigit(tecla) || (tecla == KeyEvent.VK_BACK_SPACE) || (tecla == KeyEvent.VK_DELETE))) {
            evt.consume();
        }
    }//GEN-LAST:event_textFieldDescuentoKeyTyped

    private void listProveedoresValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listProveedoresValueChanged
        int indiceProveedor = listProveedores.getSelectedIndex();
        DefaultListModel listaServicios = new DefaultListModel();
        for (int i = 0; i < usuarioController.obtenerProveedores().get(indiceProveedor).getServicios().size(); i++) {
            listaServicios.addElement(usuarioController.obtenerProveedores().get(indiceProveedor).getServicios().get(i).getNombre());
        }
        listServiciosDisponibles.setModel(listaServicios);
        listServiciosPromocion.setModel(new DefaultListModel());
    }//GEN-LAST:event_listProveedoresValueChanged

    private void buttonAddServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddServicioActionPerformed
        labelMessageError.setText(" ");
        boolean existeServicio = false;
        DefaultListModel listaServicios = new DefaultListModel();
        if (listServiciosDisponibles.getSelectedValue() != null) {
            if (listServiciosPromocion.getModel().getSize() == 0) {
                listaServicios.addElement(listServiciosDisponibles.getSelectedValue());
                listServiciosPromocion.setModel(listaServicios);
            } else {
                listaServicios = (DefaultListModel) listServiciosPromocion.getModel();
                for (int i = 0; i < listaServicios.getSize(); i++) {
                    if (listaServicios.get(i).equals(listServiciosDisponibles.getSelectedValue())) {
                        existeServicio = true;
                    }
                }
                if (!existeServicio) {
                    listaServicios.addElement(listServiciosDisponibles.getSelectedValue());
                    listServiciosPromocion.setModel(listaServicios);
                } else {
                    labelMessageError.setText("Ya se agrego ese servicio");
                }
            }
        } else {
            labelMessageError.setText("Seleccione un servicio para agregar");
        }
    }//GEN-LAST:event_buttonAddServicioActionPerformed

    private void buttonRemoveServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRemoveServicioActionPerformed
        labelMessageError.setText(" ");
        if (listServiciosPromocion.getSelectedValue() != null) {
            DefaultListModel listaServicios = (DefaultListModel) listServiciosPromocion.getModel();
            listaServicios.remove(listServiciosPromocion.getSelectedIndex());
            listServiciosPromocion.setModel(listaServicios);
        } else {
            labelMessageError.setText("Seleccione servicio a quitar");
        }
    }//GEN-LAST:event_buttonRemoveServicioActionPerformed

    private void buttonPrecioTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPrecioTotalActionPerformed
        labelMessageError.setText(" ");
        if (listServiciosPromocion.getModel().getSize() != 0) {
            DefaultListModel listaServicios = (DefaultListModel) listServiciosPromocion.getModel();
            if (!textFieldDescuento.getText().isEmpty()) {
                int descuentoAuxiliar = Integer.parseInt(textFieldDescuento.getText());
                if (descuentoAuxiliar < 100 && descuentoAuxiliar > 0) {
                    double precioTotal = 0;
                    for (int i = 0; i < listaServicios.getSize(); i++) {
                        precioTotal = precioTotal + servicioController.obtenerServicio(listaServicios.get(i).toString(), listProveedores.getSelectedValue()).getPrecio();
                    }
                    precioTotal = precioTotal - (precioTotal * descuentoAuxiliar) / 100;
                    javax.swing.JOptionPane.showMessageDialog(null, String.valueOf(precioTotal), "Precio total", 1);
                } else {
                    labelMessageError.setText("Descuento invalido");
                }
            } else {
                labelMessageError.setText("Ingrese un descuento");
            }
        } else {
            labelMessageError.setText("No hay servicios en la promocion");
        }
    }//GEN-LAST:event_buttonPrecioTotalActionPerformed

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
            java.util.logging.Logger.getLogger(AltaPromocion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AltaPromocion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AltaPromocion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AltaPromocion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AltaPromocion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAddServicio;
    private javax.swing.JButton buttonAtras;
    private javax.swing.JButton buttonCrear;
    private javax.swing.JButton buttonPrecioTotal;
    private javax.swing.JButton buttonRemoveServicio;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel labelDescuento;
    private javax.swing.JLabel labelMessageError;
    private javax.swing.JLabel labelNombrePromocion;
    private javax.swing.JLabel labelProveedores;
    private javax.swing.JLabel labelServiciosDisponibles;
    private javax.swing.JLabel labelServiciosPromocion;
    private javax.swing.JList<String> listProveedores;
    private javax.swing.JList<String> listServiciosDisponibles;
    private javax.swing.JList<String> listServiciosPromocion;
    private javax.swing.JTextField textFieldDescuento;
    private javax.swing.JTextField textFieldNombrePromocion;
    // End of variables declaration//GEN-END:variables
}
