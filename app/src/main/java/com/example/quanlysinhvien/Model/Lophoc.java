package com.example.quanlysinhvien.Model;

import java.util.Collection;

public class Lophoc {
    private int id;
    private String malop;
    private String tenlop;

    public Lophoc() {
    }

    public Lophoc(int id, String malop, String tenlop) {
        this.id = id;
        this.malop = malop;
        this.tenlop = tenlop;
    }
    public Lophoc(String malop, String tenlop) {
        this.malop = malop;
        this.tenlop = tenlop;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMalop() {
        return malop;
    }

    public void setMalop(String malop) {
        this.malop = malop;
    }

    public String getTenlop() {
        return tenlop;
    }

    public void setTenlop(String tenlop) {
        this.tenlop = tenlop;
    }

    @Override
    public String toString() {
        return "Lophoc{" +
                "id=" + id +
                ", malop='" + malop + '\'' +
                ", tenlop='" + tenlop + '\'' +
                '}';
    }
}
