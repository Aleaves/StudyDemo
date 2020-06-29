package com.sdk.recyclertest;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

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
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mRecyclerView = findViewById(R.id.recyclerView);
        initData();
    }

    private void initData(){
        List<ItemBean> list = new ArrayList<>();
        list.add(new ItemBean(MultiAdapter.VIEW_TYPE_BANNER,"1"));
        list.add(new ItemBean(MultiAdapter.VIEW_TYPE_HEAD,"2"));
        list.add(new ItemBean(MultiAdapter.VIEW_TYPE_GAME,"3"));
        list.add(new ItemBean(MultiAdapter.VIEW_TYPE_GAME,"3"));
        list.add(new ItemBean(MultiAdapter.VIEW_TYPE_GAME,"3"));
        list.add(new ItemBean(MultiAdapter.VIEW_TYPE_HEAD,"2"));
        list.add(new ItemBean(MultiAdapter.VIEW_TYPE_DOWNLOAD,"4"));
        list.add(new ItemBean(MultiAdapter.VIEW_TYPE_DOWNLOAD,"4"));
        list.add(new ItemBean(MultiAdapter.VIEW_TYPE_DOWNLOAD,"4"));
        list.add(new ItemBean(MultiAdapter.VIEW_TYPE_DOWNLOAD,"4"));
        list.add(new ItemBean(MultiAdapter.VIEW_TYPE_DOWNLOAD,"4"));
        list.add(new ItemBean(MultiAdapter.VIEW_TYPE_DOWNLOAD,"4"));
        list.add(new ItemBean(MultiAdapter.VIEW_TYPE_DOWNLOAD,"4"));
        list.add(new ItemBean(MultiAdapter.VIEW_TYPE_DOWNLOAD,"4"));
        list.add(new ItemBean(MultiAdapter.VIEW_TYPE_DOWNLOAD,"4"));
        list.add(new ItemBean(MultiAdapter.VIEW_TYPE_DOWNLOAD,"4"));
        list.add(new ItemBean(MultiAdapter.VIEW_TYPE_DOWNLOAD,"4"));
        list.add(new ItemBean(MultiAdapter.VIEW_TYPE_DOWNLOAD,"4"));
        list.add(new ItemBean(MultiAdapter.VIEW_TYPE_DOWNLOAD,"4"));
        list.add(new ItemBean(MultiAdapter.VIEW_TYPE_DOWNLOAD,"4"));
        list.add(new ItemBean(MultiAdapter.VIEW_TYPE_DOWNLOAD,"4"));
        list.add(new ItemBean(MultiAdapter.VIEW_TYPE_DOWNLOAD,"4"));
        list.add(new ItemBean(MultiAdapter.VIEW_TYPE_DOWNLOAD,"4"));
        list.add(new ItemBean(MultiAdapter.VIEW_TYPE_DOWNLOAD,"4"));
        list.add(new ItemBean(MultiAdapter.VIEW_TYPE_DOWNLOAD,"4"));
        list.add(new ItemBean(MultiAdapter.VIEW_TYPE_DOWNLOAD,"4"));

        MultiAdapter multiAdapter = new MultiAdapter(list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(multiAdapter);

    }

}
