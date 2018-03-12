package com.txmpay.ewallet.app;

import android.app.Application;

import com.txmpay.ewallet.base.BaseApp;
import com.txmpay.ewallet.model.MyObjectBox;
import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

import io.objectbox.BoxStore;

/**
 * created by czh on 2018-02-26
 */

public class MyApp extends BaseApp{


    private static BoxStore sBoxStore;

    @Override
    public void onCreate() {
        super.onCreate();
        initDataBase();
        initUmeng();
    }

    private void initDataBase(){
        sBoxStore= MyObjectBox.builder().androidContext(this).build();
    }

    public static BoxStore getBoxStore(){
        return sBoxStore;
    }


    private void initUmeng(){
        //U-App
        /**
         * 初始化common库
         * 参数1:上下文，不能为空
         * 参数2:友盟 app key
         * 参数3:友盟 channel
         * 参数4:设备类型，UMConfigure.DEVICE_TYPE_PHONE为手机、UMConfigure.DEVICE_TYPE_BOX为盒子，默认为手机
         * 参数5:Push推送业务的secret
         */
        UMConfigure.init(this, null,
                "Umeng",
                UMConfigure.DEVICE_TYPE_PHONE, null);
        /**
         * 设置日志加密
         * 参数：boolean 默认为false（不加密）
         */
        UMConfigure.setEncryptEnabled(true);
        MobclickAgent.setScenarioType(this, MobclickAgent.EScenarioType.E_UM_NORMAL);


        //UShare
        UMShareAPI.get(this);
        //78a9ae79019a3804be976225c9d89a1e  旧
        //3fb7517f7d6b679c76a1eb181677a0df   新
        //三亚
        //生产:wx84b0e96899af54ad(3fb7517f7d6b679c76a1eb181677a0df)
        // 开发:wxf86f00d5e82667b0(6a48f457be3f7d6bfe1e6f9dc3a829d0)
        //wx6acc0f5b1e869edf==58889cac1edf8171e5b8cf721748fe82
        PlatformConfig.setWeixin("wx84b0e96899af54ad", "3fb7517f7d6b679c76a1eb181677a0df");
        PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad");
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
    }
}
