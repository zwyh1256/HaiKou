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

public class SceneDetail extends AppCompatActivity {

    public static final String SCENE_NAME = "scene_name";
    public static final String SCENE_IMAGE_ID = "scene_name_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_scene);

        Intent intent = getIntent();
        String sceneName = intent.getStringExtra(SCENE_NAME);
        int sceneImageId = intent.getIntExtra(SCENE_IMAGE_ID,0);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout)findViewById(R.id.collapsing_toolbar);
        ImageView sceneImageView = (ImageView)findViewById(R.id.scene_image_view);
        TextView sceneContentText = (TextView)findViewById(R.id.scene_content_text);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();

        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        collapsingToolbar.setTitle(sceneName);
        Glide.with(this).load(sceneImageId).into(sceneImageView);
        String sceneContent = generateSceneContent (sceneName);
        sceneContentText.setText(sceneContent);
    }

    private String generateSceneContent(String sceneName){
        StringBuilder sceneContent = new StringBuilder();
        for(int i = 0;i < 500;i++){
            sceneContent.append(sceneName);
        }
        return sceneContent.toString();
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
