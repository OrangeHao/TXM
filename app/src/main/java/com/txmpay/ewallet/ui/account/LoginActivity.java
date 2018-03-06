package com.txmpay.ewallet.ui.account;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.txmpay.ewallet.R;
import com.txmpay.ewallet.base.BaseActivity;
import com.txmpay.ewallet.widget.NormalEditText;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.avatarImg)
    ImageView mAvatarImg;
    @BindView(R.id.phoneEdit)
    NormalEditText mPhoneEdit;
    @BindView(R.id.pwEdit)
    NormalEditText mPwEdit;
    @BindView(R.id.loginBtn)
    Button mLoginBtn;
    @BindView(R.id.forgetPwdTxt)
    TextView mForgetPwdTxt;
    @BindView(R.id.registerTxt)
    TextView mRegisterTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected boolean includeAppBar() {
        return false;
    }

    @Override
    protected void initView() {

    }

    @OnClick({R.id.return_img, R.id.loginBtn, R.id.forgetPwdTxt, R.id.registerTxt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.return_img:
                finish();
                break;
            case R.id.loginBtn:
                break;
            case R.id.forgetPwdTxt:
                jumpToActivity(ResetPwActivity.class);
                break;
            case R.id.registerTxt:
                jumpToActivity(RegistActivity.class);
                break;
        }
    }
}
