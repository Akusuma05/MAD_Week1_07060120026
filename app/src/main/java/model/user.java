package model;

import android.os.Parcel;
import android.os.Parcelable;

public class user implements Parcelable {
    private String nama, alamat;
    private int umur,id;


    public user(int id, String nama, String alamat, int umur) {
        this.id = id;
        this.nama = nama;
        this.alamat = alamat;
        this.umur = umur;
    }

    protected user(Parcel in) {
        nama = in.readString();
        alamat = in.readString();
        umur = in.readInt();
        id = in.readInt();
    }

    public static final Creator<user> CREATOR = new Creator<user>() {
        @Override
        public user createFromParcel(Parcel in) {
            return new user(in);
        }

        @Override
        public user[] newArray(int size) {
            return new user[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public int getUmur() {
        return umur;
    }

    public void setUmur(int umur) {
        this.umur = umur;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nama);
        dest.writeString(alamat);
        dest.writeInt(umur);
        dest.writeInt(id);
    }
}

