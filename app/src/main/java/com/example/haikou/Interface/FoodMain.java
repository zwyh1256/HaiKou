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

import com.example.haikou.Datas.Food;
import com.example.haikou.R;
import com.example.haikou.Util.FoodAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FoodMain extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;

    private Food[] foods = {new Food("海南粉", R.drawable.hnnoodle),
            new Food("肠粉",R.drawable.changfen),
            new Food("白斩鸡",R.drawable.chicken),
            new Food("和乐蟹",R.drawable.crab),
            new Food("加积鸭",R.drawable.duck),
            new Food("烤乳猪",R.drawable.pig),
            new Food("清补凉",R.drawable.qingbuliang),
            new Food("海鲜",R.drawable.seafood),
            new Food("东山羊",R.drawable.sheep)
    };

    private List<Food> foodList = new ArrayList<>();

    private FoodAdapter adapter;

    private SwipeRefreshLayout swipeRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_food);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("美食");
        setSupportActionBar(toolbar);

        ScrollTop();

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout_food);



        ActionBar actionBar = getSupportActionBar();                                                    //HomeAsUp按钮
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            //actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_1);
        }



        initFoods();                                                                                    //Food Adapter
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.food_recycler_view);
        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new FoodAdapter(foodList);
        recyclerView.setAdapter(adapter);


        //Swipe Refresh
        swipeRefresh = (SwipeRefreshLayout)findViewById(R.id.swipe_refresh);                        //拿到实例
        swipeRefresh.setColorSchemeResources(R.color.colorPrimary);                                 //进度条颜色
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {              //下拉刷新监听器
            @Override
            public void onRefresh() {
                refreshFruits();                                                                      //本地刷新
            }
        });
    }

    private void refreshFruits(){
        new Thread(new Runnable() {                                                                   //新线程沉睡2s
            @Override
            public void run() {
                try{
                    Thread.sleep(2000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {                                                        //切换主线程
                    @Override
                    public void run() {
                        initFoods();                                                                  //重新生成数据
                        adapter.notifyDataSetChanged();
                        swipeRefresh.setRefreshing(false);
                    }
                });
            }
        }).start();
    }

    private void initFoods(){
        foodList.clear();
        for(int i = 0;i < 50;i++){
            Random random = new Random();
            int index = random.nextInt(foods.length);
            foodList.add(foods[index]);
        }
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


