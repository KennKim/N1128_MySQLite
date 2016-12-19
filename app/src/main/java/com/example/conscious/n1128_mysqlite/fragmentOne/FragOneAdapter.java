package com.example.conscious.n1128_mysqlite.fragmentOne;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.conscious.n1128_mysqlite.dao.MyLocation;

import java.util.ArrayList;

/**
 * Created by conscious on 2016-11-28.
 */

public class FragOneAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<MyLocation> items = new ArrayList<MyLocation>();

    public FragOneAdapter(Context context) {
        mContext = context;
    }

    public void add(MyLocation data){
        items.add(data);
        notifyDataSetChanged();
    }

    public void clear(){
        items.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {

        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        FragOneItemView itemView;
        if (view == null) {
            itemView = new FragOneItemView(mContext);
        } else {
            itemView = (FragOneItemView) view;
        }
        itemView.setData(items.get(position));
        return itemView;
    }


}
