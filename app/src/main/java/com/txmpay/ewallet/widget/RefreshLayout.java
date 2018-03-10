package com.txmpay.ewallet.widget;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.lms.support.common.YiLog;
import com.txmpay.ewallet.R;


/**
 * 上拉下拉
 * Created by liruibiao on 17/1/4.
 */

public class RefreshLayout extends SwipeRefreshLayout {
    final int MSG_FINISHING_REFRESH = 001;
    Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_FINISHING_REFRESH:
                    setRefreshing(false);
                    break;
            }
        }
    };

    public RefreshLayout(Context context) {
        super(context);
        initView(context);
    }

    public RefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }


    public void initView(Context context) {
        setColorSchemeResources(R.color.colorAccent, R.color.textDefualt2, R.color.colorAccent, R.color.textDefualt2);
    }

    public void setRefreshingFinish() {
        handler.sendEmptyMessageDelayed(MSG_FINISHING_REFRESH, 500);
    }

    public void onDestroy() {
        handler.removeMessages(MSG_FINISHING_REFRESH);
    }


    public void setRefreshListener(final RefreshListener refreshListener) {
        setRefreshListener(new RefreshListener() {
            @Override
            public void onRefresh() {
                refreshListener.onRefresh();
            }
        });
    }

    public void setRefreshListener(RecyclerView recyclerView, final RefreshListener refreshListener) {
        setRefreshListener(new RefreshListener() {
            @Override
            public void onRefresh() {
                refreshListener.onRefresh();
            }
        });
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            Boolean isScrolling = false;

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    isScrolling = true;
                } else {
                    isScrolling = false;
                }
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                if (!isRefreshing() && newState == RecyclerView.SCROLL_STATE_IDLE && isScrolling) {
                    int lastVisibleItem = 0;
                    if (layoutManager instanceof LinearLayoutManager) {
                        lastVisibleItem = ((LinearLayoutManager) layoutManager).findLastCompletelyVisibleItemPosition();
                    } else if (layoutManager instanceof GridLayoutManager) {
                        lastVisibleItem = ((GridLayoutManager) layoutManager).findLastCompletelyVisibleItemPosition();
                    } else {
                        YiLog.getInstance().e("please extend LayoutManager");
                    }
                    int totalItemCount = layoutManager.getItemCount();
                    if (lastVisibleItem == (totalItemCount - 1)) {
                        isScrolling = false;
                        setRefreshing(true);
                        refreshListener.onLoadMore();
                    }
                }
            }
        });
    }

}
