package com.example.newslabel.contract;

import java.util.List;

/**
 * Created by daiwenbo on 17/3/21.
 */

public class NewsContract {
    public interface NewsDataLoadLiltenter{
        void onComplete(List<String> list);
        void onBottomComplete(List<String> list);
    }
}
