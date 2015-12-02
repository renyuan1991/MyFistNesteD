package com.renyuan.nestedscroll.view.adapter;

/**
 * Created by admin on 15/11/17.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * @author 任圆(Roy) E-mail:renyuan@dreamore.com
 * @date 创建时间：2015-11-12 下午3:58:33
 * @version 1.0
 * @description
 */
public class FragmentAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;
    //申请了一个Fragment的List对象，用于保存用于滑动的Fragment对象，并在构造函数中初始化
    public FragmentAdapter(FragmentManager fm,List<Fragment> fragments) {
        super(fm);
        this.fragments= fragments;
    }
    @Override
    public Fragment getItem(int arg0) {
        return fragments.get(arg0);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

}
