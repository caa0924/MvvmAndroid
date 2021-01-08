package com.ning.mvvmplayandroid.util


import java.util.regex.Pattern
import java.util.regex.PatternSyntaxException

/**
 * 正则工具类 提供验证邮箱、手机号、电话号码、身份证号码、数字等方法
 * Created by Caelan on 2016/5/27.
 * Contact: caelan@foxmail.com
 */
object RegexUtils {

    /**
     * 验证Email
     *
     * @param email
     * email地址，格式：zhangsan@sina.com，zhangsan@xxx.com.cn，xxx代表邮件服务商
     * @return 验证成功返回true，验证失败返回false ^ ：匹配输入的开始位置。 \：将下一个字符标记为特殊字符或字面值。
     * ：匹配前一个字符零次或几次。 + ：匹配前一个字符一次或多次。 (pattern) 与模式匹配并记住匹配。 x|y：匹配 x 或
     * y。 [a-z] ：表示某个范围内的字符。与指定区间内的任何字符匹配。 \w ：与任何单词字符匹配，包括下划线。
     *
     * {n,m} 最少匹配 n 次且最多匹配 m 次 $ ：匹配输入的结尾。
     */
    fun checkEmail(email: String): Boolean {
        val regex = "^(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w{2,3}){1,3})$"
        return Pattern.matches(regex, email)
    }

    /**
     * 验证身份证号码
     *
     * @param idCard
     * 居民身份证号码15位或18位，最后一位可能是数字或字母
     * @return 验证成功返回true，验证失败返回false
     */
    fun checkIdCard(idCard: String): Boolean {
        val regex = "[1-9]\\d{13,16}[a-zA-Z0-9]"
        return Pattern.matches(regex, idCard)
    }

    /**
     * 验证手机号（精确）
     *
     *
     *
     * 移动：134(0-8)、135、136、137、138、139、147、150、151、152、157、158、159、178、182、183、184、187、188
     *
     * 联通：130、131、132、145、155、156、175、176、185、186
     *
     * 电信：133、153、173、177、180、181、189
     *
     * 全球星：1349
     *
     * 虚拟运营商：170
     */
    fun checkMobile(mobile: String): Boolean {
        val regex = "^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0,3,5-8])|(18[0-9])|(147))\\d{8}$"
        return Pattern.matches(regex, mobile)
    }

    /**
     * 验证固定电话号码
     *
     * @param phone
     * 电话号码，格式：国家（地区）电话代码 + 区号（城市代码） + 电话号码，如：+8602085588447
     *
     *
     * **国家（地区） 代码 ：**标识电话号码的国家（地区）的标准国家（地区）代码。它包含从 0 到 9
     * 的一位或多位数字， 数字之后是空格分隔的国家（地区）代码。
     *
     *
     *
     * **区号（城市代码）：**这可能包含一个或多个从 0 到 9 的数字，地区或城市代码放在圆括号——
     * 对不使用地区或城市代码的国家（地区），则省略该组件。
     *
     *
     *
     * **电话号码：**这包含从 0 到 9 的一个或多个数字
     *
     * @return 验证成功返回true，验证失败返回false
     */
    fun checkPhone(phone: String): Boolean {
        // String regex = "(\\+\\d+)?(\\d{3,4}\\-?)?\\d{7,8}$";
        val regex = "^1\\d{10}$"
        return Pattern.matches(regex, phone)
    }

    /**
     * 验证整数（正整数和负整数）
     *
     * @param digit
     * 一位或多位0-9之间的整数
     * @return 验证成功返回true，验证失败返回false
     */
    fun checkDigit(digit: String): Boolean {
        val regex = "\\-?[1-9]\\d+"
        return Pattern.matches(regex, digit)
    }

    /**
     * 验证整数和浮点数（正负整数和正负浮点数）
     *
     * @param decimals
     * 一位或多位0-9之间的浮点数，如：1.23，233.30
     * @return 验证成功返回true，验证失败返回false
     */
    fun checkDecimals(decimals: String): Boolean {
        val regex = "\\-?[1-9]\\d+(\\.\\d+)?"
        return Pattern.matches(regex, decimals)
    }

