package com.renyuan.nestedscroll.view.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.renyuan.nestedscroll.R;
import com.renyuan.nestedscroll.view.adapter.FragmentAdapter;
import com.renyuan.nestedscroll.view.fragment.FragmentOne;
import com.renyuan.nestedscroll.view.fragment.FragmentThree;
import com.renyuan.nestedscroll.view.fragment.FragmentTwo;
import com.renyuan.nestedscroll.widget.MyScrollView;

import java.util.ArrayList;
import java.util.List;
/**
 * @author     任圆(Roy) E-mail:renyuan@dreamore.com
 * @version    1.0
 * time        15/12/1,下午3:02
 * description
*/
public class FgAndVP extends FragmentActivity {

    private ViewPager fragment_v_at;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fg_vp);
        MyScrollView myScrollView = (MyScrollView) findViewById(R.id.myScrollview_msv_fv);
        myScrollView.setMyScrollDis(300);
        fragment_v_at = (ViewPager) findViewById(R.id.fragment_v_at);
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new FragmentOne());
        fragments.add(new FragmentTwo());
        fragments.add(new FragmentThree());
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager() , fragments);
        //设定适配器
        fragment_v_at.setAdapter(fragmentAdapter);
    }
}
