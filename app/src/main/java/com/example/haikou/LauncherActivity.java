package com.example.haikou;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import static java.lang.Thread.sleep;

public class LauncherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );


        Intent intent = new Intent(LauncherActivity.this, MainActivity.class);
        LauncherActivity.this.startActivity(intent);

        new Thread( new Runnable( ) {
            @Override
            public void run() {
                //耗时任务，比如加载网络数据
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        LauncherActivity.this.finish();

//                        Intent intent = new Intent(LauncherActivity.this, SceneMain.class);
//                        LauncherActivity.this.startActivity(intent);
//
//                        Intent intent = MainActivity.class.newInstance(LauncherActivity.this);
//
//                        //Intent intent = MainActivity.newInstance(LauncherActivity.this);
//                        startActivity(intent);
//                        LauncherActivity.this.finish();

                    }
                });
            }
        } ).start();
    }
}
