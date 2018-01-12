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
public class Thuoc {
    private String math;
    private String tenth;
    private float gia;
    private Date hsd;
    private String cDung;
    public Thuoc(String math,String tenth,float gia,Date hsd,String cDung)
    {
    this.math= math;
    this.tenth= tenth;
    this.gia= gia;
    this.hsd= hsd;
    this.cDung= cDung;
    }

    public String getMath() {
        return math;
    }

    public void setMath(String math) {
        this.math = math;
    }

    public String getTenth() {
        return tenth;
    }

    public void setTenth(String tenth) {
        this.tenth = tenth;
    }

    public float getGia() {
        return gia;
    }

    public void setGia(float gia) {
        this.gia = gia;
    }

    public Date getHsd() {
        return hsd;
    }

    public void setHsd(Date hsd) {
        this.hsd = hsd;
    }

    public String getcDung() {
        return cDung;
    }

    public void setcDung(String cDung) {
        this.cDung = cDung;
    }
    
}
