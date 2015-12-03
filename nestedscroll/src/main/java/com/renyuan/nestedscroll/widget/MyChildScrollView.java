package com.renyuan.nestedscroll.widget;

import android.content.Context;
import android.support.v4.view.NestedScrollingChild;
import android.support.v4.view.NestedScrollingChildHelper;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * @author 任圆 (Roy) E-mail:renyuan@dreamore.com
 * @version V1.0 time：15/12/1 15:22
 */
public class MyChildScrollView extends ScrollView implements NestedScrollingChild{
    private NestedScrollingChildHelper childHelper;
    private int[] consumed = new int[2];
    private int[] offsetInWindow = new int[2];
    private int downX;
    private int downY;
    public MyChildScrollView(Context context) {
        super(context);
    }

    public MyChildScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        childHelper = new NestedScrollingChildHelper(this);
        setNestedScrollingEnabled(true);
    }

    public MyChildScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                downX = (int)ev.getRawX();
                downY = (int)ev.getRawY();
                startNestedScroll(ViewCompat.SCROLL_AXIS_HORIZONTAL | ViewCompat.SCROLL_AXIS_VERTICAL);
                break;
            case MotionEvent.ACTION_MOVE:
                int moveX = (int)ev.getRawX();
                int moveY = (int)ev.getRawY();
                int dx = moveX - downX;
                int dy = moveY - downY;
                //在consumed中就是父类滑动后剩下的距离，
                System.out.println("dispatchNestedPreScroll(0,dy,consumed,offsetInWindow) = " + dispatchNestedPreScroll(0, dy, consumed, offsetInWindow));
                if(dispatchNestedPreScroll(0,dy,consumed,offsetInWindow)){
                    if(dy>0){
                        System.out.println("-----down-----");
                        dy = dy - consumed[1];
                    }else if(dy<0){
                        System.out.println("------up------");
                        dy = dy+consumed[1];
                    }
                }
                MyChildScrollView.this.scrollBy(0, dy);
                System.out.println("dy = " + dy);
                break;
            case MotionEvent.ACTION_UP:
                stopNestedScroll();
                break;
        }
        return true;
    }

    //~~~~~~~ 嵌套滑动的处理方法 ~~~~~~~
    @Override
    public void setNestedScrollingEnabled(boolean enabled) {
        childHelper.setNestedScrollingEnabled(enabled);
    }

    @Override
    public boolean isNestedScrollingEnabled() {
        return childHelper.isNestedScrollingEnabled();

    }

    @Override
    public boolean startNestedScroll(int axes) {
        return childHelper.startNestedScroll(axes);
    }

    @Override
    public void stopNestedScroll() {
        childHelper.stopNestedScroll();

    }

    @Override
    public boolean hasNestedScrollingParent() {
        return childHelper.hasNestedScrollingParent();
    }

    @Override
    public boolean dispatchNestedScroll(int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int[] offsetInWindow) {
        return childHelper.dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, offsetInWindow);
    }

    /**
     * @param dx       水平滑动距离
     * @param dy       垂直滑动距离
     * @param consumed 父类消耗掉的距离
     * @return
     */
    @Override
    public boolean dispatchNestedPreScroll(int dx, int dy, int[] consumed, int[] offsetInWindow) {
        return childHelper.dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow);
    }

    @Override
    public boolean dispatchNestedFling(float velocityX, float velocityY, boolean consumed) {
        return childHelper.dispatchNestedFling(velocityX, velocityY, consumed);
    }

    @Override
    public boolean dispatchNestedPreFling(float velocityX, float velocityY) {
        return childHelper.dispatchNestedPreFling(velocityX, velocityY);
    }
}
