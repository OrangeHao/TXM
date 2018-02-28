package com.txmpay.ewallet.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.txmpay.ewallet.R;

import butterknife.ButterKnife;

/**
 * created by czh on 2018-02-27
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
            if (enableReturnBtn() && mToolBar!=null){
                mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
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
        mToolBar.setNavigationIcon(R.drawable.icon_return);
    }

    protected void hideReturnBtn(){
        mToolBar.setNavigationIcon(null);
    }

    protected void setBarTitle(int stringId){
        mTitle.setText(stringId);
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


    /**--------------------------------------------**/

}
