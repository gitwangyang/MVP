package com.dotawang.mvp.view;

import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.TextView;

import com.dotawang.mvp.R;
import com.dotawang.mvp.base.BaseActivity;
import com.dotawang.mvp.iview.IMainView;
import com.dotawang.mvp.presenter.MainPresenter;
import com.dotawang.mvp.utils.ToastUtils;

public class MainActivity extends BaseActivity implements IMainView {

    private long mExitTime = 0;
    private TextView textView;
    private MainPresenter mainPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.tv);

        //初始化Presenter
        if (mainPresenter == null){
            mainPresenter = new MainPresenter();
        }
        //开始View引用
        mainPresenter.attachView(this);
    }

    @Override
    public void showData(String data) {
        textView.setText(data);
    }

    @Override
    public void showFailureMessage(String msg) {

    }

    @Override
    public void showErrorMessage() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //断开View引用
        mainPresenter.detachView();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0){
            exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 点击二次退出应用
     */
    public void exit(){
        if ((System.currentTimeMillis() - mExitTime)>2000){
            ToastUtils.showShort(getResources().getString(R.string.fish_app));
            mExitTime = System.currentTimeMillis();
        }else {
            finish();
            System.exit(0);
            android.os.Process.killProcess(android.os.Process.myPid());
        }
    }
}
