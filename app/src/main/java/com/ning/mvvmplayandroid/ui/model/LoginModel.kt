package fule.com.playandroidkotlin.ui.model

/**
 * 作者： njb
 * 时间： 2018/10/26 0026-下午 4:48
 * 描述：
 * 来源：
 */
class LoginModel {

    /**
     * chapterTops : []
     * collectIds : [3249]
     * email :
     * icon :
     * id : 8912
     * password : 123456
     * token :
     * type : 0
     * username : anxt12
     */

    var email: String? = null
    var icon: String? = null
    var id: Int = 0
    var password: String? = null
    var token: String? = null
    var type: Int = 0
    var username: String? = null
    var chapterTops: List<*>? = null
    var collectIds: List<Int>? = null

    override fun toString(): String {
        return "LoginModel(email=$email, icon=$icon, id=$id, password=$password, token=$token, type=$type, username=$username, chapterTops=$chapterTops, collectIds=$collectIds)"
    }

}
