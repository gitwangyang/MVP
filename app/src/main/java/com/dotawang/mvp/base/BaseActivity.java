package com.dotawang.mvp.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.dotawang.mvp.R;
import com.dotawang.mvp.utils.ToastUtils;

/**
 * Created by Dota.Wang on 2018/10/30.
 */

public abstract class BaseActivity extends AppCompatActivity implements IBaseView{

    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setCancelable(false);
    }

    @Override
    public void showLoading() {
        if (!mProgressDialog.isShowing()){
            mProgressDialog.show();
        }
    }

    @Override
    public void hideLoading() {
        if (mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void showToast(String msg) {
        ToastUtils.showShortSafe(msg);
    }

    @Override
    public void showErr() {
        ToastUtils.showShortSafe(getResources().getString(R.string.error_msg));
    }

    @Override
    public Context getContext() {
        return BaseActivity.this;
    }
}
