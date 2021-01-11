package com.example.quanlysinhvien.Model;

public class Sinhvien {
    private String masv;
    private String tensv;
    private String namsinh;
    private String diachi;
    private String gioitinh;
    private String sodienthoai;
    private String tenlop;
    public Sinhvien() {
    }

    public Sinhvien(String masv, String tensv, String namsinh, String diachi, String gioitinh, String sodienthoai, String tenlop) {
        this.masv = masv;
        this.tensv = tensv;
        this.namsinh = namsinh;
        this.diachi = diachi;
        this.gioitinh = gioitinh;
        this.sodienthoai = sodienthoai;
        this.tenlop = tenlop;
    }

    public String getMasv() {
        return masv;
    }

    public void setMasv(String masv) {
        this.masv = masv;
    }

    public String getTensv() {
        return tensv;
    }

    public void setTensv(String tensv) {
        this.tensv = tensv;
    }

    public String getNamsinh() {
        return namsinh;
    }

    public void setNamsinh(String namsinh) {
        this.namsinh = namsinh;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public String getTenlop() {
        return tenlop;
    }

    public void setTenlop(String tenlop) {
        this.tenlop = tenlop;
    }

    public String getSodienthoai() {
        return sodienthoai;
    }


    public void setSodienthoai(String sodienthoai) {
        this.sodienthoai = sodienthoai;
    }

}
