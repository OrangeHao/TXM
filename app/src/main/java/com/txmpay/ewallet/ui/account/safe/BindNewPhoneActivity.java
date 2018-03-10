package com.txmpay.ewallet.ui.account.safe;

import android.os.Bundle;
import android.view.View;

import com.txmpay.ewallet.R;
import com.txmpay.ewallet.base.BaseActivity;
import com.txmpay.ewallet.widget.NormalEditText;

import butterknife.BindView;
import butterknife.OnClick;

public class BindNewPhoneActivity extends BaseActivity {

    @BindView(R.id.phoneNumEdt)
    NormalEditText mPhoneNumEdt;
    @BindView(R.id.verificationCodeEdit)
    NormalEditText mVerificationCodeEdit;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_bind_new_phone;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void initView() {
        setBarTitle(R.string.account_safe_bind_new_phone);
    }

    @OnClick({R.id.send_vf_code_txt, R.id.putRequest_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.send_vf_code_txt:
                break;
            case R.id.putRequest_btn:
                break;
        }
    }
}
