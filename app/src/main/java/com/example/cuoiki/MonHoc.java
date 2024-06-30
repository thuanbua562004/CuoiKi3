package com.example.cuoiki;

public class MonHoc {
    String thoigian ,phong ,giaovien ,ngay, tenmon;

    public MonHoc(String thoigian, String phong, String giaovien, String ngay, String tenmon) {
        this.thoigian = thoigian;
        this.phong = phong;
        this.giaovien = giaovien;
        this.ngay = ngay;
        this.tenmon = tenmon;
    }
    public String getThoigian() {
        return thoigian;
    }

    public void setThoigian(String thoigian) {
        this.thoigian = thoigian;
    }

    public String getPhong() {
        return phong;
    }

    public void setPhong(String phong) {
        this.phong = phong;
    }

    public String getGiaovien() {
        return giaovien;
    }

    public void setGiaovien(String giaovien) {
        this.giaovien = giaovien;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public String getTenmon() {
        return tenmon;
    }

}
