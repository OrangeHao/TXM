package com.txmpay.ewallet.utils;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class DividerGridItemDecoration extends RecyclerView.ItemDecoration {
    private int space;
    private int spanCount = 4;

    public DividerGridItemDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view,
                               RecyclerView parent, RecyclerView.State state) {
        outRect.right = space;
        outRect.bottom = space;
        // Add top margin only for the first item to avoid double space between items
        if (parent.getChildPosition(view) % spanCount == 0) {
            outRect.left = space;
        }

        if (parent.getChildPosition(view) < spanCount) {
            outRect.top = space;
        }

    }

}
