package com.example.newslabel.persenter;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;

import com.example.newslabel.R;
import com.example.newslabel.adapter.NewsLabelAdapter;
import com.example.newslabel.adapter.NewsNoneLabelAdapter;
import com.example.newslabel.adapter.OnDeleteData;
import com.example.newslabel.helper.ItemDragHelperCallback;
import com.example.newslabel.model.NewsLabelModel;
import com.example.newslabel.view.SimpleView;

import java.util.List;

public class MainActivity extends ActivityPresenter<SimpleView> {

    private static final String TAG = MainActivity.class.getSimpleName();
    private NewsLabelAdapter topadapter;
    private NewsNoneLabelAdapter bottomAdapter;
    private NewsLabelModel model;

    @Override
    protected Class<SimpleView> getDelegateClass() {
        return SimpleView.class;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        model = new NewsLabelModel();
        List<String> topComplete = model.getTopComplete();
        List<String> bottomComplete = model.getBottomComplete();

        topadapter = new NewsLabelAdapter(this,topComplete);
        bottomAdapter = new NewsNoneLabelAdapter(this,bottomComplete);

        GridLayoutManager manager = new GridLayoutManager(this, 4);
        GridLayoutManager manager_02 = new GridLayoutManager(this, 4);

        ItemDragHelperCallback callback=new ItemDragHelperCallback();
        ItemTouchHelper helper=new ItemTouchHelper(callback);
        viewDelegate.setItemHelper(helper);
        viewDelegate.setTopLayoutManager(manager);
        viewDelegate.setBottomLayoutManager(manager_02);

        viewDelegate.setTopAdapter(topadapter);
        viewDelegate.setBottomAdapter(bottomAdapter);

        topadapter.setOnIsEditMode(new NewsLabelAdapter.OnIsEditMode() {
            @Override
            public void isEditMode(boolean isEditMode) {
                Log.e(TAG,"调用该方法了1");
                viewDelegate.setIsVisible(isEditMode);
            }
        });
        topadapter.setOnDeleteData(new OnDeleteData() {
            @Override
            public void onDelete(String label) {
                bottomAdapter.addDate(label);
            }
        });
        bottomAdapter.setOnDeleteData(new OnDeleteData() {
            @Override
            public void onDelete(String label) {
                topadapter.addData(label);
            }
        });

    }

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        viewDelegate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG,"调用该方法了2");
                topadapter.setEndEditMode();
                //隐藏编辑按钮
                viewDelegate.setIsVisible(false);
            }
        }, R.id.tv_complete);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }




}
