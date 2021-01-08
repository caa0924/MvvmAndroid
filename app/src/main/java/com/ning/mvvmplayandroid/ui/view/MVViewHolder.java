package com.ning.mvvmplayandroid.ui.view;

import android.view.ViewGroup;

import androidx.databinding.ViewDataBinding;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

/**
 * @作者: njb
 * @时间: 2020/1/13 17:45
 * @描述:
 */
public class MVViewHolder<T extends ViewDataBinding> extends BaseViewHolder {
    T binding = null;

    public MVViewHolder(T binding) {
        super(binding.getRoot());
        binding.getRoot().setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        this.binding = binding;
    }

    public BaseViewHolder setQAdapter(BaseQuickAdapter adapter) {
        super.setAdapter(adapter);
        return this;
    }

    public T getDataViewBinding() {
        return this.binding;
    }
}
