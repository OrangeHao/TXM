package com.txmpay.ewallet.wxapi;

import com.umeng.weixin.callback.WXCallbackActivity;


public class WXEntryActivity extends WXCallbackActivity {   //umeng只需这一行
//public class WXEntryActivity extends Activity implements IWXAPIEventHandler {
//
//    private static final int RETURN_MSG_TYPE_LOGIN = 1;
//    private static final int RETURN_MSG_TYPE_SHARE = 2;
//
//    @Override
//    protected void onCreate(Bundle bundle) {
//        super.onCreate(bundle);
//        Log.d("czh","onCreate");
//        WxTestActivity.wx_api.handleIntent(getIntent(),this);
//    }
//
//    @Override
//    public void onReq(BaseReq baseReq) {
//
//    }
//
//    @Override
//    public void onResp(BaseResp baseResp) {
//        Log.d("czh","errCode:"+baseResp.errCode+"   errStr"+baseResp.errStr);
//
//        switch (baseResp.errCode){
//            case BaseResp.ErrCode.ERR_AUTH_DENIED:
//            case BaseResp.ErrCode.ERR_USER_CANCEL:
//                Log.d("czh","faied");
//                break;
//            case BaseResp.ErrCode.ERR_OK:
//                Log.d("czh","succeed type:"+baseResp.getFrom());
//                switch (baseResp.getFrom()) {
//                    case RETURN_MSG_TYPE_LOGIN:    //登录
//                        //拿到了微信返回的code,立马再去请求access_token
//                        final String code = ((SendAuth.Resp) baseResp).code;
//                        Log.d("czh","code:"+code);
//                        new Thread(new Runnable() {
//                            @Override
//                            public void run() {
//                                try {
//                                    WxTestActivity.getWxToken(code);
//                                } catch (IOException e) {
//                                    e.printStackTrace();
//                                }
//                            }
//                        }).start();
//                        break;
//                    case RETURN_MSG_TYPE_SHARE:     //分享
//                        break;
//            }
//            break;
//        }
//    }
}
