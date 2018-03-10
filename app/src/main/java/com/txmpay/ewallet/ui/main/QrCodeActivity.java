package com.txmpay.ewallet.ui.main;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.txmpay.ewallet.R;
import com.txmpay.ewallet.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class QrCodeActivity extends BaseActivity {

    @BindView(R.id.avatarImg)
    ImageView mAvatarImg;
    @BindView(R.id.qr_balance_text)
    TextView mQrBalanceText;
    @BindView(R.id.nameTxt)
    TextView mNameTxt;
    @BindView(R.id.cityTxt)
    TextView mCityTxt;
    @BindView(R.id.qrImg)
    ImageView mQrImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_qr_code;
    }

    @Override
    protected void initView() {
        setBarTitle(getString(R.string.home_title));
    }

    @OnClick({R.id.qrImg, R.id.qr_recharge_layout, R.id.scanTipTxt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.qrImg:
                break;
            case R.id.qr_recharge_layout:
                break;
            case R.id.scanTipTxt:
                break;
        }
    }
}
