package fule.com.playandroidkotlin.ui.model

import android.os.Parcel
import android.os.Parcelable

/**
 * 作者： njb
 * 时间： 2018/8/9 0009-下午 5:28
 * 描述：
 * 来源：
 */
class KnowledgeModel() : Parcelable {
    var data: List<DataBean>? = null

    constructor(parcel: Parcel) : this() {
        data = parcel.createTypedArrayList(DataBean)
    }


    class DataBean() : Parcelable {
        /**
         * children : [{"children":[],"courseId":13,"id":60,"name":"Android Studio相关","order":1000,"parentChapterId":150,"visible":1},{"children":[],"courseId":13,"id":169,"name":"gradle","order":1001,"parentChapterId":150,"visible":1},{"children":[],"courseId":13,"id":269,"name":"官方发布","order":1002,"parentChapterId":150,"visible":1}]
         * courseId : 13
         * id : 150
         * name : 开发环境
         * order : 1
         * parentChapterId : 0
         * visible : 1
         */

        var courseId: Int = 0
        var id: Int = 0
        var name: String? = null
        var order: Int = 0
        var parentChapterId: Int = 0
        var visible: Int = 0
        var children: List<ChildrenModel>? = null
        var isSelect: Boolean = false

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

        companion object CREATOR : Parcelable.Creator<DataBean> {
            override fun createFromParcel(parcel: Parcel): DataBean {
                return DataBean(parcel)
            }

            override fun newArray(size: Int): Array<DataBean?> {
                return arrayOfNulls(size)
            }
        }
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeTypedList(data)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<KnowledgeModel> {
        override fun createFromParcel(parcel: Parcel): KnowledgeModel {
            return KnowledgeModel(parcel)
        }

        override fun newArray(size: Int): Array<KnowledgeModel?> {
            return arrayOfNulls(size)
        }
    }

}
