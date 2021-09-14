package com.example.week1_07060120026;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;

public class loading {
    //Deklarasi
    private Activity activity;
    private AlertDialog dialog;

    loading(Activity myActivity){
        activity = myActivity;
    }

    public void loadinganimation(){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.loading, null));
        builder.setCancelable(false);

        dialog = builder.create();
        dialog.show();
    }

    public void dissmissDialog(){
        dialog.dismiss();
    }
}

