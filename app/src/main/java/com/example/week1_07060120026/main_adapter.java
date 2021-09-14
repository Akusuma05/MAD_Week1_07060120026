package com.example.week1_07060120026;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import model.user;

public class main_adapter extends RecyclerView.Adapter<main_adapter.MyListViewHolder> {

    private ArrayList<user> listuser;
    private onCardListener Cardlistener;

    public main_adapter(ArrayList<user> listuser, onCardListener cardlistener) {
        this.listuser = listuser;
        Cardlistener = cardlistener;
    }

    @NonNull
    @Override
    public main_adapter.MyListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_view_listuser, parent, false);
        return new MyListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull main_adapter.MyListViewHolder holder, int position) {
        holder.card_view_nama.setText(listuser.get(position).getNama());
        holder.card_view_alamat.setText(listuser.get(position).getAlamat());
        holder.card_view_umur.setText(String.valueOf(listuser.get(position).getUmur()));
    }

    @Override
    public int getItemCount() {
        return listuser.size();
    }

    public class MyListViewHolder extends RecyclerView.ViewHolder{
        private CardView card_view_user;
        private TextView card_view_nama, card_view_umur, card_view_alamat;

        public MyListViewHolder(@NonNull View itemView) {
            super(itemView);
            card_view_user = itemView.findViewById(R.id.card_view_user);
            card_view_nama = itemView.findViewById(R.id.card_view_nama);
            card_view_umur = itemView.findViewById(R.id.card_view_umur);
            card_view_alamat = itemView.findViewById(R.id.card_view_alamat);

            //Apabila Card Ditekan
            card_view_user.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Cardlistener.onCardClick(listuser.get(getAdapterPosition()).getId());
                }
            });
        }
    }
}
