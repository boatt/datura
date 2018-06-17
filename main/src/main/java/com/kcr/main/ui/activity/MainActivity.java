package com.kcr.main.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.kcr.common.base.BasicActivity;
import com.kcr.common.widget.multiwaveheader.NoScrollViewPager;
import com.kcr.main.R;
import com.kcr.main.adapter.HomePageAdapter;
import com.kcr.main.ui.fragment.HomeMeFragment;
import com.kcr.main.ui.fragment.HomeWelfareFragment;
import com.qmuiteam.qmui.util.QMUIResHelper;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.qmuiteam.qmui.widget.QMUITabSegment;

import butterknife.BindView;
import butterknife.ButterKnife;

@Route(path = "/main/mainActivity")
public class MainActivity extends BasicActivity {

    FrameLayout mHomePager;
    private BottomNavigationView mTabSegment;
    private HomeWelfareFragment mHomeWelfareFragment;
    private HomeMeFragment mHomeMeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTabSegment = (BottomNavigationView) findViewById(R.id.navigation);
        mHomePager = (FrameLayout) findViewById(R.id.home_pager);
        mHomeWelfareFragment = HomeWelfareFragment.newInstance(1);
        mHomeMeFragment = HomeMeFragment.newInstance(1);
        getSupportFragmentManager().beginTransaction().add(R.id.home_pager, mHomeWelfareFragment).add(R.id.home_pager, mHomeMeFragment)
                .hide(mHomeMeFragment).show(mHomeWelfareFragment).commit();

        mTabSegment.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.navigation_home) {
                    getSupportFragmentManager().beginTransaction().hide(mHomeMeFragment).show(mHomeWelfareFragment).commit();
                    QMUIStatusBarHelper.setStatusBarLightMode(MainActivity.this);
                    return true;
                } else if (item.getItemId() == R.id.navigation_me) {
                    getSupportFragmentManager().beginTransaction().hide(mHomeWelfareFragment).show(mHomeMeFragment).commit();
                    QMUIStatusBarHelper.setStatusBarDarkMode(MainActivity.this);
                    return true;
                }
                return false;
            }
        });
    }


}
