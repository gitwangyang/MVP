package com.dotawang.mvp.view;

import android.os.Bundle;
import android.widget.TextView;

import com.dotawang.mvp.R;
import com.dotawang.mvp.base.BaseActivity;
import com.dotawang.mvp.presenter.MainPresenter;

public class MainActivity extends BaseActivity implements MainView {

    private TextView textView;
    private MainPresenter mainPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.tv);

        //初始化Presenter
        mainPresenter = new MainPresenter();
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
}
