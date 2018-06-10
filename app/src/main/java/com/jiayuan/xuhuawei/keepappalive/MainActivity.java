package com.jiayuan.xuhuawei.keepappalive;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jiayuan.xuhuawei.keepappalive.services.MainService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent=new Intent(this, MainService.class);
//        intent.putExtra("bind",true);
        startService(intent);
    }

}
