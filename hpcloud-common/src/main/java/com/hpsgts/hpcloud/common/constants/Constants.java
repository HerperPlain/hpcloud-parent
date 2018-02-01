package com.hpsgts.hpcloud.common.constants;

/**
 * @author 黄朴（Herper.Plain）
 * @Date 2018/2/1 下午12:10
 */
public class Constants {
    public static final String CODE = "code";
    public static final String MESSAGE = "message";
    public static final String SUCCESSED = "successed";
    public static final String DATA = "data";

    /**
     * UTF-8编码
     */
    public static final String UTF8 = "UTF-8";

    public static final String DES_PRIVATE_ENCRYPT_KEY ="KfdF23De4FkiRvOrPPPPRIVA";
    public static final String FILEPATH = "files";

    public static final String checkQQ = "[1-9][0-9]{4,11}";	//QQ正则
    public static final String checkEmail = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";//邮箱正则
    public static final String checkWechat = "^[a-zA-Z0-9_-]{5,20}$";//微信正则
    public static final String checkTel = "^[0][0-9]{9,12}$";//座机正则
    public static final String checkCard = "^[1-9]\\d{5}[1-9]\\d{3}((0\\[1-9]))|((1[0-2]))(([0\\[1-9]|1\\d|2\\d])|3[0-1])\\d{3}([0-9]|x|X){1}$";

}
