package com.gs.materialdesigntest.itemtouchhelper;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * Created by camdora on 18-2-1.
 */

public class MyItemTouchHelper extends ItemTouchHelper.Callback{

    private OnItemCallbackListener adapter;

    public MyItemTouchHelper() {
    }

    public MyItemTouchHelper(OnItemCallbackListener adapter) {
        this.adapter = adapter;
    }

    /**
     * Item是否能被Swipe到dismiss
     * 也就是删除这条数据
     */
    @Override
    public boolean isItemViewSwipeEnabled() {
        return true;
    }

    /**
     * Item长按是否可以拖拽
     */
    @Override
    public boolean isLongPressDragEnabled() {
        return true;
    }

    /**
     * 设置Drag/Swipe的Flag
     * 这里我们把滑动(Drag)的四个方向全都设置上了,说明Item可以随意移动
     * 然后把删除(暂且叫删除/swipe)的方向设置为Start和End,说明可以水平拖动删除
     *
     * 官方文档的说明如下：
     * o control which actions user can take on each view, you should override getMovementFlags(RecyclerView, ViewHolder)
     * and return appropriate set of direction flags. (LEFT, RIGHT, START, END, UP, DOWN).
     * 返回我们要监控的方向，上下左右，我们做的是上下拖动，要返回都是UP和DOWN
     * 关键坑爹的是下面方法返回值只有1个，也就是说只能监控一个方向。
     * 不过点入到源码里面有惊喜。源码标记方向如下：
     *  public static final int UP = 1     0001
     *  public static final int DOWN = 1 << 1; （位运算：值其实就是2）0010
     *  public static final int LEFT = 1 << 2   左 值是3
     *  public static final int RIGHT = 1 << 3  右 值是8
     */
    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int dragFlag = ItemTouchHelper.LEFT | ItemTouchHelper.DOWN | ItemTouchHelper.UP | ItemTouchHelper.RIGHT;
        int swipeFlag = ItemTouchHelper.START | ItemTouchHelper.END;

        return makeMovementFlags(dragFlag, swipeFlag);

        /**
         * 备注：由getMovementFlags可以联想到setMovementFlags，不过文档么有这个方法，但是：
         * 有 makeMovementFlags (int dragFlags, int swipeFlags)
         * Convenience method to create movement flags.便捷方法创建moveMentFlag
         * For instance, if you want to let your items be drag & dropped vertically and swiped left to be dismissed,
         * you can call this method with: makeMovementFlags(UP | DOWN, LEFT);
         * 这个recyclerview的文档写的简直完美，示例代码都弄好了！！！
         * 如果你想让item上下拖动和左边滑动删除，应该这样用： makeMovementFlags(UP | DOWN, LEFT)
         */

        //拓展一下：如果只想上下的话：makeMovementFlags（UP | DOWN, 0）,标记方向的最小值1
    }

    /**
     * 官方文档的说明如下
     * If user drags an item, ItemTouchHelper will call onMove(recyclerView, dragged, target). Upon receiving this callback,
     * you should move the item from the old position (dragged.getAdapterPosition()) to new position (target.getAdapterPosition())
     * in your adapter and also call notifyItemMoved(int, int).
     * 拖动某个item的回调，在return前要更新item位置，调用notifyItemMoved（draggedPosition，targetPosition）
     * viewHolde:正在拖动item
     * target：要拖到的目标
     * @return true 表示消费事件
     */
    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        /**
         * 回调
         */
        adapter.onMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return true;
    }

    /**
     * 谷歌官方文档说明如下：
     * 这个看了一下主要是做左右拖动的回调
     * When a View is swiped, ItemTouchHelper animates it until it goes out of bounds, then calls onSwiped(ViewHolder, int).
     * At this point, you should update your adapter (e.g. remove the item) and call related Adapter#notify event.
     * @param viewHolder
     * @param direction
     */
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        /**
         * 回调
         */
        adapter.onSwipe(viewHolder.getAdapterPosition());
    }

}
