package fule.com.playandroidkotlin.ui.model

/**
 * 作者： njb
 * 时间： 2018/8/10 0010-上午 11:48
 * 描述：
 * 来源：
 */
class ProjectListModel {
    var datas: List<DatasBean>? = null

    class DatasBean {
        /**
         * apkLink :
         * author : weidh1990
         * chapterId : 294
         * chapterName : 完整项目
         * collect : false
         * courseId : 13
         * desc : 根据鸿洋的wanandroid提供的api以及网上一些开源的wanandroid的android版本写了一个小程序版的wanandroid.
         * envelopePic : http://www.wanandroid.com/blogimgs/d1a42a86-9830-4603-aa30-f8ceaac0c4e3.png
         * fresh : false
         * id : 3235
         * link : http://www.wanandroid.com/blog/show/2274
         * niceDate : 2天前
         * origin :
         * projectLink : https://github.com/weidh1990/wanAndroid
         * publishTime : 1533633279000
         * superChapterId : 294
         * superChapterName : 开源项目主Tab
         * tags : [{"name":"项目","url":"/project/list/1?cid=294"}]
         * title : wanandroid 小程序版
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
             * url : /project/list/1?cid=294
             */

            var name: String? = null
            var url: String? = null
        }
        var isSelect:Boolean = false
    }

}
