package com.gs.materialdesigntest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.gs.materialdesigntest.itemtouchhelper.MyItemTouchHelperActivity;

public class MainActivity extends AppCompatActivity
    implements View.OnClickListener{

    private Button btItemTouchHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btItemTouchHelper = (Button) findViewById(R.id.bt_item_touch_helper);

        btItemTouchHelper.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.bt_item_touch_helper:
                intent = new Intent(MainActivity.this, MyItemTouchHelperActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
