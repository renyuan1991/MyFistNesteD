package com.renyuan.nestedscroll.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.VideoView;

import com.renyuan.nestedscroll.R;
/**
 * @author     任圆(Roy) E-mail:renyuan@dreamore.com
 * @version    1.0
 * time        15/12/14,上午10:56
 * description
*/
public class SplashActivity extends Activity {
    private Uri mUri;
    private VideoView sufaceview_sfv_as;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ImageView loading_iv_as = (ImageView) findViewById(R.id.loading_iv_as);
        loading_iv_as.setVisibility(View.GONE);
        myLoadingTime();
        //动画启动页
        //startAnmina();


        //视频启动页
//        sufaceview_sfv_as = (VideoView) findViewById(R.id.sufaceview_sfv_as);
//        sufaceview_sfv_as.setOnCompletionListener(new myCompletionListener());
//        mUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.mytestplay);
//        startPlay();
    }

    //======================= 播放动画 =======================
        public void startAnmina(){

        }



    //======================= 播放视频 =======================
    /**
     * 播放视屏
     */
    public void startPlay() {
        sufaceview_sfv_as.setVideoURI(mUri);
        sufaceview_sfv_as.start();
    }

    /**
     * 播放完成后的监听
     */
    private class myCompletionListener implements MediaPlayer.OnCompletionListener {

        @Override
        public void onCompletion(MediaPlayer mp) {
            sufaceview_sfv_as.start();
        }
    }



    //======================= 设置loading展示时间 =======================
    public void myLoadingTime() {
        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 15/12/11 进入到主界面
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                SplashActivity.this.finish();
            }
        });
        t.start();
    }
}
