package com.txmpay.ewallet.ui.payment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.txmpay.ewallet.R;
import com.txmpay.ewallet.base.BaseActivity;
import com.txmpay.ewallet.utils.DividerGridItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 充值页
 */
public class ReChargeActivity extends BaseActivity {

    @BindView(R.id.account_rv)
    RecyclerView accountRv;
    @BindView(R.id.confirmBtn)
    Button confirmBtn;

    private List<PriceItem> mPrices = new ArrayList<PriceItem>();

    private int mCheckPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_re_charge;
    }

    @Override
    protected void initData() {
        String unitStr = getString(R.string.payment_unit_yuan);
        mPrices.add(new PriceItem(10, unitStr));
        mPrices.add(new PriceItem(20, unitStr));
        mPrices.add(new PriceItem(30, unitStr));
        mPrices.add(new PriceItem(40, unitStr));
        mPrices.add(new PriceItem(50, unitStr));
        mPrices.add(new PriceItem(100, unitStr));
        mPrices.add(new PriceItem(150, unitStr));
        mPrices.add(new PriceItem(200, unitStr));
    }

    @Override
    protected void initView() {
        setBarTitle(R.string.home_recharge);

        accountRv.setLayoutManager(new GridLayoutManager(this, 4));
        accountRv.addItemDecoration(new DividerGridItemDecoration((int) getResources().getDimension(R.dimen.space12)));
        BaseQuickAdapter priceAdapter = new ReCharegePriceAdapter(R.layout.item_recharge_price, mPrices);
        priceAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (mCheckPosition >= 0) {
                    mPrices.get(mCheckPosition).setCheck(false);
                }
                mCheckPosition = position;
                mPrices.get(position).setCheck(true);

                confirmBtn.setText(getString(R.string.payment_wallet_recharge_confirm,mPrices.get(position).getText()));
                adapter.notifyDataSetChanged();
            }
        });
        accountRv.setAdapter(priceAdapter);
    }


    @OnClick(R.id.confirmBtn)
    public void onViewClicked() {

    }
}
