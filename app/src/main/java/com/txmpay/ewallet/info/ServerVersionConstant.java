package com.txmpay.ewallet.info;


import com.txmpay.ewallet.BuildConfig;

/**
 * 后台版本信息
 * Created by ruibiao on 16-12-27.
 */
public class ServerVersionConstant {
    public static String CHANNEL = "lms";
    public static float DENSITY = 0;
    public final static String VERSION = BuildConfig.VERSION_NAME;
    public final static String PLATFORM = "android";
    public final static int TYPE = 0; //0手机app,1pos机固件,2pos机apk，3运维

    public final static String APP_SIZE = "lms";

    public static final String IP = "http://cs.txmpay.com:28080";
//  public static final String IP = "http://192.168.0.66:8080";
//  public static final String IP = "http://113.59.84.161:63522";
//  public static final String IP = "http://39.108.5.61";
//    public static final String IP = "http://openrs.tianyaxing.com";
//    public static final String IP = "https://alpha.txmpay.com";


    public static final String BASEPATH = IP + "/cs-pay/api";

    public static final String BASEPATH_RECHARGE = IP + "/cs-recharge/api";
    public static final String BASEPATH_BUS_GPS = IP + "/cs-bus/api";

    //public static double MENU_HEIGHT = 2.23;
    public static double MENU_HEIGHT = 2.75; // 4/11

    public final static int NUM_PASS_COUNT = 9999;

}
