package com.txmpay.ewallet.ui.payment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.txmpay.ewallet.R;
import com.txmpay.ewallet.base.BaseActivity;

public class CouponActivity extends BaseActivity {

    @Override
    protected int setLayoutId() {
        return R.layout.activity_coupon;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setBarTitle(R.string.payment_wallet_coupon);
    }
}
