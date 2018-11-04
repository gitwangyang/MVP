package com.dotawang.mvp.widget.loading;


import android.graphics.drawable.AnimationDrawable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.dotawang.mvp.R;


public class VaryViewHelperController {

    private IVaryViewHelper helper;

    private AnimationDrawable mDrawableAnim;

    private ImageView mImageView;

    public VaryViewHelperController(View view) {

        this(new VaryViewHelper(view));
    }

    public VaryViewHelperController(IVaryViewHelper helper) {
        super();
        this.helper = helper;
    }

    public void showNetworkError(View.OnClickListener onClickListener) {
        View layout = helper.inflate(R.layout.view_loadding_error);
        Button againBtn = (Button) layout.findViewById(R.id.pager_error_loadingAgain);
        if (null != onClickListener) {
            againBtn.setOnClickListener(onClickListener);
        }
        helper.showLayout(layout);
    }

    public void showEmpty(String emptyMsg) {
        View layout = helper.inflate(R.layout.view_loadding_no_data);
        TextView textView = (TextView) layout.findViewById(R.id.tv_no_data);
        if (!TextUtils.isEmpty(emptyMsg)) {
            textView.setText(emptyMsg);
        }
        helper.showLayout(layout);
    }

    public void showLoading() {
        View layout = helper.inflate(R.layout.view_loading_loading);
        helper.showLayout(layout);
    }

    public void restore() {
        helper.restoreView();
    }
}
