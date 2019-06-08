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

public class ApartmentDetail extends AppCompatActivity {

    public static final String APARTMENT_NAME = "apartment_name";
    public static final String APARTMENT_IMAGE_ID = "apartment_name_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_apartment);

        Intent intent = getIntent();
        String apartmentName = intent.getStringExtra(APARTMENT_NAME);
        int apartmentImageId = intent.getIntExtra(APARTMENT_IMAGE_ID,0);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout)findViewById(R.id.collapsing_toolbar);
        ImageView apartmentImageView = (ImageView)findViewById(R.id.apartment_image_view);
        TextView apartmentContentText = (TextView)findViewById(R.id.apartment_content_text);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();

        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        collapsingToolbar.setTitle(apartmentName);
        Glide.with(this).load(apartmentImageId).into(apartmentImageView);
        String apartmentContent = generateapartmentContent (apartmentName);
        apartmentContentText.setText(apartmentContent);
    }

    private String generateapartmentContent(String apartmentName){
        StringBuilder apartmentContent = new StringBuilder();
        for(int i = 0;i < 500;i++){
            apartmentContent.append(apartmentName);
        }
        return apartmentContent.toString();
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
