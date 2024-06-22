package com.example.cuoiki;

public class notication {
    public String name ;
    public String notication_info ;
    public  String img ;
    public  String date ;

    public notication(String name, String notication_info, String img, String date) {
        this.name = name;
        this.notication_info = notication_info;
        this.img = img;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotication_info() {
        return notication_info;
    }

    public void setNotication_info(String notication_info) {
        this.notication_info = notication_info;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
