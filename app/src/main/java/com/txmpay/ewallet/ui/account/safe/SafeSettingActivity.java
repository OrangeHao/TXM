package com.txmpay.ewallet.ui.account.safe;

import android.os.Bundle;
import android.view.View;

import com.txmpay.ewallet.R;
import com.txmpay.ewallet.base.BaseActivity;

import butterknife.OnClick;

public class SafeSettingActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_safe_setting;
    }

    @Override
    protected void initView() {
        setBarTitle(R.string.account_safe_setting);
    }

    @OnClick({R.id.changePhoneLayout, R.id.changePwLayout, R.id.paySafeLayout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.changePhoneLayout:
                jumpToActivity(ResetPhoneNumActivity.class);
                break;
            case R.id.changePwLayout:
                break;
            case R.id.paySafeLayout:
                break;
        }
    }
}
