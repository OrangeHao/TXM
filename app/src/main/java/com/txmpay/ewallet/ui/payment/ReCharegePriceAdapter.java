package com.txmpay.ewallet.ui.payment;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.txmpay.ewallet.R;

import java.util.List;

/**
 * created by czh on 2018-03-14
 * 充值价格布局适配器
 */

public class ReCharegePriceAdapter extends BaseQuickAdapter<PriceItem,BaseViewHolder>{

    public ReCharegePriceAdapter(int layoutResId, @Nullable List<PriceItem> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PriceItem item) {
        helper.setText(R.id.recharge_price,item.getText());
        if (item.isCheck()){
            helper.setBackgroundRes(R.id.recharge_item_layout,R.drawable.shape_theme_bg_small);
        }else {
            helper.setBackgroundRes(R.id.recharge_item_layout,R.drawable.shape_white_bg_small);
        }
    }
}
