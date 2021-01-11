package com.example.quanlysinhvien.Model;

public class Dangki {
    private String taiKhoan;
    private String matkhau;
    private String nhaplaimk;
    private String email;

    public Dangki() {
    }

    public Dangki(String taiKhoan, String matkhau, String nhaplaimk, String email) {
        this.taiKhoan = taiKhoan;
        this.matkhau = matkhau;
        this.nhaplaimk = nhaplaimk;
        this.email = email;
    }

    public String getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(String taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public String getNhaplaimk() {
        return nhaplaimk;
    }

    public void setNhaplaimk(String nhaplaimk) {
        this.nhaplaimk = nhaplaimk;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
