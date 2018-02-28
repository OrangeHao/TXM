package com.txmpay.ewallet.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.txmpay.ewallet.R;
import com.txmpay.ewallet.base.BaseActivity;

import butterknife.OnClick;

public class TestActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_test;
    }

    @OnClick({R.id.test_btn1,R.id.test_btn2,R.id.test_btn3,R.id.test_btn4,R.id.test_btn5})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.test_btn1:
                showReturnBtn();
                break;
            case R.id.test_btn2:
                hideReturnBtn();
                break;
            case R.id.test_btn3:
                setBarTitle("dsfadfsadf");
                break;
            case R.id.test_btn4:
                showRightText("哈哈");
                break;
            case R.id.test_btn5:
                hideRightText();
                break;
            default:
                break;
        }
    }
}
