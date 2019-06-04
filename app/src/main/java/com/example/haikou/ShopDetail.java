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

public class ShopDetail extends AppCompatActivity {

    public static final String SHOP_NAME = "shop_name";
    public static final String SHOP_IMAGE_ID = "shop_name_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_shop);

        Intent intent = getIntent();
        String shopName = intent.getStringExtra(SHOP_NAME);
        int shopImageId = intent.getIntExtra(SHOP_IMAGE_ID,0);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout)findViewById(R.id.collapsing_toolbar);
        ImageView shopImageView = (ImageView)findViewById(R.id.shop_image_view);
        TextView shopContentText = (TextView)findViewById(R.id.shop_content_text);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();

        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        collapsingToolbar.setTitle(shopName);
        Glide.with(this).load(shopImageId).into(shopImageView);
        String shopContent = generateShopContent (shopName);
        shopContentText.setText(shopContent);
    }

    private String generateShopContent(String shopName){
        StringBuilder shopContent = new StringBuilder();
        for(int i = 0;i < 500;i++){
            shopContent.append(shopName);
        }
        return shopContent.toString();
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
