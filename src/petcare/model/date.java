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
class date {
    int ngay;
    int thang;
    int nam;
    public date(int ngay, int thang, int nam)
    {
        if(ngay>=1&&ngay<=31) this.ngay=ngay;
        if(thang>=1&&thang<=12) this.thang=thang;
        this.nam=nam;
    }

    public int getNgay() {
        return ngay;
    }

    public void setNgay(int ngay) {
        this.ngay = ngay;
    }

    public int getThang() {
        return thang;
    }

    public void setThang(int thang) {
        this.thang = thang;
    }

    public int getNam() {
        return nam;
    }

    public void setNam(int nam) {
        this.nam = nam;
    }
    
}
