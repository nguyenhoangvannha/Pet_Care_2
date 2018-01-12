/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package petcare.view;

import java.sql.Connection;
import java.sql.Date;
import petcare.control.MyMSSQLControl;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import static petcare.view.QLThuCung.dtmtc;

/**
 *
 * @author ngoc
 */
public class ToaThuocTC extends javax.swing.JDialog {

    static QLThuCung qltc;
    static DefaultTableModel dtmdt = new DefaultTableModel();
    static String matc;
    static String mat;
    static int soluong;
    static Date nkdon;
    static String madt;

    /**
     * Creates new form toathuoctc
     */
    public ToaThuocTC(QLThuCung qltc) {
        this.qltc = qltc;
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(ToaThuocTC.class.getName()).log(Level.SEVERE, null, ex);
        }
        initComponents();
        this.setLocationRelativeTo(null);
        this.setModal(true);
        dtmdt.setRowCount(0);
        getContentPane().setBackground(java.awt.Color.WHITE);
//        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icondn.png")));
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jtdt = new javax.swing.JTable();
        btnEdit = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Đơn thuốc");
        setLocationByPlatform(true);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("ĐƠN THUỐC");

        jtdt.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5"
            }
        ));
        jtdt.setGridColor(new java.awt.Color(255, 255, 255));
        jScrollPane1.setViewportView(jtdt);

        btnEdit.setText("Sửa");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnDelete.setText("Xóa");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnExit.setText("Thoát");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        btnBack.setText("Trở lại");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 619, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(32, 32, 32))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(282, 282, 282))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnDelete, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnEdit))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnExit)
                    .addComponent(btnBack))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    public void loaddata() {
        dtmdt.setRowCount(0);
        Connection conn = null;
        Statement stt = null;
        try {
            conn = MyMSSQLControl.getConnect();
            if (conn == null) {
                JOptionPane.showMessageDialog(rootPane, "lỗi kết nối");
            }
            String sql = "select MADT, MATC, MATH, SOLUONG, NKDON from DONTHUOC where MATC=?";

            PreparedStatement ps = conn.prepareStatement(sql);
            // cap nhat khach hang moi
            ps.setString(1, qltc.tc.getMatc());
            ps.executeQuery();
            //JOptionPane.showMessageDialog(rootPane, qlkh.kh.getMakh());
            ResultSetMetaData md = ps.getMetaData();
            Vector row;
            Vector colum = new Vector();
            int number = md.getColumnCount();
            // LAY TEN TUNG COLLUMN name (ROW 1)
            for (int i = 1; i <= number; i++) {
                colum.add(md.getColumnName(i));
                //System.out.println(md.getColumnName(i));
                dtmdt.setColumnIdentifiers(colum);

            }
            ;
            jtdt.setModel(dtmdt);
            // JOptionPane.showMessageDialog(rootPane, qlkh.kh.getMakh());
            // LEN DU LIEU TUNG ROW
            while (ps.getResultSet().next()) {
                row = new Vector();
                //System.out.println("MACD: "+ rs.getString(1)+ " TENCD: "+rs.getString(2));
                row.addElement(ps.getResultSet().getString(1));
                row.addElement(ps.getResultSet().getString(2));
                row.addElement(ps.getResultSet().getString(3));
                row.addElement(ps.getResultSet().getInt(4));
                row.addElement(ps.getResultSet().getDate(5));
                dtmdt.addRow(row);

            }
            // JOptionPane.showMessageDialog(rootPane, qlkh.kh.getMakh());
            jtdt.setModel(dtmdt);
            jtdt.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {  // DUA DU LIEU VO 2 O TEXT FIELD TUONG UNG
                    if (jtdt.getSelectedRow() >= 0) {
                        madt = jtdt.getValueAt(jtdt.getSelectedRow(), 0).toString();
                        matc = jtdt.getValueAt(jtdt.getSelectedRow(), 1).toString();
                        mat = jtdt.getValueAt(jtdt.getSelectedRow(), 2).toString();
                        soluong = Integer.parseInt(jtdt.getValueAt(jtdt.getSelectedRow(), 3).toString());
                        nkdon = Date.valueOf(jtdt.getValueAt(jtdt.getSelectedRow(), 4).toString());

                    }

                }

            });
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Lỗi kết nối");
        }
    }
    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // sửa thông tin thú cưng nhưng không đươc sữa các khóa
        SuaToaThuocTC tttc = new SuaToaThuocTC(this);// sua toan thuoc thu cung mà viêt nhầm làm biến sửa
        tttc.setVisible(true);
        dtmdt.setRowCount(0);
        loaddata();
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        //xoa toa thuoc thu cung
        Connection conn = null;
        try {
            conn = MyMSSQLControl.getConnect();
            if (conn == null) {
                JOptionPane.showMessageDialog(rootPane, "lỗi kết nối");
            }
            String sql = "delete DONTHUOC where MADT=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, jtdt.getValueAt(jtdt.getSelectedRow(), 0).toString());
            int a = ps.executeUpdate();
            if (a > 0) {
                //JOptionPane.showConfirmDialog(this, "them thanh cong");
                // JOptionPane.showMessageDialog(this, "Xóa thành công");
                dtmdt.setRowCount(0);
                loaddata();
            }
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(rootPane, "Xóa không thành công");
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        this.setVisible(false);
        qltc.setVisible(true);
        dtmtc.setRowCount(0);
        qltc.loaddata();
    }//GEN-LAST:event_btnBackActionPerformed

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
            java.util.logging.Logger.getLogger(ToaThuocTC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ToaThuocTC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ToaThuocTC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ToaThuocTC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ToaThuocTC(qltc).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnExit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtdt;
    // End of variables declaration//GEN-END:variables
}
