package com.example.cuoiki;

public class SinhVien {
    public String  username ;
    public String password;
    public String mssv;
    public String quequan;
    public String ngaysinh;
    public String nganhhoc ;
    public String hoten;

    @Override
    public String toString() {
        return "SinhVien{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", mssv='" + mssv + '\'' +
                ", quequan='" + quequan + '\'' +
                ", ngaysinh='" + ngaysinh + '\'' +
                ", nganhhoc='" + nganhhoc + '\'' +
                ", hoten='" + hoten + '\'' +
                '}';
    }

    public SinhVien(String username, String password, String mssv, String quequan, String ngaysinh, String nganhhoc, String hoten) {
        this.username = username;
        this.password = password;
        this.mssv = mssv;
        this.quequan = quequan;
        this.ngaysinh = ngaysinh;
        this.nganhhoc = nganhhoc;
        this.hoten = hoten;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMssv() {
        return mssv;
    }

    public void setMssv(String mssv) {
        this.mssv = mssv;
    }

    public String getQuequan() {
        return quequan;
    }

    public void setQuequan(String quequan) {
        this.quequan = quequan;
    }

    public String getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(String ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public String getNganhhoc() {
        return nganhhoc;
    }

    public void setNganhhoc(String nganhhoc) {
        this.nganhhoc = nganhhoc;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }
}
