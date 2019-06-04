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

public class FoodDetail extends AppCompatActivity {

    public static final String FOOD_NAME = "food_name";
    public static final String FOOD_IMAGE_ID = "food_name_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_food);

        Intent intent = getIntent();
        String foodName = intent.getStringExtra(FOOD_NAME);
        int foodImageId = intent.getIntExtra(FOOD_IMAGE_ID,0);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout)findViewById(R.id.collapsing_toolbar);
        ImageView foodImageView = (ImageView)findViewById(R.id.food_image_view);
        TextView foodContentText = (TextView)findViewById(R.id.food_content_text);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();

        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        collapsingToolbar.setTitle(foodName);
        Glide.with(this).load(foodImageId).into(foodImageView);
        String foodContent = generateFoodContent (foodName);
        foodContentText.setText(foodContent);
    }

    private String generateFoodContent(String foodName){
        StringBuilder foodContent = new StringBuilder();
        for(int i = 0;i < 500;i++){
            foodContent.append(foodName);
        }
        return foodContent.toString();
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
