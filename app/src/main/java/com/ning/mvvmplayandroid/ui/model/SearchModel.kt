package fule.com.playandroidkotlin.ui.model

/**
 * 作者： njb
 * 时间： 2018/8/11 0011-下午 5:08
 * 描述：
 * 来源：
 */
class SearchModel {

        var datas: List<DataBean>? = null
        class DataBean {
            /**
             * apkLink :
             * author : longforus
             * chapterId : 391
             * chapterName : AS插件
             * collect : false
             * courseId : 13
             * desc : JetBrains IDEA/Android Studio MVP模版代码生成插件
             * envelopePic : http://www.wanandroid.com/resources/image/pc/default_project_img.jpg
             * fresh : false
             * id : 3137
             * link : http://www.wanandroid.com/blog/show/2221
             * niceDate : 2018-07-13
             * origin :
             * projectLink : https://github.com/longforus/MvpAutoCodePlus
             * publishTime : 1531489359000
             * superChapterId : 294
             * superChapterName : 开源项目主Tab
             * tags : [{"name":"项目","url":"/project/list/1?cid=391"}]
             * title : JetBrains IDEA/Android *Studio* MVP模版代码生成插件 MvpAutoCodePlus
             * type : 0
             * userId : -1
             * visible : 1
             * zan : 0
             */

            var apkLink: String? = null
            var author: String? = null
            var chapterId: Int = 0
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
            var tags: List<TagsBean>? = null

            class TagsBean {
                /**
                 * name : 项目
                 * url : /project/list/1?cid=391
                 */

                var name: String? = null
                var url: String? = null
            }
        }
}
