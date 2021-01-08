package com.ning.mvvmplayandroid.util;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ning.mvvmplayandroid.R;

/**
 * @作者: njb
 * @时间: 2020/1/9 16:10
 * @描述:
 */
public class LoadingDialogUtil {
    public static Dialog createLoadingDialog(Context context, String msg, boolean isCancle) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.loading_dialog, null);// 得到加载view

        LinearLayout layout = (LinearLayout) v.findViewById(R.id.dialog_view);// 加载布局
        // main.xml中的ImageView
        ProgressBar spaceshipImage = (ProgressBar) v.findViewById(R.id.img_load);
        TextView tipTextView = (TextView) v.findViewById(R.id.tipTextView);// 提示文字
        // 加载动画
        Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(context, R.anim.loading_animation);
        // 使用ImageView显示动画
        spaceshipImage.startAnimation(hyperspaceJumpAnimation);
        tipTextView.setText(msg);// 设置加载信息

        Dialog loadingDialog = new Dialog(context, R.style.dialogActive);// 创建自定义样式dialog
        loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));// 设置布局
        loadingDialog.setCancelable(true);
        loadingDialog.setCancelable(isCancle);// 返回键是否可用
        Window window = loadingDialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);
        //设置dialog的宽高为屏幕的宽高
        return loadingDialog;

    }

    /**
     * 显示加载框
     * @param dialog
     */
    public static void show(Dialog dialog) {
        if (dialog != null && !dialog.isShowing()) {
            Activity activity = dialog.getOwnerActivity();
            if(activity != null && !activity.isFinishing()){
                dialog.show();
            }
        }
    }

    /***
     * 关闭网络请求加载框
     *
     * @param dialog
     */
    public static void close(Dialog dialog) {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }
}
