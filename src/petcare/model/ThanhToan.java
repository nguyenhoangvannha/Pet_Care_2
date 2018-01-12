/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package petcare.model;

import java.sql.Date;

/**
 *
 * @author ngoc
 */
public class ThanhToan {
    
    private String makh;
    private String matt;
    private String manv;
    private Date ngaytt; // ngay thanh toan
    private float ttien; // tong tien thanh toan
    

    public ThanhToan(String matt, String manv, String makh, Date ngaytt, float ttien) {
        this.matt=matt;
        this.makh = makh;
        this.manv = manv;
        this.ngaytt = ngaytt;
        this.ttien = ttien;
        
    }

    public String getMatt() {
        return matt;
    }

    public void setMatt(String matt) {
        this.matt = matt;
    }

   

    public String getMakh() {
        return makh;
    }

    public void setMakh(String makh) {
        this.makh = makh;
    }

    public String getManv() {
        return manv;
    }

    public void setManv(String manv) {
        this.manv = manv;
    }

    public Date getNgaytt() {
        return ngaytt;
    }

    public void setNgaytt(Date ngaytt) {
        this.ngaytt = ngaytt;
    }

    public float getTtien() {
        return ttien;
    }

    public void setTtien(float ttien) {
        this.ttien = ttien;
    }

    
    
}
