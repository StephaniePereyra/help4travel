/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.cure.estacion.de.trabajo;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import static jdk.nashorn.tools.ShellFunctions.input;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import uy.edu.cure.servidor.central.dto.*;
import uy.edu.cure.servidor.central.soap.client.CategoriaWS;
import uy.edu.cure.servidor.central.soap.client.CategoriaWSImplService;

import uy.edu.cure.servidor.central.soap.client.PromocionWS;
import uy.edu.cure.servidor.central.soap.client.PromocionWSImplService;
import uy.edu.cure.servidor.central.soap.client.UsuarioWS;
import uy.edu.cure.servidor.central.soap.client.UsuarioWSImplService;
import uy.edu.cure.servidor.central.soap.client.ServicioWS;
import uy.edu.cure.servidor.central.soap.client.ServicioWSImplService;

public class VerInfoServicio extends javax.swing.JFrame {

    private UsuarioWSImplService usuarioWSImplService;
    private UsuarioWS portUsuario;
    private ServicioWSImplService servicioWSImplService;
    private ServicioWS portServicio;
    private PromocionWSImplService promocionWSImplService;
    private PromocionWS portPromocion;
    private CategoriaWSImplService categoriaWSImplService;
    private CategoriaWS portCategoria;

    private String Separador = " // ";
    //Separador es usado para separar el nombre de la promocion del nick del proveedor
    //de esta forma se puede cumplir con los requerimientos actuales de la busqueda de una promocion

    private String proveedorAux;
    private String servicioAux;

    private Properties progappProperties;
    private InputStream input = null;
    private List<JLabel> imagenes;

    private Converter convertidor;
    private String url;

