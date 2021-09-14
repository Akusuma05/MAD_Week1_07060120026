package com.example.week1_07060120026;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import model.account;
import model.user;

public class profile_user extends AppCompatActivity {
    private ArrayList<user> accounts = account.listuser;
    private Toolbar toolbar_user_details;
    private TextView output_nama, output_alamat, output_umur;
    private Button edit_button, delete_button;
    private int id = 0;
    private final loading Loading = new loading(profile_user.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_user);

        //Menghilangkan Action Bar
        getSupportActionBar().hide();

        //Program inti
        initview();
        display();
        edit();
        delete();
        backtoolbar();
    }

    private void initview() {
        //Deklarasi
        toolbar_user_details = findViewById(R.id.toolbar_user_details);
        output_nama = findViewById(R.id.output_nama);
        output_alamat = findViewById(R.id.output_alamat);
        output_umur = findViewById(R.id.output_umur);
        edit_button = findViewById(R.id.edit_button);
        delete_button = findViewById(R.id.delete_button);
        id = getIntent().getExtras().getInt("posisicard");
    }

    private void display(){
        for (int i = 0; i < accounts.size(); i++){
            if (accounts.get(i).getId() == id){
                output_nama.setText(accounts.get(i).getNama());
                output_alamat.setText(accounts.get(i).getAlamat());
                output_umur.setText(String.valueOf(accounts.get(i).getUmur()));
            }
        }
    }

    private void edit(){
        edit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Loading.loadinganimation();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Loading.dissmissDialog();

                        Intent intent = new Intent(getBaseContext(), add_user.class);
                        intent.putExtra("posisicard", id);
                        startActivity(intent);
                    }
                },1000);
            }
        });
    }

    private void delete(){
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Pop Up Are you sure Yes / No
                AlertDialog.Builder builder = new AlertDialog.Builder(profile_user.this);
                builder.setCancelable(false);
                builder.setMessage("Do you really want to delete this user? :'(");

                //Kalo Yes
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //Loading Animation
                        Loading.loadinganimation();
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Loading.dissmissDialog();

                                //Inti Delete
                                for (int i = 0; i < accounts.size(); i++) {
                                    if (id == accounts.get(i).getId()) {
                                        account.listuser.remove(i);
                                        Intent intent = new Intent(getBaseContext(), MainActivity.class);
                                        startActivity(intent);
                                        Toast.makeText(profile_user.this, "User Deleted", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        },1000);

                    }
                });

                //Kalo No
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.create();
                builder.show();

            }
        });
    }

    private void backtoolbar() {
        toolbar_user_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
    }
}