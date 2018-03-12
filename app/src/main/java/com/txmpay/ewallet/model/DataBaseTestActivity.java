package com.txmpay.ewallet.model;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.txmpay.ewallet.R;
import com.txmpay.ewallet.app.MyApp;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.objectbox.Box;

public class DataBaseTestActivity extends AppCompatActivity {

    private final String TAG="czh";

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.button)
    Button button;
    @BindView(R.id.button2)
    Button button2;
    @BindView(R.id.button3)
    Button button3;
    @BindView(R.id.button4)
    Button button4;
    @BindView(R.id.button5)
    Button button5;
    @BindView(R.id.button6)
    Button button6;
    @BindView(R.id.fab)
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_base_test);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @OnClick({R.id.button, R.id.button2, R.id.button3, R.id.button4, R.id.button5, R.id.button6, R.id.fab})
    public void onViewClicked(View view) {
        Box<UserSetting> userBox= MyApp.getBoxStore().boxFor(UserSetting.class);
        UserSetting userSetting=new UserSetting();
        userSetting.setUid(1234);
        userSetting.setFinger("111");
        userSetting.setGesture("111");
        userSetting.setGestureCheckCount(111);
        userSetting.setIsFingerPrint(111);
        userSetting.setIsGesture(111);
        switch (view.getId()) {
            case R.id.button:
                Log.d(TAG, "add");
                userBox.put(userSetting);
                break;
            case R.id.button2:
                Log.d(TAG, "delete");
                userBox.remove(1234);
                break;
            case R.id.button3:
                Log.d(TAG, "query");
                UserSetting qU=userBox.get(1234);
                Log.d(TAG, qU.getFinger());
                Log.d(TAG, "newstr"+qU.getNewstr());
                break;
            case R.id.button4:
                Log.d(TAG, "upgrade");
                userSetting.setFinger("222");
                userBox.put(userSetting);
                break;
            case R.id.button5:
                Log.d(TAG, "add new");
                userSetting.setUid(1235);
                userSetting.setNewstr("1235");
                userSetting.setNewint(222);
                userBox.put(userSetting);
                break;
            case R.id.button6:
                Log.d(TAG, "query new");
                UserSetting newuser=userBox.get(1235);
                Log.d(TAG, newuser.getNewstr());
                break;
            case R.id.fab:
                break;
        }
    }
}
