package com.gs.materialdesigntest.itemtouchhelper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.gs.materialdesigntest.R;
import com.gs.materialdesigntest.itemtouchhelper.adapter.MyItemTouchHelperAdapter;

import java.util.ArrayList;
import java.util.List;

public class MyItemTouchHelperActivity extends AppCompatActivity {

    List<String> list;
    private MyItemTouchHelperAdapter adapter;
    RecyclerView recyView;

    //初始化集合数据
    {
        list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("我的序号是：" + i);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_item_touch_helper);

        recyView = (RecyclerView) findViewById(R.id.recyclerview);
        adapter = new MyItemTouchHelperAdapter(list);
        recyView.setLayoutManager(new LinearLayoutManager(this));
        recyView.setAdapter(adapter);

        ItemTouchHelper.Callback callback = new MyItemTouchHelper(adapter);

        //1.创建item helper
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        //2.绑定到recyclerview上面去
        itemTouchHelper.attachToRecyclerView(recyView);
        //3.在ItemHelper的接口回调中过滤开启长按拖动，拓展其他操作
    }
}
