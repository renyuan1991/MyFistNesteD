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
    private int myScrollDis = 0;
    private int sumScroll = 0;
    private NestedScrollingParentHelper parentHelper;
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
//        super.onNestedPreScroll(target, dx, dy, consumed);
        //切记，把父类滑剩下的给子类
        //~~~~~~~ 正向滑动 ~~~~~~~
        if((sumScroll+dy)>sumScroll){
            //超过边界
            if((Math.abs(sumScroll+dy))>myScrollDis){
                MyScrollView.this.scrollBy(dx, myScrollDis - sumScroll);//最多滑动myScrollDis，当超出边界的时候父类只需要滑动剩下的距离
                consumed[1] = dy - (myScrollDis - sumScroll);//将没滑动的距离给子类，让子类滑动
                sumScroll = myScrollDis;
            }else{//未超过边界
                MyScrollView.this.scrollBy(dx, dy);
                sumScroll += dy;//记录这个view滑动的真实距离
                consumed[1] = 0;
            }
        }
        //~~~~~~~ 反向互动 ~~~~~~~
        else if((sumScroll+dy)<sumScroll){
            //超过边界
            if((sumScroll+dy)<0){
                MyScrollView.this.scrollBy(dx, -sumScroll);//
                consumed[1] = sumScroll+dy;
                sumScroll = 0;
            }else{//未超过边界
                MyScrollView.this.scrollBy(dx, dy);
                sumScroll += dy;//记录这个view滑动的真实距离
                consumed[1] = 0;
            }
        }else {

        }
    }

    @Override
    public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
    }
}
