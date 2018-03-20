package com.txmpay.ewallet.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * created by czh on 2018-02-27
 * Fragment类
 */

public abstract class BaseFragment<T extends BasePresenter> extends Fragment{

    protected T mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initBeforeCreateView();

        mPresenter=createPresenter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(setLayoutId(), container, false);
        ButterKnife.bind(this, rootView);
        initView(rootView);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
        initListner();
    }

    protected abstract int setLayoutId();

    protected  T createPresenter(){
        return null;
    }

    protected void initBeforeCreateView(){

    }

    protected void initView(View rootView){

    }

    protected void initData(){

    }

    protected void initListner(){

    }
}
