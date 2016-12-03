/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.cure.estacion.de.trabajo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.glass.events.KeyEvent;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import uy.edu.cure.servidor.central.dto.*;

import uy.edu.cure.servidor.central.soap.client.UsuarioWS;
import uy.edu.cure.servidor.central.soap.client.UsuarioWSImplService;
import uy.edu.cure.servidor.central.soap.client.CiudadWS;
import uy.edu.cure.servidor.central.soap.client.CiudadWSImplService;
import uy.edu.cure.servidor.central.soap.client.PaisWS;
import uy.edu.cure.servidor.central.soap.client.PaisWSImplService;
import uy.edu.cure.servidor.central.soap.client.CategoriaWS;
import uy.edu.cure.servidor.central.soap.client.CategoriaWSImplService;
import uy.edu.cure.servidor.central.soap.client.ServicioWS;
import uy.edu.cure.servidor.central.soap.client.ServicioWSImplService;

/**
 *
 * @author Rodrigo "Lobo Plateado" Pérez
 */
public class AltaServicio extends javax.swing.JFrame {

    private UsuarioWS portUsuario;
    private PaisWS portPais;
    private CiudadWS portCiudad;
    private CategoriaWS portCategoria;
    private ServicioWS portServicio;
    
    private UsuarioWSImplService usuarioWSImplService;
    private PaisWSImplService paisWSImplService;
    private CiudadWSImplService ciudadWSImplService; 
    private CategoriaWSImplService categoriaWSImplService;
    private ServicioWSImplService servicioWSImplService;
    
    private List<String> rutasImagenes;
    private List<JLabel> imagenes;
    private Properties progappProperties;
    private InputStream input = null;
    private String ruta;
    
    private Converter convertidor;
    private String url;
    
