/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package petcare.view;

import petcare.model.ThuCung;
import java.sql.Connection;
import petcare.control.MyMSSQLControl;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import petcare.control.GlobalValues;

/**
 *
 * @author ngoc
 */
public class QLThuCung extends javax.swing.JFrame {

    static DefaultTableModel dtmtc = new DefaultTableModel();
    static main m;
    static int cv;// chuc vu
    static QLKhachHang qlkh;
    static ThuCung tc; // dung để luu thông tin thú cưng đươc selected de dua qua ban chinh sua

    /**
     * Creates new form QLThuCung
     */
    public QLThuCung(main m, QLKhachHang qlkh) {
        this.m = m;
        this.cv = m.chucvu;
        this.qlkh = qlkh;
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(QLThuoc.class.getName()).log(Level.SEVERE, null, ex);
        }
        initComponents();
        getContentPane().setBackground(GlobalValues.formBackgroundColor);
        this.setLocationRelativeTo(null);
        customUI();
        dtmtc.setRowCount(0);
//        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icondn.png")));
        //jttc.setBackground(Color.WHITE)
        if (this.qlkh == null) {
            loaddata();
        }
        if (this.m == null) {
            loaddatatckh(); // load data thu cung cua 1 khach hang
        }
        GetInforSelected();
    }

    public static int getCv() {
        return cv;
    }

    public static void setCv(int cv) {
        QLThuCung.cv = cv;
    }

    public void loaddatatckh() {
        //String a =qlkh.jtkh.getValueAt(qlkh.jtkh.getSelectedRow(), qlkh.jtkh.getSelectedColumn()).toString();
        Connection conn = null;
        Statement stt = null;
        ResultSet rs = null;

        // sai o duoi day
        try {

            conn = MyMSSQLControl.getConnect();
            if (conn == null) {
                JOptionPane.showMessageDialog(rootPane, "lỗi kết nối");
            }
            String sql = "select * from THUCUNG where MAKH=?";

            PreparedStatement ps = conn.prepareStatement(sql);
            // cap nhat khach hang moi
            ps.setString(1, qlkh.kh.getMakh());

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
                dtmtc.setColumnIdentifiers(colum);

            }
            ;
            tblThuCung.setModel(dtmtc);
            // JOptionPane.showMessageDialog(rootPane, qlkh.kh.getMakh());
            // LEN DU LIEU TUNG ROW
            while (ps.getResultSet().next()) {
                row = new Vector();
                //System.out.println("MACD: "+ rs.getString(1)+ " TENCD: "+rs.getString(2));
                row.addElement(ps.getResultSet().getString(1));
                row.addElement(ps.getResultSet().getString(2));
                row.addElement(ps.getResultSet().getString(3));
                row.addElement(ps.getResultSet().getInt(4));
                row.addElement(ps.getResultSet().getString(5));
                row.addElement(ps.getResultSet().getString(6));
                dtmtc.addRow(row);

            }
            // JOptionPane.showMessageDialog(rootPane, qlkh.kh.getMakh());
            tblThuCung.setModel(dtmtc);
            customTableUI();
            tblThuCung.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {  // DUA DU LIEU VO 2 O TEXT FIELD TUONG UNG
                    if (tblThuCung.getSelectedRow() >= 0) {
                        String matc = tblThuCung.getValueAt(tblThuCung.getSelectedRow(), 0).toString();
                        String makh = tblThuCung.getValueAt(tblThuCung.getSelectedRow(), 1).toString();
                        String tentc = tblThuCung.getValueAt(tblThuCung.getSelectedRow(), 2).toString();
                        int tuoi = Integer.parseInt(tblThuCung.getValueAt(tblThuCung.getSelectedRow(), 3).toString());
                        String cloai = tblThuCung.getValueAt(tblThuCung.getSelectedRow(), 4).toString();
                        String ttrang = tblThuCung.getValueAt(tblThuCung.getSelectedRow(), 5).toString();
                        tc = new ThuCung(matc, tentc, tuoi, cloai, ttrang, makh);
                    }

                }

            });
            // JOptionPane.showMessageDialog(rootPane, qlkh.kh.getMakh());
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(rootPane, "loi ket nối");

        }

    }

    private void customTableUI(){
        tblThuCung.getColumnModel().getColumn(0).setPreferredWidth(20);
        tblThuCung.getColumnModel().getColumn(1).setPreferredWidth(50);
        tblThuCung.getColumnModel().getColumn(2).setPreferredWidth(5);
        tblThuCung.getColumnModel().getColumn(3).setPreferredWidth(5);
        tblThuCung.getColumnModel().getColumn(4).setPreferredWidth(40);
        tblThuCung.getColumnModel().getColumn(5).setPreferredWidth(50);
    }
    public void GetInforSelected() {

    }

    public static QLKhachHang getQlkh() {
        return qlkh;
    }

    public static void setQlkh(QLKhachHang qlkh) {
        QLThuCung.qlkh = qlkh;
    }

    public static main getM() {
        return m;
    }

    public static void setM(main m) {
        QLThuCung.m = m;
    }

    public static ThuCung getTc() {
        return tc;
    }

    public static void setTc(ThuCung tc) {
        QLThuCung.tc = tc;
    }

    public void loaddata() {
        dtmtc.setRowCount(0);
        Connection conn = null;
        Statement stt = null;
        ResultSet rs = null;
        // trươc khi ket noi fail add thu vien jdbc42.rar
        try {
            conn = MyMSSQLControl.getConnect();
            if (conn == null) {
                JOptionPane.showMessageDialog(rootPane, "lỗi kết nối");
            }
            stt = conn.createStatement();
            String sql = "select * from THUCUNG";
            rs = stt.executeQuery(sql);
            ResultSetMetaData md = rs.getMetaData();
            Vector row;
            Vector colum = new Vector();
            int number = md.getColumnCount();
            // LAY TEN TUNG COLLUMN name (ROW 1)
            for (int i = 1; i <= number; i++) {
                colum.add(md.getColumnName(i));
                //System.out.println(md.getColumnName(i));
                dtmtc.setColumnIdentifiers(colum);

            }
            ;
            tblThuCung.setModel(dtmtc);

            // LEN DU LIEU TUNG ROW
            while (rs.next()) {
                row = new Vector();
                //System.out.println("MACD: "+ rs.getString(1)+ " TENCD: "+rs.getString(2));
                row.addElement(rs.getString(1));
                row.addElement(rs.getString(2));
                row.addElement(rs.getString(3));
                row.addElement(rs.getInt(4));
                row.addElement(rs.getString(5));
                row.addElement(rs.getString(6));
                dtmtc.addRow(row);

            }

            tblThuCung.setModel(dtmtc);
            tblThuCung.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {  // DUA DU LIEU VO 2 O TEXT FIELD TUONG UNG
                    if (tblThuCung.getSelectedRow() >= 0) {
                        String matc = tblThuCung.getValueAt(tblThuCung.getSelectedRow(), 0).toString();
                        String makh = tblThuCung.getValueAt(tblThuCung.getSelectedRow(), 1).toString();
                        String tentc = tblThuCung.getValueAt(tblThuCung.getSelectedRow(), 2).toString();
                        int tuoi = Integer.parseInt(tblThuCung.getValueAt(tblThuCung.getSelectedRow(), 3).toString());
                        String cloai = tblThuCung.getValueAt(tblThuCung.getSelectedRow(), 4).toString();
                        String ttrang = tblThuCung.getValueAt(tblThuCung.getSelectedRow(), 5).toString();
                        tc = new ThuCung(matc, tentc, tuoi, cloai, ttrang, makh);
                    }

                }

            });

        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "loi ket nối");

        }
    }

    public static DefaultTableModel getDtmtc() {
        return dtmtc;
    }

    public static void setDtmtc(DefaultTableModel dtmtc) {
        QLThuCung.dtmtc = dtmtc;
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
        tblThuCung = new javax.swing.JTable();
        pnSearch = new javax.swing.JPanel();
        btnXemDonThuoc = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        btnKeDon = new javax.swing.JButton();
        pnAction = new javax.swing.JPanel();
        btnEdit = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Quản lý thú cưng");
        setLocationByPlatform(true);
        setPreferredSize(new java.awt.Dimension(1366, 700));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("DANH SÁCH THÚ CƯNG");

        tblThuCung.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6"
            }
        ));
        tblThuCung.setGridColor(new java.awt.Color(255, 255, 255));
        tblThuCung.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        jScrollPane1.setViewportView(tblThuCung);

        btnXemDonThuoc.setText("Xem đơn thuốc");
        btnXemDonThuoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXemDonThuocActionPerformed(evt);
            }
        });

        btnBack.setText("Trở lại");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        btnKeDon.setText("Kê đơn");
        btnKeDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKeDonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnSearchLayout = new javax.swing.GroupLayout(pnSearch);
        pnSearch.setLayout(pnSearchLayout);
        pnSearchLayout.setHorizontalGroup(
            pnSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnSearchLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnSearchLayout.createSequentialGroup()
                        .addComponent(btnXemDonThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnKeDon))
                    .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        pnSearchLayout.setVerticalGroup(
            pnSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnSearchLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXemDonThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnKeDon))
                .addGap(18, 18, 18)
                .addComponent(btnBack)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnEdit.setText("Sửa");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnExit.setText("Thoát");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        btnAdd.setText("Thêm");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnDelete.setText("Xóa");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnActionLayout = new javax.swing.GroupLayout(pnAction);
        pnAction.setLayout(pnActionLayout);
        pnActionLayout.setHorizontalGroup(
            pnActionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnActionLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnActionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnActionLayout.createSequentialGroup()
                        .addComponent(btnAdd)
                        .addGap(18, 18, 18)
                        .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pnActionLayout.setVerticalGroup(
            pnActionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnActionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnActionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnDelete, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnActionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnEdit)
                        .addComponent(btnAdd)))
                .addGap(18, 18, 18)
                .addComponent(btnExit)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 769, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(pnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnAction, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(337, 337, 337))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 359, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnAction, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnSearch, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        this.setVisible(false);
        if (m != null) {
            m.setVisible(true);
        } else {
            qlkh.setVisible(true);
        }
        dtmtc.setRowCount(0);
        this.loaddata();
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // xu ly them thu cung
        XLThuCung ttc = new XLThuCung(this, null, 0);// o tuc la dang them
        ttc.setVisible(true);
        dtmtc.setRowCount(0);
        loaddata();

        //this.setVisible(true);
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // sửa thông tin thú cưng nhưng không đươc sữa các khóa
        XLThuCung ttc = new XLThuCung(this, null, 1);// o tuc la dang them
        ttc.setVisible(true);
        dtmtc.setRowCount(0);
        loaddata();

    }//GEN-LAST:event_btnEditActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        //xoa thu cung
        Connection conn = null;
        String url = "jdbc:sqlserver://localhost;databaseName=PETCARE;user=sa;password=250497kakashi";
        try {
            conn = MyMSSQLControl.getConnect();
            if (conn == null) {
                JOptionPane.showMessageDialog(rootPane, "lỗi kết nối");
            }
            String sql = "delete THUCUNG where MATC=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, tblThuCung.getValueAt(tblThuCung.getSelectedRow(), 0).toString());
            int a = ps.executeUpdate();
            if (a > 0) {
                //JOptionPane.showConfirmDialog(this, "them thanh cong");
                // JOptionPane.showMessageDialog(this, "Xóa thành công");
                dtmtc.setRowCount(0);
                loaddata();
            }
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(rootPane, "Xóa không thành công");
        }


    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnKeDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKeDonActionPerformed
        // bac sy thuc hien ke don thuoc o day
        KeDon kd = new KeDon(this);
        kd.setVisible(true);

    }//GEN-LAST:event_btnKeDonActionPerformed

    private void btnXemDonThuocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXemDonThuocActionPerformed
        this.setVisible(false);
        ToaThuocTC tttc = new ToaThuocTC(this);
        tttc.setVisible(true);
        tttc.loaddata();
    }//GEN-LAST:event_btnXemDonThuocActionPerformed

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
            java.util.logging.Logger.getLogger(QLThuCung.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QLThuCung.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QLThuCung.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QLThuCung.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QLThuCung(m, qlkh).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnKeDon;
    private javax.swing.JButton btnXemDonThuoc;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pnAction;
    private javax.swing.JPanel pnSearch;
    private javax.swing.JTable tblThuCung;
    // End of variables declaration//GEN-END:variables

    private void customUI() {
        pnAction.setBackground(GlobalValues.formBackgroundColor);
        pnSearch.setBackground(GlobalValues.formBackgroundColor);
    }

}
