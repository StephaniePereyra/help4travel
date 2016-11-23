/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.cure.estacion.de.trabajo;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import uy.edu.cure.servidor.central.dto.Promocion;
import uy.edu.cure.servidor.central.dto.Servicio;

import uy.edu.cure.servidor.central.soap.client.UsuarioWS;
import uy.edu.cure.servidor.central.soap.client.UsuarioWSImplService;
import uy.edu.cure.servidor.central.soap.client.PromocionWS;
import uy.edu.cure.servidor.central.soap.client.PromocionWSImplService;
import uy.edu.cure.servidor.central.soap.client.ReservaWS;
import uy.edu.cure.servidor.central.soap.client.ReservaWSImplService;
import uy.edu.cure.servidor.central.soap.client.ServicioWS;
import uy.edu.cure.servidor.central.soap.client.ServicioWSImplService;

/**
 *
 * @author juan
 */
public class Reserva extends javax.swing.JFrame {

    /**
     * Creates new form Reserva
     */
    List<Servicio> reservaServicios;
    List<Promocion> reservaPromocion;
    DefaultListModel listServicios;
    DefaultListModel listPromocion;
    DefaultListModel listReserva;
    DefaultListModel listServiciosDePromo;
    DefaultListModel listClientes;
    Integer nroReserva;
    Converter convertidor;

    private List<String> serviciosAux;
    private List<String> promocionesAux;
    private String clienteAux;
    private String separador;

    ReservaWSImplService reservaWSImplService;
    ReservaWS portReserva;
    ServicioWSImplService servicioWSImplService;
    ServicioWS portServicio;
    PromocionWSImplService promocionWSImplService;
    PromocionWS portPromocion;
    UsuarioWSImplService usuarioWSImplService;
    UsuarioWS portUsuario;

