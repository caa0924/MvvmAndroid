package com.ning.mvvmplayandroid.util;

import android.content.Context;
import android.content.SharedPreferences;

import fule.com.playandroidkotlin.base.App;

/**
 * 用户信息存储管理类
 */
@SuppressWarnings("ALL")
public class UserInfoManager {
    private static final String USER_INFO_FILE = "userInfo";

    /**
     * 设置用户登录状态
     *
     * @param isLogin
     */
    public static void setIsLogin(Boolean isLogin) {
        SharedPreferences sharedPreferences = (App.Companion.getContext()).getSharedPreferences(USER_INFO_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isLogin", isLogin);
        editor.apply();
    }

    /**
     * 获取用户登录状态
     *
     * @return
     */
    public static Boolean getIsLogin() {
        SharedPreferences sharedPreferences = App.Companion.getContext().getSharedPreferences(USER_INFO_FILE, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean("isLogin", false);
    }

    /**
     * 保存用户手机号
     *
     * @param phone
     */
    public static void saveUserPhone(String phone) {
        SharedPreferences sharedPreferences = App.Companion.getContext().getSharedPreferences(USER_INFO_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("phone", phone);
        editor.apply();
    }

    /**
     * 获取用户手机号
     *
     * @return
     */
    public static String getUserPhone() {
        SharedPreferences sharedPreferences = App.Companion.getContext().getSharedPreferences(USER_INFO_FILE, Context.MODE_PRIVATE);
        return sharedPreferences.getString("phone", "");
    }

   /* *//**
     * 保存用户登录后的主要信息
     *
     * @param userData
     *//*
    public static void saveUserInfo(UserData userData) {
        SharedPreferences sharedPreferences = App.Companion.getContext().getSharedPreferences(USER_INFO_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isLogin", true);
        editor.putString("phone", userData.getPhone());
        editor.putInt("id", userData.getId());
        editor.putString("token", userData.getToken());
        editor.apply();
    }*/

    /**
     * 移除用户主要信息
     */
    public static void removeUserInfo() {
        SharedPreferences sharedPreferences = App.Companion.getContext().getSharedPreferences(USER_INFO_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isLogin", false);
        editor.remove("id");
        editor.putString("token", "");
        editor.remove("phone");

        removeUserDetailInfo();
        editor.apply();
    }

/*    *//**
     * 保存用户的信息详情
     *
     * @param infoData
     *//*
    public static void saveUserDetailInfo(UserInfoData infoData) {
        SharedPreferences sharedPreferences = App.Companion.getContext().getSharedPreferences(USER_INFO_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("nickname", infoData.getNickname());
        editor.putInt("sex", infoData.getSex());
        editor.putString("signature", infoData.getSignature());
        editor.apply();
    }*/

    /**
     * 移除用户详细信息
     */
    public static void removeUserDetailInfo() {
        SharedPreferences sharedPreferences = App.Companion.getContext().getSharedPreferences(USER_INFO_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("nickname");
        editor.remove("sex");
        editor.remove("signature");
        editor.apply();
    }

    /**
     * 保存用户昵称
     *
     * @param nickname
     */
    public static void saveUserNickname(String nickname) {
        SharedPreferences sharedPreferences = App.Companion.getContext().getSharedPreferences(USER_INFO_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("nickname", nickname);
        editor.apply();
    }

    /**
     * 保存用户签名信息
     *
     * @param signature
     */
    public static void saveUserSignature(String signature) {
        SharedPreferences sharedPreferences = App.Companion.getContext().getSharedPreferences(USER_INFO_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("signature", signature);
        editor.apply();
    }

    /**
     * 保存用户性别
     *
     * @param sex
     */
    public static void saveUserSex(int sex) {
        SharedPreferences sharedPreferences = App.Companion.getContext().getSharedPreferences(USER_INFO_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("sex", sex);
        editor.apply();
    }

    /**
     * 获取用户昵称
     *
     * @return
     */
    public static String getUserNickname() {
        SharedPreferences sharedPreferences = App.Companion.getContext().getSharedPreferences(USER_INFO_FILE, Context.MODE_PRIVATE);
        return sharedPreferences.getString("nickname", "");
    }

    /**
     * 获取用户签名信息
     *
     * @return
     */
    public static String getUserSignature() {
        SharedPreferences sharedPreferences = App.Companion.getContext().getSharedPreferences(USER_INFO_FILE, Context.MODE_PRIVATE);
        return sharedPreferences.getString("signature", "");
    }

    /**
     * 获取用户性别
     *
     * @return
     */
    public static int getUserSex() {
        SharedPreferences sharedPreferences = App.Companion.getContext().getSharedPreferences(USER_INFO_FILE, Context.MODE_PRIVATE);
        return sharedPreferences.getInt("sex", 1);
    }

    /**
     * 获取用户Id
     *
     * @return
     */
    public static int getUserId() {
        SharedPreferences sharedPreferences = App.Companion.getContext().getSharedPreferences(USER_INFO_FILE, Context.MODE_PRIVATE);
        return sharedPreferences.getInt("id", -1);
    }

    /**
     * 获取用户token
     *
     * @return
     */
    public static String getUserToken() {
        SharedPreferences sharedPreferences = App.Companion.getContext().getSharedPreferences(USER_INFO_FILE, Context.MODE_PRIVATE);
        return sharedPreferences.getString("token", "");
    }

    /**
     * 保存用户token
     *
     * @param token
     */
    public static void saveUserToken(String token) {
        SharedPreferences sharedPreferences = App.Companion.getContext().getSharedPreferences(USER_INFO_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("token", token);
        editor.apply();
    }

    /**
     * 保存用户临时token
     *
     * @param token
     */
    public static void saveTempToken(String token) {
        SharedPreferences sharedPreferences = App.Companion.getContext().getSharedPreferences(USER_INFO_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("tempToken", token);
        editor.apply();
    }

    /**
     * 获取用户临时token
     *
     * @return
     */
    public static String getTempToken() {
        SharedPreferences sharedPreferences = App.Companion.getContext().getSharedPreferences(USER_INFO_FILE, Context.MODE_PRIVATE);
        return sharedPreferences.getString("tempToken", "");
    }

    /**
     * 移除用户临时token
     */
    public static void removeTempToken() {
        SharedPreferences sharedPreferences = App.Companion.getContext().getSharedPreferences(USER_INFO_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("tempToken");
        editor.apply();
    }



    /**
     * 保存充电模式
     *
     * @param model
     * @param modelValue
     */
    public static void saveChargeModel(int model, String modelValue) {
        SharedPreferences sharedPreferences = App.Companion.getContext().getSharedPreferences(USER_INFO_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("model", model);
        editor.putString("modelValue", modelValue);
        editor.apply();
    }

    public static int getChargeModel() {
        SharedPreferences sharedPreferences = App.Companion.getContext().getSharedPreferences(USER_INFO_FILE, Context.MODE_PRIVATE);
        return sharedPreferences.getInt("model", 0);
    }

    public static String getModelValue() {
        SharedPreferences sharedPreferences = App.Companion.getContext().getSharedPreferences(USER_INFO_FILE, Context.MODE_PRIVATE);
        return sharedPreferences.getString("modelValue", "");
    }

    public static void removeChargeModelAndValue() {
        SharedPreferences sharedPreferences = App.Companion.getContext().getSharedPreferences(USER_INFO_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("model");
        editor.remove("modelValue");
        editor.apply();
    }

    public static void saveQrCode(String qrCode) {
        SharedPreferences sharedPreferences = App.Companion.getContext().getSharedPreferences(USER_INFO_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("qrCode", qrCode);
        editor.apply();
    }

    public static String getQrCode() {
        SharedPreferences sharedPreferences = App.Companion.getContext().getSharedPreferences(USER_INFO_FILE, Context.MODE_PRIVATE);
        return sharedPreferences.getString("qrCode", "");
    }

    public static void removeQrCode() {
        SharedPreferences sharedPreferences = App.Companion.getContext().getSharedPreferences(USER_INFO_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("qrCode");
        editor.apply();
    }

    public static boolean saveIsCharging(Boolean charging, String phone) {
        SharedPreferences sharedPreferences = App.Companion.getContext().getSharedPreferences(USER_INFO_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(phone, charging);
        editor.apply();
        return false;
    }

    public static Boolean getIsCharging(String phone) {
        SharedPreferences sharedPreferences = App.Companion.getContext().getSharedPreferences(USER_INFO_FILE, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(phone, false);
    }

    public static void removeIsCharging(String phone) {
        SharedPreferences sharedPreferences = App.Companion.getContext().getSharedPreferences(USER_INFO_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(phone);
        editor.apply();
    }


    /**
     * 保存极光推送注册ID
     *
     * @param id
     */
    public static void saveRegistrationId(String registrationId) {
        SharedPreferences sharedPreferences = App.Companion.getContext().getSharedPreferences(USER_INFO_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("registrationId", registrationId);
        editor.apply();
    }

    /**
     * 获取极光推送注册ID
     *
     * @return
     */
    public static String getRegistrationId() {
        SharedPreferences sharedPreferences = App.Companion.getContext().getSharedPreferences(USER_INFO_FILE, Context.MODE_PRIVATE);
        return sharedPreferences.getString("registrationId", "");
    }
}
