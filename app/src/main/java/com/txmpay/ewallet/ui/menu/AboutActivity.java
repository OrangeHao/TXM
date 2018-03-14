package com.txmpay.ewallet.ui.menu;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.txmpay.ewallet.R;
import com.txmpay.ewallet.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class AboutActivity extends BaseActivity {

    @BindView(R.id.avatarImg)
    ImageView avatarImg;
    @BindView(R.id.nameTxt)
    TextView nameTxt;
    @BindView(R.id.versionTxt)
    TextView versionTxt;
    @BindView(R.id.versionMessage)
    TextView versionMessage;
    @BindView(R.id.setvicePhone)
    TextView setvicePhone;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_about;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setBarTitle(R.string.about_title);

        nameTxt.setText("yikatong");
        versionTxt.setText("v1.0.0");
        versionMessage.setText("newest");
        setvicePhone.setText("110000");
    }

    @OnClick({R.id.versionLayout, R.id.serviceLayout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.versionLayout:
                break;
            case R.id.serviceLayout:
                break;
        }
    }
}
