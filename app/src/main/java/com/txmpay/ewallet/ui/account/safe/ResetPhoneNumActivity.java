package com.txmpay.ewallet.ui.account.safe;

import android.os.Bundle;
import android.view.View;

import com.txmpay.ewallet.R;
import com.txmpay.ewallet.base.BaseActivity;
import com.txmpay.ewallet.widget.NormalEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ResetPhoneNumActivity extends BaseActivity {

    @BindView(R.id.verificationCodeEdit)
    NormalEditText mVerificationCodeEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_reset_phone_num;
    }


    @Override
    protected void initView() {
        showReturnBtn();
        setBarTitle(R.string.account_safe_change_phone_num);
    }

    @OnClick({R.id.send_vf_code_txt, R.id.next_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.send_vf_code_txt:
                break;
            case R.id.next_btn:
                jumpToActivity(BindNewPhoneActivity.class);
                break;
        }
    }
}
