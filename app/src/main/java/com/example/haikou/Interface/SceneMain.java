package com.example.haikou.Interface;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.haikou.Datas.Scene;
import com.example.haikou.R;
import com.example.haikou.Util.SceneAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SceneMain extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;

    private Scene[] scenes = {new Scene("海南粉", R.drawable.hnnoodle),
            new Scene("肠粉",R.drawable.changfen),
            new Scene("白斩鸡",R.drawable.chicken),
            new Scene("和乐蟹",R.drawable.crab),
            new Scene("加积鸭",R.drawable.duck),
            new Scene("烤乳猪",R.drawable.pig),
            new Scene("清补凉",R.drawable.qingbuliang),
            new Scene("海鲜",R.drawable.seafood),
            new Scene("东山羊",R.drawable.sheep)
    };

    private List<Scene> sceneList = new ArrayList<>();

    private SceneAdapter adapter;

    private SwipeRefreshLayout swipeRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_scene);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("景点");
        setSupportActionBar(toolbar);

        ScrollTop();

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);



        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        initScenes();
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.scene_recycler_view);
        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new SceneAdapter(sceneList);
        recyclerView.setAdapter(adapter);

        //Swipe Refresh
        swipeRefresh = (SwipeRefreshLayout)findViewById(R.id.swipe_refresh);
        swipeRefresh.setColorSchemeResources(R.color.colorPrimary);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshFruits();
            }
        });
    }

    private void refreshFruits(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(2000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initScenes();
                        adapter.notifyDataSetChanged();
                        swipeRefresh.setRefreshing(false);
                    }
                });
            }
        }).start();
    }

    private void ScrollTop(){
        FloatingActionButton top = (FloatingActionButton)findViewById(R.id.top);
        top.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                RecyclerView recyclerView = (RecyclerView)findViewById(R.id.food_recycler_view);
                recyclerView.smoothScrollToPosition(0);
            }
        });
    }

    private void initScenes(){
        sceneList.clear();
        for(int i = 0;i < 50;i++){
            Random random = new Random();
            int index = random.nextInt(scenes.length);
            sceneList.add(scenes[index]);
        }
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.search:
                Toast.makeText(this, "You clicked Search", Toast.LENGTH_SHORT).
                        show();
                break;
            default:
        }
        return true;
    }
}
