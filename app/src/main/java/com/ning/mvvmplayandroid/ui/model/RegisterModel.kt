package fule.com.playandroidkotlin.ui.model

/**
 * 作者： njb
 * 时间： 2018/10/26 0026-下午 4:57
 * 描述：
 * 来源：
 */
class RegisterModel {

    /**
     * chapterTops : []
     * collectIds : []
     * email :
     * icon :
     * id : 12109
     * password : 123456
     * token :
     * type : 0
     * username : zhaomao
     */

    var email: String? = null
    var icon: String? = null
    var id: Int = 0
    var password: String? = null
    var token: String? = null
    var type: Int = 0
    var username: String? = null
    var chapterTops: List<*>? = null
    var collectIds: List<*>? = null

    override fun toString(): String {
        return "RegisterModel{" +
                "email='" + email + '\''.toString() +
                ", icon='" + icon + '\''.toString() +
                ", id=" + id +
                ", password='" + password + '\''.toString() +
                ", token='" + token + '\''.toString() +
                ", type=" + type +
                ", username='" + username + '\''.toString() +
                ", chapterTops=" + chapterTops +
                ", collectIds=" + collectIds +
                '}'.toString()
    }
}
