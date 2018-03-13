package com.txmpay.ewallet.ui.main;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.txmpay.ewallet.R;
import com.txmpay.ewallet.base.BaseActivity;
import com.txmpay.ewallet.model.DataBaseTestActivity;
import com.txmpay.ewallet.ui.account.safe.SafeSettingActivity;
import com.txmpay.ewallet.ui.card.MyCardActivity;
import com.txmpay.ewallet.ui.payment.MyWalletActivity;
import com.txmpay.ewallet.ui.payment.ReChargeActivity;
import com.txmpay.ewallet.ui.webview.BaseWebviewActivity;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bingoogolapple.badgeview.BGABadgeImageView;
import routeplan.RoutePlanActivity;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    @BindView(R.id.home_avatar_img)
    ImageView mHomeAvatarImg;
    @BindView(R.id.home_login_tips_text)
    TextView mHomeLoginTipsText;
    @BindView(R.id.noticeImg)
    BGABadgeImageView mNoticeImg;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.nav_view)
    NavigationView mNavigationView;
    @BindView(R.id.home_banner)
    Banner mBanner;
    @BindView(R.id.home_content_linearLayout)
    LinearLayout mContentLayout;
    @BindView(R.id.home_bottom_layout)
    LinearLayout mBottomLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //实现侧滑菜单状态栏透明
//        getWindow().setStatusBarColor(Color.TRANSPARENT);
//        getWindow().getDecorView().setSystemUiVisibility(
//                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);

//        StatusBarUtil.setColorForDrawerLayout(this,mDrawerLayout,getResources().getColor(R.color.colorPrimary));
        StatusBarUtil.setTransparent(this);
//        StatusBarUtil.setTransparentForDrawerLayout(this,mDrawerLayout);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected boolean includeAppBar() {
        return false;
    }

    @Override
    protected void initView() {
        mNavigationView.setNavigationItemSelectedListener(this);
        mNavigationView.setItemIconTintList(null);
        resetItemLayout(mNavigationView);



        //添加占位布局，方便滑动
        mBottomLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int temp=mBottomLayout.getHeight();
                View view=new View(mContext);
                LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                lp.height=temp-10;
                view.setLayoutParams(lp);
                mContentLayout.addView(view);
                mBottomLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });

        initAdv();
    }


    private void initAdv() {
        mBanner.setImageLoader(new GlideImageLoader());
        mBanner.setOnBannerListener((int position) -> {

        });
        mBanner.setDelayTime(4000);
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);  //样式，标题和数字indicator
        mBanner.setIndicatorGravity(BannerConfig.CENTER);
        mBanner.setBannerAnimation(Transformer.DepthPage);
        List<Integer> tempList = new ArrayList<>();
        tempList.add(R.drawable.adv_0);
        tempList.add(R.drawable.adv_0);
        tempList.add(R.drawable.adv_0);
        mBanner.setImages(tempList);
        mBanner.start();
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_card) {
            jumpToActivity(MyCardActivity.class);
        } else if (id == R.id.nav_safe_setting) {
            jumpToActivity(SafeSettingActivity.class);
        } else if (id == R.id.nav_clear) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_about) {

        } else if (id == R.id.nav_logout) {

        }
//        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @OnClick({R.id.home_avatar_img, R.id.noticeImg, R.id.home_img_qr,
            R.id.home_menu_wallet, R.id.home_menu_route_plan, R.id.home_menu_take_bus})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.home_avatar_img:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.noticeImg:
                jumpToActivity(NoticesActivity.class);
                break;
            case R.id.home_img_qr:
                jumpToActivity(QrCodeActivity.class);
                break;
            case R.id.home_menu_wallet:
                jumpToActivity(MyWalletActivity.class);
                break;
            case R.id.home_menu_route_plan:
                jumpToActivity(RoutePlanActivity.class);
                break;
            case R.id.home_menu_take_bus:
                jumpToActivity(BaseWebviewActivity.class);
                break;
        }
    }


    private void resetItemLayout(NavigationView navigationView) {
        //通过反射拿到menu的item布局。修改布局参数
        try {
            Field mPresenter = NavigationView.class.getDeclaredField("mPresenter");
            mPresenter.setAccessible(true);
            //此处mPresenter.get(navigationView)会得到一个NavigationMenuPresenter对象，但是该类是@hide的。所以此处直接再拿其内部的NavigationMenuView。该类也是@hide的。需要注意的是，该类继承自RecyclerView。菜单的布局也就是由其完成的。
            Field mMenuView = mPresenter.get(navigationView).getClass().getDeclaredField("mMenuView");
            mMenuView.setAccessible(true);
            //由于NavigationMenuView是隐藏类。此处用其父类。
            RecyclerView recycler = (RecyclerView) mMenuView.get(mPresenter.get(navigationView));
            for (int i = 0; i < recycler.getAdapter().getItemCount(); i++) {
                RecyclerView.ViewHolder holder = recycler.findViewHolderForLayoutPosition(i);
                //这里看实际项目了。我的项目中添加了一个head。
                if (i == 0 || holder == null)//因为这里有一个header。所以要先排除第一个
                    continue;
                //剩下的就是修改整体布局参数了。
                ViewGroup.LayoutParams params = holder.itemView.getLayoutParams();
                params.width = ViewGroup.LayoutParams.MATCH_PARENT;
                params.height = getResources().getDimensionPixelOffset(R.dimen.space100);
                holder.itemView.setLayoutParams(params);
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
