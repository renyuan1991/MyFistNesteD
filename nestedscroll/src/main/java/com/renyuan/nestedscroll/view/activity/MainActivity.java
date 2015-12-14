package com.renyuan.nestedscroll.view.activity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.renyuan.nestedscroll.R;
/**
 * @author     任圆(Roy) E-mail:renyuan@dreamore.com
 * @version    1.0
 * time        15/12/14,上午10:57
 * description
*/
public class MainActivity extends Activity implements OnClickListener {
    private Button frag_viewpager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        frag_viewpager = (Button) findViewById(R.id.frag_viewpager);
        frag_viewpager.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent ;
        switch (v.getId()){
            case R.id.frag_viewpager:
                intent = new Intent(this, com.renyuan.nestedscroll.view.activity.FgAndVP.class);
                startActivity(intent);
            break;
        }
    }
}
