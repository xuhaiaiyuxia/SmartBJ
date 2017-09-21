package com.itheima.smart.beijing.activity;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.itheima.smart.beijing.R;
import com.itheima.smart.beijing.utils.MyConstaints;
import com.itheima.smart.beijing.utils.SPUtils;

import org.xutils.x;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SplashActivity extends Activity {

    @Bind(R.id.ll_splash_root_bgc)
    ImageView mLlSplashRootBgc;
    private AnimationSet mAnimationSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        initView();
        initData();
        initEvent();

    }

    private void initView() {

        mAnimationSet = new AnimationSet(false);

        //旋转动画
        RotateAnimation rotateAnimation = new RotateAnimation(0,360,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        rotateAnimation.setDuration(2000);
        rotateAnimation.setFillAfter(true);

        ScaleAnimation scaleAnimation = new ScaleAnimation(0,1,0,1, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        scaleAnimation.setDuration(2000);
        scaleAnimation.setFillAfter(true);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0,1);
        alphaAnimation.setDuration(2000);
        alphaAnimation.setFillAfter(true);

        mAnimationSet.addAnimation(rotateAnimation);
        mAnimationSet.addAnimation(scaleAnimation);
        mAnimationSet.addAnimation(alphaAnimation);
        mAnimationSet.setDuration(2000);
        mLlSplashRootBgc.startAnimation(mAnimationSet);
    }

    private void initData() {

    }

    private void initEvent() {
        mAnimationSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (!SPUtils.getBoolean(getApplicationContext(), MyConstaints.ISSETUPFINISH, false)) {
                    enterSetNav();
                } else {
                    enterHome();
                }

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void enterHome() {
        startActivity(new Intent(getApplicationContext(),HomeActivity.class));
        finish();
    }

    private void enterSetNav() {
        startActivity(new Intent(getApplicationContext(),GuidActivity.class));
        finish();
    }
}
