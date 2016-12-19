package com.example.conscious.n1128_mysqlite.fragmentTwo;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.conscious.n1128_mysqlite.R;
import com.example.conscious.n1128_mysqlite.activity.DBHelper;
import com.example.conscious.n1128_mysqlite.dao.MyLocation;
import com.example.conscious.n1128_mysqlite.fragmentOne.FragOneAdapter;

import java.util.ArrayList;

/**
 * Created by conscious on 2016-11-28.
 */

public class FragTwo extends Fragment {

    private DBHelper dbHelper;

    private Button btnCreate;
    private EditText etLocName;
    private EditText etLocComment;
    private Button btnInsert;
    private Button btnSelect;
    private Button btnInitialize;
    private Button btnListClear;
    private TextView tvNoData;
    private ListView listView;
    private ImageView ivProfile;

    private FragOneAdapter fragOneAdapter;

    public FragTwo() {
    }

    public FragTwo newInstance(String param1, String param2) {
        FragTwo fragment = new FragTwo();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_two, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        btnCreate = (Button) view.findViewById(R.id.btnCreate);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "toast", Toast.LENGTH_SHORT).show();
                if (dbHelper == null) {
                    dbHelper = new DBHelper(getContext(), "TESTtt", null, 1);
                }
            }
        });

        etLocName = (EditText) view.findViewById(R.id.etLocName);
        etLocComment = (EditText) view.findViewById(R.id.etLocComment);

        btnInsert = (Button) view.findViewById(R.id.btnInsert);
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addYourLoc();
                getYourLocAll();
            }
        });

        btnSelect = (Button) view.findViewById(R.id.btnSelect);
        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getYourLocAll();
            }
        });

        btnInitialize = (Button) view.findViewById(R.id.btnInitialize);
        btnInitialize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initialize();
            }
        });

        btnListClear = (Button)view.findViewById(R.id.btnListClear);
        btnListClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragOneAdapter.clear();
            }
        });

        tvNoData = (TextView) view.findViewById(R.id.tvNoData);

        fragOneAdapter = new FragOneAdapter(getContext());
        listView = (ListView) view.findViewById(R.id.listView);
        listView.setAdapter(fragOneAdapter);

        getYourLocAll();

    }


    private void addYourLoc() {
        if (dbHelper == null) {
            dbHelper = new DBHelper(getContext(), "TEST", null, 1);
        }

        String locName = etLocName.getText().toString();
        String locComment = etLocComment.getText().toString();

        MyLocation myLocation = new MyLocation();
        myLocation.setLoc_name(locName);
        myLocation.setLoc_comment(locComment);
        dbHelper.addYourLocation(myLocation);
    }

    private void getYourLocAll() {
        if (dbHelper == null) {
            dbHelper = new DBHelper(getContext(), "TEST", null, 1);
        }

        ArrayList<MyLocation> myLocations = dbHelper.getYourLocationAll();
        for (MyLocation data : myLocations) {
            fragOneAdapter.add(data);
        }

        if (myLocations.isEmpty()) {
            tvNoData.setVisibility(View.VISIBLE);
            listView.setVisibility(View.GONE);
        }else{
            tvNoData.setVisibility(View.GONE);
            listView.setVisibility(View.VISIBLE);
        }

        listView.smoothScrollToPosition(fragOneAdapter.getCount() - 1);
    }

    private void initialize(){
        if (dbHelper == null) {
            dbHelper = new DBHelper(getContext(), "TEST", null, 1);
        }
        dbHelper.initialize();
        fragOneAdapter.clear();
    }



}
