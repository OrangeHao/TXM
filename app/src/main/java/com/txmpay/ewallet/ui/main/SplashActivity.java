package com.txmpay.ewallet.ui.main;

import android.Manifest;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.txmpay.ewallet.R;
import com.txmpay.ewallet.base.BaseActivity;
import com.txmpay.ewallet.utils.TaskScheduler.TaskScheduler;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SplashActivityPermissionsDispatcher.startMainActivityWithPermissionCheck(this);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_splash;
    }





    @NeedsPermission({Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void startMainActivity() {
        TaskScheduler.runOnUIThread(
                ()-> {
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    finish();
                }
            ,2000);

    }


    @OnShowRationale({Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void showRationale(final PermissionRequest request) {
        new AlertDialog.Builder(this)
                .setMessage("ration")
                .setPositiveButton("yunxu", (dialog, button) -> request.proceed())
                .setNegativeButton("deny", (dialog, button) -> request.cancel())
                .show();
    }

    @OnPermissionDenied({Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void showDenied() {
        Toast.makeText(this, "deny", Toast.LENGTH_SHORT).show();
    }

    @OnNeverAskAgain({Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void showNeverAsk() {
        Toast.makeText(this, "never", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        SplashActivityPermissionsDispatcher.onRequestPermissionsResult(this,requestCode,grantResults);
    }
}
