package com.example.basicbankingapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class user_list extends AppCompatActivity {
    List<com.example.basicbankingapp.Model> modelList_showlist = new ArrayList<>();
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager layoutManager;
    com.example.basicbankingapp.CustomerAdapter_userlist adapter;

    String phonenumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_allusers);

        mRecyclerView = findViewById(R.id.recyclerview);
        mRecyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        showData();
    }

    private void showData() {
        modelList_showlist.clear();
        Cursor cursor = new com.example.basicbankingapp.DatabaseHelper(this).readalldata();
        while(cursor.moveToNext()){
            String balancefromdb = cursor.getString(2);
            Double balance = Double.parseDouble(balancefromdb);

            NumberFormat nf = NumberFormat.getNumberInstance();
            nf.setGroupingUsed(true);
            nf.setMaximumFractionDigits(2);
            nf.setMinimumFractionDigits(2);
            String price = nf.format(balance);

            com.example.basicbankingapp.Model model = new com.example.basicbankingapp.Model(cursor.getString(0), cursor.getString(1), price);
            modelList_showlist.add(model);
        }

        adapter = new com.example.basicbankingapp.CustomerAdapter_userlist(com.example.basicbankingapp.user_list.this, modelList_showlist);
        mRecyclerView.setAdapter(adapter);

    }

    public void nextActivity(int position) {
        phonenumber = modelList_showlist.get(position).getPhoneno();
        Intent intent = new Intent(com.example.basicbankingapp.user_list.this, com.example.basicbankingapp.user_data.class);
        intent.putExtra("phonenumber",phonenumber);
        startActivity(intent);
    }




}
