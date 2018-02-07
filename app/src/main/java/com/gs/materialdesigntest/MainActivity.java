package com.gs.materialdesigntest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.gs.materialdesigntest.itemtouchhelper.MyItemTouchHelperActivity;

public class MainActivity extends AppCompatActivity
    implements View.OnClickListener{
    private final static String TAG = "MainActivity";

    private Button btItemTouchHelper;
    private ImageButton btTouch;

    private int lastX,lastY,screenWidth,screenHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btItemTouchHelper = (Button) findViewById(R.id.bt_item_touch_helper);
        btTouch = (ImageButton) findViewById(R.id.bt_touch);

        btItemTouchHelper.setOnClickListener(this);

        btTouch.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                float raw_x, raw_y;
                Log.e(TAG, "++++++++++ "+action);
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        lastX = (int) event.getRawX();//设定移动的初始位置相对位置
                        lastY = (int) event.getRawY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        //event.getRawX()事件点距离屏幕左上角的距离
                        int dx = (int) event.getRawX() - lastX;
                        int dy = (int) event.getRawY() - lastY;

                        int left = btTouch.getLeft() + dx;
                        int top = btTouch.getTop() + dy;
                        int right = btTouch.getRight() + dx;
                        int bottom = btTouch.getBottom() + dy;

                        btTouch.layout(left, top, right, bottom);//设置控件的新位置
                        lastX = (int) event.getRawX();//再次将滑动其实位置定位
                        lastY = (int) event.getRawY();
                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                }

                return false;
            }
        });
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
