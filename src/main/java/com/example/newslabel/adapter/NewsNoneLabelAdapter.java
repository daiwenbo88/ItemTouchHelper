package com.example.newslabel.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.newslabel.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by daiwenbo on 17/3/22.
 */

public class NewsNoneLabelAdapter extends RecyclerView.Adapter<NewsNoneLabelAdapter.ViewHolder> {
    public List<String> labels=new ArrayList<>();
    public Context context;
    int color;
    private OnDeleteData onDeleteData;
    public void setOnDeleteData(OnDeleteData onDeleteData) {
        this.onDeleteData = onDeleteData;
    }
    public NewsNoneLabelAdapter(Context context, List<String> label) {
        this.context=context;
        this.labels=label;
        color = ContextCompat.getColor(context, R.color.c_474747);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_label,null,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.textView.setText(labels.get(position));
        holder.textView.setTextColor(color);
        Drawable drawable = context.getResources().getDrawable(R.drawable.selector_label_none);
        holder.textView.setBackground(drawable);
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onDeleteData!=null){
                    //更新视图
                    String tag = labels.remove(position);
                    if (!tag.isEmpty()){
                        notifyItemRemoved(position);
                        onDeleteData.onDelete(tag);
                        notifyItemRangeChanged(0,labels.size());
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return labels.size();
    }

    public void addDate(String label) {
        labels.add(label);
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        ImageView image;
        public ViewHolder(View itemView) {
            super(itemView);
            textView= (TextView) itemView.findViewById(R.id.tv_label);
            image= (ImageView) itemView.findViewById(R.id.image);
        }
    }

}
