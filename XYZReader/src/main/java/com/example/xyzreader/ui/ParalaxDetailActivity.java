package com.example.xyzreader.ui;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.app.LoaderManager;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xyzreader.R;
import com.example.xyzreader.data.ArticleLoader;
import com.example.xyzreader.data.ItemsContract;
import com.squareup.picasso.Picasso;

/**
 * Created by tfbarbosa on 28/08/17.
 */

public class ParalaxDetailActivity extends AppCompatActivity{

    private Cursor mCursor;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_detail_paralax);

        myArticlesUtils ma = new myArticlesUtils(getIntent().getExtras());
        mCursor = ma.loadContent();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(mCursor.getString(ma.getTitle()));

        TextView textView = (TextView) findViewById(R.id.article_text);
        textView.setText(mCursor.getString(ma.getBody()));

        ImageView imageView = (ImageView) findViewById(R.id.header);
        Picasso.with(this).load(mCursor.getString(ma.getImage())).into(imageView);
    }


    private class myArticlesUtils{

        private Bundle bundle;
        private int title = 2;
        private int body = 4;
        private int image = 6;

        public myArticlesUtils(Bundle b){
            bundle = b;
        }

        public Cursor loadContent(){
            Cursor mCursor;
            Uri uri = Uri.parse(bundle.getString("item_uri"));
            mCursor = getContentResolver().query(uri, null, null, null, null);
            if(mCursor != null) mCursor.moveToFirst();
            return mCursor;
        }

        public int getTitle(){
            return title;
        }

        public int getBody(){
            return body;
        }

        public int getImage(){
            return image;
        }
    }
}
