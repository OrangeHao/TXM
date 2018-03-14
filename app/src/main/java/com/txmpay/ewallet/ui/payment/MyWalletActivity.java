package com.txmpay.ewallet.ui.payment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.txmpay.ewallet.R;
import com.txmpay.ewallet.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class MyWalletActivity extends BaseActivity {

    @BindView(R.id.balanceTxt)
    TextView balanceTxt;
    @BindView(R.id.frozenBalanceTxt)
    TextView frozenBalanceTxt;
    @BindView(R.id.couponNumText)
    TextView couponNumText;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_my_wallet;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setBarTitle(R.string.home_my_wallet);

        frozenBalanceTxt.setText("0.00$");
        couponNumText.setText("0 zhang");
        balanceTxt.setText("28.50");
    }

    @OnClick({R.id.recharge_layout, R.id.lookFrozenTxt, R.id.lookCouponTxt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.recharge_layout:
                jumpToActivity(ReChargeActivity.class);
                break;
            case R.id.lookFrozenTxt:
                break;
            case R.id.lookCouponTxt:
                jumpToActivity(CouponActivity.class);
                break;
        }
    }
}
