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
public class NhanVien {
    private String manv;
    private String tennv;
    private String phai;
    private String dChi;
    private String sdt;
    private String email;
    private String cvu;
    private String cmnd;
    private boolean nmk;// nho mat khau hay khong
    private int macv;// ma chuc vu
    private String pw;// pass word
    public static ArrayList<KhachHang> dskh = new ArrayList<>();
   
    public static ArrayList<KhachHang> getA() {
        return dskh;
    }

    public int getMacv() {
        return macv;
    }

    public void setMacv(int macv) {
        this.macv = macv;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public static void setA(ArrayList<KhachHang> dskh) {
        NhanVien.dskh = dskh;
    }
    public NhanVien(String manv,String tennv,String phai,String dChi,String sdt,String email,String cmnd,String Chucvu,boolean lmk,int macv,String pw)
    {
    this.manv=manv;
    this.tennv=tennv;
    this.phai= phai;
    this.dChi= dChi;
    this.sdt= sdt;
    this.email= email;
    this.cvu= Chucvu;
    this.cmnd=cmnd;
    this.nmk=nmk;
    this.macv=macv;
    this.pw=pw;
    }

    public String getCvu() {
        return cvu;
    }

    public void setCvu(String cvu) {
        this.cvu = cvu;
    }

    public String getCmnd() {
        return cmnd;
    }

    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
    }

    public String getManv() {
        return manv;
    }

    public void setManv(String manv) {
        this.manv = manv;
    }

    public String getTennv() {
        return tennv;
    }

    public void setTennv(String tennv) {
        this.tennv = tennv;
    }

    public String getPhai() {
        return phai;
    }

    public void setPhai(String phai) {
        this.phai = phai;
    }

    public String getdChi() {
        return dChi;
    }

    public void setdChi(String dChi) {
        this.dChi = dChi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getChucvu() {
        return cvu;
    }

    public void setChucvu(String Chucvu) {
        this.cvu = Chucvu;
        
    }

    public boolean isNmk() {
        return nmk;
    }

    public void setNmk(boolean nmk) {
        this.nmk = nmk;
    }

    public static ArrayList<KhachHang> getDskh() {
        return dskh;
    }

    public static void setDskh(ArrayList<KhachHang> dskh) {
        NhanVien.dskh = dskh;
    }
    
    
}
