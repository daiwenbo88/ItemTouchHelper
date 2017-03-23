package com.example.newslabel.view;


import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.newslabel.divider.DividerGridItemDecoration;
import com.example.newslabel.R;

/**
 * Created by daiwenbo on 17/3/21.
 */

public class SimpleView extends ViewDelegate {
    RecyclerView top_recyclerView;
    RecyclerView bottom_recyclerView;
    TextView tv_complete;
    @Override
    public int getRootLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initWidget() {
        super.initWidget();
        Toolbar toolbar=get(R.id.toolbar);
        toolbar.setTitle("网易新闻");
        top_recyclerView=get(R.id.top_recyclerView);
        bottom_recyclerView=get(R.id.bottom_recyclerView);
        tv_complete=get(R.id.tv_complete);
        top_recyclerView.addItemDecoration(new DividerGridItemDecoration(this.getActivity()));
        bottom_recyclerView.addItemDecoration(new DividerGridItemDecoration(this.getActivity()));

    }
    public void setTopLayoutManager(GridLayoutManager manager){
        top_recyclerView.setLayoutManager(manager);
    }
    public void setTopAdapter(RecyclerView.Adapter adapter){
        top_recyclerView.setAdapter(adapter);
    }
    public void setBottomLayoutManager(GridLayoutManager manager){
        bottom_recyclerView.setLayoutManager(manager);
    }

    public void setBottomAdapter(RecyclerView.Adapter adapter){
        bottom_recyclerView.setAdapter(adapter);
    }

    public void setItemHelper(ItemTouchHelper itemHelper) {
       itemHelper.attachToRecyclerView(top_recyclerView);
    }
    public void setIsVisible(boolean isVisible){
        if(isVisible){

            tv_complete.setVisibility(View.VISIBLE);
        }else {
            tv_complete.setVisibility(View.GONE);
        }
    }
}
