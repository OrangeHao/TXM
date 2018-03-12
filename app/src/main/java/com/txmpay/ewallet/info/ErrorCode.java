package com.txmpay.ewallet.info;

import com.lms.support.common.YiLog;
import com.lms.support.util.NetworkUtils;
import com.txmpay.ewallet.R;

/**
 * 错误码
 * Created by ruibiao on 16-12-27.
 */
public enum ErrorCode {
    HAND_FAIL(1000, "操作失败"),
    DATA_ISTEXIST(1001, "数据已存在"),
    CHECK_PARA_FAILE(1002, "参数验证失败"),
    DATA_NOTEXIST(1003, "数据不存在"),
    RESTRICT_LOGIN(1004, "系统限制登录"),
    RESTRICT_REGISTER(1005, "系统限制注册"),
    SEND_FAILE(1006, "短信发送失败"),
    LOGINNAME_PWD_ERROR(1007, "用户帐号或密码错误"),
    NOT_ALLOW(1008, "非法操作"),
    USER_ALREADY_FROZEN(1009, "用户已冻结"),
    INVALID_CODE(1010, "无效验证码"),
    USER_NOTEXIST(1011, "用户不存在"),
    USER_NOTMONEY(1012, "用户余额不足"),
    INVALID_SALT(1013, "无效令牌"),
    LOGINVERSION_ERROR(1014, "登录版本错误"),
    UNKNOWN_ERROR(-1, "未知错误");

    private int code;
    private String message;

    private ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public static ErrorCode valueOf(int code) {
        ErrorCode[] errors = values();
        for (ErrorCode err : errors) {
            if (err.getCode() == code) {
                return err;
            }
        }
        return ErrorCode.UNKNOWN_ERROR;
    }



    public static String responseCode(int code) {
        ErrorCode[] errors = values();
        for (ErrorCode err : errors) {
            if (err.getCode() == code) {
                return err.getMessage();
            }
        }

        return ErrorCode.UNKNOWN_ERROR.getMessage();

    }
}
