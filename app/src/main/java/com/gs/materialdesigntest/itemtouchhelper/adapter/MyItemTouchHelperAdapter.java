package com.gs.materialdesigntest.itemtouchhelper.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gs.materialdesigntest.itemtouchhelper.OnItemCallbackListener;
import com.gs.materialdesigntest.R;

import java.util.Collections;
import java.util.List;

/**
 * Created by camdora on 18-2-1.
 */

public class MyItemTouchHelperAdapter extends RecyclerView.Adapter<MyItemTouchHelperAdapter.MyViewHolder>
        implements OnItemCallbackListener {

    private List<String> list;

    public MyItemTouchHelperAdapter(List<String> list) {
        this.list=list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        MyViewHolder holder = new MyViewHolder(itemView);
        return holder;
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.textNumber.setText(list.get(position));
    }

    @Override
    public void onMove(int fromPosition, int toPosition) {
        //在这里进行给原数组数据的移动
        Collections.swap(list, fromPosition, toPosition);
        //通知数据移动
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onSwipe(int position) {
        //原数据移除数据
        list.remove(position);
        //通知移除
        notifyItemRemoved(position);
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textNumber;

        public MyViewHolder(View itemView) {
            super(itemView);
            textNumber = (TextView) itemView.findViewById(R.id.textNumber);
        }
    }
}
