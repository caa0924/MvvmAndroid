package fule.com.playandroidkotlin.ui.model

/**
 * 作者： njb
 * 时间： 2018/8/10 0010-上午 10:29
 * 描述：
 * 来源：
 */
class ProjectTitleModel {


    var data: List<DataBean>? = null

    class DataBean {
        /**
         * children : []
         * courseId : 13
         * id : 294
         * name : 完整项目
         * order : 145000
         * parentChapterId : 293
         * visible : 0
         */

        var courseId: Int = 0
        var id: Int = 0
        var name: String? = null
        var order: Int = 0
        var parentChapterId: Int = 0
        var visible: Int = 0
        var children: List<*>? = null
        var isSelect:Boolean = false
    }
}
