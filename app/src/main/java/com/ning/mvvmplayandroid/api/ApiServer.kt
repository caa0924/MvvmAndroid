package com.ning.mvvmplayandroid.api


import fule.com.playandroidkotlin.base.BaseListModel
import fule.com.playandroidkotlin.base.BaseModel
import fule.com.playandroidkotlin.ui.model.*
import io.reactivex.Observable
import retrofit2.http.*

/**
 * 作者： ch
 * 时间： 2016/12/27.13:56
 * 描述：
 * 来源：
 */

interface ApiServer {

    //首页文章列表
    @GET("/article/list/{page}/json")
    fun articleList(@Path("page") page: Int): Observable<BaseModel<ArticleListModel>>

    //首页广告
    @GET("/banner/json")
    fun banner(): Observable<BaseListModel<BannerModel>>

    //常用网站
    @GET("/friend/json")
    fun friend(): Observable<BaseListModel<*>>

    //搜索热词
    @GET("/hotkey/json")
    fun hotkey(): Observable<BaseListModel<HotKeyModel>>

    //体系数据
    @GET("/tree/json")
    fun tree(): Observable<BaseListModel<KnowledgeModel.DataBean>>

    //知识体系下的文章
    @GET("article/list/{page}/json")
    fun article(@Path("page") page: Int,@Query("cid") cid: Int): Observable<BaseModel<ArticleListModel>>

    //导航
    @GET("/navi/json")
    fun navi(): Observable<BaseListModel<NavigationModel.DataBean>>

    //导航分类
    @GET("/navi/json")
    fun navicontent(): Observable<BaseListModel<NavigationModel.DataBean>>

    //项目分类
    @GET("/project/tree/json")
    fun projectTree(): Observable<BaseListModel<ProjectTitleModel.DataBean>>

    //项目分类下的列表
    @GET("/project/list/{page}/json")
    fun projectList(@Path("page") page: Int,@Query("cid") cid:Int): Observable<BaseModel<ProjectListModel>>

    //登录
    @POST("/user/login")
    @FormUrlEncoded
    fun login(@Field("username") username: String, @Field("password") password:String):Observable<BaseModel<LoginModel>>

    //注册
    @POST("/user/register")
    @FormUrlEncoded
    fun register(@Field("username") username: String, @Field("password") password: String, @Field("repassword") repassword: String) : Observable<BaseModel<RegisterModel>>

    //收藏文章列表
    @GET("/lg/collect/list/0/json")
    fun collect():Observable<BaseModel<*>>

    //收藏站内文章
    @POST("/lg/collect/1165/json")
    @FormUrlEncoded
    fun collectLg(@Path("id") id:Int):Observable<BaseModel<*>>

    //收藏站外文章
    @POST("/lg/collect/add/json")
    @FormUrlEncoded()
    fun collectadd():Observable<BaseModel<*>>

   //取消收藏
   @POST("/lg/uncollect_originId/2333/json")
   @FormUrlEncoded
    fun uncollect(@Path("id") id:Int):Observable<BaseModel<*>>

    //我的收藏
    @POST("/lg/uncollect/2805/json")
    @FormUrlEncoded
    fun mycollect(@Path("id") id: Int,@Path("orignId") orignId:Int ) :Observable<BaseModel<*>>

    //收藏网站列表
    @GET("/lg/collect/usertools/json")
    fun usertools():Observable<BaseModel<*>>


    //收藏网址
    @POST("/lg/collect/addtool/json")
    @FormUrlEncoded
    fun addcollect(@Path("name") name: Int,@Path("link") link: Int):Observable<BaseModel<*>>

    //编辑收藏网站
    @POST("/lg/collect/updatetool/json")
    @FormUrlEncoded
    fun updatetool(@Path("id") id: Int,@Path("name") name:Int,@Path("link") liml:Int):Observable<BaseModel<*>>

    //删除收藏网站
    @POST("/lg/collect/deletetool/json")
    @FormUrlEncoded
    fun deletetool(@Path("id") id: Int):Observable<BaseModel<*>>

    //搜索
    @POST("/article/query/{page}/json")
    @FormUrlEncoded
    fun searchResult(@Path("page") page: Int, @Field("k") keyword:String):Observable<BaseModel<SearchModel>>

    //新增一个TODO
    @POST("/lg/todo/add/json")
    @FormUrlEncoded
    fun add(@Path("title") title:Int,@Path("content") content:Int,@Path("date") date:Int,@Path("type") type: Int):Observable<BaseModel<*>>

    //更新一条todo内容
    @POST("/lg/todo/update/83/json")
    @FormUrlEncoded
    fun update(@Path("id") id: Int,@Path("title") title: Int,@Path("content") content: Int,@Path("date") date: Int,
               @Path("status") status: Int,@Path("type") type: Int):Observable<BaseModel<*>>

    //删除一条todo
    @POST("/lg/todo/delete/83/json")
    @FormUrlEncoded
    fun delete(@Path("id") id: Int, @Path("status") status: Int):Observable<BaseModel<*>>

    //未完成TODO列表
    @POST("/lg/todo/listnotdo/")
    @FormUrlEncoded
    fun listnotdo(@Path("type") type: Int,@Path("page")page: Int):Observable<BaseModel<*>>

    //已完成TODO列表
    @POST("/lg/todo/listdone/")
    @FormUrlEncoded
    fun listdone(@Path("type") type:Int,@Path("page") page:Int):Observable<BaseModel<*>>

    // 仅更新完成状态Todo
    @POST("/lg/todo/done/80/json")
    @FormUrlEncoded
    fun done(@Path("id") id:Int):Observable<BaseModel<*>>

}
