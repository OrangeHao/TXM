package com.txmpay.ewallet.ui.main;

import android.os.Bundle;

import com.txmpay.ewallet.R;
import com.txmpay.ewallet.base.BaseActivity;

public class NoticesActivity extends BaseActivity {

    @Override
    protected int setLayoutId() {
        return R.layout.activity_notices;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setBarTitle(R.string.home_message);
    }
}
