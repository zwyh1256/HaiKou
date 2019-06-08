package com.example.haikou.Interface;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.haikou.Util.GlideImageLoader;
import com.example.haikou.Util.MainAdapter;
import com.example.haikou.R;
import com.example.haikou.Datas.Sort;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Banner banner;
    private ArrayList<Integer> imageList;
    private DrawerLayout mDrawerLayout;

    private Sort[] sorts = {new Sort("景点", R.drawable.car,1),
            new Sort("美食", R.drawable.food,2),
            new Sort("医疗", R.drawable.hospital,3),
            new Sort("购物", R.drawable.shop,4),
            new Sort("住宿", R.drawable.house3,5),
            new Sort("娱乐", R.drawable.entertainment,6)};

    private List<Sort> sortList = new ArrayList<>();


    private MainAdapter adapter;

    private SwipeRefreshLayout swipeRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //TopButton
//        FloatingActionButton top = (FloatingActionButton)findViewById(R.id.top);
//        top.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view){
//                RecyclerView recyclerView = (RecyclerView)findViewById(R.id.food_recycler_view);
//                recyclerView.smoothScrollToPosition(0);
//            }
//        });

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        NavigationView navView = (NavigationView)findViewById(R.id.nav_view);

        //toolbar
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



        //Banner
        initImages();


        //RecyclerView
        initSorts();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MainAdapter(sortList);
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

    //initSorts
    private void initSorts() {
        sortList.clear();
        for (int i = 0; i < 6; i++) {
            sortList.add(sorts[i]);
        }
    }

    //toolbar
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }

    //Serach
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


    //refresh
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
                        initSorts();                                                                  //重新生成数据
                        adapter.notifyDataSetChanged();
                        swipeRefresh.setRefreshing(false);
                    }
                });
            }
        }).start();
    }

    private void initImages(){
        Banner banner = findViewById(R.id.banner);

        imageList = new ArrayList<Integer>();
        imageList.add(R.drawable.banner6);
        imageList.add(R.drawable.banner7);
        imageList.add(R.drawable.banner8);
        imageList.add(R.drawable.banner9);
        imageList.add(R.drawable.banner10);
        imageList.add(R.drawable.banner11);


        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(imageList);
        banner.setDelayTime(5000);
        //banner设置方法全部调用完毕时最后调用
        banner.start();

//        banner.setOnBannerListener(new OnBannerListener() {
//            @Override5
//            public void OnBannerClick(int position) {
//                Uri parse = Uri.parse("https://www.baidu.com/");
//                Intent intent = new Intent(Intent.ACTION_VIEW,parse);
//                startActivity(intent);
//            }
//        });
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {

                Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
                intent.putExtra("urlBaidu","https://www.baidu.com");
                startActivity(intent);
            }
        });


    }

}