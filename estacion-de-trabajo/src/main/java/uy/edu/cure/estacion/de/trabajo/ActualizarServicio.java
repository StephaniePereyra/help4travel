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
import uy.edu.cure.servidor.central.soap.client.CategoriaWS;
import uy.edu.cure.servidor.central.soap.client.CategoriaWSImplService;
import uy.edu.cure.servidor.central.soap.client.CiudadWS;
import uy.edu.cure.servidor.central.soap.client.CiudadWSImplService;
import uy.edu.cure.servidor.central.soap.client.ServicioWS;
import uy.edu.cure.servidor.central.soap.client.ServicioWSImplService;

/**
 *
 * @author Rodrigo "Lobo Plateado" Pérez
 */
public class ActualizarServicio extends javax.swing.JFrame {

    private CiudadWS portCiudad;
    private CategoriaWS portCategoria;
    private ServicioWS portServicio;

    private CiudadWSImplService ciudadWSImplService;
    private CategoriaWSImplService categoriaWSImplService;
    private ServicioWSImplService servicioWSImplService;

    private List<String> rutasImagenes;
    private List<JLabel> imagenes;
    private Properties progappProperties;
    private InputStream input = null;

    private Converter convertidor;
    private String url;

    private String proveedorAux;
    private String servicioAux;
    private String Separador = " // ";

