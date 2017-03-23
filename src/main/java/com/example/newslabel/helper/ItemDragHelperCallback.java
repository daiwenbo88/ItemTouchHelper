package com.example.newslabel.helper;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;

import com.example.newslabel.adapter.NewsLabelAdapter;

/**
 * Created by daiwenbo on 17/3/22.
 */

public class ItemDragHelperCallback extends ItemTouchHelper.Callback {
    /**
     * 指定需要拖拽的方向
     *
     * @param recyclerView
     * @param viewHolder
     * @return
     */
    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int dragFlags;//拖拽方向
        int swipeFlags;//滑动方向
        //针对第一个不能拖拽
        if (viewHolder.getAdapterPosition() == 0) {
            return 0;
        }
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager || layoutManager instanceof StaggeredGridLayoutManager) {
            dragFlags = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT | ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        } else {
            dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        }
        swipeFlags = 0;
        return makeMovementFlags(dragFlags, swipeFlags);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        //不同类型的item 不能拖拽
        if (viewHolder.getItemViewType() != target.getItemViewType()) {
            return false;
        }
        //如果目标点为第一个 也不能拖拽过去
        if(target.getAdapterPosition()==0){
            return false;
        }
        if (recyclerView.getAdapter() instanceof OnItemMoveListener) {
            OnItemMoveListener listener = (OnItemMoveListener) recyclerView.getAdapter();
            listener.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        }
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        // 不需要滑动功能
        return false;
    }

    @Override
    public boolean isLongPressDragEnabled() {
        // 长按拖拽功能
        return true;
    }
}
