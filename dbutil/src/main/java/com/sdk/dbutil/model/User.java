package com.sdk.dbutil.model;

import androidx.annotation.NonNull;

import com.sdk.db.annotation.DbField;
import com.sdk.db.annotation.DbTable;

@DbTable("tb_user")
public class User {

    @DbField(value = "u_id",unique = true)
    private int id;

    private String name;

    private String password;

    public User() {
    }

    public User(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password == null ? "" : password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NonNull
    @Override
    public String toString() {
        return this.id + "===" + this.name + "===" + this.password;
    }
}