    /**
     * Creates new form VerInfoServicio
     */
    public VerInfoServicio() {
        initComponents();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        setLocationRelativeTo(null);
        imagenes = new ArrayList<JLabel>();
        imagenes.add(labelImage1);
        imagenes.add(labelImage2);
        imagenes.add(labelImage3);
        scrollPaneProveedores.setVisible(false);
        listProveedores.setVisible(false);
        DefaultTreeModel model = (DefaultTreeModel) treeCategorias.getModel();
        DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();

        setLocationRelativeTo(null);
        convertidor = new Converter();

        try {
            categoriaWSImplService = new CategoriaWSImplService(new URL("http://localhost:8080/servidor-central-webapp/soap/CategoriaWSImplService?wsdl"));
        } catch (MalformedURLException ex) {
            Logger.getLogger(AltaCategoria.class.getName()).log(Level.SEVERE, null, ex);
        }
        portCategoria = categoriaWSImplService.getCategoriaWSImplPort();

        this.progappProperties = new Properties();
        input = this.getClass().getClassLoader().getResourceAsStream("progapp.properties");
        try {
            progappProperties.load(input);
        } catch (IOException ex) {
            Logger.getLogger(VerInfoServicio.class.getName()).log(Level.SEVERE, null, ex);
        }

        DatosRest categoriasAux = null;

        url = "http://localhost:8080/servidor-central-webapp/rest/api/ObtenerCategorias/traer";
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(url);
        ObjectMapper mapper = new ObjectMapper();
        HttpResponse response = null;
        String result = null;
        try {
            response = client.execute(request);
            result = getStringFromInputStream(response.getEntity().getContent());
            categoriasAux = mapper.readValue(result, DatosRest.class);
        } catch (IOException ex) {
            Logger.getLogger(AltaCategoria.class.getName()).log(Level.SEVERE, null, ex);
        }
        int i = categoriasAux.getCategorias().size();
        Iterator<Categoria> iteratorCategorias = categoriasAux.getCategorias().iterator();
        while (iteratorCategorias.hasNext()) {
            Categoria categoriaAuxiliar = iteratorCategorias.next();
            if (categoriaAuxiliar.getPadre() == "") {
                root.add(new DefaultMutableTreeNode(categoriaAuxiliar.getNombre()));
            } else {
                Enumeration<DefaultMutableTreeNode> enumerationNodo = root.depthFirstEnumeration();
                while (enumerationNodo.hasMoreElements()) {
                    DefaultMutableTreeNode nodoAuxiliar = enumerationNodo.nextElement();
                    if (nodoAuxiliar.toString().equals(categoriaAuxiliar.getPadre())) {
                        model.insertNodeInto(new DefaultMutableTreeNode(categoriaAuxiliar.getNombre()), nodoAuxiliar, nodoAuxiliar.getChildCount());
                    }
                }
            }
        }
        model.reload();
        treeCategorias.setRootVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelCategorias = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        treeCategorias = new javax.swing.JTree();
        labelServicios = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listServicios = new javax.swing.JList<>();
        labelProveedor = new javax.swing.JLabel();
        labelDescripcion = new javax.swing.JLabel();
        labelPrecio = new javax.swing.JLabel();
        labelOrigen = new javax.swing.JLabel();
        labelDestino = new javax.swing.JLabel();
        buttonAtras = new javax.swing.JButton();
        scrollPaneProveedores = new javax.swing.JScrollPane();
        listProveedores = new javax.swing.JList<>();
        labelImage1 = new javax.swing.JLabel();
        labelImage2 = new javax.swing.JLabel();
        labelImage3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        labelCategorias.setText("Categorias: ");

        treeCategorias.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("Categorias");
        treeCategorias.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        treeCategorias.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
            public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
                treeCategoriasValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(treeCategorias);

        labelServicios.setText("Servicios: ");

        listServicios.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        listServicios.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listServiciosValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(listServicios);

        labelProveedor.setText("Proveedor: ");

        labelDescripcion.setText("Descripcion: ");

        labelPrecio.setText("Precio: ");

        labelOrigen.setText("Ciudad de origen: ");

        labelDestino.setText("Ciudad de destino: ");

        buttonAtras.setText("Atras");
        buttonAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAtrasActionPerformed(evt);
            }
        });

        scrollPaneProveedores.setViewportView(listProveedores);

        labelImage1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelImage1.setText("NoImage");
        labelImage1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        labelImage2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelImage2.setText("NoImage");
        labelImage2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        labelImage3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelImage3.setText("NoImage");
        labelImage3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelCategorias)
                        .addGap(58, 58, 58)
                        .addComponent(labelServicios)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(buttonAtras, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(labelProveedor)
                                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addComponent(scrollPaneProveedores, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(labelDescripcion)
                                    .addComponent(labelPrecio)
                                    .addComponent(labelOrigen)
                                    .addComponent(labelDestino))
                                .addGap(64, 64, 64))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(labelImage1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(labelImage2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(labelImage3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(21, 21, 21)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelCategorias)
                    .addComponent(labelServicios))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(scrollPaneProveedores, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(labelProveedor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelDescripcion)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelPrecio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelOrigen)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelDestino)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelImage1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelImage2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelImage3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addComponent(buttonAtras)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void treeCategoriasValueChanged(javax.swing.event.TreeSelectionEvent evt) {//GEN-FIRST:event_treeCategoriasValueChanged
        labelProveedor.setText("Proveedor: ");
        labelDescripcion.setText("Descripcion: ");
        labelPrecio.setText("Precio: ");
        labelOrigen.setText("Ciudad de origen: ");
        labelDestino.setText("Ciudad de destino: ");
        labelImage1.setIcon(null);
        labelImage2.setIcon(null);
        labelImage3.setIcon(null);

        DefaultListModel listaServicios = new DefaultListModel();
        //DefaultListModel listaProveedores = new DefaultListModel();

        try {
            servicioWSImplService = new ServicioWSImplService(new URL("http://localhost:8080/servidor-central-webapp/soap/ServicioWSImplService?wsdl"));
        } catch (MalformedURLException ex) {
            //Logger.getLogger(VerInfoProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
        portServicio = servicioWSImplService.getServicioWSImplPort();

        DefaultMutableTreeNode nodoSeleccionado = (DefaultMutableTreeNode) treeCategorias.getLastSelectedPathComponent();
        portServicio.obtenerTodosServiciosWS();
        Iterator<uy.edu.cure.servidor.central.soap.client.Servicio> iteratorServicios = portServicio.obtenerTodosServiciosWS().iterator();
        //Iterator<Servicio> iteratorServicios = servicioController.obtenerTodosServicios().iterator();
        while (iteratorServicios.hasNext()) {
            uy.edu.cure.servidor.central.soap.client.Servicio servicioAuxiliar = iteratorServicios.next();
            Iterator<uy.edu.cure.servidor.central.soap.client.Categoria> iteratorCategorias = portServicio.obtenerCategoriasServicioWS(servicioAuxiliar.getNombre(), servicioAuxiliar.getProveedor().getNickName()).iterator();
            while (iteratorCategorias.hasNext()) {
                uy.edu.cure.servidor.central.soap.client.Categoria categoriaAuxiliar = iteratorCategorias.next();
                if (categoriaAuxiliar.getNombre().equals(nodoSeleccionado.toString())) {
                    String nomServAux = servicioAuxiliar.getNombre();
                    String nickProvAux = servicioAuxiliar.getProveedor().getNickName();
                    listaServicios.addElement(nomServAux + Separador + nickProvAux);
                    //listaServicios.addElement(servicioAuxiliar.getNombre());
                    //listaProveedores.addElement(servicioAuxiliar.getProveedor().getNickName());
                }
            }
        }
        listServicios.setModel(listaServicios);
        //listProveedores.setModel(listaProveedores);

    }//GEN-LAST:event_treeCategoriasValueChanged

    private void buttonAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAtrasActionPerformed
        this.dispose();
    }//GEN-LAST:event_buttonAtrasActionPerformed

    private void listServiciosValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listServiciosValueChanged
        /*try {
            servicioWSImplService = new ServicioWSImplService(new URL("http://localhost:8080/servidor-central-webapp/soap/ServicioWSImplService?wsdl"));
        } catch (MalformedURLException ex) {
            //Logger.getLogger(VerInfoProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
        portServicio = servicioWSImplService.getServicioWSImplPort();

        servicioAux = listServicios.getSelectedValue();

        labelDescripcion.setText(portServicio.obtenerServicioWS(servicioAux, proveedorAux).getDescripcion());
        labelPrecio.setText("Precio: " + String.valueOf(portServicio.obtenerServicioWS(servicioAux, proveedorAux).getPrecio()));
        labelOrigen.setText("Ciudad de origen: " + portServicio.obtenerServicioWS(servicioAux, proveedorAux).getOrigen().getNombre());
        if (portServicio.obtenerServicioWS(servicioAux, proveedorAux).getDestino() == null) {
            labelDestino.setText("No hay ciudad de destino");
        } else {
            labelDestino.setText("Ciudad de destino: " + portServicio.obtenerServicioWS(servicioAux, proveedorAux).getDestino().getNombre());
        }

        //Imagenes del servicio
        for (int i = 0; i < portServicio.obtenerImagenesServicioWS(servicioAux, proveedorAux).size(); i++) {
            ImageIcon imageIcon = new ImageIcon(portServicio.obtenerImagenesServicioWS(servicioAux, proveedorAux).get(i));
            Image image = imageIcon.getImage();
            Image imageFinal = image.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
            ImageIcon imageIconFinal = new ImageIcon(imageFinal);
            imagenes.get(i).setIcon(imageIconFinal);
        }*/

        try {
            servicioWSImplService = new ServicioWSImplService(new URL("http://localhost:8080/servidor-central-webapp/soap/ServicioWSImplService?wsdl"));
        } catch (MalformedURLException ex) {
            //Logger.getLogger(VerInfoProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
        portServicio = servicioWSImplService.getServicioWSImplPort();

        String aux = listServicios.getSelectedValue();
        String[] arrayPromocionAux = aux.split(Separador);
        servicioAux = arrayPromocionAux[0];
        proveedorAux = arrayPromocionAux[1];

        labelProveedor.setText("Proveedor: " + proveedorAux);
        labelDescripcion.setText("Descripcion: " + portServicio.obtenerServicioWS(servicioAux, proveedorAux).getDescripcion());
        labelPrecio.setText("Precio: " + String.valueOf(portServicio.obtenerServicioWS(servicioAux, proveedorAux).getPrecio()));
        labelOrigen.setText("Ciudad de origen: " + portServicio.obtenerServicioWS(servicioAux, proveedorAux).getOrigen().getNombre());
        if (portServicio.obtenerServicioWS(servicioAux, proveedorAux).getDestino() == null) {
            labelDestino.setText("No hay ciudad de destino");
        } else {
            labelDestino.setText("Ciudad de destino: " + portServicio.obtenerServicioWS(servicioAux, proveedorAux).getDestino().getNombre());
        }

        //Categorias del servicio
        //String categorias = "Categorias: ";
        /*
            for (int i = 0; i < servicio.getCategorias().size(); i++) {
                categorias = categorias + servicio.getCategorias().get(i).getNombre();
                categorias = categorias + " ";
            }*/
        //labelServicioCategorias.setText(categorias);
        //Imagenes del servicio
        for (int i = 0; i < portServicio.obtenerImagenesServicioWS(servicioAux, proveedorAux).size(); i++) {
            String ruta = progappProperties.getProperty("ruta.imagenes") + portServicio.obtenerImagenesServicioWS(servicioAux, proveedorAux).get(i);
            ImageIcon imageIcon = new ImageIcon(ruta);
            Image image = imageIcon.getImage();
            Image imageFinal = image.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
            ImageIcon imageIconFinal = new ImageIcon(imageFinal);
            imagenes.get(i).setIcon(imageIconFinal);
        }


    }//GEN-LAST:event_listServiciosValueChanged

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
            java.util.logging.Logger.getLogger(VerInfoServicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VerInfoServicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VerInfoServicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VerInfoServicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VerInfoServicio().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAtras;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labelCategorias;
    private javax.swing.JLabel labelDescripcion;
    private javax.swing.JLabel labelDestino;
    private javax.swing.JLabel labelImage1;
    private javax.swing.JLabel labelImage2;
    private javax.swing.JLabel labelImage3;
    private javax.swing.JLabel labelOrigen;
    private javax.swing.JLabel labelPrecio;
    private javax.swing.JLabel labelProveedor;
    private javax.swing.JLabel labelServicios;
    private javax.swing.JList<String> listProveedores;
    private javax.swing.JList<String> listServicios;
    private javax.swing.JScrollPane scrollPaneProveedores;
    private javax.swing.JTree treeCategorias;
    // End of variables declaration//GEN-END:variables

    private String getStringFromInputStream(InputStream is) {
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {

            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return sb.toString();

    }

}
