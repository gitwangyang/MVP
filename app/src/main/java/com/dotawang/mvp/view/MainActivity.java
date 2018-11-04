package com.dotawang.mvp.view;

import android.os.Bundle;
import android.os.Process;
import android.view.KeyEvent;
import android.widget.TextView;

import com.dotawang.mvp.R;
import com.dotawang.mvp.base.BaseActivity;
import com.dotawang.mvp.bean.BrandBean;
import com.dotawang.mvp.presenter.MainPresenter;
import com.dotawang.mvp.presenter.contract.MainContract;
import com.dotawang.mvp.utils.ToastUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends BaseActivity<MainPresenter, List<BrandBean>> implements MainContract.IMainView {

    @BindView(R.id.tv)
    TextView tv;
    private Unbinder unbinder;
    private long mExitTime = 0;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initToolbar() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        unbinder = ButterKnife.bind(this);

        //开始View引用
//        mainPresenter.attachView((IMainView) this);
    }

    /**
     * fragment切换
     */
//    public Fragment switchContent(int resId) {
//
//        Fragment fragment = (Fragment) mFragmentFactory.instantiateItem(
//                myFragment, resId);
//        mFragmentFactory.setPrimaryItem(myFragment, 0, fragment);
//        mFragmentFactory.finishUpdate(myFragment);
//        return fragment;
//    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        //断开View引用
//        mainPresenter.detachView();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 点击二次退出应用
     */
    public void exit() {
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            ToastUtils.showShort(getResources().getString(R.string.fish_app));
            mExitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
            Process.killProcess(Process.myPid());
        }
    }

}
