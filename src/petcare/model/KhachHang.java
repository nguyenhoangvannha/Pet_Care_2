/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package petcare.model;

/**
 *
 * @author ngoc
 */
public class KhachHang {
    private String makh;
    private String tenkh;
    private String gtinh;
    private String dChi;
    private String sdt;
    private String email;
    private String cmnd;
    private int sltc;// so luong thu cungs
    public KhachHang(String makh,String tenkh,String gtinh,String dChi, String sdt,String email,String cmnd,int sltc)
    {
    this.makh= makh;
    this.tenkh= tenkh;
    this.gtinh= gtinh;
    this.dChi= dChi;
    this.sdt= sdt;
    this.email= email;
    this.cmnd= cmnd;
    this.sltc= sltc;
    }

    public String getMakh() {
        return makh;
    }

    public void setMakh(String makh) {
        this.makh = makh;
    }

    public String getTenkh() {
        return tenkh;
    }

    public void setTenkh(String tenkh) {
        this.tenkh = tenkh;
    }

    public String getGtinh() {
        return gtinh;
    }

    public void setGtinh(String gtinh) {
        this.gtinh = gtinh;
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

    public String getCmnd() {
        return cmnd;
    }

    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
    }

    public int getSltc() {
        return sltc;
    }

    public void setSltc(int sltc) {
        this.sltc = sltc;
    }
    
}
