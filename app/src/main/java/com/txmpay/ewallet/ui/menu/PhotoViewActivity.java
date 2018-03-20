package com.txmpay.ewallet.ui.menu;

import android.os.Bundle;

import com.github.chrisbanes.photoview.PhotoView;
import com.txmpay.ewallet.R;
import com.txmpay.ewallet.base.BaseActivity;

import butterknife.BindView;


/**
 * 图片查看页
 */
public class PhotoViewActivity extends BaseActivity {

    @BindView(R.id.photoView)
    PhotoView photoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_photo_view;
    }

    @Override
    protected void initView() {
        photoView.setImageResource(R.drawable.card_bg);
    }
}
