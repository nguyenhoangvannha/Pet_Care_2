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
public class ThuCung {
    private String matc;
    private String tentc;
    private int tuoi;
    private String cLoai;
    private String tTrang;
    private String makh;

    public String getMatc() {
        return matc;
    }

    public void setMatc(String matc) {
        this.matc = matc;
    }

    public String getTentc() {
        return tentc;
    }

    public void setTentc(String tentc) {
        this.tentc = tentc;
    }

    public int getTuoi() {
        return tuoi;
    }

    public void setTuoi(int tuoi) {
        this.tuoi = tuoi;
    }

    public String getcLoai() {
        return cLoai;
    }

    public void setcLoai(String cLoai) {
        this.cLoai = cLoai;
    }

    public String gettTrang() {
        return tTrang;
    }

    public void settTrang(String tTrang) {
        this.tTrang = tTrang;
    }

    public String getMakh() {
        return makh;
    }

    public void setMakh(String makh) {
        this.makh = makh;
    }
    
    public ThuCung(String matc,String tentc,int tuoi,String cLoai,String tTrang,String makh)
    {
    this.matc= matc;
    this.tentc= tentc;
    this.tuoi=tuoi;
    this.cLoai= cLoai;
    this.tTrang= tTrang;
    this.makh= makh;
    }
    
}
