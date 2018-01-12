/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package petcare.view;

import petcare.model.DonThuoc;
import petcare.model.KhachHang;
import petcare.model.KhamBenh;
import petcare.model.ThanhToan;
import petcare.model.Thuoc;
import petcare.model.NhanVien;
import petcare.model.ThuCung;
import java.io.IOException;
import java.sql.Connection;
import petcare.control.MyMSSQLControl;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import petcare.control.GlobalValues;
import static petcare.view.DangNhap.dangnhap;

/**
 *
 * @author ngoc
 */
public class main extends javax.swing.JFrame {

    static String taikhoan;
    static String manv;
    static int chucvu;
    public JTable jtb = new JTable();
    public DefaultTableModel dtm = new DefaultTableModel();
    public static ArrayList<ThuCung> dstc = new ArrayList<ThuCung>();
    public static ArrayList<KhachHang> dskh = new ArrayList<KhachHang>();
    public static ArrayList<NhanVien> dsnv = new ArrayList<NhanVien>();
    public static ArrayList<Thuoc> dst = new ArrayList<Thuoc>();
    public static ArrayList<DonThuoc> dsdt = new ArrayList<DonThuoc>();
    public static ArrayList<KhamBenh> dskb = new ArrayList<KhamBenh>();
    public static ArrayList<ThanhToan> dstt = new ArrayList<ThanhToan>();

    public DefaultTableModel getDtm() {
        return dtm;
    }

    public void setDtm(DefaultTableModel dtm) {
        this.dtm = dtm;
    }

    public static ArrayList<KhamBenh> getDskb() {
        return dskb;
    }

    public static void setDskb(ArrayList<KhamBenh> dskb) {
        main.dskb = dskb;
    }

    public static ArrayList<ThanhToan> getDstt() {
        return dstt;
    }

    public static void setDstt(ArrayList<ThanhToan> dstt) {
        main.dstt = dstt;
    }

    public static ArrayList<ThuCung> getDstc() {
        return dstc;
    }

    public static void setDstc(ArrayList<ThuCung> dstc) {
        main.dstc = dstc;
    }

    public static ArrayList<KhachHang> getDskh() {
        return dskh;
    }

    public static void setDskh(ArrayList<KhachHang> dskh) {
        main.dskh = dskh;
    }

    public static ArrayList<NhanVien> getDsnv() {
        return dsnv;
    }

    public static void setDsnv(ArrayList<NhanVien> dsnv) {
        main.dsnv = dsnv;
    }

    public static ArrayList<Thuoc> getDst() {
        return dst;
    }

    public static void setDst(ArrayList<Thuoc> dst) {
        main.dst = dst;
    }

    public static ArrayList<DonThuoc> getDsdt() {
        return dsdt;
    }

    public static void setDsdt(ArrayList<DonThuoc> dsdt) {
        main.dsdt = dsdt;
    }

    public static String getTaikhoan() {
        return taikhoan;
    }

    public static void setTaikhoan(String taikhoan) {
        main.taikhoan = taikhoan;
    }

    public static int getChucvu() {
        return chucvu;
    }

    public static void setChucvu(int chucvu) {
        main.chucvu = chucvu;
    }

    /**
     * Creates new form giaodien
     */
    public main(String taikhoan, int chucvu) throws IOException {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.taikhoan = taikhoan;
        this.chucvu = chucvu;//0-1-2 / quan ly - bac sy - yta
        initComponents();
        getContentPane().setBackground(GlobalValues.formBackgroundColor);
        this.setLocationRelativeTo(null);
        //setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icondn.png")));
        lblTaiKhoan.setText(this.taikhoan);
        //load full database vo data (class doi tuong)
        loadfulldata(jtb, dtm);
        customUI();

    }

    public void loadfulldata(JTable jtb, DefaultTableModel dtm) {
        loadthucung(jtb, dtm);
        loadKhachHang(jtb, dtm);
        loadNhanVien(jtb, dtm);
        loadThuoc(jtb, dtm);
        loadDonThuoc(jtb, dtm);
        loadKhamBenh(jtb, dtm);
        loadThanhToan(jtb, dtm);

    }

