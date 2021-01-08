package com.ning.mvvmplayandroid.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

/**
 * @author: njb
 * @date: 2020/1/18 0018 12:57
 * @desc:
 */
abstract class BindingDateActivity<T extends ViewDataBinding> extends AppCompatActivity {

    /**
     * binding ,用于子类继承
     */
    protected T mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, getContentLayoutId());
        initData();
    }

    /**
     * 返回子类的 layoutId
     *
     * @return
     */
    protected abstract int getContentLayoutId();


    /**
     * 初始化数据
     */
    protected abstract void initData();


}
