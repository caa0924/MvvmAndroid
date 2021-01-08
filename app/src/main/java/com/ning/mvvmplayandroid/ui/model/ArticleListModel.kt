package fule.com.playandroidkotlin.ui.model

/**
 * 作者： njb
 * 时间： 2018/8/6 0006-下午 3:29
 * 描述： 文章列表实体类
 * 来源：
 */
class ArticleListModel {
    var datas: MutableList<DatasBean>? = null
    class DatasBean {
        /**
         * apkLink :
         * author : Jetictors
         * chapterId : 232
         * chapterName : 入门及知识点
         * collect : false
         * courseId : 13
         * desc :
         * envelopePic :
         * fresh : true
         * id : 3226
         * link : http://www.cnblogs.com/Jetictors/tag/Kotlin/
         * niceDate : 4小时前
         * origin :
         * projectLink :
         * publishTime : 1533522956000
         * superChapterId : 232
         * superChapterName : Kotlin
         * tags : []
         * title : Kotlin 系列文章
         * type : 0
         * userId : -1
         * visible : 1
         * zan : 0
         */

        var apkLink: String? = null         // 文章uri
        var author: String? = null          //
        var chapterId: Int = 0              //
        var chapterName: String? = null
        var isCollect: Boolean = false
        var courseId: Int = 0
        var desc: String? = null
        var envelopePic: String? = null
        var isFresh: Boolean = false
        var id: Int = 0
        var link: String? = null
        var niceDate: String? = null
        var origin: String? = null
        var projectLink: String? = null
        var publishTime: Long = 0
        var superChapterId: Int = 0
        var superChapterName: String? = null
        var title: String? = null
        var type: Int = 0
        var userId: Int = 0
        var visible: Int = 0
        var zan: Int = 0
        var tags: List<*>? = null
        var isSelect: Boolean  = false
    }
}
