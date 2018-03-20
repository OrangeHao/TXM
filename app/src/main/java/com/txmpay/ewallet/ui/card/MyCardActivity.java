package com.txmpay.ewallet.ui.card;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.txmpay.ewallet.R;
import com.txmpay.ewallet.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 我的特殊卡
 */
public class MyCardActivity extends BaseActivity {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.empty_img)
    ImageView emptyImg;
    @BindView(R.id.empty_tips)
    TextView emptyTips;
    @BindView(R.id.empty_layout)
    RelativeLayout emptyLayout;
    @BindView(R.id.applyCardBtn)
    Button applyCardBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_my_card;
    }

    @Override
    protected void initView() {
        setBarTitle(R.string.card_my_special_card);
        emptyImg.setImageDrawable(getResources().getDrawable(R.drawable.card_img_none));
        emptyTips.setText(R.string.card_none_tips);
    }


    @OnClick(R.id.applyCardBtn)
    public void onViewClicked() {

    }
}
