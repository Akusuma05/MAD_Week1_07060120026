package com.example.week1_07060120026;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

import model.account;
import model.user;

public class add_user extends AppCompatActivity {

    private Toolbar add_toolbar;
    private TextInputLayout input_nama, input_umur, input_alamat;
    private Button save_button;
    private ArrayList<user> accounts = account.listuser;
    private int id = 0;
    private final loading Loading = new loading(add_user.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        //Menghilangkan Action Bar
        getSupportActionBar().hide();

        //Program inti
        initview();
        backtoolbar();
        nambahuser();
    }

    private void initview() {
        //Deklarasi
        input_nama = findViewById(R.id.input_nama);
        input_umur = findViewById(R.id.input_umur);
        input_alamat = findViewById(R.id.input_alamat);
        add_toolbar = findViewById(R.id.add_toolbar);
        save_button = findViewById(R.id.save_button);

        //Apabila Edit User
        if (getIntent().getExtras() != null) {
            id = getIntent().getExtras().getInt("posisicard");
            for (int i = 0; i < accounts.size(); i++) {
                if (id == accounts.get(i).getId()) {
                    input_nama.getEditText().setText(accounts.get(i).getNama());
                    input_umur.getEditText().setText(String.valueOf(accounts.get(i).getUmur()));
                    input_alamat.getEditText().setText(accounts.get(i).getAlamat());
                    add_toolbar.setSubtitle("Edit User");
                    save_button.setText("Save Edit");
                }
            }
        }
    }

    private void backtoolbar() {
        add_toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
    }

    private void nambahuser() {
        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Apabila Edit User || *id=0 add || *id!=0 edit ||

                if (id != 0) {

                    //Edit User
                    String tempnama = input_nama.getEditText().getText().toString().trim();
                    int tempumur = Integer.parseInt(input_umur.getEditText().getText().toString().trim());
                    String tempalamat = input_alamat.getEditText().getText().toString().trim();

                    //Loading Screen
                    Loading.loadinganimation();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Loading.dissmissDialog();

                            //Inti Edit User
                            for (int i = 0; i < accounts.size(); i++) {
                                if (id == accounts.get(i).getId()) {
                                    user user = new user(id, tempnama, tempalamat, tempumur);
                                    account.listuser.set(i, user);
                                    Intent intent = new Intent(getBaseContext(), MainActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
                                    Toast.makeText(add_user.this, "User Edited", Toast.LENGTH_SHORT).show();
                                }
                            }

                        }
                    }, 1000);


                } else {

                    String tempnama = input_nama.getEditText().getText().toString().trim();
                    int tempumur = Integer.parseInt(input_umur.getEditText().getText().toString().trim());
                    String tempalamat = input_alamat.getEditText().getText().toString().trim();

                    if (accounts.size() > 0) {
                        //Add User
                        int temp = accounts.get(accounts.size() - 1).getId();
                        if (accounts.size() == temp) {

                            //Account > 0 udah pernah di delete
                            //Loading animation
                            Loading.loadinganimation();
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Loading.dissmissDialog();

                                    //Add User inti
                                    user user = new user(accounts.size() + 1, tempnama, tempalamat, tempumur);
                                    account.listuser.add(user);
                                    Intent intent = new Intent(getBaseContext(), MainActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
                                    Toast.makeText(add_user.this, "User Added", Toast.LENGTH_SHORT).show();
                                }
                            }, 1000);

                        } else {

                            //Account>0 blom pernah di delete
                            //Loading Animation
                            Loading.loadinganimation();
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Loading.dissmissDialog();

                                    //Add User Inti
                                    user user = new user(temp + 1, tempnama, tempalamat, tempumur);
                                    account.listuser.add(user);
                                    Intent intent = new Intent(getBaseContext(), MainActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
                                    Toast.makeText(add_user.this, "User Added", Toast.LENGTH_SHORT).show();
                                }
                            }, 1000);
                        }

                    } else {

                        //Account = 0
                        //Loading Animation
                        Loading.loadinganimation();
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Loading.dissmissDialog();

                                //Add User inti
                                user user = new user(accounts.size() + 1, tempnama, tempalamat, tempumur);
                                account.listuser.add(user);
                                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                                Toast.makeText(add_user.this, "User Added", Toast.LENGTH_SHORT).show();
                            }
                        }, 1000);
                    }
                }
            }
        });
    }
}