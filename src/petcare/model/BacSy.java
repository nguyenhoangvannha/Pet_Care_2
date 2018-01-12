/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package petcare.model;

import java.util.ArrayList;

/**
 *
 * @author ngoc
 */
public class BacSy extends NhanVien {
    
    public BacSy(String manv, String tennv, String phai, String dChi, String sdt, String email,String cmnd ,String Chucvu,boolean nmk,int macv,String pw) {
        super(manv, tennv, phai, dChi, sdt, email,cmnd, Chucvu,nmk,macv,pw);
    }

    public static ArrayList<KhachHang> getDskh() {
        return dskh;
    }

    public static void setDskh(ArrayList<KhachHang> dskh) {
        NhanVien.dskh = dskh;
    }
    
    public KhachHang xem(int x)
    {
       return dskh.get(x);
    }
    // phuong thuc ke don se co ham kham benh va don thuoc
    public KhamBenh kham(int x, KhachHang a, String makb,String matc,String tchung,String dtri)
    {
        KhamBenh kb;
        kb = new KhamBenh(makb,super.getManv(),matc,tchung,dtri);
     return kb;
    }
    
}
