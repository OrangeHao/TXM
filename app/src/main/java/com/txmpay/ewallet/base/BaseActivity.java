package com.txmpay.ewallet.base;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.txmpay.ewallet.R;
import com.umeng.analytics.MobclickAgent;

import butterknife.ButterKnife;

/**
 * created by czh on 2018-02-27
 * activity基类
 */

public abstract class BaseActivity<V,T extends BasePresenter> extends AppCompatActivity{

    protected T mPresenter;
    protected Context mContext;

    private AppBarLayout mAppBarLayout;
    private Toolbar mToolBar;
    private TextView mTitle;
    private TextView mRightText;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        initBeforeSetContentView();

        mPresenter=createPresenter();
        if(mPresenter!=null){
            mPresenter.attachView((V)this);
        }

        setContentView(setLayoutId());
        mContext=this;
        ButterKnife.bind(this);

        setUpAppBar();

        initView();
        initData();
        initListner();
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    protected abstract int setLayoutId();

    protected  T createPresenter(){
        return null;
    }

    protected void initBeforeSetContentView(){

    }

    protected void initView(){

    }

    protected void initData(){

    }

    protected void initListner(){

    }


    /**---------------------- toolbar  --------------------**/

    private void setUpAppBar(){
        if (includeAppBar()){
            mAppBarLayout=(AppBarLayout) findViewById(R.id.appbar_layout);
            mToolBar=(Toolbar)findViewById(R.id.toolbar_layout);
            mTitle=(TextView)findViewById(R.id.appbar_title);
            mRightText=(TextView)findViewById(R.id.appbar_right_text);
            mToolBar.setNavigationIcon(R.drawable.base_icon_return_white);
            if (enableReturnBtn() && mToolBar!=null){
                mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        finish();
                        onBackPressed();
                    }
                });
            }
        }
    }


//    <include layout="@layout/appbar_layout"/>

    protected boolean includeAppBar(){
        return true;
    }

    protected boolean enableReturnBtn(){
        return true;
    }


    protected void showReturnBtn(){
        mToolBar.setNavigationIcon(R.drawable.base_icon_return_white);
    }

    protected void showReturnBtn(int drawableID){
        mToolBar.setNavigationIcon(drawableID);
    }

    protected void hideReturnBtn(){
        mToolBar.setNavigationIcon(null);
    }


    protected void setBarTitle(int stringId){
        mTitle.setText(stringId);
    }
    protected void setBarLeftTitle(int stringId){
        mToolBar.setTitle(stringId);
        mToolBar.setTitleTextColor(getResources().getColor(R.color.white));
    }
    protected void setBarTitle(String title){
        mTitle.setText(title);
    }
    protected void setBarTitleColor(int colorId){
        mTitle.setTextColor(colorId);
    }
    protected String getBarTitle(){
        return mTitle.getText().toString();
    }

    protected void setBarColor(int colorId){
        mToolBar.setBackgroundColor(getResources().getColor(colorId));
    }

    protected void showRightText(String text){
        mRightText.setVisibility(View.VISIBLE);
        mRightText.setText(text);
    }
    protected void showRightText(int stringId){
        mRightText.setVisibility(View.VISIBLE);
        mRightText.setText(stringId);
    }
    protected void hideRightText(){
        mRightText.setVisibility(View.GONE);
    }
    protected TextView getRightText(){
        return mRightText;
    }

    protected Toolbar getmToolBar(){
        return mToolBar;
    }

    protected void setToolBarScrollEnable(boolean scroll){
        AppBarLayout.LayoutParams params =
                (AppBarLayout.LayoutParams) mToolBar.getLayoutParams();
        if (scroll){
            params.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL
                    | AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS);
        }else {
            params.setScrollFlags(0);
        }
    }


    /**--------------------------------------------**/


    public void jumpToActivity(Intent intent) {
        startActivity(intent);
    }

    public void jumpToActivity(Class activity) {
        Intent intent = new Intent(this, activity);
        startActivity(intent);
    }

    public void jumpToActivity(Class activity,Bundle bundle) {
        Intent intent = new Intent(this, activity);
        startActivity(intent,bundle);
    }
}
