package com.txmpay.ewallet.app;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.lms.support.common.YiPrefsKeeper;

/**
 * App信息缓存
 *
 * @author ruibiao
 */
public class Config implements YiPrefsKeeper.YiPrefsKeepable {
    private static Config mConfig = null;

    /**
     * 是否第一次启动app
     */
    private boolean isFirstLaunchApp;
    /**
     * 是否登录
     */
    private boolean isLogin;

    /**
     * 保存常用的用户信息,用户详细资料可通过UserDao获取UserModel
     */
    private int uid;
    private int cartoonid;
    private String loginPhone;
    private String password;
    private String securityinfo;
    private String privateKey;
    private String publicKey;
    private boolean isApplySuccess;

    //游客模式下可用数据,可以手动选择城市变更
    private int cityCode;
    private String cityName;

    public static Config getInstance() {
        if (mConfig == null) {
            mConfig = new Config();
        }
        return mConfig;
    }

    private Config() {
        YiPrefsKeeper.read(MyApp.getContext(), this);
    }

    @Override
    public void save(Editor editor) {
        editor.putBoolean("isFirstLaunchApp", isFirstLaunchApp);
        editor.putInt("uid", uid);
        editor.putBoolean("isLogin", isLogin);
        editor.putString("loginPhone", loginPhone);
        editor.putInt("cartoonid", cartoonid);
        editor.putInt("cityCode", cityCode);
        editor.putString("cityName", cityName);
        editor.putString("password", password);
        editor.putString("securityinfo", securityinfo);
        editor.putString("prk", privateKey);
        editor.putString("pbk", publicKey);
        editor.putBoolean("isApplySuccess", isApplySuccess);
    }

    @Override
    public void restore(SharedPreferences preferences) {
        isFirstLaunchApp = preferences.getBoolean("isFirstLaunchApp", true);
        uid = preferences.getInt("uid", 0);
        isLogin = preferences.getBoolean("isLogin", false);
        loginPhone = preferences.getString("loginPhone", null);
        cartoonid = preferences.getInt("cartoonid", 6420);
        cityCode = preferences.getInt("cityCode", 6420);
        cityName = preferences.getString("cityName", null);
        password = preferences.getString("password", null);
        securityinfo = preferences.getString("securityinfo", null);
        privateKey = preferences.getString("prk", null);
        publicKey = preferences.getString("pbk", null);
        isApplySuccess = preferences.getBoolean("isApplySuccess", false);
    }

    /**
     * 退出登录后需要清除的缓存数据
     */
    @Override
    public void clearAll() {
        uid = 0;
        isLogin = false;
        loginPhone = null;
        password = null;
        securityinfo = null;
        privateKey = null;
        publicKey = null;
    }

    @Override
    public String getPrefsName() {
        return "ewallet_config";
    }

    public boolean isFirstLaunchApp() {
        return isFirstLaunchApp;
    }

    public void setFirstLaunchApp(boolean firstLaunchApp) {
        isFirstLaunchApp = firstLaunchApp;
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getLoginPhone() {
        return loginPhone;
    }

    public void setLoginPhone(String loginPhone) {
        this.loginPhone = loginPhone;
    }

    public int getCartoonid() {
        return cartoonid;
    }

    public void setCartoonid(int cartoonid) {
        this.cartoonid = cartoonid;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getCityCode() {
        return cityCode;
    }

    public void setCityCode(int cityCode) {
        this.cityCode = cityCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSecurityinfo() {
        return securityinfo;
    }

    public void setSecurityinfo(String securityinfo) {
        this.securityinfo = securityinfo;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public boolean isApplySuccess() {
        return isApplySuccess;
    }

    public void setApplySuccess(boolean applySuccess) {
        isApplySuccess = applySuccess;
    }
}
