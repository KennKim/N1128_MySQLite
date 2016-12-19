package com.example.conscious.n1128_mysqlite.dao;

/**
 * Created by conscious on 2016-11-28.
 */

public class Stuff {

    int stuff_no;
    String stuff_name;
    String stuff_comment;
    String img_address;
    String data_saved;

    @Override
    public String toString() {
        return "Stuff{" +
                "stuff_no=" + stuff_no +
                ", stuff_name='" + stuff_name + '\'' +
                ", stuff_comment='" + stuff_comment + '\'' +
                ", img_address='" + img_address + '\'' +
                ", data_saved='" + data_saved + '\'' +
                '}';
    }

    public void setStuff_no(int stuff_no) {
        this.stuff_no = stuff_no;
    }

    public void setStuff_name(String stuff_name) {
        this.stuff_name = stuff_name;
    }

    public void setStuff_comment(String stuff_comment) {
        this.stuff_comment = stuff_comment;
    }

    public void setImg_address(String img_address) {
        this.img_address = img_address;
    }

    public void setData_saved(String data_saved) {
        this.data_saved = data_saved;
    }

    public int getStuff_no() {

        return stuff_no;
    }

    public String getStuff_name() {
        return stuff_name;
    }

    public String getStuff_comment() {
        return stuff_comment;
    }

    public String getImg_address() {
        return img_address;
    }

    public String getData_saved() {
        return data_saved;
    }
}
