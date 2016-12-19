package com.example.conscious.n1128_mysqlite.dao;

/**
 * Created by conscious on 2016-11-28.
 */

public class StuffTag {

    public String tag_name;

    @Override
    public String toString() {
        return "StuffTag{" +
                "tag_name='" + tag_name + '\'' +
                '}';
    }

    public void setTag_name(String tag_name) {
        this.tag_name = tag_name;
    }

    public String getTag_name() {

        return tag_name;
    }
}
