package com.sdk.dbutil;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sdk.db.core.BaseDao;
import com.sdk.db.core.BaseDaoFactory;
import com.sdk.dbutil.model.Dog;
import com.sdk.dbutil.model.Test;
import com.sdk.dbutil.model.TestModel;
import com.sdk.dbutil.model.User;

import java.util.List;

import cn.ximoon.framework.db.SQLUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BaseDao baseDao = BaseDaoFactory.getInstance(MainActivity.this).getBaseDao(User.class);
            }
        });

        findViewById(R.id.bt_insert).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseDao baseDao = BaseDaoFactory.getInstance(MainActivity.this).getBaseDao(User.class);
                long result = baseDao.insert(new User(1, "xixi", "123456"));
                Log.i("==========",result+"");
            }
        });

        findViewById(R.id.bt_insert_dog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseDao baseDao = BaseDaoFactory.getInstance(MainActivity.this).getBaseDao(Dog.class);
                baseDao.insert(new Dog(1, "小狗", 18, "bessfds"));
            }
        });

        findViewById(R.id.bt_query_user).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseDao baseDao = BaseDaoFactory.getInstance(MainActivity.this).getBaseDao(User.class);
                User user1 = new User();
                user1.setId(1);
                List<User> users = baseDao.query(user1);
                Log.i("==========",users.size()+"====");
                for (User user : users) {
                    Log.i("==========", user.toString());
                }
            }
        });

        findViewById(R.id.bt_delete_user).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseDao baseDao = BaseDaoFactory.getInstance(MainActivity.this).getBaseDao(User.class);
                User user1 = new User();
                user1.setId(1);
                baseDao.delete(user1);
            }
        });
        findViewById(R.id.bt_update_user).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseDao baseDao = BaseDaoFactory.getInstance(MainActivity.this).getBaseDao(User.class);
                User user1 = new User();
                user1.setId(1);
                User user2 = new User();
                user2.setId(1);
                user2.setName("xiha");
                user2.setPassword("333333");
                long result = baseDao.update(user2,user1);
                Log.i("========",result+"");
            }
        });

        findViewById(R.id.bt_ximoon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    SQLUtil.writeFile(Test.class, TestModel.class,
                            "E:\\mobileGit\\StudyDemo\\dbutil\\src\\main\\java\\com\\sdk\\dbutil\\model\\Test.java",
                            "E:\\mobileGit\\StudyDemo\\dbutil\\src\\main\\java\\com\\sdk\\dbutil\\model\\TestModel.java",
                            "BaseDaoFactory.getInstance(this).getSqLiteDatabase()"
                            );
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


    }

}
