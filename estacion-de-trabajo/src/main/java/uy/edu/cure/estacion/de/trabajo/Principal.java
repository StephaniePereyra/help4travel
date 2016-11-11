/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.cure.estacion.de.trabajo;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import java.awt.Image;
import java.awt.Graphics;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.ImageIcon;
import uy.edu.cure.servidor.central.lib.jeringa.Jeringa;
import uy.edu.cure.servidor.central.lib.*;
import uy.edu.cure.servidor.central.lib.jeringa.JeringaInjector;
import uy.edu.cure.servidor.central.soap.client.UsuarioWS;
import uy.edu.cure.servidor.central.soap.client.UsuarioWSImplService;

/**
 *
 * @author SCN
 */
public class Principal extends javax.swing.JFrame {
    @Jeringa (value = "usuariocontroller")
    private UsuarioControllerImpl usuarioController;
    @Jeringa (value = "serviciocontroller")
    private ServicioControllerImpl servicioController;
    @Jeringa (value = "reservacontroller")
    private ReservaControllerImpl reservaController;
    @Jeringa (value = "promocioncontroller")
    private PromocionControllerImpl promocionController;
    @Jeringa (value = "paiscontroller")
    private PaisControllerImpl paisController;
    @Jeringa (value = "ciudadcontroller")
    private CiudadControllerImpl ciudadController;
    @Jeringa (value = "categoriacontroller")
    private CategoriaControllerImpl categoriaController;
    private UsuarioWSImplService usuarioWSImplService;
    /**
     * Creates new form Principal
     */
    public Principal() {

        try {
            usuarioWSImplService = new UsuarioWSImplService(new URL("http://localhost:8080/servidor-central-webapp/soap/UsuarioWSImplService?wsdl"));
        } catch (MalformedURLException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        UsuarioWS port = usuarioWSImplService.getUsuarioWSImplPort();
        String nick = port.obtenerClienteWS("NickPrueba").getNickName();
        
        System.out.println("--------------------------------------------------");
        System.out.print(nick);

        initComponents();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        try {
            JeringaInjector.getInstance().inyectar(this);
        } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }

        /*/ Clientes de prueba
        usuarioController.crearCliente("nicknameUsuario1", "nombreUsuario1", "apellidoUsuario1", "correo@correo1", 10, 10, 1995, null);
        usuarioController.crearCliente("nicknameUsuario2", "nombreUsuario2", "apellidoUsuario2", "correo@correo2", 11, 11, 1996, null);
        usuarioController.crearCliente("nicknameUsuario3", "nombreUsuario3", "apellidoUsuario3", "correo@correo3", 12, 12, 1997, null);
        //
        // Proveedores de Prueba
        usuarioController.crearProveedor("nicknameProveedor1", "nombreProveedor1", "apellidoProveedor1", "correo@correo4", 7, 7, 1992, "nombreEmpresa1", "enlaceEmpresa1", null);
        usuarioController.crearProveedor("nicknameProveedor2", "nombreProveedor1", "apellidoProveedor2", "correo@correo5", 8, 8, 1993, "nombreEmpresa2", "enlaceEmpresa2", null);
        usuarioController.crearProveedor("nicknameProveedor3", "nombreProveedor1", "apellidoProveedor3", "correo@correo6", 9, 9, 1994, "nombreEmpresa3", "enlaceEmpresa3", null);
        /*/
        // Pais de Prueba
        paisController.crearPais("pais1");
        //
        // Ciudades de Prueba
        ciudadController.crearCiudad("ciudad1", "pais1");
        ciudadController.crearCiudad("ciudad2", "pais1");
        ciudadController.crearCiudad("ciudad3", "pais1");
        ciudadController.crearCiudad("ciudad4", "pais1");
        ciudadController.crearCiudad("ciudad5", "pais1");
        //
        /*/ Categorias de Prueba
        categoriaController.darAltaCategoria("Vuelos", "");
        categoriaController.darAltaCategoria("Empresa", "Vuelos");
        categoriaController.darAltaCategoria("Iberian", "Empresa");
        categoriaController.darAltaCategoria("AmericaAirlines", "Empresa");
        categoriaController.darAltaCategoria("Tipo", "Vuelos");
        categoriaController.darAltaCategoria("LowCost", "Tipo");
        categoriaController.darAltaCategoria("Autos", "");
        categoriaController.darAltaCategoria("Ubicacion", "Autos");
        categoriaController.darAltaCategoria("Playa", "Ubicacion");
        /*/
        // Servicios de prueba
        /*
        servicioController.crearServicio("servicio1", "descripcion1", 750.0, "ciudad1", "ciudad2", "nicknameProveedor1");
        servicioController.obtenerServicio("servicio1", "nicknameProveedor1").setCategorias(categoriaController.obtenerCategoria("AmericaAirlines"));
        servicioController.crearServicio("servicio2", "descripcion2", 1000.5, "ciudad3", "ciudad5", "nicknameProveedor2");
        servicioController.obtenerServicio("servicio2", "nicknameProveedor2").setCategorias(categoriaController.obtenerCategoria("Iberian"));
        servicioController.obtenerServicio("servicio2", "nicknameProveedor2").setCategorias(categoriaController.obtenerCategoria("LowCost"));
        servicioController.crearServicio("servicio3", "descripcion3", 1125.25, "ciudad4", "ciudad1", "nicknameProveedor2");
        servicioController.obtenerServicio("servicio3", "nicknameProveedor2").setCategorias(categoriaController.obtenerCategoria("Iberian"));
        servicioController.crearServicio("servicio4", "descripcion4", 900.0, "ciudad5", "<null>", "nicknameProveedor1");
        servicioController.obtenerServicio("servicio4", "nicknameProveedor1").setCategorias(categoriaController.obtenerCategoria("Playa"));
        servicioController.crearServicio("servicio5", "descripcion5", 1560.75, "ciudad2", "<null>", "nicknameProveedor3");
        servicioController.obtenerServicio("servicio5", "nicknameProveedor3").setCategorias(categoriaController.obtenerCategoria("Playa"));
         */
        //
        // Promociones de prueba
        /*
        promocionController.crearPromocion("promocion1", 10, 1404.675, "nicknameProveedor3");
        promocionController.obtenerPromocion("promocion1", "nicknameProveedor3").setServicios(servicioController.obtenerServicio("servicio5", "nicknameProveedor3"));
        promocionController.crearPromocion("promocion2", 25, 1237.5, "nicknameProveedor1");
        promocionController.obtenerPromocion("promocion2", "nicknameProveedor1").setServicios(servicioController.obtenerServicio("servicio1", "nicknameProveedor1"));
        promocionController.obtenerPromocion("promocion2", "nicknameProveedor1").setServicios(servicioController.obtenerServicio("servicio4", "nicknameProveedor1"));
         */
        //

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ImageIcon icon = new ImageIcon (getClass().getResource("/Fondo/Help4Traveling.png"));
        final Image imagen = icon.getImage();
        ContenedordeForms = new javax.swing.JDesktopPane(){

            public void paintComponent (Graphics g){
                g.drawImage(imagen, 0, 0, getWidth(),getHeight(),this);
            }

        };
        BotonSalir = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        BotonSalir.setText("Salir");
        BotonSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonSalirActionPerformed(evt);
            }
        });

        ContenedordeForms.setLayer(BotonSalir, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout ContenedordeFormsLayout = new javax.swing.GroupLayout(ContenedordeForms);
        ContenedordeForms.setLayout(ContenedordeFormsLayout);
        ContenedordeFormsLayout.setHorizontalGroup(
            ContenedordeFormsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ContenedordeFormsLayout.createSequentialGroup()
                .addContainerGap(335, Short.MAX_VALUE)
                .addComponent(BotonSalir)
                .addContainerGap())
        );
        ContenedordeFormsLayout.setVerticalGroup(
            ContenedordeFormsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ContenedordeFormsLayout.createSequentialGroup()
                .addContainerGap(234, Short.MAX_VALUE)
                .addComponent(BotonSalir)
                .addContainerGap())
        );

        jMenu1.setText("Agregar");

        jMenuItem1.setText("Cliente");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Proveedor");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem5.setText("Servicio");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem5);

        jMenuItem6.setText("Reserva");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem6);

        jMenuItem8.setText("Promocion");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem8);

        jMenuItem11.setText("Categoria");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem11);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Ver Info");

        jMenuItem3.setText("Cliente");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenuItem4.setText("Proveedor");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem4);

        jMenuItem7.setText("Servicio");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem7);

        jMenuItem12.setText("Promocion");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem12);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Actualizar");

        jMenuItem9.setText("Reserva");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem9);

        jMenuItem10.setText("Servicio");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem10);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ContenedordeForms, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ContenedordeForms)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        AltaProveedor altaproveedor = new AltaProveedor();
        altaproveedor.setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        AltaCliente altacliente;
        try {
            altacliente = new AltaCliente();
            altacliente.setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        if (!usuarioController.obtenerCientes().isEmpty()) {
            VerInfoCliente vercliente = new VerInfoCliente();
            vercliente.setVisible(true);
        } else {
            javax.swing.JOptionPane.showMessageDialog(null, "No hay clientes ingresados", "Ver Clientes", 0);
        }
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        if (!usuarioController.obtenerProveedores().isEmpty()) {
            VerInfoProveedor verproveedor = new VerInfoProveedor();
            verproveedor.setVisible(true);
        } else {
            javax.swing.JOptionPane.showMessageDialog(null, "No hay proveedores ingresados", "Ver Proveedores", 0);
        }
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        if (!usuarioController.obtenerProveedores().isEmpty()) {
            if (!categoriaController.obtenerTodosCategorias().isEmpty()) {
                AltaServicio altaservicio;
                try {
                    altaservicio = new AltaServicio();
                    altaservicio.setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                javax.swing.JOptionPane.showMessageDialog(null, "No hay categorias ingresadas", "Alta Servicio", 0);
            }
        } else {
            javax.swing.JOptionPane.showMessageDialog(null, "No hay proveedores ingresados", "Alta Servicio", 0);
        }
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        if (!usuarioController.obtenerCientes().isEmpty()) {
            if (!servicioController.obtenerTodosServicios().isEmpty() || !promocionController.obtenerTodasPromociones().isEmpty()) {
                Reserva reserva = new Reserva();
                reserva.setVisible(true);
            } else {
                javax.swing.JOptionPane.showMessageDialog(null, "No hay servicios/promociones ingresadas", "Alta Reserva", 0);
            }
        } else {
            javax.swing.JOptionPane.showMessageDialog(null, "No hay clientes ingresados", "Alta Reserva", 0);
        }
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        if (!servicioController.obtenerTodosServicios().isEmpty()) {
            VerInfoServicio infoservicio = new VerInfoServicio();
            infoservicio.setVisible(true);
        } else {
            javax.swing.JOptionPane.showMessageDialog(null, "No hay servicios ingresados", "Ver Servicios", 0);
        }
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        if (!servicioController.obtenerTodosServicios().isEmpty()) {
            AltaPromocion altapromocion = new AltaPromocion();
            altapromocion.setVisible(true);
        } else {
            javax.swing.JOptionPane.showMessageDialog(null, "No hay servicios ingresados", "Alta Promocion", 0);
        }
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        if (!reservaController.obtenerTodasReservas().isEmpty()) {
            EstadoReserva estadoReserva = new EstadoReserva();
            estadoReserva.setVisible(true);
        } else {
            javax.swing.JOptionPane.showMessageDialog(null, "No hay reservas ingresadas", "Ver Reserva", 0);
        }
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        if (!servicioController.obtenerTodosServicios().isEmpty()) {
            ActualizarServicio actualizarServicio;
            try {
                actualizarServicio = new ActualizarServicio();
                actualizarServicio.setVisible(true);
            } catch (IOException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            javax.swing.JOptionPane.showMessageDialog(null, "No hay servicios ingresados", "Actualizar Servicio", 0);
        }
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        AltaCategoria categoria = new AltaCategoria();
        categoria.setVisible(true);
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void BotonSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_BotonSalirActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
        if (!promocionController.obtenerTodasPromociones().isEmpty()) {
            VerInfoPromocion verInfoPromocion = new VerInfoPromocion();
            verInfoPromocion.setVisible(true);
        } else {
            javax.swing.JOptionPane.showMessageDialog(null, "No hay promociones ingresadas", "Ver Promociones", 0);
        }
    }//GEN-LAST:event_jMenuItem12ActionPerformed

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
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotonSalir;
    private javax.swing.JDesktopPane ContenedordeForms;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    // End of variables declaration//GEN-END:variables
}
