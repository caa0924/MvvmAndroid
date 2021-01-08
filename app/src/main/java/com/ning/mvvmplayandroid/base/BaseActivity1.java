package com.ning.mvvmplayandroid.base;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ning.mvvmplayandroid.R;
import com.ning.mvvmplayandroid.ui.view.LoadingDialog;

/**
 * @author: njb
 * @date: 2020/1/18 0018 14:39
 * @desc:
 */
public class BaseActivity1 extends AppCompatActivity {
    private String TAG = "";
    protected boolean isActive;
    private LoadingDialog dialog;
    protected boolean mCheckNetWork = true; //默认检查网络状态
    public static final String TYPE = "type";
    public static final String NO = "no";
    private Context context;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
    }


    protected void setStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0及以上
            View decorView = getWindow().getDecorView();
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.4到5.0
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
        //修改字体颜色
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//android6.0以后可以对状态栏文字颜色和图标进行修改
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    public void toast(String msg) {
        if (!TextUtils.isEmpty(msg)) {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public float getwindowwith() {
        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();

        float x = display.getWidth();
        return x;
    }


    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isActive = false;
    }


    private void hasNetWork(boolean has) {
        if (isCheckNetWork()) {
            if (has) {
                reConnect();
            } else {
//                toast("fjklasdfdkla");
            }
        }
    }

    protected void reConnect() {
    }

    public void setCheckNetWork(boolean checkNetWork) {
        mCheckNetWork = checkNetWork;
    }

    public boolean isCheckNetWork() {
        return mCheckNetWork;
    }

    /**
     * 显示软键盘
     *
     * @param view
     */
    public void showSoftInput(View view) {
        if (null == view) {
            return;
        }
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        view.requestFocus();
        imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
    }

    /**
     * 隐藏软键盘
     */
    public void hideSoftInput() {
        if (null == getCurrentFocus()) {
            return;
        }
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager
                .HIDE_NOT_ALWAYS);
    }


    /**
     * 显示加载动画
     */
    public void showLoadingDialog() {

        if (dialog != null && dialog.isShowing()) {
            return;
        }
        if (null == dialog) {
            dialog = new LoadingDialog(context, R.style.dialogActive);
        }
        dialog.setCancelable(false);
        dialog.show();

    }

    /**
     * 隐藏加载动画
     */
    public void closeLoadingDialog() {

        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }
}
