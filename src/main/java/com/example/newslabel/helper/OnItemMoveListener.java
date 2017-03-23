package com.example.newslabel.helper;

/**
 * Created by daiwenbo on 17/3/22.
 */

public interface OnItemMoveListener {
    void onItemMove(int fromPosition, int toPosition);//item拖拽的起始位置
    void onItemDismiss(int position);//滑动删除的item
}
