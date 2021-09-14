package com.example.week1_07060120026;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import model.account;
import model.user;

public class MainActivity extends AppCompatActivity implements onCardListener{
    private ArrayList<user> accounts = account.listuser;
    private RecyclerView recyclerView;
    private main_adapter adapter;
    private FloatingActionButton add_floating_button;
    private TextView nodata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Menghilangkan Action Bar
        getSupportActionBar().hide();

        //Program Inti
        initview();
        setupRecyclerView();
        buttonadd();
    }

    private void initview() {
        //Deklarasi
        add_floating_button = findViewById(R.id.add_floating_button);
        recyclerView = findViewById(R.id.recyclerView);
        nodata = findViewById(R.id.nodata);
        adapter = new main_adapter(account.listuser, this);
        if (accounts.size()==0){
            nodata.setVisibility(View.VISIBLE);
        }else{
            nodata.setVisibility(View.INVISIBLE);
        }
    }

    private void setupRecyclerView(){
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getBaseContext());
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onCardClick(int position) {
        Intent intent = new Intent(getBaseContext(),profile_user.class);
        intent.putExtra("posisicard", position);
        startActivity(intent);
    }

    public void buttonadd(){
        add_floating_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(),add_user.class);
                startActivity(intent);
            }
        });
    }
}