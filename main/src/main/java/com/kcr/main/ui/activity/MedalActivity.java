package com.kcr.main.ui.activity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.kcr.common.base.BasicActivity;
import com.kcr.common.util.UIUtils;
import com.kcr.common.widget.CircleImageView;
import com.kcr.main.R;
import com.mobike.library.PelletContainer;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/12/5/005.
 */

public class MedalActivity extends BasicActivity {
    PelletContainer medalView;
    LinearLayout llShowAll;
    private SensorManager sensorManager;
    private Sensor defaultSensor;
    private int[] imgs = {
            R.mipmap.icon_main_inner,
            R.mipmap.icon_main_sg,
            R.mipmap.icon_main_inner,
            R.mipmap.icon_main_sg,
            R.mipmap.icon_main_inner,
    };
    private static final String TAG = "MedalActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medal);
        ButterKnife.bind(this);
        medalView=  findViewById(R.id.medal_view);
        llShowAll=  findViewById(R.id.ll_showAll);
        medalView.getmPelletWorld().setFriction(0.5f);
        findUserBadge();
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        defaultSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        llShowAll.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                medalView.getmPelletWorld().random();
                return false;
            }
        });
    }

    private void findUserBadge() {
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(UIUtils.convertDpToPixelInt(mContext, 44), UIUtils.convertDpToPixelInt(mContext, 44));
        layoutParams.gravity = Gravity.TOP | Gravity.CENTER_HORIZONTAL;
        for (int i = 0; i < imgs.length; i++) {
            CircleImageView imageView = new CircleImageView(mContext);
            imageView.setCircleBackgroundColor(getResources().getColor(R.color.colorPrimaryLight));
            imageView.setTag(R.id.mobike_view_circle_tag, true);
            Glide.with(this).load(imgs[i]).asBitmap().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);
            medalView.addView(imageView, layoutParams);
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        medalView.getmPelletWorld().onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        medalView.getmPelletWorld().onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(listerner, defaultSensor, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(listerner);
    }


    private SensorEventListener listerner = new SensorEventListener() {

        @Override
        public void onSensorChanged(SensorEvent event) {
            if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
//                float x =  event.values[0];
//                float y =   event.values[1] * 2.0f;

                float x = event.values[0] * 4.0f;
                float y = event.values[1] * 2.0f * 4.0f;
                medalView.getmPelletWorld().onSensorChanged(-x, y);
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

}
