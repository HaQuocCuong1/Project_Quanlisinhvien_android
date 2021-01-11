package com.example.quanlysinhvien.Model;

public class Diemsv {
    private String tenLop;
    private String monhoc;
//    private String loaidiem;
    private String maSV;
    private String tenSV;
    private double diemGK;
    private double diemthi;
    private double tongdiem;
    private String xeploai;
    public Diemsv() {
    }
    public Diemsv(String tenLop, String monhoc, String maSV, String tenSV, double diemGK, double diemthi, double tongdiem, String xeploai) {
        this.tenLop = tenLop;
        this.monhoc = monhoc;
       // this.loaidiem = loaidiem;
        this.maSV = maSV;
        this.tenSV = tenSV;
        this.diemGK = diemGK;
        this.diemthi = diemthi;
        this.tongdiem = tongdiem;
        this.xeploai = xeploai;
    }
    public String getTenLop() {
        return tenLop;
    }

    public void setTenLop(String tenLop) {
        this.tenLop = tenLop;
    }

    public String getMonhoc() {
        return monhoc;
    }

    public void setMonhoc(String monhoc) {
        this.monhoc = monhoc;
    }


    public String getMaSV() {
        return maSV;
    }

    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }

    public String getTenSV() {
        return tenSV;
    }

    public void setTenSV(String tenSV) {
        this.tenSV = tenSV;
    }

    public double getDiemGK() {
        return diemGK;
    }

    public void setDiemGK(double diemGK) {
        this.diemGK = diemGK;
    }

    public double getDiemthi() {
        return diemthi;
    }

    public void setDiemthi(double diemthi) {
        this.diemthi = diemthi;
    }

    public double getTongdiem() {
        return tongdiem;
    }

    public void setTongdiem(double tongdiem) {
        this.tongdiem = tongdiem;
    }

    public String getXeploai() {
        return xeploai;
    }

    public void setXeploai(String xeploai) {
        this.xeploai = xeploai;
    }
}
