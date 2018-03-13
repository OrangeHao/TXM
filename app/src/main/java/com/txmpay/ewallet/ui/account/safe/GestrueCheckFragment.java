package com.txmpay.ewallet.ui.account.safe;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lms.support.util.ScreenUtils;
import com.txmpay.ewallet.R;
import com.txmpay.ewallet.base.BaseFragment;
import com.txmpay.ewallet.widget.lockpattern.LockPatternView;

import java.util.List;

import butterknife.BindView;

/**
 * created by czh on 2018-03-12
 */

public class GestrueCheckFragment extends BaseFragment{

    @BindView(R.id.messageTxt)
    TextView messageTxt;

    @BindView(R.id.lockPatternView)
    LockPatternView lockPatternView;

    @BindView(R.id.closeBtn)
    ImageView closeTxt;

    ViewGroup group;
    View view;
    boolean dismissed = true;

    private static final long DELAYTIME = 600l;
    private String gesturePassword;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_gesture_check;
    }

    @Override
    protected void initView(View rootView) {
        view=rootView;

        //隐藏键盘
        InputMethodManager imm = (InputMethodManager) getActivity()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            View focusView = getActivity().getCurrentFocus();
            if (focusView != null) {
                imm.hideSoftInputFromWindow(focusView.getWindowToken(), 0);
            }
        }
        //添加整个布局
        getActivity().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN
                        | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        group = (ViewGroup) getActivity().getWindow().getDecorView();
        group.addView(view);

        RelativeLayout.LayoutParams closeLP = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        closeLP.setMargins(0, ScreenUtils.getStatusBarHeight(getContext()), 0, 0);
        closeTxt.setLayoutParams(closeLP);

    }

    @Override
    protected void initListner() {
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });
        //关闭按钮
        closeTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        lockPatternView.setOnPatternListener(patternListener);
    }

    private LockPatternView.OnPatternListener patternListener = new LockPatternView.OnPatternListener() {

        @Override
        public void onPatternStart() {
            lockPatternView.removePostClearPatternRunnable();
        }

        @Override
        public void onPatternComplete(List<LockPatternView.Cell> pattern) {
//            if (pattern != null) {
//                if (LockPatternUtil.checkPattern2(pattern, gesturePassword)) {
//                    userSettingModel.setGestureCheckCount(0);
//                    userSettingDao.updateUserSetting(userSettingModel);
//
//                    updateStatus(Status.CORRECT);
//                } else {
//                    updateStatus(Status.ERROR);
//                    userSettingModel.setGestureCheckCount(userSettingModel.getGestureCheckCount() + 1);
//                    userSettingDao.updateUserSetting(userSettingModel);
//                    if (userSettingModel.getGestureCheckCount() == 5) {
//                        //手势输入错误次数大于5次需要输入登录密码
//                        AuthenticationType type = (AuthenticationType) getArguments().getSerializable("type");
//                        GestureErrorFragment gestureErrorFragment = (GestureErrorFragment) Fragment.instantiate(getContext(), GestureErrorFragment.class.getName());
//                        Bundle bundle = new Bundle();
//                        bundle.putSerializable("type", type);
//                        gestureErrorFragment.setArguments(bundle);
//                        gestureErrorFragment.show(getActivity().getSupportFragmentManager(), "GestureErrorFragment");
//                        dismiss();
//                    }
//                }
//            }
        }
    };

    /**
     * 更新状态
     *
     * @param status
     */
    private void updateStatus(Status status) {
        messageTxt.setText(status.strId);
        messageTxt.setTextColor(getResources().getColor(status.colorId));
        switch (status) {
            case DEFAULT:
                lockPatternView.setPattern(LockPatternView.DisplayMode.DEFAULT);
                break;
            case ERROR:
                lockPatternView.setPattern(LockPatternView.DisplayMode.ERROR);
                lockPatternView.postClearPatternRunnable(DELAYTIME);
                break;
            case CORRECT:
                lockPatternView.setPattern(LockPatternView.DisplayMode.DEFAULT);
                loginGestureSuccess();
                break;
        }
    }

    /**
     * 手势成功
     */
    private void loginGestureSuccess() {

        dismiss();
    }

    public static class GesturePwdCheckEvent {
    }

    private enum Status {
        //默认的状态
        DEFAULT(R.string.gesture_default, R.color.textDefualt3),
        //密码输入错误
        ERROR(R.string.gesture_error, R.color.home_menu_red),
        //密码输入正确
        CORRECT(R.string.gesture_correct, R.color.textDefualt3);

        private Status(int strId, int colorId) {
            this.strId = strId;
            this.colorId = colorId;
        }

        private int strId;
        private int colorId;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        group.removeView(view);
    }

    public void show(FragmentManager manager, String tag) {
        if (!dismissed) {
            return;
        }
        dismissed = false;
        FragmentTransaction ft = manager.beginTransaction();
        ft.add(this, tag);
        ft.addToBackStack(null);
        ft.commit();
    }

    public void dismiss() {
        if (dismissed) {
            return;
        }
        dismissed = true;
        if (getFragmentManager() != null) {
            getFragmentManager().popBackStack();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.remove(this);
            ft.commit();
        }
    }


}
