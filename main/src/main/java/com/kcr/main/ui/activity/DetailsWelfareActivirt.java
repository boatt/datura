package com.kcr.main.ui.activity;


import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.transition.Explode;
import android.transition.Slide;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.kcr.common.util.TimeHelper;
import com.kcr.common.util.TimeUtils;
import com.kcr.main.R;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;


public class DetailsWelfareActivirt extends AppCompatActivity {
    private ImageView mImgBg;
    private ImageView mImgGif;
    private TextView mTvTitle;
    private TextView tvBottom;
    private int mImgPath;


    public static long midTime;
    private Disposable mDisposable;
    private Button btJoin;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welfare_details);
        mImgBg = findViewById(R.id.img_bg);
        btJoin = findViewById(R.id.bt_join);
        mTvTitle = findViewById(R.id.tv_title);
        tvBottom = findViewById(R.id.tv_bottom);
        mImgGif = findViewById(R.id.img_gif);
        if (getIntent() != null) {
            mImgPath = getIntent().getIntExtra("img", R.mipmap.skid_right_5);
            String title = getIntent().getStringExtra("title");
            String desc = getIntent().getStringExtra("desc");
            mTvTitle.setText(title);
            tvBottom.setText(desc);
            Glide.with(this).load(mImgPath).asBitmap().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(mImgBg);

        }
        mHandler.sendEmptyMessageDelayed(1, 1000);
        mHandler.sendEmptyMessageDelayed(2, 500);
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    Glide.with(DetailsWelfareActivirt.this).load(mImgPath).asGif().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(mImgGif);
                    break;
                case 2:
                    long endTime = TimeUtils.string2Millis("2018-9-9 9:30:00");
                    long startTime = TimeUtils.getNowTimeMills();
                    String s1 = TimeUtils.millis2String(startTime);
                    String s2 = TimeUtils.millis2String(endTime);
                    Log.d(TAG, "onCreate: " + endTime + "=" + startTime + "=" + s1 + "=" + s2);
                    midTime = (Math.abs(endTime - startTime)) / 1000;
                    // time1();//方式一
                    time2();// 方式二
                    break;
            }
        }
    };
    private final String TAG = "DetailsWelfareActivirt";

    /**
     * 方式二： 设定时间戳，倒计时
     */
    private void time2() {
        mDisposable = Flowable.interval(1, TimeUnit.SECONDS)
                .doOnNext(new Consumer<Long>() {
                    @Override
                    public void accept(@NonNull Long aLong) throws Exception {
                        midTime--;
                        Log.e(TAG, "accept: doOnNext : " + aLong);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(@NonNull Long aLong) throws Exception {
                        Log.e(TAG, "accept: 设置文本 ：" + aLong);
                        long day = midTime / 60 / 60 / 24;
                        long hh = midTime / 60 / 60 % 60;
                        long mm = midTime / 60 % 60;
                        long ss = midTime % 60;
                        String time = day + "天" + hh + "小时" + mm + "分钟" + ss + "秒";
                        Log.d(TAG, "time2: " + time);
                        btJoin.setText(time + "后开启");
                    }
                });


    }

    @Override
    protected void onDestroy() {
        mHandler.removeMessages(1);
        mHandler.removeMessages(2);
        if (mDisposable != null) {
            mDisposable.dispose();
        }
        super.onDestroy();

    }

    @Override
    public void onBackPressed() {
        mImgGif.setVisibility(View.INVISIBLE);
        super.onBackPressed();
    }
}
