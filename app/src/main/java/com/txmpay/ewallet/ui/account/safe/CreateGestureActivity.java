package com.txmpay.ewallet.ui.account.safe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.lms.support.common.YiLog;
import com.txmpay.ewallet.R;
import com.txmpay.ewallet.app.Config;
import com.txmpay.ewallet.base.BaseActivity;
import com.txmpay.ewallet.model.ObjectBoxHelper;
import com.txmpay.ewallet.model.UserSetting;
import com.txmpay.ewallet.widget.lockpattern.LockPatternIndicator;
import com.txmpay.ewallet.widget.lockpattern.LockPatternUtil;
import com.txmpay.ewallet.widget.lockpattern.LockPatternView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.objectbox.Box;

public class CreateGestureActivity extends BaseActivity {

    @BindView(R.id.lockPatterIndicator)
    LockPatternIndicator lockPatterIndicator;
    @BindView(R.id.messageTxt)
    TextView messageTxt;
    @BindView(R.id.lockPatternView)
    LockPatternView lockPatternView;

    private static final long DELAYTIME = 600L;
    private List<LockPatternView.Cell> mChosenPattern = null;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_create_gesture;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setBarTitle(R.string.gesture_create_activity);

        lockPatternView.setOnPatternListener(patternListener);
    }

    @OnClick(R.id.resetBtn)
    public void onClick() {
        mChosenPattern = null;
        lockPatterIndicator.setDefaultIndicator();
        updateStatus(Status.DEFAULT, null);
        lockPatternView.setPattern(LockPatternView.DisplayMode.DEFAULT);
    }

    /**
     * 手势监听
     */
    private LockPatternView.OnPatternListener patternListener = new LockPatternView.OnPatternListener() {


        @Override
        public void onPatternStart() {
            lockPatternView.removePostClearPatternRunnable();
            //updateStatus(Status.DEFAULT, null);
            lockPatternView.setPattern(LockPatternView.DisplayMode.DEFAULT);
        }

        @Override
        public void onPatternComplete(List<LockPatternView.Cell> pattern) {
            //Log.e(TAG, "--onPatternDetected--");
            if (mChosenPattern == null && pattern.size() >= 4) {
                mChosenPattern = new ArrayList<LockPatternView.Cell>(pattern);
                updateStatus(Status.CORRECT, pattern);
            } else if (mChosenPattern == null && pattern.size() < 4) {
                updateStatus(Status.LESSERROR, pattern);
            } else if (mChosenPattern != null) {
                if (mChosenPattern.equals(pattern)) {
                    updateStatus(Status.CONFIRMCORRECT, pattern);
                } else {
                    updateStatus(Status.CONFIRMERROR, pattern);
                }
            }
        }
    };


    /**
     * 更新状态
     *
     * @param status
     * @param pattern
     */
    private void updateStatus(Status status, List<LockPatternView.Cell> pattern) {
        messageTxt.setTextColor(getResources().getColor(status.colorId));
        messageTxt.setText(status.strId);
        switch (status) {
            case DEFAULT:
                lockPatternView.setPattern(LockPatternView.DisplayMode.DEFAULT);
                break;
            case CORRECT:
                updateLockPatternIndicator();
                lockPatternView.setPattern(LockPatternView.DisplayMode.DEFAULT);
                break;
            case LESSERROR:
                lockPatternView.setPattern(LockPatternView.DisplayMode.DEFAULT);
                break;
            case CONFIRMERROR:
                lockPatternView.setPattern(LockPatternView.DisplayMode.ERROR);
                lockPatternView.postClearPatternRunnable(DELAYTIME);
                break;
            case CONFIRMCORRECT:
                saveChosenPattern(pattern);
                lockPatternView.setPattern(LockPatternView.DisplayMode.DEFAULT);
                setLockPatternSuccess();
                break;
        }
    }

    /**
     * 更新 Indicator
     */
    private void updateLockPatternIndicator() {
        if (mChosenPattern == null)
            return;
        lockPatterIndicator.setIndicator(mChosenPattern);
    }

    /**
     * 成功设置了手势密码(跳到首页)
     */
    private void setLockPatternSuccess() {
//       Toast.makeText(this, "create gesture success", Toast.LENGTH_SHORT).show();
//        if (getIntent().hasExtra("is_event")) {
//            YiLog.getInstance().i("is_event");
//            EventBus.getDefault().post(new GestureCheckActivity.GesturePwdCheckEvent());
//        }
//        finish();
    }

    /**
     * 保存手势密码
     */
    private void saveChosenPattern(List<LockPatternView.Cell> cells) {
        Box<UserSetting> userSettingBox= ObjectBoxHelper.getBox(UserSetting.class);
        UserSetting setting=userSettingBox.get(Config.getInstance().getUid());
        if (setting == null) {
            setting = new UserSetting();
            setting.setUid(Config.getInstance().getUid());
            setting.setGesture(LockPatternUtil.patternToStr(cells));
        } else {
            setting.setGesture(LockPatternUtil.patternToStr(cells));
        }
        userSettingBox.put(setting);
    }

    private enum Status {
        //默认的状态，刚开始的时候（初始化状态）
        DEFAULT(R.string.gesture_create_gesture_default, R.color.textDefualt3),
        //第一次记录成功
        CORRECT(R.string.gesture_create_gesture_correct, R.color.textDefualt3),
        //连接的点数小于4（二次确认的时候就不再提示连接的点数小于4，而是提示确认错误）
        LESSERROR(R.string.gesture_create_gesture_less_error, R.color.base_red),
        //二次确认错误
        CONFIRMERROR(R.string.gesture_create_gesture_confirm_error, R.color.base_red),
        //二次确认正确
        CONFIRMCORRECT(R.string.gesture_create_gesture_confirm_correct, R.color.textDefualt3);

        private Status(int strId, int colorId) {
            this.strId = strId;
            this.colorId = colorId;
        }

        private int strId;
        private int colorId;
    }

}
