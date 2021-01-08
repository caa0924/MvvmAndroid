package com.ning.mvvmplayandroid.impl;

import com.ning.mvvmplayandroid.util.ACache;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import fule.com.playandroidkotlin.base.App;

/**
 * 作者： ch
 * 时间： 2018/6/13 0013-上午 9:43
 * 描述： 历史记录 实现
 * 来源：
 */


public class HistoryImpl {

    private static final String HISTORY_CACHE = "HISTORY_CACHE";


    /**
     * 获取缓存的对象
     *
     * @return
     */
    private static HistoryImplModel getHistoryModel() {
        ACache cache = ACache.get(App.Companion.getContext());
        HistoryImplModel implModel = (HistoryImplModel) cache.getAsObject(HISTORY_CACHE);
        return implModel;
    }

    /**
     * 保存
     *
     * @param model
     */
    private static void saveCartModel(HistoryImplModel model) {
        if (model != null) {
            model.setLastEditTime(System.currentTimeMillis());
        }
        ACache cache = ACache.get(App.Companion.getContext());
        cache.put(HISTORY_CACHE, model);
    }

    /**
     * 添加历史搜索
     *
     * @param text
     */
    public static void addHistory(String text) {
        HistoryImplModel model = getHistoryModel();
        if (model == null) {
            model = new HistoryImplModel();
        }
        if (model.getList() == null) {
            List<String> list = new ArrayList<>();
            list.add(text);
            model.setList(list);

        } else {
            //如果没有 则添加
            if (!model.getList().contains(text)) {
                model.getList().add(text);
            }
        }

        saveCartModel(model);
    }

    /**
     * 清空历史搜索
     */
    public static void clearHistory() {
        saveCartModel(null);
    }

    /**
     * 获取历史搜索列表
     *
     * @return
     */
    public static List<String> getHistoryList() {
        if (getHistoryModel() == null) {
            return null;
        }
        return getHistoryModel().getList();
    }

    public static class HistoryImplModel implements Serializable {
        List<String> list;
        private long lastEditTime;

        public List<String> getList() {
            return list;
        }

        public void setList(List<String> list) {
            this.list = list;
        }

        public long getLastEditTime() {
            return lastEditTime;
        }

        public void setLastEditTime(long lastEditTime) {
            this.lastEditTime = lastEditTime;
        }
    }

}
