package com.kcr.common.widget;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.ViewFlipper;


import com.kcr.common.R;
import com.kcr.common.bean.BannerBean;

import java.util.List;

/**
 * Created by aoe on 2017/11/2.
 */

public class NoticeView extends ViewFlipper implements View.OnClickListener {
    private Context mContext;
    private List<BannerBean> mNotices;

    public NoticeView(Context context) {
        super(context);
    }

    public NoticeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        // 轮播间隔时间为3s
        setFlipInterval(3000);
        // 内边距5dp
        setPadding(dp2px(5f), dp2px(5f), dp2px(5f), dp2px(5f));
        // 设置enter和leave动画
        setInAnimation(AnimationUtils.loadAnimation(mContext, R.anim.notify_in));
        setOutAnimation(AnimationUtils.loadAnimation(mContext, R.anim.notice_out));
    }

    /**
     * 添加需要轮播展示的公告
     *
     * @param notices
     */
    public void addNotice(List<BannerBean> notices) {
        mNotices = notices;
        removeAllViews();
        for (int i = 0; i < mNotices.size(); i++) {
            // 根据公告内容构建一个TextView
            BannerBean notice = notices.get(i);
            String title = notice.getActivityDescription();
            TextView textView = new TextView(mContext);
            textView.setMaxLines(2);
            textView.setMinLines(2);
            textView.setText(title);
            textView.setTextSize(14f);
            textView.setEllipsize(TextUtils.TruncateAt.END);
            textView.setTextColor(Color.parseColor("#888888"));
            textView.setGravity(Gravity.TOP|Gravity.LEFT);
            // 将公告的位置设置为textView的tag方便点击是回调给用户
            textView.setTag(i);
            textView.setOnClickListener(this);
            // 添加到ViewFlipper
            addView(textView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        }
    }

    @Override
    public void onClick(View v) {
        int position = (int) v.getTag();
        BannerBean notice = (BannerBean) mNotices.get(position);
        if (mOnNoticeClickListener != null) {
            mOnNoticeClickListener.onNotieClick(position, notice);
        }
    }

    /**
     * 通知点击监听接口
     */
    public interface OnNoticeClickListener {
        void onNotieClick(int position, BannerBean notice);
    }

    private OnNoticeClickListener mOnNoticeClickListener;

    /**
     * 设置通知点击监听器
     *
     * @param onNoticeClickListener 通知点击监听器
     */
    public void setOnNoticeClickListener(OnNoticeClickListener onNoticeClickListener) {
        mOnNoticeClickListener = onNoticeClickListener;
    }

    private int dp2px(float dpValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpValue,
                mContext.getResources().getDisplayMetrics());
    }

}
