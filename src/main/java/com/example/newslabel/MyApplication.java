package com.example.newslabel;

import android.app.Application;

/**
 * Created by daiwenbo on 17/3/22.
 */

public class MyApplication extends Application {
    public static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public  static MyApplication getInstance() {
        return instance;
    }
}
