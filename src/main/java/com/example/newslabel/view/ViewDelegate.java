package com.example.newslabel.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


/**
 * Created by daiwenbo on 17/3/21.
 * 视图层代理的基类
 */

public abstract class ViewDelegate implements IView {
    protected  final SparseArray<View> mViews=new SparseArray<>();
    protected  View rootView;

    /**
     * 获取视图的布局
     * @return
     */
    public abstract  int getRootLayoutId();
    @Override
    public void create(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        int rootLayoutId=getRootLayoutId();
        rootView=inflater.inflate(rootLayoutId,container,false);
    }

    @Override
    public int getOptiondMenuId() {
        return 0;
    }

    @Override
    public Toolbar getToolbar() {
        return null;
    }

    @Override
    public View getRootView() {
        return rootView;
    }

    @Override
    public void initWidget() {

    }
    public <T extends View> T get(@NonNull int id){
        return (T)bindView(id);
    }
    protected  <T extends View> T bindView(@NonNull int id){
        T view=(T)mViews.get(id);
        if(null==view){
            view= (T) rootView.findViewById(id);
            mViews.put(id,view);
        }
        return view;
    }
    public void setOnClickListener(View.OnClickListener listener,int...ids ){
        if(null==ids){
            return;
        }
        //遍历绑定监听的多个View
        for (int id:ids){
            get(id).setOnClickListener(listener);
        }
    }
    public void toast(CharSequence msg){
        Toast.makeText(rootView.getContext(),msg,Toast.LENGTH_LONG).show();
    }
    public <T extends Activity> T getActivity(){
        return (T) rootView.getContext();
    }


}
