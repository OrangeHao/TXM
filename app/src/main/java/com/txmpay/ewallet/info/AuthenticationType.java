package com.txmpay.ewallet.info;

/**
 * 指纹手势验证
 * Created by liruibiao on 17/2/8.
 */

public enum AuthenticationType {
    DRIVE_CAR,       //二维码乘车
    CHANGE_PHONE,    //更换手机
    CHANGE_LOGIN_PWD,//修改登录密码
    FINGER_STATUS,   //指纹开关检测
    GESTURE_STATUS,  //手势开关检测
    CHANGE_GESTURE,  //重新设置手势密码
    FORGOT_PWD,       //忘记密码
}