    /**
     * 验证空白字符
     *
     * @param blankSpace
     * 空白字符，包括：空格、\t、\n、\r、\f、\x0B
     * @return 验证成功返回true，验证失败返回false
     */
    fun checkBlankSpace(blankSpace: String): Boolean {
        val regex = "\\s+"
        return Pattern.matches(regex, blankSpace)
    }

    /**
     * 验证中文
     *
     * @param chinese
     * 中文字符
     * @return 验证成功返回true，验证失败返回false
     */
    fun checkChinese(chinese: String): Boolean {
        val regex = "^[\u4E00-\u9FA5]+$"
        return Pattern.matches(regex, chinese)
    }

    /**
     * 验证日期（年月日）
     *
     * @param birthday
     * 日期，格式：1992-09-03，或1992.09.03
     * @return 验证成功返回true，验证失败返回false
     */
    fun checkBirthday(birthday: String): Boolean {
        val regex = "[1-9]{4}([-./])\\d{1,2}\\1\\d{1,2}"
        return Pattern.matches(regex, birthday)
    }

    /**
     * 验证URL地址
     *
     * @param url
     * 格式：http://blog.csdn.net:80/xyang81/article/details/7705960? 或
     * http://www.csdn.net:80
     * @return 验证成功返回true，验证失败返回false
     */
    fun checkURL(url: String): Boolean {
        val regex =
            "(https?://(w{3}\\.)?)?\\w+\\.\\w+(\\.[a-zA-Z]+)*(:\\d{1,5})?(/\\w*)*(\\??(.+=.*)?(&.+=.*)?)?"
        return Pattern.matches(regex, url)
    }

    /**
     * 匹配中国邮政编码
     *
     * @param postcode
     * 邮政编码
     * @return 验证成功返回true，验证失败返回false
     */
    fun checkPostcode(postcode: String): Boolean {
        val regex = "[1-9]\\d{5}"
        return Pattern.matches(regex, postcode)
    }

    /**
     * 匹配IP地址(简单匹配，格式，如：192.168.1.1，127.0.0.1，没有匹配IP段的大小)
     *
     * @param ipAddress
     * IPv4标准地址
     * @return 验证成功返回true，验证失败返回false
     */
    fun checkIpAddress(ipAddress: String): Boolean {
        val regex =
            "[1-9](\\d{1,2})?\\.(0|([1-9](\\d{1,2})?))\\.(0|([1-9](\\d{1,2})?))\\.(0|([1-9](\\d{1,2})?))"
        return Pattern.matches(regex, ipAddress)
    }

    fun checkNickname(nickname: String): Boolean {
        val regex = "^[a-zA-Z0-9\u4E00-\u9FA5_]+$"
        return Pattern.matches(regex, nickname)
    }

    fun checkLoginName(loginName: String): Boolean {
        val regex = "^[a-zA-Z0-9_]+$"
        return Pattern.matches(regex, loginName)
    }

    fun checkPassword(password: String): Boolean {
        val regex = "^[a-zA-Z0-9_*-+=&@!~()<>?{}]+$"
        return Pattern.matches(regex, password)
    }

    /**
     * 只允许字母、数字和汉字
     * @param str
     * @return
     * @throws PatternSyntaxException
     */
    @Throws(PatternSyntaxException::class)
    fun stringFilter(str: String?): Boolean { // 只允许字母、数字和汉字
        val regEx = "^[a-zA-Z0-9\\u4e00-\\u9fa5]+$"
        return Pattern.matches(regEx, str)
    }

    fun hasCrossScriptRiskInAddress(str: String?): Boolean {
        var str = str
        val regex = "[`~!@#$%^&*+=|{}':;',\\[\\].<>~！@#￥%……&*——+|{}【】‘；：”“’。，、？-]"
        if (str != null) {
            str = str.trim { it <= ' ' }
            val p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE)
            val m = p.matcher(str)
            return m.find()
        }
        return false
    }
}