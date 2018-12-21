package com.dilerdesenvolv.cinqagendateste.domain.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;


@Entity(tableName = "users")
public class User implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo
    private String nome;

    @ColumnInfo
    private String email;

    @ColumnInfo
    private String senha;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }


    public User() {}

    // PARCELABLE
    public User(Parcel parcel) {
        setId(parcel.readInt());
        setNome(parcel.readString());
        setEmail(parcel.readString());
        setSenha(parcel.readString());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(getId());
        dest.writeString(getNome());
        dest.writeString(getEmail());
        dest.writeString(getSenha());
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }
        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

}