    /**
     * Creates new form ActualizarServicio
     */
    public ActualizarServicio() throws IOException {
        initComponents();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        this.progappProperties = new Properties();
        input = this.getClass().getClassLoader().getResourceAsStream("progapp.properties");
        progappProperties.load(input);

        rutasImagenes = new ArrayList<String>();
        imagenes = new ArrayList<JLabel>();
        imagenes.add(labelImage1);
        imagenes.add(labelImage2);
        imagenes.add(labelImage3);

        try {
            ciudadWSImplService = new CiudadWSImplService(new URL("http://localhost:8080/servidor-central-webapp/soap/CiudadWSImplService?wsdl"));
            categoriaWSImplService = new CategoriaWSImplService(new URL("http://localhost:8080/servidor-central-webapp/soap/CategoriaWSImplService?wsdl"));
            servicioWSImplService = new ServicioWSImplService(new URL("http://localhost:8080/servidor-central-webapp/soap/ServicioWSImplService?wsdl"));
        } catch (MalformedURLException e) {
            Logger.getLogger(ActualizarServicio.class.getName()).log(Level.SEVERE, null, e);
        }
        portCiudad = ciudadWSImplService.getCiudadWSImplPort();
        portCategoria = categoriaWSImplService.getCategoriaWSImplPort();
        portServicio = servicioWSImplService.getServicioWSImplPort();

        setLocationRelativeTo(null);
        this.progappProperties = new Properties();
        input = this.getClass().getClassLoader().getResourceAsStream("progapp.properties");
        progappProperties.load(input);
        setLocationRelativeTo(null);
        imagenes = new ArrayList<JLabel>();
        imagenes.add(labelImage1);
        imagenes.add(labelImage2);
        imagenes.add(labelImage3);

        DefaultListModel listaServicios = new DefaultListModel();

        for (int i = 0; i < portServicio.obtenerTodosServiciosWS().size(); i++) {
            servicioAux = portServicio.obtenerTodosServiciosWS().get(i).getNombre();
            proveedorAux = portServicio.obtenerTodosServiciosWS().get(i).getProveedor().getNickName();
            listaServicios.add(i, servicioAux + Separador + proveedorAux);
        }
        listServicios.setModel(listaServicios);

        DefaultListModel listaCiudadesOrigen = new DefaultListModel();
        for (int i = 0; i < portCiudad.obtenerTodasCiudadesWS().size(); i++) {
            listaCiudadesOrigen.add(i, portCiudad.obtenerTodasCiudadesWS().get(i).getNombre());
        }
        listOrigen.setModel(listaCiudadesOrigen);
        DefaultListModel listaCiudadesDestino = new DefaultListModel();
        int indiceDestino;
        for (indiceDestino = 0; indiceDestino < portCiudad.obtenerTodasCiudadesWS().size(); indiceDestino++) {
            listaCiudadesDestino.add(indiceDestino, portCiudad.obtenerTodasCiudadesWS().get(indiceDestino).getNombre());
        }
        listaCiudadesDestino.add(indiceDestino, "<null>");
        listDestino.setModel(listaCiudadesDestino);

        //Carga categorias
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

        labelServicios = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listServicios = new javax.swing.JList<>();
        labelDescripcion = new javax.swing.JLabel();
        textFieldDescripcion = new javax.swing.JTextField();
        labelPrecio = new javax.swing.JLabel();
        textFieldPrecio = new javax.swing.JTextField();
        labelOrigen = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listOrigen = new javax.swing.JList<>();
        labelDestino = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        listDestino = new javax.swing.JList<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        treeCategorias = new javax.swing.JTree();
        jScrollPane5 = new javax.swing.JScrollPane();
        listCategorias = new javax.swing.JList<>();
        buttonAddCategoria = new javax.swing.JButton();
        buttonRemoveCategoria = new javax.swing.JButton();
        buttonBack = new javax.swing.JButton();
        labelMessageError = new javax.swing.JLabel();
        buttonChangeDescripcion = new javax.swing.JButton();
        buttonChangePrecio = new javax.swing.JButton();
        buttonChangeOrigen = new javax.swing.JButton();
        buttonChangeDestino = new javax.swing.JButton();
        buttonChangeCategorias = new javax.swing.JButton();
        buttonAddImage = new javax.swing.JButton();
        buttonRemoveImage = new javax.swing.JButton();
        textFieldIndexImage = new javax.swing.JTextField();
        labelImage1 = new javax.swing.JLabel();
        labelImage2 = new javax.swing.JLabel();
        labelImage3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        labelServicios.setText("Servicios Actuales: ");

        listServicios.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listServiciosValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(listServicios);

        labelDescripcion.setText("Descripcion: ");

        labelPrecio.setText("Precio: ");

        textFieldPrecio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textFieldPrecioKeyTyped(evt);
            }
        });

        labelOrigen.setText("Ciudad de Origen: ");

        jScrollPane2.setViewportView(listOrigen);

        labelDestino.setText("Ciudad de Destino: ");

        jScrollPane3.setViewportView(listDestino);

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("Categorias");
        treeCategorias.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        jScrollPane4.setViewportView(treeCategorias);

        jScrollPane5.setViewportView(listCategorias);

        buttonAddCategoria.setText("> >");
        buttonAddCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddCategoriaActionPerformed(evt);
            }
        });

        buttonRemoveCategoria.setText("< <");
        buttonRemoveCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRemoveCategoriaActionPerformed(evt);
            }
        });

        buttonBack.setText("Atras");
        buttonBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBackActionPerformed(evt);
            }
        });

        labelMessageError.setForeground(new java.awt.Color(255, 0, 0));

        buttonChangeDescripcion.setText("Cambiar Descripcion");
        buttonChangeDescripcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonChangeDescripcionActionPerformed(evt);
            }
        });

        buttonChangePrecio.setText("Cambiar Precio");
        buttonChangePrecio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonChangePrecioActionPerformed(evt);
            }
        });

        buttonChangeOrigen.setText("Cambiar Origen");
        buttonChangeOrigen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonChangeOrigenActionPerformed(evt);
            }
        });

        buttonChangeDestino.setText("Cambiar Destino");
        buttonChangeDestino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonChangeDestinoActionPerformed(evt);
            }
        });

        buttonChangeCategorias.setText("Cambiar Categorias");
        buttonChangeCategorias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonChangeCategoriasActionPerformed(evt);
            }
        });

        buttonAddImage.setText("Añadir Imagen");
        buttonAddImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddImageActionPerformed(evt);
            }
        });

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

        labelImage1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelImage1.setText("Imagen1");
        labelImage1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        labelImage2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelImage2.setText("Imagen2");
        labelImage2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        labelImage3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelImage3.setText("Imagen3");
        labelImage3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labelServicios)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(buttonAddCategoria)
                                            .addComponent(buttonRemoveCategoria))
                                        .addGap(18, 18, 18)
                                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(95, 95, 95))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(labelMessageError, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(18, 18, 18)))
                                .addComponent(buttonBack))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelPrecio)
                                    .addComponent(labelDescripcion)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(buttonChangeDescripcion)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(textFieldDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(buttonChangeOrigen, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
                                            .addComponent(buttonChangePrecio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(textFieldPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(labelDestino)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(buttonChangeDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                                    .addComponent(labelOrigen)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(buttonRemoveImage, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(buttonAddImage, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(buttonChangeCategorias, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE))
                                            .addComponent(textFieldIndexImage, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(labelImage1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(labelImage2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(labelImage3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelServicios)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addComponent(labelDescripcion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textFieldDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonChangeDescripcion))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelPrecio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonChangePrecio)
                    .addComponent(textFieldPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelOrigen)
                    .addComponent(labelDestino))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(buttonChangeOrigen)
                        .addGap(69, 69, 69)
                        .addComponent(buttonChangeCategorias))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonChangeDestino))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(buttonAddCategoria)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(buttonRemoveCategoria)))))
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(buttonAddImage)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buttonRemoveImage)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textFieldIndexImage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(labelImage1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(labelImage2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(labelImage3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonBack, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelMessageError, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonAddCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddCategoriaActionPerformed
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
    }//GEN-LAST:event_buttonAddCategoriaActionPerformed

    private void buttonRemoveCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRemoveCategoriaActionPerformed
        labelMessageError.setText(" ");
        if (listCategorias.getSelectedValue() != null) {
            DefaultListModel listaCategorias = (DefaultListModel) listCategorias.getModel();
            listaCategorias.remove(listCategorias.getSelectedIndex());
            listCategorias.setModel(listaCategorias);
        } else {
            labelMessageError.setText("No se ha seleccionado categoria para remover");
        }
    }//GEN-LAST:event_buttonRemoveCategoriaActionPerformed

    private void buttonBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBackActionPerformed
        this.dispose();
    }//GEN-LAST:event_buttonBackActionPerformed

    private void listServiciosValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listServiciosValueChanged

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

        labelDescripcion.setText("Descripcion: " + portServicio.obtenerServicioWS(servicioAux, proveedorAux).getDescripcion());
        labelPrecio.setText("Precio: " + String.valueOf(portServicio.obtenerServicioWS(servicioAux, proveedorAux).getPrecio()));
        labelOrigen.setText("Ciudad de origen: " + portServicio.obtenerServicioWS(servicioAux, proveedorAux).getOrigen().getNombre());
        if (portServicio.obtenerServicioWS(servicioAux, proveedorAux).getDestino() == null) {
            labelDestino.setText("No hay ciudad de destino");
        } else {
            labelDestino.setText("Ciudad de destino: " + portServicio.obtenerServicioWS(servicioAux, proveedorAux).getDestino().getNombre());
        }

        for (int i = 0; i < portServicio.obtenerImagenesServicioWS(servicioAux, proveedorAux).size(); i++) {
            ImageIcon imageIcon = new ImageIcon(portServicio.obtenerImagenesServicioWS(servicioAux, proveedorAux).get(i));
            Image image = imageIcon.getImage();
            Image imageFinal = image.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
            ImageIcon imageIconFinal = new ImageIcon(imageFinal);
            imagenes.get(i).setIcon(imageIconFinal);
        }


    }//GEN-LAST:event_listServiciosValueChanged

    private void textFieldPrecioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textFieldPrecioKeyTyped
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || c == KeyEvent.VK_BACKSPACE || c == KeyEvent.VK_DELETE || c == 46)) {
            evt.consume();
        }
    }//GEN-LAST:event_textFieldPrecioKeyTyped

    private void textFieldIndexImageKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textFieldIndexImageKeyTyped
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || c == KeyEvent.VK_BACKSPACE || c == KeyEvent.VK_DELETE)) {
            evt.consume();
        }
    }//GEN-LAST:event_textFieldIndexImageKeyTyped

    private void buttonChangeDescripcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonChangeDescripcionActionPerformed
        labelMessageError.setText(" ");
        if (listServicios.getSelectedValue() != null) {
            if (!textFieldDescripcion.getText().isEmpty()) {
                //int indiceServicio = listServicios.getSelectedIndex();
                String nuevaDescripcion = textFieldDescripcion.getText();
                portServicio.editarDescripcion(servicioAux, proveedorAux, nuevaDescripcion);
                //portServicio.obtenerTodosServiciosWS().get(indiceServicio).setDescripcion(nuevaDescripcion);
                labelDescripcion.setText("Descripcion: " + nuevaDescripcion);
            } else {
                labelMessageError.setText("Descripcion no puede quedar vacio");
            }
        } else {
            labelMessageError.setText("Seleccione un servicio");
        }
    }//GEN-LAST:event_buttonChangeDescripcionActionPerformed

    private void buttonChangePrecioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonChangePrecioActionPerformed
        labelMessageError.setText(" ");
        if (listServicios.getSelectedValue() != null) {
            if (!textFieldPrecio.getText().isEmpty()) {
                int validadorPrecio = 0;
                for (int i = 0; i < textFieldPrecio.getText().length(); i++) {
                    if (textFieldPrecio.getText().charAt(i) == '.') {
                        validadorPrecio++;
                    }
                }
                if (validadorPrecio <= 1 && !textFieldPrecio.getText().endsWith(".") && !textFieldPrecio.getText().startsWith(".")) {
                    //int indiceServicio = listServicios.getSelectedIndex();
                    double precioAuxiliar = Double.parseDouble(textFieldPrecio.getText());
                    portServicio.obtenerServicioWS(servicioAux, proveedorAux).setPrecio(precioAuxiliar);
                    //portServicio.obtenerTodosServiciosWS().get(indiceServicio).setPrecio(precioAuxiliar);
                    labelPrecio.setText("Precio: " + String.valueOf(precioAuxiliar));
                } else {
                    labelMessageError.setText("Precio debe ser un numero valido");
                }
            } else {
                labelMessageError.setText("Precio no puede quedar vacio");
            }
        } else {
            labelMessageError.setText("Seleccione un servicio");
        }
    }//GEN-LAST:event_buttonChangePrecioActionPerformed

    private void buttonChangeOrigenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonChangeOrigenActionPerformed
        labelMessageError.setText(" ");
        if (listServicios.getSelectedValue() != null) {
            if (listOrigen.getSelectedValue() != null) {
                int indiceServicio = listServicios.getSelectedIndex();
                String nuevaCiudad = listOrigen.getSelectedValue();
                if (!nuevaCiudad.equals(portServicio.obtenerTodosServiciosWS().get(indiceServicio).getDestino().getNombre())) {
                    portServicio.obtenerServicioWS(servicioAux, proveedorAux).setOrigen(portCiudad.obtenerCiudadWS(nuevaCiudad));
                    //portServicio.obtenerTodosServiciosWS().get(indiceServicio).setOrigen(portCiudad.obtenerCiudadWS(nuevaCiudad));
                    labelOrigen.setText("Ciudad de Origen: " + nuevaCiudad);
                } else {
                    labelMessageError.setText("Ciudad de origen y destino deben de ser diferentes");
                }
            } else {
                labelMessageError.setText("Seleccione una ciudad de origen");
            }
        } else {
            labelMessageError.setText("Seleccione un servicio");
        }
    }//GEN-LAST:event_buttonChangeOrigenActionPerformed

    private void buttonChangeDestinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonChangeDestinoActionPerformed
        labelMessageError.setText(" ");
        if (listServicios.getSelectedValue() != null) {
            if (listDestino.getSelectedValue() != null) {
                int indiceServicio = listServicios.getSelectedIndex();
                String nuevaCiudad = listDestino.getSelectedValue();
                if (!nuevaCiudad.equals(portServicio.obtenerTodosServiciosWS().get(indiceServicio).getOrigen().getNombre())) {
                    if (nuevaCiudad.equals("<null>")) {
                        portServicio.obtenerServicioWS(servicioAux, proveedorAux).setDestino(null);
                        //portServicio.obtenerTodosServiciosWS().get(indiceServicio).setDestino(null);
                        labelDestino.setText("Ciudad de Destino: Sin ciudad de destino");
                    } else {
                        portServicio.obtenerServicioWS(servicioAux, proveedorAux).setDestino(portCiudad.obtenerCiudadWS(nuevaCiudad));
                        //portServicio.obtenerTodosServiciosWS().get(indiceServicio).setDestino(portCiudad.obtenerCiudadWS(nuevaCiudad));
                        labelDestino.setText("Ciudad de Destino: " + nuevaCiudad);
                    }
                } else {
                    labelMessageError.setText("Ciudad de origen y destino deben de ser diferentes");
                }
            } else {
                labelMessageError.setText("Seleccione una ciudad de destino");
            }
        } else {
            labelMessageError.setText("Seleccione un servicio");
        }
    }//GEN-LAST:event_buttonChangeDestinoActionPerformed

    private void buttonChangeCategoriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonChangeCategoriasActionPerformed
        labelMessageError.setText(" ");
        if (listServicios.getSelectedValue() != null) {
            if (listCategorias.getModel().getSize() != 0) {
                int indiceServicio = listServicios.getSelectedIndex();
                //portServicio.obtenerTodosServiciosWS().get(indiceServicio).getCategorias().clear();
                portServicio.obtenerCategoriasServicioWS(servicioAux, proveedorAux).clear();
                DefaultListModel listaCategorias = (DefaultListModel) listCategorias.getModel();
                
                
                //Necesita un metodo para editar categorias que reciba lista de String
                List<String> categorias = new ArrayList<String>();
                for (int i = 0; i < listCategorias.getModel().getSize(); i++) {
                    categorias.add(listCategorias.getModel().getElementAt(i));
                    //portServicio.obtenerServicioWS(servicioAux, proveedorAux).setCategorias(categorias);
                }
                
                
            } else {
                labelMessageError.setText("Debe elegir una categoria como minimo");
            }
        } else {
            labelMessageError.setText("Seleccione un servicio");
        }
    }//GEN-LAST:event_buttonChangeCategoriasActionPerformed

    private void buttonAddImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddImageActionPerformed
        /*  labelMessageError.setText(" ");
        if (listServicios.getSelectedValue() != null) {
            int indiceServicio = listServicios.getSelectedIndex();
            if (portServicio.obtenerTodosServiciosWS().get(indiceServicio).getImagenes().size() < 3) {
                JFileChooser selectorImage = new JFileChooser();
                FileNameExtensionFilter filtro = new FileNameExtensionFilter("Formato imagen", "png", "jpg");
                selectorImage.setFileFilter(filtro);
                int opcion = selectorImage.showOpenDialog(this);
                if (opcion == JFileChooser.APPROVE_OPTION) {
                    Date d = new Date();
                    String nombreImagen = Long.toString(d.getTime());
                    String ruta = progappProperties.getProperty("ruta.imagenes");
                    ruta = ruta + nombreImagen + ".png";
                    File imagendestino = new File(ruta);
                    File JFile = new File(selectorImage.getSelectedFile().toString());
                    try {
                        Files.copy(JFile.toPath(), imagendestino.toPath());
                    } catch (IOException ex) {
                        Logger.getLogger(ActualizarServicio.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    portServicio.obtenerTodosServiciosWS().get(indiceServicio).setImagen(ruta);
                    for (int i = 0; i < portServicio.obtenerTodosServiciosWS().get(indiceServicio).getImagenes().size(); i++) {
                        ImageIcon imageIcon = new ImageIcon(portServicio.obtenerTodosServiciosWS().get(indiceServicio).getImagenes().get(i));
                        Image image = imageIcon.getImage();
                        Image imageFinal = image.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
                        ImageIcon imageIconFinal = new ImageIcon(imageFinal);
                        imagenes.get(i).setIcon(imageIconFinal);
                    }
                }
            } else {
                labelMessageError.setText("Se permite un maximo de 3 imagenes");
            }
        } else {
            labelMessageError.setText("Seleccione un servicio");
        }*/
    }//GEN-LAST:event_buttonAddImageActionPerformed

    private void buttonRemoveImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRemoveImageActionPerformed
        /* labelMessageError.setText(" ");
        if (listServicios.getSelectedValue() != null) {
            if (!textFieldIndexImage.getText().isEmpty()) {
                int indiceServicio = listServicios.getSelectedIndex();
                if (!portServicio.obtenerTodosServiciosWS().get(indiceServicio).getImagenes().isEmpty()) {
                    int indiceImagen = Integer.parseInt(textFieldIndexImage.getText());
                    if (indiceImagen <= portServicio.obtenerTodosServiciosWS().get(indiceServicio).getImagenes().size()
                            && indiceImagen != 0) {
                        portServicio.obtenerTodosServiciosWS().get(indiceServicio).getImagenes().remove(indiceImagen - 1);
                        for (int i = 0; i < 3; i++) {
                            imagenes.get(i).setIcon(null);
                        }
                        for (int i = 0; i < portServicio.obtenerTodosServiciosWS().get(indiceServicio).getImagenes().size(); i++) {
                            ImageIcon imageIcon = new ImageIcon(portServicio.obtenerTodosServiciosWS().get(indiceServicio).getImagenes().get(i));
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
        } else {
            labelMessageError.setText("Seleccione un servicio");
        }*/
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
            java.util.logging.Logger.getLogger(ActualizarServicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ActualizarServicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ActualizarServicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ActualizarServicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new ActualizarServicio().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(ActualizarServicio.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAddCategoria;
    private javax.swing.JButton buttonAddImage;
    private javax.swing.JButton buttonBack;
    private javax.swing.JButton buttonChangeCategorias;
    private javax.swing.JButton buttonChangeDescripcion;
    private javax.swing.JButton buttonChangeDestino;
    private javax.swing.JButton buttonChangeOrigen;
    private javax.swing.JButton buttonChangePrecio;
    private javax.swing.JButton buttonRemoveCategoria;
    private javax.swing.JButton buttonRemoveImage;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel labelDescripcion;
    private javax.swing.JLabel labelDestino;
    private javax.swing.JLabel labelImage1;
    private javax.swing.JLabel labelImage2;
    private javax.swing.JLabel labelImage3;
    private javax.swing.JLabel labelMessageError;
    private javax.swing.JLabel labelOrigen;
    private javax.swing.JLabel labelPrecio;
    private javax.swing.JLabel labelServicios;
    private javax.swing.JList<String> listCategorias;
    private javax.swing.JList<String> listDestino;
    private javax.swing.JList<String> listOrigen;
    private javax.swing.JList<String> listServicios;
    private javax.swing.JTextField textFieldDescripcion;
    private javax.swing.JTextField textFieldIndexImage;
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
