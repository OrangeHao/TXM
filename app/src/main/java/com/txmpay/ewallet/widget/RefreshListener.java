package com.txmpay.ewallet.widget;

/**
 * 上拉下拉监听接口
 * Created by liruibiao on 17/1/4.
 */

public abstract class RefreshListener {
    public abstract void onRefresh();

    public void onLoadMore() {
    }

    public void onfinish() {
    }
}
