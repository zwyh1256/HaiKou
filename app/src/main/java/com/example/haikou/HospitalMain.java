package com.example.haikou;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HospitalMain extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;

    private Hospital[] hospitals = {new Hospital("海南粉",R.drawable.hnnoodle),
            new Hospital("肠粉",R.drawable.changfen),
            new Hospital("白斩鸡",R.drawable.chicken),
            new Hospital("和乐蟹",R.drawable.crab),
            new Hospital("加积鸭",R.drawable.duck),
            new Hospital("烤乳猪",R.drawable.pig),
            new Hospital("清补凉",R.drawable.qingbuliang),
            new Hospital("海鲜",R.drawable.seafood),
            new Hospital("东山羊",R.drawable.sheep)
    };

    private List<Hospital> hospitalList = new ArrayList<>();

    private HospitalAdapter adapter;

    private SwipeRefreshLayout swipeRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_hospital);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton top = (FloatingActionButton)findViewById(R.id.top);
        top.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Snackbar.make(view,"Data deleted", Snackbar.LENGTH_SHORT)
                        .setAction("Undo",new View.OnClickListener(){
                            @Override
                            public void onClick(View v){
                                Toast.makeText(HospitalMain.this,"Data restored",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }).show();
            }
        });

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        NavigationView navView = (NavigationView)findViewById(R.id.nav_view);

        ActionBar actionBar = getSupportActionBar();                                                    //HomeAsUp按钮
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_1);
        }
        navView.setCheckedItem(R.id.nav_home);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                mDrawerLayout.closeDrawers();
                return true;
            }
        });

        inithospitals();                                                                                    //hospital Adapter
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.hospital_recycler_view);
        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new HospitalAdapter(hospitalList);
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
                        inithospitals();                                                                  //重新生成数据
                        adapter.notifyDataSetChanged();
                        swipeRefresh.setRefreshing(false);
                    }
                });
            }
        }).start();
    }

    private void inithospitals(){
        hospitalList.clear();
        for(int i = 0;i < 50;i++){
            Random random = new Random();
            int index = random.nextInt(hospitals.length);
            hospitalList.add(hospitals[index]);
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
                mDrawerLayout.openDrawer(GravityCompat.START);
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


