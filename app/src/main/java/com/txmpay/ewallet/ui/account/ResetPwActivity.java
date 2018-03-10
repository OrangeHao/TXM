package com.txmpay.ewallet.ui.account;

import android.os.Bundle;
import android.view.View;

import com.txmpay.ewallet.R;
import com.txmpay.ewallet.base.BaseActivity;
import com.txmpay.ewallet.widget.NormalEditText;

import butterknife.BindView;
import butterknife.OnClick;

public class ResetPwActivity extends BaseActivity {

    @BindView(R.id.phoneEdit)
    NormalEditText mPhoneEdit;
    @BindView(R.id.verificationCodeEdit)
    NormalEditText mVerificationCodeEdit;
    @BindView(R.id.pwEdit)
    NormalEditText mPwEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_reset_pw;
    }

    @Override
    protected void initView() {
        setBarTitle(R.string.account_reset_pw);
        setBarTitleColor(getResources().getColor(R.color.white));
    }

    @OnClick({R.id.send_vf_code_txt, R.id.resetPwBtn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.send_vf_code_txt:
                break;
            case R.id.resetPwBtn:
                break;
        }
    }
}
