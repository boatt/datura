package com.kcr.video;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kcr.common.base.BasicActivity;
import com.kcr.common.widget.AdImageViewVersion1;
import com.kcr.video.util.KCRVideoAllCallBack;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.listener.VideoAllCallBack;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

import java.util.ArrayList;
import java.util.List;

@Route(path = "/video/videoActivity")
public class VideoActivity extends BasicActivity {

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        init();
    }

    private void init() {
        mRecyclerView = findViewById(R.id.id_recyclerview);

        final List<String> mockDatas = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            mockDatas.add(i + "");
        }

        mRecyclerView.setLayoutManager(mLinearLayoutManager = new LinearLayoutManager(this));

        mRecyclerView.setAdapter(new BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_video, mockDatas) {

            @Override
            protected void convert(BaseViewHolder holder, String item) {
                StandardGSYVideoPlayer videoPlayer = holder.getView(R.id.video_player);
                initGSYView(videoPlayer);
                ImageView ivCoverVideo = new ImageView(mContext);
                ivCoverVideo.setScaleType(ImageView.ScaleType.CENTER_CROP);
                Glide.with(ivCoverVideo.getContext()).load(R.mipmap.img_news_gril).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(ivCoverVideo);
                videoPlayer.setThumbImageView(ivCoverVideo);
                String url = "http://baobab.kaiyanapp.com/api/v1/playUrl?vid=41789&resourceType=video&editionType=default&source=aliyun";
                videoPlayer.setUp(url, false, "");
//                videoPlayer.startPlayLogic();
            }

            private void initGSYView(final StandardGSYVideoPlayer videoPlayer) {
                videoPlayer.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
                    @SuppressLint("WrongConstant")
                    public final void onClick(View it) {
                        videoPlayer.startWindowFullscreen(VideoActivity.this, false, true);
                    }
                });
                videoPlayer.getBackButton().setVisibility(View.GONE);
                videoPlayer.getBackButton().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onBackPressed();
                    }
                });
                videoPlayer.setVideoAllCallBack(new KCRVideoAllCallBack());
            }
        });
    }
    @Override
    protected void onPause() {
        super.onPause();
        GSYVideoManager.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        GSYVideoManager.onResume();
    }

    @Override
    protected void onDestroy() {
        if (GSYVideoManager.instance().listener() != null) {
            GSYVideoManager.instance().listener().onCompletion();
        }
        GSYVideoManager.instance().releaseMediaPlayer();
        super.onDestroy();
    }
}
