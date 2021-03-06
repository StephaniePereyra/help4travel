/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.cure.estacion.de.trabajo;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import uy.edu.cure.servidor.central.soap.client.RankigWSImplService;
import uy.edu.cure.servidor.central.soap.client.RankingWS;

/**
 *
 * @author juan
 */
public class Rankig extends javax.swing.JFrame {

    /**
     * Creates new form Rankig
     */
    public Rankig() {

        String ranking = "Proveedor     Servicio        Visitas\n\n";
        RankigWSImplService rankigWSImplService = null;
        RankingWS portRank;

        try {
            rankigWSImplService = new RankigWSImplService(new URL("http://localhost:8080/servidor-central-webapp/soap/RankigWSImplService?wsdl"));
        } catch (MalformedURLException ex) {
        }
        portRank = rankigWSImplService.getRankigWSImplPort();
        for (int i = 0; i < portRank.obtenerRanking().size(); i++) {
            ranking = ranking + portRank.obtenerRanking().get(i).getProveedor() + "     " + portRank.obtenerRanking().get(i).getServicio() + "      " + portRank.obtenerRanking().get(i).getVisitas() + "\n";

        }

        initComponents();
        Object col[] = {"Proveedor", "Servicio", "Visitas"};
        DefaultTableModel tablemodel = new DefaultTableModel(new Object[0][0], col);
        List<uy.edu.cure.servidor.central.soap.client.Ranking> Ranking = portRank.obtenerRanking();

        for (int i = 0; i < Ranking.size(); i++) {
            Object[] objs = new Object[3];

            objs[0] = Ranking.get(i).getProveedor();
            objs[1] = Ranking.get(i).getServicio();
            objs[2] = Ranking.get(i).getVisitas();

            tablemodel.addRow(objs);
        }

        jTable1.setModel(tablemodel);

        TableRowSorter<TableModel> elQueOrdena = new TableRowSorter<TableModel>(tablemodel);
        jTable1.setRowSorter(elQueOrdena);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)
                .addGap(13, 13, 13))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(Rankig.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Rankig.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Rankig.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Rankig.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Rankig().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
