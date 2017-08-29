package com.example.xyzreader.ui;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.xyzreader.R;

import java.util.ArrayList;

/**
 * Created by tfbarbosa on 28/08/17.
 */

public class ParalaxDetailActivity extends AppCompatActivity {

    private ArrayList<String> stringArrayList;
    private RecyclerView recyclerView;
    private RecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_detail_paralax);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle("teste");

        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager lm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(lm);

        setData();

        adapter = new RecyclerAdapter(this, stringArrayList);
        recyclerView.setAdapter(adapter);
    }

    private void setData(){
        stringArrayList = new ArrayList<>();

        for(int i=0; i<100; i++){
            stringArrayList.add("item " + (i+1));
        }
    }
}
