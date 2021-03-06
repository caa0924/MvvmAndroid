package com.ning.mvvmplayandroid.weight

import android.content.Context
import android.text.DynamicLayout
import android.text.StaticLayout
import androidx.appcompat.widget.AppCompatTextView
import java.lang.reflect.Field

/**
 *@author: njb
 *@date:   2020/2/26 0026 14:22
 *@desc:
 */
class MyTextView(context: Context?) : AppCompatTextView(context) {
    fun MyTextView(context: Context?) {
        val value: Context? = (context)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var layout: StaticLayout? = null
        var field: Field? = null
        try {
            val staticField = DynamicLayout::class.java!!.getDeclaredField("sStaticLayout")
            staticField.setAccessible(true)
            layout = staticField.get(DynamicLayout::class.java) as StaticLayout
        } catch (e: NoSuchFieldException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        }
        if (layout != null) {
            try {
                field = StaticLayout::class.java!!.getDeclaredField("mMaximumVisibleLineCount")
                field!!.isAccessible = true
                field.setInt(layout, maxLines)
            } catch (e: NoSuchFieldException) {
                e.printStackTrace()
            } catch (e: IllegalAccessException) {
                e.printStackTrace()
            }
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        if (layout != null && field != null) {
            try {
                field.setInt(layout, Integer.MAX_VALUE)
            } catch (e: IllegalAccessException) {
                e.printStackTrace()
            }
        }
    }
}