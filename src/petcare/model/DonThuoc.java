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
public class DonThuoc {
    private String madt;
    private String makb; // MA KHAM BENH
    private String makh; 
    private String math;
    private String matc;
    private int sluong;
    private Date nkdon;//ngay ke don thuoc
    public DonThuoc(String madt,String makh,String matc,String makb,String math,int sluong,Date nkdon)
    {
     this.makh= makh; 
    this.math= math;
    this.sluong= sluong;
    this.nkdon=nkdon;
    this.madt=madt;
    this.matc= matc;
    this.makb=makb;
    }

    public String getMadt() {
        return madt;
    }

    public void setMadt(String madt) {
        this.madt = madt;
    }

    public String getMakb() {
        return makb;
    }

    public void setMakb(String makb) {
        this.makb = makb;
    }
    
    public String getMatc() {
        return matc;
    }

    public void setMatc(String matc) {
        this.matc = matc;
    }

    public String getMakh() {
        return makh;
    }

    public void setMakh(String makh) {
        this.makh = makh;
    }

    public String getMath() {
        return math;
    }

    public void setMath(String math) {
        this.math = math;
    }

    public int getSluong() {
        return sluong;
    }

    public void setSluong(int sluong) {
        this.sluong = sluong;
    }

    public Date getNkdon() {
        return nkdon;
    }

    public void setNkdon(Date nkdon) {
        this.nkdon = nkdon;
    }
    
}
