package com.ning.mvvmplayandroid.ui.view;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;

/**
 * @作者: njb
 * @时间: 2020/1/14 12:34
 * @描述: 图片显示工具类
 */
public class ImageUtil {
    @BindingAdapter("image_view")
    public static void setImageView(ImageView imageView, String urlString) {
        Glide.with(imageView.getContext()).load(urlString).into(imageView);
    }


    @BindingAdapter("home_image")
    public static void setHomeImage(ImageView imageView, String urlString) {
        Glide.with(imageView.getContext()).load("http://pic.3h3.com/up/2014-3/20143314140858312456.gif").into(imageView);
    }

    @BindingAdapter(("search_image"))
    public static void setSearchImage(ImageView imageView, String urlString) {
        Glide.with(imageView.getContext()).load(urlString).into(imageView);
    }

}
