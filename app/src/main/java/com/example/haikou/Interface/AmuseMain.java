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

import com.example.haikou.Datas.Amuse;
import com.example.haikou.R;
import com.example.haikou.Util.AmuseAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AmuseMain extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;

    private Amuse[] amuses = {new Amuse("海南粉", R.drawable.hnnoodle),
            new Amuse("肠粉",R.drawable.changfen),
            new Amuse("白斩鸡",R.drawable.chicken),
            new Amuse("和乐蟹",R.drawable.crab),
            new Amuse("加积鸭",R.drawable.duck),
            new Amuse("烤乳猪",R.drawable.pig),
            new Amuse("清补凉",R.drawable.qingbuliang),
            new Amuse("海鲜",R.drawable.seafood),
            new Amuse("东山羊",R.drawable.sheep)
    };

    private List<Amuse> amuseList = new ArrayList<>();

    private AmuseAdapter adapter;

    private SwipeRefreshLayout swipeRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_amuse);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("娱乐");
        setSupportActionBar(toolbar);

        ScrollTop();

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);


        ActionBar actionBar = getSupportActionBar();                                                    //HomeAsUp按钮
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }


        initamuses();                                                                                    //amuse Adapter
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.amuse_recycler_view);
        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new AmuseAdapter(amuseList);
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
                        initamuses();                                                                  //重新生成数据
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

    private void initamuses(){
        amuseList.clear();
        for(int i = 0;i < 50;i++){
            Random random = new Random();
            int index = random.nextInt(amuses.length);
            amuseList.add(amuses[index]);
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


