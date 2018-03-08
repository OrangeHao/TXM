package routeplan.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import routeplan.R;


/**
 * created by czh on 2018-03-07
 */

public class LoadingDialog extends Dialog {
    private static LoadingDialog loadDialog;
    private String tipMsg;
    private TextView mShowMessage;

    public LoadingDialog(Context ctx, boolean canNotCancel, String tipMsg) {
        super(ctx);
        this.tipMsg = tipMsg;
        this.setContentView(R.layout.dialog_loading);
        if(!TextUtils.isEmpty(this.tipMsg)) {
            this.mShowMessage = (TextView)this.findViewById(R.id.message);
            this.mShowMessage.setVisibility(View.VISIBLE);
            this.mShowMessage.setText(this.tipMsg);
        }

        Window window = this.getWindow();
        WindowManager.LayoutParams attributesParams = window.getAttributes();
        attributesParams.flags = 2;
        attributesParams.dimAmount = 0.5F;
        window.setAttributes(attributesParams);
        window.setLayout(-2, -2);
    }

    public static void show(Context context) {
        show(context, (String)null, false);
    }

    public static void show(Context context, String message) {
        show(context, message, false);
    }

    private static void show(Context context, String message, boolean isCancel) {
        if(!(context instanceof Activity) || !((Activity)context).isFinishing()) {
            if(loadDialog == null || !loadDialog.isShowing()) {
                loadDialog = new LoadingDialog(context, isCancel, message);
                loadDialog.show();
            }
        }
    }

    public static void dismiss(Context context) {
        try {
            if(context instanceof Activity && ((Activity)context).isFinishing()) {
                loadDialog = null;
                return;
            }

            if(loadDialog != null && loadDialog.isShowing()) {
                Context loadContext = loadDialog.getContext();
                if(loadContext != null && loadContext instanceof Activity && ((Activity)loadContext).isFinishing()) {
                    loadDialog = null;
                    return;
                }

                loadDialog.dismiss();
                loadDialog = null;
            }
        } catch (Exception var2) {
            var2.printStackTrace();
            loadDialog = null;
        }

    }
}
