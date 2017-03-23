package com.example.newslabel.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.newslabel.R;
import com.example.newslabel.helper.ItemTouchHelperViewHolder;
import com.example.newslabel.helper.OnItemMoveListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by daiwenbo on 17/3/22.
 */

public class NewsLabelAdapter extends RecyclerView.Adapter<NewsLabelAdapter.ViewHolder> implements OnItemMoveListener {
    private static final String TAG = NewsLabelAdapter.class.getSimpleName();
    public List<String> labels = new ArrayList<>();
    public Context context;
    public static int num = 0;
    int color;
    private boolean isEditMode;
    private OnIsEditMode onIsEditMode;
    private OnDeleteData onDeleteData;

    public void setOnDeleteData(OnDeleteData onDeleteData) {
        this.onDeleteData = onDeleteData;
    }

    public void setOnIsEditMode(OnIsEditMode onIsEditMode) {
        this.onIsEditMode = onIsEditMode;
    }

    public NewsLabelAdapter(Context context, List<String> label) {
        this.context = context;
        this.labels.addAll(label);
        color = ContextCompat.getColor(context, R.color.c_474747);
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_label, null, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.textView.setText(labels.get(position));
        if (position == 0) {
            //第一个为选中状态
            if (num == position) {
                //背景为红色
                setItemStyle(holder, R.drawable.selector_label, Color.WHITE, position);
            } else {
                //编辑状态下
                if (isEditMode) {
                    //第一个 为不带边框
                    int color = ContextCompat.getColor(context, R.color.c_929292);
                    setItemStyle(holder, R.drawable.selector_label_one, color, position);
                } else {
                    //非编辑状态下 第一个没有选中 就是带边框
                    setItemStyle(holder, R.drawable.selector_label_none, color, position);
                }
            }
        } else if (num == position) {
            //其余选中为红色
            setItemStyle(holder, R.drawable.selector_label, Color.WHITE, position);
        } else {
            setItemStyle(holder, R.drawable.selector_label_none, color, position);
        }

        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //不是在编辑状态 且该item没有被选中
                if (!isEditMode && num != position) {
                    num = position;
                    Drawable drawable = context.getResources().getDrawable(R.drawable.selector_label);
                    holder.textView.setBackground(drawable);
                    holder.textView.setTextColor(Color.WHITE);
                    notifyDataSetChanged();
                }
            }
        });
        holder.textView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (!isEditMode) {
                    isEditMode = true;
                    if (null != onIsEditMode) {
                        //显示编辑按钮
                        onIsEditMode.isEditMode(isEditMode);
                    }
                    notifyDataSetChanged();
                }
                return true;
            }
        });
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //不是第一个 在编辑状态下 监听不为空
                if (position != 0 && isEditMode && onDeleteData != null) {
                    //更新视图
                    String tag = labels.remove(position);
                    if (!tag.isEmpty()) {
                        notifyItemRemoved(position);
                        onDeleteData.onDelete(tag);
                        notifyItemRangeChanged(0,labels.size());
                    }

                }
            }
        });
    }

    private void setItemStyle(ViewHolder holder, int drawable_id, int color, int position) {
        if (isEditMode) {
            if (0 != position) {
                //不是第一个的才显示
                holder.image.setVisibility(View.VISIBLE);
            }else {
                //是第一个就 隐藏
                holder.image.setVisibility(View.GONE);
            }
        } else {
            holder.image.setVisibility(View.GONE);
        }
        Drawable drawable = context.getResources().getDrawable(drawable_id);
        holder.textView.setBackground(drawable);
        holder.textView.setTextColor(color);
    }

    @Override
    public int getItemCount() {
        return labels.size();
    }

    /**
     * 拖拽交换
     *
     * @param fromPosition
     * @param toPosition
     * @return
     */
    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        Collections.swap(labels, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
    }

    /**
     * 滑动删除
     *
     * @param position
     */
    @Override
    public void onItemDismiss(int position) {

    }

    public void startEditMode(RecyclerView recyclerView) {
        isEditMode = true;
        int childCount = recyclerView.getChildCount();
        //从1开始
        for (int i = 1; i < childCount; i++) {
            View view = recyclerView.getChildAt(i);
            ImageView image = (ImageView) view.findViewById(R.id.image);
            if (image != null) {
                image.setVisibility(View.VISIBLE);
            }
        }
    }

    public void endEditMode(RecyclerView recyclerView) {
        isEditMode = false;
        int childCount = recyclerView.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = recyclerView.getChildAt(i);
            ImageView image = (ImageView) view.findViewById(R.id.image);
            if (image != null) {
                image.setVisibility(View.GONE);
            }
        }
    }

    public void setEndEditMode() {
        isEditMode = false;
        notifyDataSetChanged();
    }

    /**
     * 添加数据
     *
     * @param label
     */
    public void addData(String label) {
        labels.add(label);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder {
        TextView textView;
        ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tv_label);
            image = (ImageView) itemView.findViewById(R.id.image);
        }

        @Override
        public void onItemSelected() {

        }

        @Override
        public void onItemClear() {

        }
    }

    //是否显示 完成按钮
    public interface OnIsEditMode {
        void isEditMode(boolean isEditMode);
    }

}
