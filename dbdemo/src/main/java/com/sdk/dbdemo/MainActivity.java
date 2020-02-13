package com.sdk.dbdemo;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.sdk.dbdemo.db.BaseDao;
import com.sdk.dbdemo.db.BaseDaoFactory;
import com.sdk.dbdemo.model.User;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

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
                test();
            }
        });

        findViewById(R.id.bt_insert).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BaseDao baseDao = BaseDaoFactory.getInstance().getBaseDao(User.class);
                baseDao.insert(new User(1,"netease1","111"));
                baseDao.insert(new User(2,"netease2","111"));
                baseDao.insert(new User(3,"netease3","111"));
                baseDao.insert(new User(4,"netease4","111"));
                baseDao.insert(new User(5,"netease5","111"));
                baseDao.insert(new User(6,"netease6","111"));
            }
        });

        findViewById(R.id.bt_query).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("===========","111");
                BaseDao baseDao = BaseDaoFactory.getInstance().getBaseDao(User.class);
                User where = new User();
                //where.setName("netease1");
                //where.setPassword("111");
                List<User> list = baseDao.query(where);
                Log.e("=========="," list size is "+list.size());
                for(int i=0;i<list.size();i++){
                    Log.i("==========",list.get(i).toString());
                }
            }
        });

    }

    private void  test(){
        BaseDao baseDao = new BaseDao();
        baseDao.init(null, User.class);
    }

}
