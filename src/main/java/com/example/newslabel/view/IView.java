package com.example.newslabel.view;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * Created by daiwenbo on 17/3/21.
 * 视图层代理的接口协议
 */

public interface IView {
    void create(LayoutInflater inflater, ViewGroup container, Bundle bundle);

    int getOptiondMenuId();

    Toolbar getToolbar();

    View getRootView();

    void initWidget();
}
