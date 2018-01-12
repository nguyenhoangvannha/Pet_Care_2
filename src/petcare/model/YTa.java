/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package petcare.model;
 
import petcare.model.NhanVien;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author ngoc
 */
public class YTa extends NhanVien {
    
   
    
    public YTa(String manv, String tennv, String phai, String dChi, String sdt, String email,String cmnd, String Chucvu,boolean nmk,int macv,String pw) {
        super(manv, tennv, phai, dChi, sdt, email,cmnd, Chucvu,nmk,macv,pw);
        
    }

   
    public void them(KhachHang a)
    {
        dskh.add(a);
    }
    public void sua(int i,KhachHang a)
    {
        dskh.set(i, a);
    }
    public void xoa(int i)
    {
        dskh.remove(i);
    }
    public KhachHang xem(int x)
    {
        return dskh.get(x);
    }
  
}
