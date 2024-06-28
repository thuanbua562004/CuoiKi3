package com.example.cuoiki;

public class SinhVien {
    public String  username ;
    public String password;
    public String mssv;
    public String quequan;
    public String ngaysinh;
    public String nganhhoc ;
    public String hoten;
    public  String email ;

    public SinhVien(String username, String password, String mssv, String quequan, String ngaysinh, String nganhhoc, String hoten, String email) {
        this.username = username;
        this.password = password;
        this.mssv = mssv;
        this.quequan = quequan;
        this.ngaysinh = ngaysinh;
        this.nganhhoc = nganhhoc;
        this.hoten = hoten;
        this.email = email;
    }

    public SinhVien(String mssv) {
        this.mssv = mssv;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getMssv() {
        return mssv;
    }

    public String getQuequan() {
        return quequan;
    }

    public String getNgaysinh() {
        return ngaysinh;
    }

    public String getNganhhoc() {
        return nganhhoc;
    }

    public String getHoten() {
        return hoten;
    }

    public String getEmail() {
        return email;
    }
}
