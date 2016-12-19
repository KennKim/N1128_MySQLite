package com.example.conscious.n1128_mysqlite.activityCollapsing;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.conscious.n1128_mysqlite.R;
import com.example.conscious.n1128_mysqlite.activity.DBHelper;
import com.example.conscious.n1128_mysqlite.dao.MyLocation;

import java.util.ArrayList;

/**
 * Created by conscious on 2016-12-07.
 */

public class ActivityCollapsing extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private Toolbar toolBar;

    private DBHelper dbHelper;
    private ArrayList<MyLocation> items;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collapsing);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        toolBar = (Toolbar) findViewById(R.id.toolbar);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbarLayout);

        setSupportActionBar(toolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        collapsingToolbarLayout.setTitle(getString(R.string.activityOne));

    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        if (dbHelper == null) {
            dbHelper = new DBHelper(this, "TEST", null, 1);
        }

        items = dbHelper.getMyLocationAll();

//        List<String> items = new ArrayList<>();
//        for (int i = 0; i < 20; i++) {
//            items.add("Item=" + i);
//        }


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
//        recyclerView.setLayoutManager(new GridLayoutManager(this,3));
//        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(new MyAdapter(items));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }



    private class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

        private final ArrayList<MyLocation> items;

        public MyAdapter(ArrayList<MyLocation> items) {
            this.items = items;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_holder_one, viewGroup, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final MyViewHolder viewHolder, int position) {
            String locName = items.get(position).getLoc_name();
            String locComment = items.get(position).getLoc_comment();
            String locDate = items.get(position).getLoc_date();
            viewHolder.tvLocName.setText(locName);
            viewHolder.tvLocComment.setText(locComment);
            viewHolder.tvLocDate.setText(locDate);
            viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getApplicationContext(),"carView " + viewHolder.getAdapterPosition(),Toast.LENGTH_SHORT).show();
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
