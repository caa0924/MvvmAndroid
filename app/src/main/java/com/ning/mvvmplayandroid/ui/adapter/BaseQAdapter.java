package com.ning.mvvmplayandroid.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ning.mvvmplayandroid.ui.view.MVViewHolder;

import java.util.List;

/**
 * @作者: njb
 * @时间: 2020/1/13 17:51
 * @描述:
 */
public abstract class BaseQAdapter<T, D extends ViewDataBinding, K extends BaseViewHolder> extends BaseQuickAdapter<T, K> {
    public BaseQAdapter(int layoutResId, @Nullable List<T> data) {
        super(layoutResId, data);
    }

    public BaseQAdapter(@Nullable List<T> data) {
        super(data);
    }

    public BaseQAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    public K onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType != LOADING_VIEW && viewType != HEADER_VIEW && viewType != EMPTY_VIEW && viewType != FOOTER_VIEW) {
            D d = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), this.mLayoutResId, null, false);
            d.executePendingBindings();
            MVViewHolder mvViewHolder = new MVViewHolder(d);
            bindViewClickListener(mvViewHolder);
            mvViewHolder.setQAdapter(this);
            return (K) mvViewHolder;
        } else {
            return super.onCreateViewHolder(parent, viewType);
        }
    }


    @Override
    protected void bindViewClickListener(final BaseViewHolder baseViewHolder) {
        if (baseViewHolder == null) {
            return;
        }
        final View view = baseViewHolder.itemView;
        if (view == null) {
            return;
        }
        if (getOnItemClickListener() != null) {
            view.setOnClickListener(v -> getOnItemClickListener().onItemClick(BaseQAdapter.this, v, baseViewHolder.getLayoutPosition() - getHeaderLayoutCount()));
        }
        if (getOnItemLongClickListener() != null) {
            view.setOnLongClickListener(v -> getOnItemLongClickListener().onItemLongClick(BaseQAdapter.this, v, baseViewHolder.getLayoutPosition() - getHeaderLayoutCount()));
        }
    }
}
