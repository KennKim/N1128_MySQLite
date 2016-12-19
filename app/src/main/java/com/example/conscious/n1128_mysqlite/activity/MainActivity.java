package com.example.conscious.n1128_mysqlite.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.conscious.n1128_mysqlite.activityCollapsing.ActivityCollapsing;
import com.example.conscious.n1128_mysqlite.R;
import com.example.conscious.n1128_mysqlite.fragmentCollapsing.FragCollapsing;
import com.example.conscious.n1128_mysqlite.fragmentOne.FragOne;
import com.example.conscious.n1128_mysqlite.fragmentTwo.FragTwo;

public class MainActivity extends AppCompatActivity {

    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(this, "create?", Toast.LENGTH_SHORT).show();
        if (dbHelper == null) {
            dbHelper = new DBHelper(getApplicationContext(), "MYTABLE", null, 1);
        }

        Button btn = (Button) findViewById(R.id.btnMy);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fr = new FragOne();
                FragmentTransaction fragTr = getSupportFragmentManager().beginTransaction();
                fragTr.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
                fragTr.replace(R.id.myframe, fr);
                fragTr.commitAllowingStateLoss();
            }
        });

        btn = (Button) findViewById(R.id.btnYour);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fr = new FragTwo();
                FragmentTransaction fragTr = getSupportFragmentManager().beginTransaction();
                fragTr.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
                fragTr.replace(R.id.myframe,fr);
                fragTr.commitAllowingStateLoss();
            }
        });

        btn = (Button) findViewById(R.id.btnActiRe);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ActivityCollapsing.class);
                startActivity(intent);
            }
        });
        btn = (Button) findViewById(R.id.btnFragRe);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fr = new FragCollapsing();
                FragmentTransaction fragTr = getSupportFragmentManager().beginTransaction();
                fragTr.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
                fragTr.replace(R.id.myframe,fr);
                fragTr.commitAllowingStateLoss();
            }
        });


    }
}