    /**
     * Creates new form AltaServicio
     */
    public AltaServicio() throws IOException {
        initComponents();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        this.progappProperties = new Properties();
        input = this.getClass().getClassLoader().getResourceAsStream("progapp.properties");
        progappProperties.load(input);
 
          try {
            usuarioWSImplService = new UsuarioWSImplService(new URL("http://localhost:8080/servidor-central-webapp/soap/UsuarioWSImplService?wsdl"));
            paisWSImplService = new PaisWSImplService(new URL("http://localhost:8080/servidor-central-webapp/soap/PaisWSImplService?wsdl"));
            ciudadWSImplService = new CiudadWSImplService(new URL("http://localhost:8080/servidor-central-webapp/soap/CiudadWSImplService?wsdl"));
            categoriaWSImplService = new CategoriaWSImplService(new URL("http://localhost:8080/servidor-central-webapp/soap/CategoriaWSImplService?wsdl"));
            servicioWSImplService = new ServicioWSImplService(new URL("http://localhost:8080/servidor-central-webapp/soap/ServicioWSImplService?wsdl"));
        } catch (MalformedURLException e) {
            Logger.getLogger(AltaServicio.class.getName()).log(Level.SEVERE,null,e);
        }
        portUsuario = usuarioWSImplService.getUsuarioWSImplPort();
        portPais = paisWSImplService.getPaisWSImplPort();
        portCiudad = ciudadWSImplService.getCiudadWSImplPort();
        portCategoria = categoriaWSImplService.getCategoriaWSImplPort();
        portServicio = servicioWSImplService.getServicioWSImplPort();
                
        rutasImagenes = new ArrayList<String>();
        imagenes = new ArrayList<JLabel>();
        imagenes.add(labelImage1);
        imagenes.add(labelImage2);
        imagenes.add(labelImage3);
        DefaultListModel listaProveedores = new DefaultListModel();
        for (int i = 0; i < portUsuario.obtenerTodosProveedoresWS().size(); i++) {
            listaProveedores.add(i, portUsuario.obtenerTodosProveedoresWS().get(i).getNickName());
        }
        listProveedores.setModel(listaProveedores);
        
        //Carga lista de ciudades
        DefaultListModel listaCiudadesOrigen = new DefaultListModel();
        for (int i = 0; i < portCiudad.obtenerTodasCiudadesWS().size(); i++) {
            listaCiudadesOrigen.add(i, portCiudad.obtenerTodasCiudadesWS().get(i).getNombre());
        }
        listCiudadOrigen.setModel(listaCiudadesOrigen);
        DefaultListModel listaCiudadesDestino = new DefaultListModel();
        int indiceDestino;
        for (indiceDestino = 0; indiceDestino < portCiudad.obtenerTodasCiudadesWS().size(); indiceDestino++) {
            listaCiudadesDestino.add(indiceDestino, portCiudad.obtenerTodasCiudadesWS().get(indiceDestino).getNombre());
        }
        listaCiudadesDestino.add(indiceDestino, "<null>");
        listCiudadDestino.setModel(listaCiudadesDestino);
        
        //Carga categorias, recordar metodo auxiliar getStringFromInputStream y variable String url
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

        jScrollPane4 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();
        labelNombreServicio = new javax.swing.JLabel();
        textFieldNombreServicio = new javax.swing.JTextField();
        labelProveedor = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listProveedores = new javax.swing.JList<>();
        labelDescripcion = new javax.swing.JLabel();
        textFieldDescripcion = new javax.swing.JTextField();
        labelPrecio = new javax.swing.JLabel();
        textFieldPrecio = new javax.swing.JTextField();
        labelCiudadOrigen = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listCiudadOrigen = new javax.swing.JList<>();
        labelCiudadDestino = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        listCiudadDestino = new javax.swing.JList<>();
        jScrollPane5 = new javax.swing.JScrollPane();
        treeCategorias = new javax.swing.JTree();
        buttonAgregarCategoria = new javax.swing.JButton();
        buttonQuitarCategoria = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        listCategorias = new javax.swing.JList<>();
        labelMessageError = new javax.swing.JLabel();
        buttonCancelar = new javax.swing.JButton();
        buttonAceptar = new javax.swing.JButton();
        buttonAddImage = new javax.swing.JButton();
        labelImage1 = new javax.swing.JLabel();
        labelImage2 = new javax.swing.JLabel();
        labelImage3 = new javax.swing.JLabel();
        buttonRemoveImage = new javax.swing.JButton();
        textFieldIndexImage = new javax.swing.JTextField();

        jScrollPane4.setViewportView(jTree1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        labelNombreServicio.setText("Nombre servicio: ");

        labelProveedor.setText("Proveedor: ");

        jScrollPane1.setViewportView(listProveedores);

        labelDescripcion.setText("Descripcion: ");

        labelPrecio.setText("Precio: ");

        textFieldPrecio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textFieldPrecioKeyTyped(evt);
            }
        });

        labelCiudadOrigen.setText("Ciudad de Origen: ");

        jScrollPane2.setViewportView(listCiudadOrigen);

        labelCiudadDestino.setText("Ciudad de Destino: ");

        jScrollPane3.setViewportView(listCiudadDestino);

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("Categorias");
        treeCategorias.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        jScrollPane5.setViewportView(treeCategorias);

        buttonAgregarCategoria.setText("> >");
        buttonAgregarCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAgregarCategoriaActionPerformed(evt);
            }
        });

        buttonQuitarCategoria.setText("< <");
        buttonQuitarCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonQuitarCategoriaActionPerformed(evt);
            }
        });

        jScrollPane6.setViewportView(listCategorias);

        labelMessageError.setForeground(new java.awt.Color(255, 0, 0));
        labelMessageError.setText("   ");

        buttonCancelar.setText("Cancelar");
        buttonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCancelarActionPerformed(evt);
            }
        });

        buttonAceptar.setText("Aceptar");
        buttonAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAceptarActionPerformed(evt);
            }
        });

        buttonAddImage.setText("Añadir Imagen");
        buttonAddImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddImageActionPerformed(evt);
            }
        });

        labelImage1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelImage1.setText("Imagen1");
        labelImage1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        labelImage2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelImage2.setText("Imagen2");
        labelImage2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        labelImage3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelImage3.setText("Imagen3");
        labelImage3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        buttonRemoveImage.setText("Quitar Imagen");
        buttonRemoveImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRemoveImageActionPerformed(evt);
            }
        });

        textFieldIndexImage.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textFieldIndexImageKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(labelMessageError, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buttonAceptar)
                        .addGap(4, 4, 4)
                        .addComponent(buttonCancelar))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(buttonAddImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(buttonRemoveImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(textFieldIndexImage, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(220, 220, 220)
                                .addComponent(labelImage3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(labelImage1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(labelImage2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(110, 110, 110))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(labelProveedor)
                            .addComponent(labelNombreServicio)
                            .addComponent(labelDescripcion)
                            .addComponent(labelPrecio)
                            .addComponent(labelCiudadOrigen))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(textFieldNombreServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                                .addComponent(textFieldPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(textFieldDescripcion))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(labelCiudadDestino)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(buttonAgregarCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(buttonQuitarCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textFieldNombreServicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelNombreServicio))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelProveedor)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelDescripcion)
                    .addComponent(textFieldDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textFieldPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelPrecio))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelCiudadDestino)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                        .addComponent(labelCiudadOrigen, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(buttonAgregarCategoria)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonQuitarCategoria)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(labelImage1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(labelImage2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(labelImage3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(buttonAddImage)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buttonRemoveImage)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textFieldIndexImage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(buttonCancelar)
                        .addComponent(buttonAceptar))
                    .addComponent(labelMessageError, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonAgregarCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAgregarCategoriaActionPerformed
        labelMessageError.setText(" ");
        DefaultListModel listaCategorias = new DefaultListModel();
        DefaultMutableTreeNode nodoSeleccionado = (DefaultMutableTreeNode) treeCategorias.getLastSelectedPathComponent();
        if (nodoSeleccionado != null) {
            if (nodoSeleccionado.isLeaf()) {
                if (listCategorias.getModel().getSize() == 0) {
                    listaCategorias.add(0, nodoSeleccionado.toString());
                    listCategorias.setModel(listaCategorias);
                } else {
                    boolean existeCategoria = false;
                    listaCategorias = (DefaultListModel) listCategorias.getModel();
                    for (int i = 0; i < listaCategorias.size(); i++) {
                        if (listaCategorias.get(i).equals(nodoSeleccionado.toString())) {
                            existeCategoria = true;
                        }
                    }
                    if (!existeCategoria) {
                        listaCategorias.addElement(nodoSeleccionado.toString());
                        listCategorias.setModel(listaCategorias);
                    } else {
                        labelMessageError.setText("No se puede ingresar la misma categoria");
                    }
                }
            } else {
                labelMessageError.setText("La categoria seleccionada debe ser un nodo hoja");
            }
        } else {
            labelMessageError.setText("Debe seleccionar una categoria");
        }
    }//GEN-LAST:event_buttonAgregarCategoriaActionPerformed

    private void buttonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_buttonCancelarActionPerformed

    private void buttonQuitarCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonQuitarCategoriaActionPerformed
        labelMessageError.setText(" ");
        if (listCategorias.getSelectedValue() != null) {
            DefaultListModel listaCategorias = (DefaultListModel) listCategorias.getModel();
            listaCategorias.remove(listCategorias.getSelectedIndex());
            listCategorias.setModel(listaCategorias);
        } else {
            labelMessageError.setText("No se ha seleccionado categoria para remover");
        }
    }//GEN-LAST:event_buttonQuitarCategoriaActionPerformed

    private void buttonAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAceptarActionPerformed
        labelMessageError.setText(" ");
        String precioValido = portServicio.verificarPrecio(textFieldPrecio.getText());
        if (precioValido.equals("OK")) {
            String nombreServicio = textFieldNombreServicio.getText();
            String nickNameProveedor = listProveedores.getSelectedValue();
            String descripcion = textFieldDescripcion.getText();
            double precio = Double.parseDouble(textFieldPrecio.getText());
            String ciudadOrigen = listCiudadOrigen.getSelectedValue();
            String ciudadDestino = listCiudadDestino.getSelectedValue();
            List<String> categorias = new ArrayList<String>();
            for (int i = 0; i < listCategorias.getModel().getSize(); i++) {
                categorias.add(listCategorias.getModel().getElementAt(i));
            }
            String resultado = portServicio.crearServicioWS(nombreServicio, descripcion, precio, ciudadOrigen, ciudadDestino, categorias, rutasImagenes, nickNameProveedor);
            if (resultado.equals("OK")) {
                javax.swing.JOptionPane.showMessageDialog(null, "Servicio creado exitosamente", "Alta Servicio", 1);
                this.dispose();
            } else {
                javax.swing.JOptionPane.showMessageDialog(null, resultado, "Alta Servicio", 0);
            }
        } else {
            javax.swing.JOptionPane.showMessageDialog(null, precioValido, "Alta Servicio", 0);
        }
    }//GEN-LAST:event_buttonAceptarActionPerformed

    private void textFieldPrecioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textFieldPrecioKeyTyped
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || c == KeyEvent.VK_BACKSPACE || c == KeyEvent.VK_DELETE || c == 46)) {
            evt.consume();
        }
    }//GEN-LAST:event_textFieldPrecioKeyTyped

    private void buttonAddImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddImageActionPerformed
        labelMessageError.setText(" ");
        if (rutasImagenes.size() < 3) {
            JFileChooser selectorImage = new JFileChooser();
            FileNameExtensionFilter filtro = new FileNameExtensionFilter("Formato imagen", "png", "jpg");
            selectorImage.setFileFilter(filtro);
            int opcion = selectorImage.showOpenDialog(this);
            if (opcion == JFileChooser.APPROVE_OPTION) {
                Date d = new Date();
                String nombreImagen = Long.toString(d.getTime());
                String rutaWS = progappProperties.getProperty("ruta.imagenes.servicio");
                rutaWS = rutaWS + nombreImagen + ".png";
                ruta = progappProperties.getProperty("ruta.imagenes");
                File imagendestino = new File(ruta+rutaWS);
                File JFile = new File(selectorImage.getSelectedFile().toString());
                rutasImagenes.add(rutaWS);
                try {
                    Files.copy(JFile.toPath(), imagendestino.toPath());
                } catch (IOException ex) {
                    Logger.getLogger(AltaServicio.class.getName()).log(Level.SEVERE, null, ex);
                }
                for (int i = 0; i < rutasImagenes.size(); i++) {
                    ImageIcon imageIcon = new ImageIcon(ruta + rutasImagenes.get(i));
                    Image image = imageIcon.getImage();
                    Image imageFinal = image.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
                    ImageIcon imageIconFinal = new ImageIcon(imageFinal);
                    imagenes.get(i).setIcon(imageIconFinal);
                }
            }
        } else {
            labelMessageError.setText("Se permite un maximo de 3 imagenes");
        }
    }//GEN-LAST:event_buttonAddImageActionPerformed

    private void textFieldIndexImageKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textFieldIndexImageKeyTyped
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || c == KeyEvent.VK_BACKSPACE || c == KeyEvent.VK_DELETE)) {
            evt.consume();
        }
    }//GEN-LAST:event_textFieldIndexImageKeyTyped

    private void buttonRemoveImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRemoveImageActionPerformed
        labelMessageError.setText(" ");
        if (!textFieldIndexImage.getText().isEmpty()) {
            if (!rutasImagenes.isEmpty()) {
                int indiceImagen = Integer.parseInt(textFieldIndexImage.getText());
                if (indiceImagen <= rutasImagenes.size() && indiceImagen != 0) {
                    rutasImagenes.remove(indiceImagen - 1);
                    for (int i = 0; i < 3; i++) {
                        imagenes.get(i).setIcon(null);
                    }
                    for (int i = 0; i < rutasImagenes.size(); i++) {
                        ImageIcon imageIcon = new ImageIcon(ruta + rutasImagenes.get(i));
                        Image image = imageIcon.getImage();
                        Image imageFinal = image.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
                        ImageIcon imageIconFinal = new ImageIcon(imageFinal);
                        imagenes.get(i).setIcon(imageIconFinal);
                    }
                } else {
                    labelMessageError.setText("Indice de imagen no valido");
                }
            } else {
                labelMessageError.setText("No hay imagenes para quitar");
            }
        } else {
            labelMessageError.setText("Debe introducir indice de la imagen");
        }
    }//GEN-LAST:event_buttonRemoveImageActionPerformed

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
            java.util.logging.Logger.getLogger(AltaServicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AltaServicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AltaServicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AltaServicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new AltaServicio().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(AltaServicio.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAceptar;
    private javax.swing.JButton buttonAddImage;
    private javax.swing.JButton buttonAgregarCategoria;
    private javax.swing.JButton buttonCancelar;
    private javax.swing.JButton buttonQuitarCategoria;
    private javax.swing.JButton buttonRemoveImage;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTree jTree1;
    private javax.swing.JLabel labelCiudadDestino;
    private javax.swing.JLabel labelCiudadOrigen;
    private javax.swing.JLabel labelDescripcion;
    private javax.swing.JLabel labelImage1;
    private javax.swing.JLabel labelImage2;
    private javax.swing.JLabel labelImage3;
    private javax.swing.JLabel labelMessageError;
    private javax.swing.JLabel labelNombreServicio;
    private javax.swing.JLabel labelPrecio;
    private javax.swing.JLabel labelProveedor;
    private javax.swing.JList<String> listCategorias;
    private javax.swing.JList<String> listCiudadDestino;
    private javax.swing.JList<String> listCiudadOrigen;
    private javax.swing.JList<String> listProveedores;
    private javax.swing.JTextField textFieldDescripcion;
    private javax.swing.JTextField textFieldIndexImage;
    private javax.swing.JTextField textFieldNombreServicio;
    private javax.swing.JTextField textFieldPrecio;
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
