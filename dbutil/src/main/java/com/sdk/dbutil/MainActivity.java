package com.sdk.dbutil;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.sdk.db.core.BaseDao;
import com.sdk.db.core.BaseDaoFactory;
import com.sdk.dbutil.model.Dog;
import com.sdk.dbutil.model.User;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
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
                baseDao.insert(new User(1,"xixi","123456"));
            }
        });

        findViewById(R.id.bt_insert_dog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseDao baseDao = BaseDaoFactory.getInstance(MainActivity.this).getBaseDao(Dog.class);
                baseDao.insert(new Dog(1,"小狗",18,"bessfds"));
            }
        });

    }

}
