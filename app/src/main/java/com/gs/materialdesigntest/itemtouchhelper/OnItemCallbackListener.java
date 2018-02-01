package com.gs.materialdesigntest.itemtouchhelper;

/**
 * Created by camdora on 18-2-1.
 */

public interface OnItemCallbackListener {
    /**
     * @param fromPosition 起始位置
     * @param toPosition 移动的位置
     */
    void onMove(int fromPosition, int toPosition);
    void onSwipe(int position);
}