    public Reserva() {
        separador = " // ";
        
        reservaServicios = new ArrayList<Servicio>();
        reservaPromocion = new ArrayList<>();
        listServicios = new DefaultListModel();
        listPromocion = new DefaultListModel();
        listReserva = new DefaultListModel();
        listServiciosDePromo = new DefaultListModel();
        listClientes = new DefaultListModel();
        serviciosAux = new ArrayList<>();
        promocionesAux = new ArrayList<>();

        try {
            reservaWSImplService = new ReservaWSImplService(new URL("http://localhost:8080/servidor-central-webapp/soap/ReservaWSImplService?wsdl"));
        } catch (MalformedURLException ex) {

        }
        portReserva = reservaWSImplService.getReservaWSImplPort();

        try {
            servicioWSImplService = new ServicioWSImplService(new URL("http://localhost:8080/servidor-central-webapp/soap/ServicioWSImplService?wsdl"));
        } catch (MalformedURLException ex) {
            //Logger.getLogger(VerInfoProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
        portServicio = servicioWSImplService.getServicioWSImplPort();

        try {
            promocionWSImplService = new PromocionWSImplService(new URL("http://localhost:8080/servidor-central-webapp/soap/PromocionWSImplService?wsdl"));
        } catch (MalformedURLException ex) {
            //Logger.getLogger(VerInfoPromocion.class.getName()).log(Level.SEVERE, null, ex);
        }
        portPromocion = promocionWSImplService.getPromocionWSImplPort();

        try {
            usuarioWSImplService = new UsuarioWSImplService(new URL("http://localhost:8080/servidor-central-webapp/soap/UsuarioWSImplService?wsdl"));
        } catch (MalformedURLException ex) {
            //Logger.getLogger(Reserva.class.getName()).log(Level.SEVERE, null, ex);
        }
        portUsuario = usuarioWSImplService.getUsuarioWSImplPort();

        initComponents();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        for (int i = 0; i < portServicio.obtenerTodosServiciosWS().size(); i++) {
            listServicios.add(i, portServicio.obtenerTodosServiciosWS().get(i).getNombre());
        }
        listaServicio.setModel(listServicios);
        for (int i = 0; i < portPromocion.obtenerTodasPromociones().size(); i++) {
            listPromocion.add(i, portPromocion.obtenerTodasPromociones().get(i).getNombre());
        }
        listaPromocion.setModel(listPromocion);

        for (int i = 0; i < portUsuario.obtenerTodosClientes().size(); i++) {
            listClientes.addElement(portUsuario.obtenerTodosClientes().get(i).getNickName());
        }
        listaClientes.setModel(listClientes);
        listaReserva.setModel(listReserva);
        serviciosDePromo.setModel(listServiciosDePromo);

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
        listaServicio = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblNombreServicio = new javax.swing.JLabel();
        lblDescripcioServicio = new javax.swing.JLabel();
        lblPrecioServicio = new javax.swing.JLabel();
        lblOrigen = new javax.swing.JLabel();
        lblDestino = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblProveedorServicio = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listaPromocion = new javax.swing.JList<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lblNombrePromocion = new javax.swing.JLabel();
        lblDocPromocion = new javax.swing.JLabel();
        lblPrecioPromocion = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        lblProveedorPromocion = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        serviciosDePromo = new javax.swing.JList<>();
        reservaServicio = new javax.swing.JButton();
        reservarPromocion = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        listaReserva = new javax.swing.JList<>();
        Confirmar = new javax.swing.JButton();
        Cancelar = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        listaClientes = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        listaServicio.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        listaServicio.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listaServicioValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(listaServicio);

        jLabel1.setText("Nombre");

        jLabel2.setText("Descripcion");

        jLabel3.setText("Precio");

        jLabel4.setText("Origen");

        jLabel5.setText("Destino");

        jLabel6.setText("Proveedor");

        listaPromocion.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        listaPromocion.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listaPromocionValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(listaPromocion);

        jLabel7.setText("Promocion");

        jLabel8.setText("Nombre");

        jLabel9.setText("Doc.");

        jLabel10.setText("Precio");

        jLabel11.setText("Proveedor");

        serviciosDePromo.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(serviciosDePromo);

        reservaServicio.setText("Reservar Servicio");
        reservaServicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reservaServicioActionPerformed(evt);
            }
        });

        reservarPromocion.setText("Reservar Promocion");
        reservarPromocion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reservarPromocionActionPerformed(evt);
            }
        });

        jLabel12.setText("Servicio");

        listaReserva.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane4.setViewportView(listaReserva);

        Confirmar.setText("Confirmar");
        Confirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConfirmarActionPerformed(evt);
            }
        });

        Cancelar.setText("Cancelar");
        Cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelarActionPerformed(evt);
            }
        });

        listaClientes.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane5.setViewportView(listaClientes);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                    .addComponent(jScrollPane5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblNombreServicio))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblDescripcioServicio))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblPrecioServicio))
                    .addComponent(jLabel12)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblOrigen))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblDestino))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblProveedorServicio))
                    .addComponent(reservaServicio))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 124, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Confirmar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(reservarPromocion)
                            .addComponent(jLabel7)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel10)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(lblPrecioPromocion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel9)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(lblDocPromocion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel11)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(lblProveedorPromocion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel8)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(lblNombrePromocion, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel12)
                            .addGap(4, 4, 4)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel1)
                                .addComponent(lblNombreServicio))
                            .addGap(11, 11, 11)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2)
                                .addComponent(lblDescripcioServicio))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel3)
                                .addComponent(lblPrecioServicio))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel4)
                                .addComponent(lblOrigen))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel5)
                                .addComponent(lblDestino))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel6)
                                .addComponent(lblProveedorServicio)))
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel7)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel8)
                                .addComponent(lblNombrePromocion, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblDocPromocion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel10)
                                .addComponent(lblPrecioPromocion, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel11)
                                .addComponent(lblProveedorPromocion, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(reservarPromocion)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(reservaServicio)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(Confirmar)
                        .addGap(18, 18, 18)
                        .addComponent(Cancelar))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void listaServicioValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listaServicioValueChanged
        int indice = listaServicio.getSelectedIndex();
        lblNombreServicio.setText(portServicio.obtenerTodosServiciosWS().get(indice).getNombre());
        lblDescripcioServicio.setText(portServicio.obtenerTodosServiciosWS().get(indice).getDescripcion());
        lblPrecioServicio.setText(String.valueOf(portServicio.obtenerTodosServiciosWS().get(indice).getPrecio()));

        lblOrigen.setText(portServicio.obtenerTodosServiciosWS().get(indice).getOrigen().getNombre());

        lblProveedorServicio.setText(portServicio.obtenerTodosServiciosWS().get(indice).getProveedor().getNickName());

        lblDestino.setText("No hay ciudad de destino");
        if (portServicio.obtenerTodosServiciosWS().get(indice).getDestino().getNombre() == null) {
            lblDestino.setText("No hay ciudad de destino");
        } else {
            lblDestino.setText(portServicio.obtenerTodosServiciosWS().get(indice).getDestino().getNombre());
        }

    }//GEN-LAST:event_listaServicioValueChanged

    private void listaPromocionValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listaPromocionValueChanged
        int index = listaPromocion.getSelectedIndex();
        lblNombrePromocion.setText(portPromocion.obtenerTodasPromociones().get(index).getNombre());
        lblDocPromocion.setText(String.valueOf(portPromocion.obtenerTodasPromociones().get(index).getDescuento()));
        lblPrecioPromocion.setText(String.valueOf(portPromocion.obtenerTodasPromociones().get(index).getPrecioTotal()));
        lblProveedorPromocion.setText(portPromocion.obtenerTodasPromociones().get(index).getProveedor().getNickName());
        listServiciosDePromo = new DefaultListModel();
        serviciosDePromo.setModel(listServiciosDePromo);

        for (int i = 0; i < portPromocion.obtenerServiciosPromoWS(listaPromocion.getSelectedValue(), lblProveedorPromocion.getText()).size(); i++) {
            listServiciosDePromo.addElement(portPromocion.obtenerServiciosPromoWS(listaPromocion.getSelectedValue(), lblProveedorPromocion.getText()).get(i).getNombre());
        }
        serviciosDePromo.setModel(listServiciosDePromo);

    }//GEN-LAST:event_listaPromocionValueChanged

    private void reservaServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reservaServicioActionPerformed
        if (listaServicio.getSelectedValue() != null) {
            int index = listaServicio.getSelectedIndex();
            convertidor = new Converter();
            reservaServicios.add(convertidor.convertirServicio(portServicio.obtenerTodosServiciosWS().get(index)));
            listReserva.addElement(lblNombreServicio.getText());
            
            serviciosAux.add(listaServicio.getSelectedValue() + separador + lblProveedorServicio.getText());
            listaReserva.setModel(listReserva);
        } else {
            // Mensage de error por si presiono el boton sin seleccionar servicio
        }
    }//GEN-LAST:event_reservaServicioActionPerformed

    private void reservarPromocionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reservarPromocionActionPerformed
        if (listaPromocion.getSelectedValue() != null) {
            int index = listaPromocion.getSelectedIndex();
            convertidor = new Converter();
            reservaPromocion.add(convertidor.convertirPromocion(portPromocion.obtenerTodasPromociones().get(index)));
            listReserva.addElement(lblNombrePromocion.getText());
            promocionesAux.add(lblNombrePromocion.getText() + separador + lblProveedorPromocion.getText());
            listaReserva.setModel(listReserva);
        } else {
            // Mensage de error por si presiono el boton sin seleccionar 
        }
    }//GEN-LAST:event_reservarPromocionActionPerformed

    private void ConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConfirmarActionPerformed
        // TODO add your handling code here:

        if (listaClientes.getSelectedValue() != null) {
            //int indiceUsuario = listaClientes.getSelectedIndex();
            promocionesAux = promocionesAux;
            serviciosAux = serviciosAux;
            clienteAux = listaClientes.getSelectedValue();

            try {
                reservaWSImplService = new ReservaWSImplService(new URL("http://localhost:8080/servidor-central-webapp/soap/ReservaWSImplService?wsdl"));
            } catch (MalformedURLException ex) {

            }
            portReserva = reservaWSImplService.getReservaWSImplPort();

            boolean crearReserva = portReserva.crearReservaWS(promocionesAux, serviciosAux, clienteAux);
            if (crearReserva) {
                JOptionPane.showMessageDialog(null, "Reserva creada");
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Seleccione al menos una promocion o servicio");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione un cliente");
        }

    }//GEN-LAST:event_ConfirmarActionPerformed

    private void CancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelarActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_CancelarActionPerformed

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
            java.util.logging.Logger.getLogger(Reserva.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Reserva.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Reserva.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Reserva.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Reserva().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Cancelar;
    private javax.swing.JButton Confirmar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel lblDescripcioServicio;
    private javax.swing.JLabel lblDestino;
    private javax.swing.JLabel lblDocPromocion;
    private javax.swing.JLabel lblNombrePromocion;
    private javax.swing.JLabel lblNombreServicio;
    private javax.swing.JLabel lblOrigen;
    private javax.swing.JLabel lblPrecioPromocion;
    private javax.swing.JLabel lblPrecioServicio;
    private javax.swing.JLabel lblProveedorPromocion;
    private javax.swing.JLabel lblProveedorServicio;
    private javax.swing.JList<String> listaClientes;
    private javax.swing.JList<String> listaPromocion;
    private javax.swing.JList<String> listaReserva;
    private javax.swing.JList<String> listaServicio;
    private javax.swing.JButton reservaServicio;
    private javax.swing.JButton reservarPromocion;
    private javax.swing.JList<String> serviciosDePromo;
    // End of variables declaration//GEN-END:variables
}
