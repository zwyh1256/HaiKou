package com.example.haikou.Interface;

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
import com.example.haikou.R;

public class HospitalDetail extends AppCompatActivity {

    public static final String HOSPITAL_NAME = "hospital_name";
    public static final String HOSPITAL_IMAGE_ID = "hospital_name_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_hospital);

        Intent intent = getIntent();
        String hospitalName = intent.getStringExtra(HOSPITAL_NAME);
        int hospitalImageId = intent.getIntExtra(HOSPITAL_IMAGE_ID,0);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout)findViewById(R.id.collapsing_toolbar);
        ImageView hospitalImageView = (ImageView)findViewById(R.id.hospital_image_view);
        TextView hospitalContentText = (TextView)findViewById(R.id.hospital_content_text);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();

        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        collapsingToolbar.setTitle(hospitalName);
        Glide.with(this).load(hospitalImageId).into(hospitalImageView);
        String hospitalContent = generatehospitalContent (hospitalName);
        hospitalContentText.setText(hospitalContent);
    }

    private String generatehospitalContent(String hospitalName){
        StringBuilder hospitalContent = new StringBuilder();
        for(int i = 0;i < 500;i++){
            hospitalContent.append(hospitalName);
        }
        return hospitalContent.toString();
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
