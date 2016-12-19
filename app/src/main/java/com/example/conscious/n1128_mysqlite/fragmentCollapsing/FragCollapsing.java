package com.example.conscious.n1128_mysqlite.fragmentCollapsing;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.conscious.n1128_mysqlite.R;
import com.example.conscious.n1128_mysqlite.activity.DBHelper;
import com.example.conscious.n1128_mysqlite.dao.MyLocation;

import java.util.ArrayList;

/**
 * Created by conscious on 2016-12-09.
 */

public class FragCollapsing extends Fragment {

    private RecyclerView recyclerView;
    private MyAdapter mAdapter;

    private DBHelper dbHelper;
    private ArrayList<MyLocation> items;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (dbHelper == null) {
            dbHelper = new DBHelper(getContext(), "TEST", null, 1);
        }

        items = dbHelper.getMyLocationAll();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_collapsing, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.scrollToPosition(0);
        recyclerView.smoothScrollToPosition(items.size());

        mAdapter = new MyAdapter(items);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private class MyAdapter extends RecyclerView.Adapter<FragCollapsing.MyViewHolder> {

        private final ArrayList<MyLocation> items;

        public MyAdapter(ArrayList<MyLocation> items) {
            this.items = items;
        }

        @Override
        public FragCollapsing.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_holder_one, viewGroup, false);
            return new FragCollapsing.MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final FragCollapsing.MyViewHolder viewHolder, int position) {
            String locName = items.get(position).getLoc_name();
            String locComment = items.get(position).getLoc_comment();
            String locDate = items.get(position).getLoc_date();
            viewHolder.tvLocName.setText(locName);
            viewHolder.tvLocComment.setText(locComment);
            viewHolder.tvLocDate.setText(locDate);
            viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                }
            });
        }

        @Override
        public int getItemCount() {
            return items.size();
        }
    }

    private class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView tvLocName;
        private TextView tvLocComment;
        private TextView tvLocDate;
        private CardView cardView;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvLocName = (TextView) itemView.findViewById(R.id.tvLocName);
            tvLocComment = (TextView) itemView.findViewById(R.id.tvLocComment);
            tvLocDate = (TextView) itemView.findViewById(R.id.tvLocDate);
            cardView = (CardView) itemView.findViewById(R.id.cardview);
        }
    }
}

