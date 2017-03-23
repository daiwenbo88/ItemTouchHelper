package com.example.newslabel.persenter;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;

import com.example.newslabel.R;
import com.example.newslabel.adapter.NewsLabelAdapter;
import com.example.newslabel.util.StatusBarUtil;
import com.example.newslabel.view.IView;

/**
 * Created by daiwenbo on 17/3/21.
 */

public  abstract class ActivityPresenter<T extends IView> extends AppCompatActivity{
    protected T viewDelegate;

    public ActivityPresenter() {
        try {
            viewDelegate=getDelegateClass().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setColor(this, ContextCompat.getColor(this,R.color.colorPrimary));
        viewDelegate.create(getLayoutInflater(),null,savedInstanceState);
        setContentView(viewDelegate.getRootView());
        viewDelegate.initWidget();
        bindEvenListener();
    }

    protected void bindEvenListener(){

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if(null==viewDelegate){
            try {
                viewDelegate=getDelegateClass().newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewDelegate=null;
    }

    protected abstract Class<T> getDelegateClass();
}
