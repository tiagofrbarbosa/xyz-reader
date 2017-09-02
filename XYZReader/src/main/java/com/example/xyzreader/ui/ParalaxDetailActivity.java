package com.example.xyzreader.ui;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xyzreader.R;
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

        final myArticlesUtils ma = new myArticlesUtils(getIntent().getExtras());
        mCursor = ma.loadContent();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(mCursor.getString(ma.getTitle()));

        TextView textView = (TextView) findViewById(R.id.article_text);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            textView.setText(Html.fromHtml(mCursor.getString(ma.getBody()), Html.FROM_HTML_MODE_COMPACT));
        }else{
            textView.setText(Html.fromHtml(mCursor.getString(ma.getBody())));
        }

        ImageView imageView = (ImageView) findViewById(R.id.header);
        Picasso.with(this).load(mCursor.getString(ma.getImage())).into(imageView);

        FloatingActionButton myFabShare = (FloatingActionButton) findViewById(R.id.my_fab_share);
        myFabShare.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String shareText = mCursor.getString(ma.getTitle()) + " by " + mCursor.getString(ma.getAuthor());
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT,shareText);
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
            }
        });
    }


    private class myArticlesUtils{

        private Bundle bundle;
        private int title = 2;
        private int author = 3;
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

        public int getAuthor(){
            return author;
        }
    }
}
