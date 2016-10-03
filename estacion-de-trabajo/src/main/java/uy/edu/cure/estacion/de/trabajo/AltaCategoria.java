package uy.edu.cure.estacion.de.trabajo;

import java.lang.reflect.InvocationTargetException;
import java.util.Enumeration;
import java.util.Iterator;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import uy.edu.cure.servidor.central.dto.Categoria;
import uy.edu.cure.servidor.central.lib.CategoriaControllerImpl;
import uy.edu.cure.servidor.central.lib.jeringa.Jeringa;
import uy.edu.cure.servidor.central.lib.jeringa.JeringaInjector;

/**
 *
 * @author Stephanie
 */
public class AltaCategoria extends javax.swing.JFrame {
    @Jeringa(value="categoriacontroller")
    private CategoriaControllerImpl categoriaControllerForm;

    public AltaCategoria() {
        initComponents();
        setLocationRelativeTo(null);
        
          try {
            JeringaInjector.getInstance().inyectar(this);
        } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }
        
        DefaultTreeModel model = (DefaultTreeModel) treeCategorias.getModel();
        DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();
        Iterator<Categoria> iteratorCategorias = categoriaControllerForm.obtenerTodosCategorias().iterator();
        while (iteratorCategorias.hasNext()) {
            Categoria categoriaAuxiliar = iteratorCategorias.next();
            if (categoriaAuxiliar.getPadre() == null) {
                root.add(new DefaultMutableTreeNode(categoriaAuxiliar.getNombre()));
            } else {
                Enumeration<DefaultMutableTreeNode> enumerationNodo = root.depthFirstEnumeration();
                while (enumerationNodo.hasMoreElements()) {
                    DefaultMutableTreeNode nodoAuxiliar = enumerationNodo.nextElement();
                    if (nodoAuxiliar.toString().equals(categoriaAuxiliar.getPadre().getNombre())) {
                        model.insertNodeInto(new DefaultMutableTreeNode(categoriaAuxiliar.getNombre()), nodoAuxiliar, nodoAuxiliar.getChildCount());
                    }
                }
            }
        }
        model.reload();
        treeCategorias.setRootVisible(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        lblNuevaCategoria = new javax.swing.JLabel();
        txtCategoria = new javax.swing.JTextField();
        lblCategoriaPadre = new javax.swing.JLabel();
        bSalair = new javax.swing.JButton();
        bAceptar = new javax.swing.JButton();
        errorNuevaCategoria = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        treeCategorias = new javax.swing.JTree();
        errorAceptar = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblTitulo.setText("Dar de alta una categoría");

        lblNuevaCategoria.setText("Nueva Categoría:");

        lblCategoriaPadre.setText("Categoría Padre:");

        bSalair.setText("Salir");
        bSalair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSalairActionPerformed(evt);
            }
        });

        bAceptar.setText("Aceptar");
        bAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAceptarActionPerformed(evt);
            }
        });

        errorNuevaCategoria.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("root");
        treeCategorias.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        jScrollPane1.setViewportView(treeCategorias);

        errorAceptar.setForeground(new java.awt.Color(255, 0, 0));
        errorAceptar.setText(" ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(bAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(errorAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(41, 41, 41)))
                        .addComponent(bSalair, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblCategoriaPadre, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblNuevaCategoria)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(errorNuevaCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(154, 154, 154))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(errorNuevaCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblNuevaCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(lblCategoriaPadre, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(bAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bSalair, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(errorAceptar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bSalairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSalairActionPerformed
        this.dispose();
    }//GEN-LAST:event_bSalairActionPerformed

    private void bAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAceptarActionPerformed
        errorNuevaCategoria.setText(" ");
        errorAceptar.setText(" ");
        DefaultMutableTreeNode nodoSeleccionado = (DefaultMutableTreeNode) treeCategorias.getLastSelectedPathComponent();
        if (!txtCategoria.getText().isEmpty()) {
            if (!categoriaControllerForm.existeCategoria(txtCategoria.getText())) {
                if (nodoSeleccionado != null) {
                    categoriaControllerForm.darAltaCategoria(txtCategoria.getText(), nodoSeleccionado.toString());
                } else {
                    categoriaControllerForm.darAltaCategoria(txtCategoria.getText(), " ");
                }
                /**
                 * mostrar nuevo arbol *
                 */
                DefaultTreeModel model = (DefaultTreeModel) treeCategorias.getModel();
                DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();
                if (categoriaControllerForm.obtenerCategoria(txtCategoria.getText()).getPadre() == null) {
                    model.insertNodeInto(new DefaultMutableTreeNode(txtCategoria.getText()),
                            root, root.getChildCount());
                } else {
                    model.insertNodeInto(new DefaultMutableTreeNode(txtCategoria.getText()),
                            nodoSeleccionado, nodoSeleccionado.getChildCount());
                }
                model.reload();
            } else {
                errorNuevaCategoria.setText("La categoria ingresada ya existe");
            }
        } else {
            errorAceptar.setText("El nombre de la categoria no puede estar vacio");
        }
    }//GEN-LAST:event_bAceptarActionPerformed

    public static void main(String args[]) {

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
            java.util.logging.Logger.getLogger(AltaCategoria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AltaCategoria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AltaCategoria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AltaCategoria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AltaCategoria().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bAceptar;
    private javax.swing.JButton bSalair;
    private javax.swing.JLabel errorAceptar;
    private javax.swing.JLabel errorNuevaCategoria;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCategoriaPadre;
    private javax.swing.JLabel lblNuevaCategoria;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTree treeCategorias;
    private javax.swing.JTextField txtCategoria;
    // End of variables declaration//GEN-END:variables

}