    public void loadthucung(JTable jtb, DefaultTableModel dtm) {
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
                dtm.setColumnIdentifiers(colum);

            }
            ;
            jtb.setModel(dtm);

            // LEN DU LIEU TUNG ROW
            while (rs.next()) {
                row = new Vector();
                //System.out.println("MACD: "+ rs.getString(1)+ " TENCD: "+rs.getString(2));
                row.addElement(rs.getString(1));// matc
                row.addElement(rs.getString(2));// ma kh
                row.addElement(rs.getString(3)); //ten thu cung
                row.addElement(rs.getInt(4)); // tuoi
                row.addElement(rs.getString(5)); //loai
                row.addElement(rs.getString(6)); //tinh trang
                ThuCung tc = new ThuCung(rs.getString(1), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(2));
                dtm.addRow(row);
                dstc.add(tc);

            }

            jtb.setModel(dtm);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "loi ket nối");

        }
    }

    /// load data NhanVien
    public void loadNhanVien(JTable jtb, DefaultTableModel dtm) {
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
            String sql = "select * from NHANVIEN";
            rs = stt.executeQuery(sql);
            ResultSetMetaData md = rs.getMetaData();
            Vector row;
            Vector colum = new Vector();
            int number = md.getColumnCount();
            // LAY TEN TUNG COLLUMN name (ROW 1)
            for (int i = 1; i <= number; i++) {
                colum.add(md.getColumnName(i));
                dtm.setColumnIdentifiers(colum);

            }
            ;
            jtb.setModel(dtm);

            // LEN DU LIEU TUNG ROW
            while (rs.next()) {
                row = new Vector();
                //System.out.println("MACD: "+ rs.getString(1)+ " TENCD: "+rs.getString(2));
                row.addElement(rs.getString(1));
                row.addElement(rs.getString(2));
                row.addElement(rs.getString(3));
                row.addElement(rs.getString(4));
                row.addElement(rs.getString(5));
                row.addElement(rs.getString(6));
                row.addElement(rs.getString(7));
                row.addElement(rs.getString(8));
                row.addElement(rs.getBoolean(9));
                row.addElement(rs.getInt(10));
                row.addElement(rs.getString(11));
                if (taikhoan.equals(rs.getNString(2)) && chucvu == rs.getInt(10)) {
                    dangnhap = true;// xem viec dang nhap co thanh cong hay khong
                    manv = rs.getString(1);
                    //chucvu=rs.getInt(10);// tra ve chuc vụ don gian hon dung cot NCHAR chucvu
                }
                NhanVien nv = new NhanVien(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getBoolean(9), rs.getInt(10), rs.getString(11));
                dtm.addRow(row);
                dsnv.add(nv);

            }

            jtb.setModel(dtm);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "loi ket nối");

        }
    }

    public void loadKhachHang(JTable jtb, DefaultTableModel dtm) {
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
            String sql = "select * from KHACHHANG";
            rs = stt.executeQuery(sql);
            ResultSetMetaData md = rs.getMetaData();
            Vector row;
            Vector colum = new Vector();
            int number = md.getColumnCount();
            // LAY TEN TUNG COLLUMN name (ROW 1)
            for (int i = 1; i <= number; i++) {
                colum.add(md.getColumnName(i));
                dtm.setColumnIdentifiers(colum);

            }
            ;
            jtb.setModel(dtm);

            // LEN DU LIEU TUNG ROW
            while (rs.next()) {
                row = new Vector();
                //System.out.println("MACD: "+ rs.getString(1)+ " TENCD: "+rs.getString(2));
                row.addElement(rs.getString(1));// ma khach hang
                row.addElement(rs.getString(2));// ten khach hang
                row.addElement(rs.getString(3)); // gioi tinh
                row.addElement(rs.getString(4)); // dia chi
                row.addElement(rs.getString(5)); // so dien thoai
                row.addElement(rs.getString(6)); // email
                row.addElement(rs.getString(7)); // cmnd
                row.addElement(rs.getInt(8)); // so luong
                KhachHang kh = new KhachHang(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8));
                dtm.addRow(row);
                dskh.add(kh);

            }

            jtb.setModel(dtm);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "loi ket nối");

        }
    }

    public void loadThuoc(JTable jtb, DefaultTableModel dtm) {
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
            String sql = "select * from THUOC";
            rs = stt.executeQuery(sql);
            ResultSetMetaData md = rs.getMetaData();
            Vector row;
            Vector colum = new Vector();
            int number = md.getColumnCount();
            // LAY TEN TUNG COLLUMN name (ROW 1)
            for (int i = 1; i <= number; i++) {
                colum.add(md.getColumnName(i));
                dtm.setColumnIdentifiers(colum);

            }
            ;
            jtb.setModel(dtm);

            // LEN DU LIEU TUNG ROW
            while (rs.next()) {
                row = new Vector();
                //System.out.println("MACD: "+ rs.getString(1)+ " TENCD: "+rs.getString(2));
                row.addElement(rs.getString(1));// ma Thuoc
                row.addElement(rs.getString(2));// ten Thuoc
                row.addElement(rs.getFloat(3)); // gia
                row.addElement(rs.getDate(4)); // hsd khong the getdate boi vi kieu du lieu date tu dinh nghia co the ko dong nhat vs kieu date tren csdl
                row.addElement(rs.getString(5)); // cong dung
                Thuoc th = new Thuoc(rs.getString(1), rs.getString(2), rs.getFloat(3), rs.getDate(4), rs.getString(5));
                dtm.addRow(row);
                dst.add(th);

            }

            jtb.setModel(dtm);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "loi ket nối");

        }
    }

    public void loadDonThuoc(JTable jtb, DefaultTableModel dtm) {
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
            String sql = "select * from DONTHUOC";
            rs = stt.executeQuery(sql);
            ResultSetMetaData md = rs.getMetaData();
            Vector row;
            Vector colum = new Vector();
            int number = md.getColumnCount();
            // LAY TEN TUNG COLLUMN name (ROW 1)
            for (int i = 1; i <= number; i++) {
                colum.add(md.getColumnName(i));
                dtm.setColumnIdentifiers(colum);

            }
            ;
            jtb.setModel(dtm);

            // LEN DU LIEU TUNG ROW
            while (rs.next()) {
                row = new Vector();
                //System.out.println("MACD: "+ rs.getString(1)+ " TENCD: "+rs.getString(2));
                row.addElement(rs.getString(1));// ma don Thuoc
                row.addElement(rs.getString(2));// ten khach hang
                row.addElement(rs.getString(3)); // ma thu cung
                row.addElement(rs.getString(4)); // ma kham benh
                row.addElement(rs.getString(5)); // ma Thuoc
                row.addElement(rs.getInt(6));// so luong
                row.addElement(rs.getDate(7));// ngay ke don
                DonThuoc dt = new DonThuoc(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getDate(7));
                dtm.addRow(row);
                dsdt.add(dt);

            }

            jtb.setModel(dtm);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "loi ket nối");

        }
    }

    public void loadKhamBenh(JTable jtb, DefaultTableModel dtm) {
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
            String sql = "select * from KHAMBENH";
            rs = stt.executeQuery(sql);
            ResultSetMetaData md = rs.getMetaData();
            Vector row;
            Vector colum = new Vector();
            int number = md.getColumnCount();
            // LAY TEN TUNG COLLUMN name (ROW 1)
            for (int i = 1; i <= number; i++) {
                colum.add(md.getColumnName(i));
                dtm.setColumnIdentifiers(colum);

            }
            ;
            jtb.setModel(dtm);

            // LEN DU LIEU TUNG ROW
            while (rs.next()) {
                row = new Vector();
                //System.out.println("MACD: "+ rs.getString(1)+ " TENCD: "+rs.getString(2));
                row.addElement(rs.getString(1));// ma kham benh
                row.addElement(rs.getString(2));// ma bac sy
                row.addElement(rs.getString(3)); // ma thu cung
                row.addElement(rs.getString(4)); // trieu chung
                row.addElement(rs.getString(5)); // dieu tri

                KhamBenh kb = new KhamBenh(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                dtm.addRow(row);
                dskb.add(kb);

            }

            jtb.setModel(dtm);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "loi ket nối");

        }
    }

    public void loadThanhToan(JTable jtb, DefaultTableModel dtm) {
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
            String sql = "select * from THANHTOAN";
            rs = stt.executeQuery(sql);
            ResultSetMetaData md = rs.getMetaData();
            Vector row;
            Vector colum = new Vector();
            int number = md.getColumnCount();
            // LAY TEN TUNG COLLUMN name (ROW 1)
            for (int i = 1; i <= number; i++) {
                colum.add(md.getColumnName(i));
                dtm.setColumnIdentifiers(colum);

            }
            ;
            jtb.setModel(dtm);

            // LEN DU LIEU TUNG ROW
            while (rs.next()) {
                row = new Vector();
                //System.out.println("MACD: "+ rs.getString(1)+ " TENCD: "+rs.getString(2));
                row.addElement(rs.getString(1));

                row.addElement(rs.getString(2));
                row.addElement(rs.getString(3));
                row.addElement(rs.getDate(4));
                row.addElement(rs.getFloat(5));

                ThanhToan tt = new ThanhToan(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getFloat(5));
                dtm.addRow(row);
                dstt.add(tt);

            }

            jtb.setModel(dtm);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "loi ket nối");

        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        label1 = new java.awt.Label();
        jLabel1 = new javax.swing.JLabel();
        btnExit = new javax.swing.JButton();
        btnCustomer = new javax.swing.JButton();
        btnMedicine = new javax.swing.JButton();
        btnPet = new javax.swing.JButton();
        btnStaff = new javax.swing.JButton();
        btnMoney = new javax.swing.JButton();
        pnUser = new javax.swing.JPanel();
        lblTaiKhoan = new javax.swing.JLabel();
        btnLogout = new javax.swing.JButton();
        lblLogo = new javax.swing.JLabel();

        label1.setText("label1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("PETCARE - Phần mềm quản lý thú cưng");
        setLocationByPlatform(true);

        btnExit.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/petcare/view/drawable/ic_exit.png"))); // NOI18N
        btnExit.setText("Thoát");
        btnExit.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnExit.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        btnCustomer.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnCustomer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/petcare/view/drawable/ic_client.png"))); // NOI18N
        btnCustomer.setText("Quản lý khách hàng");
        btnCustomer.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCustomer.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        btnCustomer.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCustomerActionPerformed(evt);
            }
        });

        btnMedicine.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnMedicine.setIcon(new javax.swing.ImageIcon(getClass().getResource("/petcare/view/drawable/ic_medical_suitecase.png"))); // NOI18N
        btnMedicine.setText("Quản lý thuốc");
        btnMedicine.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMedicine.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnMedicine.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMedicineActionPerformed(evt);
            }
        });

        btnPet.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnPet.setIcon(new javax.swing.ImageIcon(getClass().getResource("/petcare/view/drawable/ic_pet.png"))); // NOI18N
        btnPet.setText("Quản lý thú cưng");
        btnPet.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnPet.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        btnPet.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnPet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPetActionPerformed(evt);
            }
        });

        btnStaff.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnStaff.setIcon(new javax.swing.ImageIcon(getClass().getResource("/petcare/view/drawable/ic_staff_manager.png"))); // NOI18N
        btnStaff.setText("Quản lý  nhân viên");
        btnStaff.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnStaff.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnStaff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStaffActionPerformed(evt);
            }
        });

        btnMoney.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnMoney.setIcon(new javax.swing.ImageIcon(getClass().getResource("/petcare/view/drawable/ic_money_manager.png"))); // NOI18N
        btnMoney.setText("Quản lý thu chi");
        btnMoney.setToolTipText("");
        btnMoney.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMoney.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnMoney.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoneyActionPerformed(evt);
            }
        });

        btnLogout.setText("Đăng xuất");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/petcare/view/drawable/ic_user_male_24.png"))); // NOI18N

        javax.swing.GroupLayout pnUserLayout = new javax.swing.GroupLayout(pnUser);
        pnUser.setLayout(pnUserLayout);
        pnUserLayout.setHorizontalGroup(
            pnUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnUserLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addGroup(pnUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTaiKhoan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnLogout, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnUserLayout.setVerticalGroup(
            pnUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnUserLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnUserLayout.createSequentialGroup()
                        .addComponent(lblTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(btnLogout)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(64, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnMedicine, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(430, 430, 430))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(btnPet, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(340, 340, 340)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(7, 7, 7))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(pnUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(59, 59, 59))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(214, 214, 214)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(btnMoney, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnCustomer))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnStaff, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(56, 56, 56)))))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnCustomer, btnExit, btnMedicine, btnMoney, btnPet, btnStaff});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnUser, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnCustomer, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnStaff, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnPet, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(51, 51, 51)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnMoney, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnExit)
                    .addComponent(btnMedicine, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(60, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnCustomer, btnExit, btnMedicine, btnMoney, btnStaff});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        this.setVisible(false);
        DangNhap dn = new DangNhap();
        dn.setVisible(true);
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnPetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPetActionPerformed
        //quan ly thu cung
        if (chucvu == 2 || chucvu == 1) {
            QLThuCung qltc = new QLThuCung(this, null);
            this.setVisible(false);

            qltc.setVisible(true);
            qltc.loaddata();
        } else {
            JOptionPane.showMessageDialog(rootPane, "Chỉ có bác sỹ và y tá mới có quyền truy cập");
        }
    }//GEN-LAST:event_btnPetActionPerformed

    private void btnCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCustomerActionPerformed
        // quan ly khach hang
        if (chucvu == 2) {
            QLKhachHang qlkh = new QLKhachHang(this);
            this.setVisible(false);
            qlkh.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(rootPane, "Chỉ có y tá mới có quyền truy cập");
        }
    }//GEN-LAST:event_btnCustomerActionPerformed

    private void btnStaffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStaffActionPerformed
        // quan ly nhan vien
        if (this.chucvu == 0) {
            QLNhanVien qlnv = new QLNhanVien(this);
            this.setVisible(false);
            qlnv.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(rootPane, "Chỉ có người quản lý mới có quyền truy cập");
        }
    }//GEN-LAST:event_btnStaffActionPerformed

    private void btnMedicineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMedicineActionPerformed
        // quan ly Thuoc
        if (this.chucvu == 0) {
            QLThuoc qlt = new QLThuoc(this);
            this.setVisible(false);
            qlt.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(rootPane, "Chỉ có người quản lý mới có quyền truy cập");
        }
    }//GEN-LAST:event_btnMedicineActionPerformed

    private void btnMoneyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoneyActionPerformed
        // quản ly tài chính
        if (chucvu == 2) {
            QLThanhToan qltt = new QLThanhToan(this);
            this.setVisible(false);
            qltt.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(rootPane, "Chỉ có y tá mới có quyền truy cập");
        }
    }//GEN-LAST:event_btnMoneyActionPerformed

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
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new main(taikhoan, chucvu).setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCustomer;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnMedicine;
    private javax.swing.JButton btnMoney;
    private javax.swing.JButton btnPet;
    private javax.swing.JButton btnStaff;
    private javax.swing.JLabel jLabel1;
    private java.awt.Label label1;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblTaiKhoan;
    private javax.swing.JPanel pnUser;
    // End of variables declaration//GEN-END:variables

    private void customUI() {
        pnUser.setBackground(GlobalValues.formBackgroundColor);
        btnPet.setContentAreaFilled(false);
        btnPet.requestFocus();
        btnStaff.setContentAreaFilled(false);
        btnMedicine.setContentAreaFilled(false);
        btnMoney.setContentAreaFilled(false);
        btnCustomer.setContentAreaFilled(false);
        btnExit.setContentAreaFilled(false);
    }
}
