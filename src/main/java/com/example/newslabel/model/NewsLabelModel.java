package com.example.newslabel.model;

import android.support.v4.content.ContextCompat;

import com.example.newslabel.MyApplication;
import com.example.newslabel.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by daiwenbo on 17/3/22.
 */

public class NewsLabelModel implements IModel {
    public List<String> getTopComplete(){
        return getLabel(R.array.news_label);
    }
    public List<String> getBottomComplete(){
        return getLabel(R.array.none_label);
    }
    public List<String> getLabel(int id){
        if (0==id){return null;};
        List<String> lists=new ArrayList<>();
        String[] labels= MyApplication.getInstance().getResources().getStringArray(id);
        lists.addAll(Arrays.asList(labels));
        return lists;
    }
}
