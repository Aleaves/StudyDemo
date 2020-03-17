package com.sdk.dbutil.model;
import com.sdk.db.annotation.DbField;
import com.sdk.db.annotation.DbTable;

@DbTable("tb_user")
public class User {

    @DbField("u_id")
    private int id;

    private String name;

    private String password;

}
