package com.example.haikou;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class AmuseDetail extends AppCompatActivity {

    public static final String AMUSE_NAME = "amuse_name";
    public static final String AMUSE_IMAGE_ID = "amuse_name_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_amuse);

        Intent intent = getIntent();
        String amuseName = intent.getStringExtra(AMUSE_NAME);
        int amuseImageId = intent.getIntExtra(AMUSE_IMAGE_ID,0);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout)findViewById(R.id.collapsing_toolbar);
        ImageView amuseImageView = (ImageView)findViewById(R.id.amuse_image_view);
        TextView amuseContentText = (TextView)findViewById(R.id.amuse_content_text);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();

        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        collapsingToolbar.setTitle(amuseName);
        Glide.with(this).load(amuseImageId).into(amuseImageView);
        String amuseContent = generateamuseContent (amuseName);
        amuseContentText.setText(amuseContent);
    }

    private String generateamuseContent(String amuseName){
        StringBuilder amuseContent = new StringBuilder();
        for(int i = 0;i < 500;i++){
            amuseContent.append(amuseName);
        }
        return amuseContent.toString();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
