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
public class KhamBenh {
    private String makb; // ma kham benh 
    private String mabs; // ma bac sy
    private String matc; // ma thu cung
    private String tchung; // trieu chung
    private String dtri;  // dieu tri
    
    public KhamBenh(String makb,String mabs,String matc,String tchung,String dtri)
    {
    this.makb= makb; 
    this.mabs= mabs; 
    this.matc= matc; 
    this.tchung= tchung; 
    this.dtri= dtri;  
    }
    public String getMakb() {
        return makb;
    }

    public void setMakb(String makb) {
        this.makb = makb;
    }

    public String getMabs() {
        return mabs;
    }

    public void setMabs(String mabs) {
        this.mabs = mabs;
    }

    public String getMatc() {
        return matc;
    }

    public void setMatc(String matc) {
        this.matc = matc;
    }

    public String getTchung() {
        return tchung;
    }

    public void setTchung(String tchung) {
        this.tchung = tchung;
    }

    public String getDtri() {
        return dtri;
    }

    public void setDtri(String dtri) {
        this.dtri = dtri;
    }
    
    
}
