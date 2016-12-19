package com.example.conscious.n1128_mysqlite.fragmentOne;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.conscious.n1128_mysqlite.R;
import com.example.conscious.n1128_mysqlite.dao.MyLocation;

/**
 * Created by conscious on 2016-11-28.
 */

public class FragOneItemView extends FrameLayout {

    private TextView tvLocNo;
    private TextView tvLocName;
    private TextView tvLocComment;
    private MyLocation data;

    public FragOneItemView(Context context) {
        super(context);
        init();
    }

    public void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.item_one, this);

        tvLocNo = (TextView) findViewById(R.id.tvLocNo);
        tvLocName = (TextView) findViewById(R.id.tvLocName);
        tvLocComment = (TextView) findViewById(R.id.tvLocComment);

    }

    public void setData(MyLocation data) {
        this.data = data;
        tvLocNo.setText(data.getLoc_no()+"");
        tvLocName.setText(data.getLoc_name());
        tvLocComment.setText(data.getLoc_comment());

    }


}
