package com.renyuan.nestedscroll.widget;
/**
 * @author 任圆(Roy) E-mail:renyuan@dreamore.com
 * @time 15/11/19,下午4:57
 * @version 1.0
 * @description 自定义的viewpager 将要使用nestedscrolling解决嵌套滑动
 */

import android.content.Context;
import android.support.v4.view.NestedScrollingChild;
import android.support.v4.view.NestedScrollingChildHelper;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.NestedScrollingParentHelper;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

public class MyViewPager extends ViewPager implements NestedScrollingChild,NestedScrollingParent{
    private NestedScrollingChildHelper childHelper;
    private NestedScrollingParentHelper parentHelper;
    private int[] consumed = new int[2];
    private int[] offsetInWindow = new int[2];
    private float downX;
    private float downY;
    public MyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        childHelper = new NestedScrollingChildHelper(this);
        parentHelper = new NestedScrollingParentHelper(this);
        setNestedScrollingEnabled(true);
    }
    public MyViewPager(Context context) {
        super(context);
    }

    //~~~~~~~ 作为父类滑动的四种处理方法 ~~~~~~~

    @Override
    public void onNestedScrollAccepted(View child, View target, int axes) {
        parentHelper.onNestedScrollAccepted(child,target,axes);
    }

    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        startNestedScroll(nestedScrollAxes);
        return true;
    }

    @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
        System.out.println("======MyViewPager.onNestedPreScroll=====");
        System.out.println("dx = " + dx);
        System.out.println("dy = " + dy);
        dispatchNestedPreScroll(dx, dy, consumed, null);
        System.out.println("consumed = " + consumed[1]);
        System.out.println("======MyViewPager.onNestedPreScroll=====");
    }

    @Override
    public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
    }

    //~~~~~~~ 作为子类滑动的处理方法 ~~~~~~~
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
