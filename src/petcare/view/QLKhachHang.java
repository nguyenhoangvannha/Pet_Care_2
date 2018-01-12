/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package petcare.view;

import petcare.model.KhachHang;
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
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import petcare.control.GlobalValues;
import static petcare.view.QLThuCung.dtmtc;

/**
 *
 * @author ngoc
 */
public class QLKhachHang extends javax.swing.JFrame {

    static DefaultTableModel dtmkh = new DefaultTableModel();
    static main m;
    static KhachHang kh;
    static JTable tblKhachHang1;

    /**
     * Creates new form qlKhachHang
     */
    public QLKhachHang(main m) {
        this.m = m;
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
        initComponents();
        getContentPane().setBackground(GlobalValues.formBackgroundColor);
        this.setLocationRelativeTo(null);
        customUI();
        //setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icondn.png")));
        //jttc.setBackground(Color.WHITE)
        loaddata();

        tblKhachHang.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (tblKhachHang.getSelectedRow() >= 0) {
                    String makh = tblKhachHang.getValueAt(tblKhachHang.getSelectedRow(), 0).toString();
                    String tenkh = tblKhachHang.getValueAt(tblKhachHang.getSelectedRow(), 1).toString();
                    String gtinh = tblKhachHang.getValueAt(tblKhachHang.getSelectedRow(), 2).toString();
                    String diachi = tblKhachHang.getValueAt(tblKhachHang.getSelectedRow(), 3).toString();
                    String sdt = tblKhachHang.getValueAt(tblKhachHang.getSelectedRow(), 4).toString();
                    String email = tblKhachHang.getValueAt(tblKhachHang.getSelectedRow(), 5).toString();
                    String cmnd = tblKhachHang.getValueAt(tblKhachHang.getSelectedRow(), 6).toString();
                    int soluong = Integer.parseInt(tblKhachHang.getValueAt(tblKhachHang.getSelectedRow(), 7).toString());
                    kh = new KhachHang(makh, tenkh, gtinh, diachi, sdt, email, cmnd, soluong);
                }
            }

        });

    }

    public static JTable getJtkh1() {
        return tblKhachHang1;
    }

    public static void setJtkh1(JTable tblKhachHang1) {
        QLKhachHang.tblKhachHang1 = tblKhachHang1;
    }

    public JTable getJtkh() {
        return tblKhachHang;
    }

    public void setJtkh(JTable tblKhachHang) {
        this.tblKhachHang = tblKhachHang;
    }

    public static KhachHang getKh() {
        return kh;
    }

    public static void setKh(KhachHang kh) {
        QLKhachHang.kh = kh;
    }

    public static DefaultTableModel getDtmkh() {
        return dtmkh;
    }

    public static void setDtmkh(DefaultTableModel dtmkh) {
        QLKhachHang.dtmkh = dtmkh;
    }

    public static main getM() {
        return m;
    }

    public static void setM(main m) {
        QLKhachHang.m = m;
    }

    public void loaddata() {
        Connection conn = null;
        Statement stt = null;
        ResultSet rs = null;
        dtmtc.setRowCount(0);
        dtmkh.setRowCount(0);
        try {
            conn = MyMSSQLControl.getConnect();
            if (conn == null) {
                JOptionPane.showMessageDialog(rootPane, "lỗi kết nối");
            }
            stt = conn.createStatement();
            String sql = "select * from KHACHHANG";
            rs = stt.executeQuery(sql);
            ResultSetMetaData md = rs.getMetaData();
            Vector row;
            Vector colum = new Vector();
            int number = md.getColumnCount();
            // LAY TEN TUNG COLLUMN name (ROW 1)
            for (int i = 1; i <= number; i++) {
                colum.add(md.getColumnName(i));
                //System.out.println(md.getColumnName(i));
                dtmkh.setColumnIdentifiers(colum);

            }
            ;
            tblKhachHang.setModel(dtmkh);

            // LEN DU LIEU TUNG ROW
            while (rs.next()) {
                row = new Vector();
                //System.out.println("MACD: "+ rs.getString(1)+ " TENCD: "+rs.getString(2));
                row.addElement(rs.getString(1));
                row.addElement(rs.getString(2));
                row.addElement(rs.getString(3));
                //row.addElement(rs.getInt(4));
                row.addElement(rs.getString(4));
                row.addElement(rs.getString(5));
                row.addElement(rs.getString(6));
                row.addElement(rs.getString(7));
                row.addElement(rs.getInt(8));
                dtmkh.addRow(row);

            }
            
            tblKhachHang.setModel(dtmkh);
            customTableUI();
            tblKhachHang.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {  // DUA DU LIEU VO 2 O TEXT FIELD TUONG UNG
                    if (tblKhachHang.getSelectedRow() >= 0) {
                        String makh = tblKhachHang.getValueAt(tblKhachHang.getSelectedRow(), 0).toString();
                        String tenkh = tblKhachHang.getValueAt(tblKhachHang.getSelectedRow(), 1).toString();
                        String gtinh = tblKhachHang.getValueAt(tblKhachHang.getSelectedRow(), 2).toString();
                        // int diachi = Integer.parseInt(tblKhachHang.getValueAt(tblKhachHang.getSelectedRow(), 3).toString());
                        String dchi = tblKhachHang.getValueAt(tblKhachHang.getSelectedRow(), 3).toString();
                        String sdt = tblKhachHang.getValueAt(tblKhachHang.getSelectedRow(), 4).toString();
                        String email = tblKhachHang.getValueAt(tblKhachHang.getSelectedRow(), 5).toString();
                        String cmnd = tblKhachHang.getValueAt(tblKhachHang.getSelectedRow(), 6).toString();
                        int soluong = Integer.parseInt(tblKhachHang.getValueAt(tblKhachHang.getSelectedRow(), 7).toString());
                        kh = new KhachHang(makh, tenkh, gtinh, dchi, sdt, email, cmnd, soluong);
                    }

                }

            });

        } catch (Exception e) {
            //JOptionPane.showMessageDialog(rootPane, "loi ket nối");

        }
    }

    private void customTableUI(){
        tblKhachHang.getColumnModel().getColumn(0).setPreferredWidth(20);
        tblKhachHang.getColumnModel().getColumn(1).setPreferredWidth(50);
        tblKhachHang.getColumnModel().getColumn(2).setPreferredWidth(5);
        tblKhachHang.getColumnModel().getColumn(3).setPreferredWidth(150);
        tblKhachHang.getColumnModel().getColumn(4).setPreferredWidth(40);
        tblKhachHang.getColumnModel().getColumn(5).setPreferredWidth(50);
        tblKhachHang.getColumnModel().getColumn(6).setPreferredWidth(40);
        tblKhachHang.getColumnModel().getColumn(7).setPreferredWidth(40);
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
        tblKhachHang = new javax.swing.JTable();
        pnAction = new javax.swing.JPanel();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        btnThoat = new javax.swing.JButton();
        btnKhamBenh = new javax.swing.JButton();
        pnSearch = new javax.swing.JPanel();
        btnNhapThongTin = new javax.swing.JButton();
        btnTroLai = new javax.swing.JButton();
        btnTimKiem = new javax.swing.JButton();
        txtTaiKhoan = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Quản lý khách hàng");
        setPreferredSize(new java.awt.Dimension(1366, 700));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("DANH SÁCH KHÁCH HÀNG");

        tblKhachHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8"
            }
        ));
        tblKhachHang.setGridColor(new java.awt.Color(255, 255, 255));
        tblKhachHang.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        jScrollPane1.setViewportView(tblKhachHang);

        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnThoat.setText("Thoát");
        btnThoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoatActionPerformed(evt);
            }
        });

        btnKhamBenh.setText("Khám bệnh");
        btnKhamBenh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKhamBenhActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnActionLayout = new javax.swing.GroupLayout(pnAction);
        pnAction.setLayout(pnActionLayout);
        pnActionLayout.setHorizontalGroup(
            pnActionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnActionLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnActionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnActionLayout.createSequentialGroup()
                        .addComponent(btnThem)
                        .addGap(18, 18, 18)
                        .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnActionLayout.createSequentialGroup()
                        .addComponent(btnKhamBenh)
                        .addGap(18, 18, 18)
                        .addComponent(btnThoat, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pnActionLayout.setVerticalGroup(
            pnActionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnActionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnActionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnXoa, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnActionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnSua)
                        .addComponent(btnThem)))
                .addGap(18, 18, 18)
                .addGroup(pnActionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThoat)
                    .addComponent(btnKhamBenh))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnNhapThongTin.setText("Nhập thông tin thú cưng");
        btnNhapThongTin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNhapThongTinActionPerformed(evt);
            }
        });

        btnTroLai.setText("Trở lại");
        btnTroLai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTroLaiActionPerformed(evt);
            }
        });

        btnTimKiem.setText("Tìm kiếm");
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        txtTaiKhoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTaiKhoanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnSearchLayout = new javax.swing.GroupLayout(pnSearch);
        pnSearch.setLayout(pnSearchLayout);
        pnSearchLayout.setHorizontalGroup(
            pnSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnSearchLayout.createSequentialGroup()
                .addComponent(btnTroLai, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(pnSearchLayout.createSequentialGroup()
                .addComponent(btnNhapThongTin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnTimKiem)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnSearchLayout.setVerticalGroup(
            pnSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnSearchLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTimKiem)
                    .addComponent(txtTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNhapThongTin))
                .addGap(18, 18, 18)
                .addComponent(btnTroLai)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 770, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(261, 261, 261)
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(pnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnAction, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 363, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnAction, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // sửa thông tin thú cưng nhưng không đươc sữa các khóa

        XLKhachHang tkh = new XLKhachHang(this, 1);// o tuc la dang them
        tkh.setVisible(true);
        loaddata();
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        //xoa khach hang
        Connection conn = null;
        try {
            conn = MyMSSQLControl.getConnect();
            if (conn == null) {
                JOptionPane.showMessageDialog(rootPane, "lỗi kết nối");
            }
            String sql = "delete KHACHHANG where MAKH=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, tblKhachHang.getValueAt(tblKhachHang.getSelectedRow(), 0).toString());
            int a = ps.executeUpdate();
            if (a > 0) {
                //JOptionPane.showConfirmDialog(this, "them thanh cong");
                // JOptionPane.showMessageDialog(this, "Xóa thành công");
                dtmkh.setRowCount(0);
                loaddata();
            }
            // else JOptionPane.showMessageDialog(rootPane, "Xóa không thành công");
        } catch (Exception e) {
            // JOptionPane.showMessageDialog(rootPane, "Xóa không thành công");
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // xu ly them thu cung
        XLKhachHang tkh = new XLKhachHang(this, 0);// o tuc la dang them
        tkh.setVisible(true);
        loaddata();
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoatActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnThoatActionPerformed

    private void btnTroLaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTroLaiActionPerformed
        this.dispose();
        m.setVisible(true);
    }//GEN-LAST:event_btnTroLaiActionPerformed

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        // tim kiếm

        boolean kt = false;
        for (int i = 0; i < tblKhachHang.getRowCount(); i++) {
            for (int j = 0; j < tblKhachHang.getColumnCount(); j++) {

                if (txtTaiKhoan.getText().equals(tblKhachHang.getValueAt(i, j).toString())) {
                    kt = true;
                    tblKhachHang.setCellSelectionEnabled(true);
                    tblKhachHang.changeSelection(i, j, false, false);
                    tblKhachHang.requestFocus();
                }
            }
        }
        if (kt == false) {
            JOptionPane.showMessageDialog(this, "Không có thông tin khách hàng cần tìm");
        }
    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void btnKhamBenhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKhamBenhActionPerformed
        //khám bệnh
        this.setVisible(false);
        QLThuCung qltc = new QLThuCung(null, this);
        qltc.setVisible(true);

    }//GEN-LAST:event_btnKhamBenhActionPerformed

    private void btnNhapThongTinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNhapThongTinActionPerformed
        // nhap thong tin thu cung theo so luong 

        XLThuCung tc = new XLThuCung(null, this, 2);
        tc.setVisible(true);
    }//GEN-LAST:event_btnNhapThongTinActionPerformed

    private void txtTaiKhoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTaiKhoanActionPerformed
        // Tim kiếm bằng nút enter
        String cm = evt.getActionCommand();
        if (cm != null) {

            boolean kt = false;
            for (int i = 0; i < tblKhachHang.getRowCount(); i++) {
                for (int j = 0; j < tblKhachHang.getColumnCount(); j++) {

                    if (txtTaiKhoan.getText().equals(tblKhachHang.getValueAt(i, j).toString())) {
                        kt = true;
                        tblKhachHang.setCellSelectionEnabled(true);
                        tblKhachHang.changeSelection(i, j, false, false);
                        tblKhachHang.requestFocus();
                    }
                }
            }
            if (kt == false) {
                JOptionPane.showMessageDialog(this, "Không có thông tin khách hàng cần tìm");
            }
        }
    }//GEN-LAST:event_txtTaiKhoanActionPerformed

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
            java.util.logging.Logger.getLogger(QLKhachHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QLKhachHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QLKhachHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QLKhachHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QLKhachHang(m).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnKhamBenh;
    private javax.swing.JButton btnNhapThongTin;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnThoat;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnTroLai;
    private javax.swing.JButton btnXoa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pnAction;
    private javax.swing.JPanel pnSearch;
    private javax.swing.JTable tblKhachHang;
    private javax.swing.JTextField txtTaiKhoan;
    // End of variables declaration//GEN-END:variables

    private void customUI() {
        pnAction.setBackground(GlobalValues.formBackgroundColor);
        pnSearch.setBackground(GlobalValues.formBackgroundColor);
    }
}
