package com.txmpay.ewallet.ui.menu;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.txmpay.ewallet.R;
import com.txmpay.ewallet.base.BaseActivity;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import permissions.dispatcher.NeedsPermission;

public class PersonalInfoActivity extends BaseActivity {

    @BindView(R.id.avatarImg)
    ImageView avatarImg;
    @BindView(R.id.nameTxt)
    TextView nameTxt;
    @BindView(R.id.genderTxt)
    TextView genderTxt;


    private final int REQUEST_CODE_CHOOSE=01;
    List<Uri> mSelected;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_personal_info;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setBarTitle(R.string.personal_info_person_info);
    }

    @OnClick({R.id.avatarLayout, R.id.nameLayout, R.id.genderLayout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.avatarLayout:
                showPicture();
                break;
            case R.id.nameLayout:
                break;
            case R.id.genderLayout:
                break;
        }
    }


    private void showPicture() {
        Matisse.from(PersonalInfoActivity.this)
                .choose(MimeType.allOf())
                .countable(true)
                .maxSelectable(1)
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                .thumbnailScale(0.85f)
                .imageEngine(new GlideEngine())
                .theme(R.style.MatisseTheme)
                .forResult(REQUEST_CODE_CHOOSE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            mSelected = Matisse.obtainResult(data);
            Log.d("czh", "mSelected: " + mSelected);
        }
    }
}
