package com.renyuan.nestedscroll.widget;

import android.content.Context;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.NestedScrollingParentHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;

/**
 * @author     任圆(Roy) E-mail:renyuan@dreamore.com
 * @version    1.0
 * time        15/11/24,上午10:36
 * description
*/
public class MyScrollView extends ScrollView implements NestedScrollingParent {
    private NestedScrollingParentHelper parentHelper;
    private int myScrollDis = 0;
    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        parentHelper = new NestedScrollingParentHelper(this);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        System.out.println("MyScrollView.onTouchEvent");
        return super.onTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
//        return super.onInterceptTouchEvent(ev);
    }

    //~~~~~~~ 添加一个方法，在代码中设置这个父类滚动的限度 ~~~~~~~
    public void setMyScrollDis(int myScrollDis){
        this.myScrollDis = myScrollDis;
    }

    //~~~~~~~ 嵌套滑动 ~~~~~~~
    @Override
    public void onNestedScrollAccepted(View child, View target, int axes) {
        super.onNestedScrollAccepted(child, target, axes);
    }

    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        return super.onStartNestedScroll(child, target, nestedScrollAxes);
    }

    @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
        System.out.println("MyScrollView dy = " + dy);
//        super.onNestedPreScroll(target, dx, dy, consumed);
        if(Math.abs(dy)>myScrollDis){//在y轴上的距离大于要滚动的距离，这个时候要让子空间消耗剩下的距离
            MyScrollView.this.scrollBy(dx, myScrollDis);
            consumed[0] = dx;
            if(dy>0){
                consumed[1] = dy-myScrollDis;
            }
            if(dy<0){
                consumed[1] = dy+myScrollDis;
            }
        }else{
            MyScrollView.this.scrollBy(dx, dy);
            consumed[1] = 0;
        }
        System.out.println("MyScrollView  myScrollDis = " + myScrollDis);
        System.out.println("MyScrollView  剩余的dy = " + consumed[1]);
    }

    @Override
    public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
    }
}
