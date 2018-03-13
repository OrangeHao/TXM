package com.txmpay.ewallet.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.txmpay.ewallet.base.BaseActivity;
import com.txmpay.ewallet.info.AuthenticationType;
import com.txmpay.ewallet.ui.account.safe.GestrueCheckFragment;

/**
 * created by czh on 2018-03-12
 */

public class SecureAccessManage {



    public static void startGestureCheck(BaseActivity activity, AuthenticationType type){
        GestrueCheckFragment gestrueCheckFragment = (GestrueCheckFragment) Fragment.instantiate(activity, GestrueCheckFragment.class.getName());
        Bundle bundle = new Bundle();
        bundle.putSerializable("type", type);
        gestrueCheckFragment.setArguments(bundle);
        gestrueCheckFragment.show(activity.getSupportFragmentManager(), "gestrueCheckFragment");
    }
}
