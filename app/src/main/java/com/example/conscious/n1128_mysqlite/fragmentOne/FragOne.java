package com.example.conscious.n1128_mysqlite.fragmentOne;

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

import java.util.ArrayList;

/**
 * Created by conscious on 2016-11-28.
 */

public class FragOne extends Fragment {

    private DBHelper dbHelper;

    private static final int PICK_FROM_CAMERA = 0;
    private static final int PICK_FROM_ALBUM = 1;
    private static final int CROP_FROM_IMAGE = 2;

    private Button btnCreate;
    private EditText etLocName;
    private EditText etLocNo;
    private EditText etLocComment;
    private Button btnInsert;
    private Button btnSelect;
    private Button btnInitialize;
    private Button btnListClear;
    private Button btnWhere;
    private TextView tvNoData;
    private ListView listView;
    private ImageView ivProfile;

    private FragOneAdapter fragOneAdapter;

    public FragOne() {
    }

    public FragOne newInstance(String param1, String param2) {
        FragOne fragment = new FragOne();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_one, container, false);
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
        etLocNo = (EditText) view.findViewById(R.id.etLocNo);
        etLocComment = (EditText) view.findViewById(R.id.etLocComment);

        btnInsert = (Button) view.findViewById(R.id.btnInsert);
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addLoc();
                getLocAll();
            }
        });

        btnSelect = (Button) view.findViewById(R.id.btnSelect);
        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getLocAll();
            }
        });

        btnInitialize = (Button) view.findViewById(R.id.btnListClear);
        btnInitialize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initialize();
            }
        });

        btnListClear = (Button) view.findViewById(R.id.btnListClear);
        btnListClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragOneAdapter.clear();
            }
        });

        btnWhere = (Button) view.findViewById(R.id.btnWhere);
        btnWhere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getItem(etLocNo.getText().toString());
            }
        });

        tvNoData = (TextView) view.findViewById(R.id.tvNoData);

        fragOneAdapter = new FragOneAdapter(getContext());
        listView = (ListView) view.findViewById(R.id.listView);
        listView.setAdapter(fragOneAdapter);


        getLocAll();

    }

    private void addLoc() {
        if (dbHelper == null) {
            dbHelper = new DBHelper(getContext(), "TEST", null, 1);
        }

        String locName = etLocName.getText().toString();
        String locComment = etLocComment.getText().toString();

        MyLocation myLocation = new MyLocation();
        myLocation.setLoc_name(locName);
        myLocation.setLoc_comment(locComment);
        dbHelper.addMyLocation(myLocation);
    }

    private void getLocAll() {
        if (dbHelper == null) {
            dbHelper = new DBHelper(getContext(), "TEST", null, 1);
        }

        ArrayList<MyLocation> myLocations = dbHelper.getMyLocationAll();
        for (MyLocation data : myLocations) {
            fragOneAdapter.add(data);
        }

        if (myLocations.isEmpty()) {
            tvNoData.setVisibility(View.VISIBLE);
            listView.setVisibility(View.GONE);
        } else {
            tvNoData.setVisibility(View.GONE);
            listView.setVisibility(View.VISIBLE);
        }

        listView.smoothScrollToPosition(fragOneAdapter.getCount() - 1);
    }


    private void initialize() {
        if (dbHelper == null) {
            dbHelper = new DBHelper(getContext(), "TEST", null, 1);
        }
        dbHelper.initialize();
        fragOneAdapter.clear();
    }

    private void getItem(String locNo) {
        if (dbHelper == null) {
            dbHelper = new DBHelper(getContext(), "TEST", null, 1);
        }

        ArrayList<MyLocation> items = dbHelper.getLocItem(locNo);
//        MyLocation items = dbHelper.getLocItem(locNo);
        for (MyLocation data : items) {
            fragOneAdapter.add(data);
        }
//        if (items.getLoc_name()==null||items.getLoc_name()=="") {
        if (items.isEmpty()) {
            tvNoData.setVisibility(View.VISIBLE);
            listView.setVisibility(View.GONE);
        } else {
            tvNoData.setVisibility(View.GONE);
            listView.setVisibility(View.VISIBLE);
        }

    }

}
