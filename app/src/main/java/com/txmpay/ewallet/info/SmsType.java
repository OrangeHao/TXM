package com.txmpay.ewallet.info;

/**
 * 短信验证码类型
 * 1注册短信、2忘记密码、3更换手机、4绑定新手机、5登陆短信
 * Created by liruibiao on 2017/3/8.
 */

public class SmsType {
    /**
     * 有效时间
     */
    public static final int EFFECTIVE_TIME = 90;

    /**
     * 注册短信
     */
    public static final int REGISTER = 1;
    /**
     * 忘记密码
     */
    public static final int FORGET_PWD = 2;
    /**
     * 更换手机
     */
    public static final int CHANGE_PHONE = 3;
    /**
     * 绑定新手机
     */
    public static final int BIND_NEW_PHONE = 4;
    /**
     * 登陆短信
     */
    public static final int LOGIN = 5;
    /**
     * 切换城市
     */
    public static final int SWITCH_AREA = 7;

    /**
     * iccard
     */
    public static final int IC_CARD = 8;
}
