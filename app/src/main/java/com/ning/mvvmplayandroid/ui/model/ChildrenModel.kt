package fule.com.playandroidkotlin.ui.model

import android.os.Parcel
import android.os.Parcelable

/**
 * 作者： njb
 * 时间： 2018/8/11 0011-下午 3:06
 * 描述：
 * 来源：
 */
class ChildrenModel() : Parcelable{
    /**
     * children : []
     * courseId : 13
     * id : 60
     * name : Android Studio相关
     * order : 1000
     * parentChapterId : 150
     * visible : 1
     */

    var courseId: Int = 0
    var id: Int = 0
    var name: String? = null
    var order: Int = 0
    var parentChapterId: Int = 0
    var visible: Int = 0
    var children: List<*>? = null
    var isSelect:Boolean = false

    constructor(parcel: Parcel) : this() {
        courseId = parcel.readInt()
        id = parcel.readInt()
        name = parcel.readString()
        order = parcel.readInt()
        parentChapterId = parcel.readInt()
        visible = parcel.readInt()
        isSelect = parcel.readByte() != 0.toByte()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(courseId)
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeInt(order)
        parcel.writeInt(parentChapterId)
        parcel.writeInt(visible)
        parcel.writeByte(if (isSelect) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ChildrenModel> {
        override fun createFromParcel(parcel: Parcel): ChildrenModel {
            return ChildrenModel(parcel)
        }

        override fun newArray(size: Int): Array<ChildrenModel?> {
            return arrayOfNulls(size)
        }
    }


}


