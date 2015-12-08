package com.renyuan.nestedscroll.widget;

import android.content.Context;
import android.support.v4.view.NestedScrollingChild;
import android.support.v4.view.NestedScrollingChildHelper;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;
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
    private VelocityTracker mVelocityTracker = null;
    private GestureDetector gestureDetector;
    public MyChildScrollView(Context context) {
        super(context);
    }

    public MyChildScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        childHelper = new NestedScrollingChildHelper(this);
        setNestedScrollingEnabled(true);

        gestureDetector = new GestureDetector(context,new GestureListener());
        gestureDetector.setIsLongpressEnabled(false);
    }

    public MyChildScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if(mVelocityTracker == null){
            mVelocityTracker = VelocityTracker.obtain();
        }
        mVelocityTracker.addMovement(ev);

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
                int dy = -(moveY - downY);//滚动方法的方向跟坐标是相反的，所以这里要加一个负号
                downX = moveX;
                downY = moveY;
                //在consumed中就是父类滑动后剩下的距离，
                if(dispatchNestedPreScroll(0,dy,consumed,offsetInWindow)){
                    dy = consumed[1];
                    MyChildScrollView.this.scrollBy(0, dy);
                    System.out.println("调用了 mVelocityTracker.computeCurrentVelocity(1000);");
                    mVelocityTracker.computeCurrentVelocity(1000);//该参数指定的是1S内滑动的像素。也可以指定最大速率。
                    System.out.println("   ----   " + mVelocityTracker.getYVelocity() + "");//得到y轴上的速度。
                    fling(-(int)mVelocityTracker.getYVelocity());
                }else {

                }
                break;
            case MotionEvent.ACTION_UP:
                stopNestedScroll();
                break;
        }
        return true;
    }

    private class GestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            System.out.println("GestureListener.onFling ========= "+velocityY);
            return super.onFling(e1, e2, velocityX, velocityY);
        }
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
