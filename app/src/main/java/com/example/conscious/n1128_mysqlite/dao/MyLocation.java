package com.example.conscious.n1128_mysqlite.dao;

/**
 * Created by conscious on 2016-11-28.
 */

public class MyLocation {

    private int loc_no;
    private String loc_name;
    private String loc_comment;
    private String img_address;
    private String loc_date;

    @Override
    public String toString() {
        return "MyLocation{" +
                "img_address='" + img_address + '\'' +
                ", loc_no=" + loc_no +
                ", loc_name='" + loc_name + '\'' +
                ", loc_comment='" + loc_comment + '\'' +
                ", loc_date='" + loc_date + '\'' +
                '}';
    }

    public String getImg_address() {
        return img_address;
    }

    public void setImg_address(String img_address) {
        this.img_address = img_address;
    }

    public String getLoc_comment() {
        return loc_comment;
    }

    public void setLoc_comment(String loc_comment) {
        this.loc_comment = loc_comment;
    }

    public String getLoc_date() {
        return loc_date;
    }

    public void setLoc_date(String loc_date) {
        this.loc_date = loc_date;
    }

    public String getLoc_name() {
        return loc_name;
    }

    public void setLoc_name(String loc_name) {
        this.loc_name = loc_name;
    }

    public int getLoc_no() {
        return loc_no;
    }

    public void setLoc_no(int loc_no) {
        this.loc_no = loc_no;
    }
}
