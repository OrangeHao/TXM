package com.txmpay.ewallet.ui.account.safe;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;

import com.kyleduo.switchbutton.SwitchButton;
import com.txmpay.ewallet.R;
import com.txmpay.ewallet.base.BaseActivity;
import com.txmpay.ewallet.info.AuthenticationType;
import com.txmpay.ewallet.ui.SecureAccessManage;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 支付安全页
 */
public class PaySafeActivity extends BaseActivity {

    @BindView(R.id.changeGePwdLayout)
    RelativeLayout changeGePwdLayout;

    @BindView(R.id.gestureVSwitch)
    SwitchButton mSwitchBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_pay_safe;
    }

    @Override
    protected void initView() {
        setBarTitle(R.string.account_safe_pay_safe);

        mSwitchBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.d("czg","ischeck:"+isChecked);
                if (isChecked){
                    jumpToActivity(CreateGestureActivity.class);
                }
            }
        });
    }

    @OnClick({R.id.gestureVSwitch, R.id.changeGePwdLayout,R.id.gestureSwitchLayout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.gestureVSwitch:

                break;
            case R.id.changeGePwdLayout:
                SecureAccessManage.startGestureCheck(PaySafeActivity.this, AuthenticationType.CHANGE_GESTURE);
                break;
            case R.id.gestureSwitchLayout:
                if (mSwitchBtn.isChecked()){
                    mSwitchBtn.setChecked(false);
                }else {
                    mSwitchBtn.setChecked(true);
                }
                break;
        }
    }
}
