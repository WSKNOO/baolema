package com.cook.baolema.respdata;

/**
 * @author 徐亮
 * 返回数据的状态码
 */
public class Code {
    public static final Integer SAVE_OK = 20011;
    public static final Integer DELETE_OK = 20021;
    public static final Integer UPDATE_OK = 20031;
    public static final Integer GET_OK = 20041;
    public static final Integer LOGIN_OK = 20051;

    public static final Integer PHONENUMBER_NOT_EXIST = 20061;

    public static final Integer VERIFICATION_CODE_OK = 20071;

    public static final Integer SAVE_ERR = 20010;
    public static final Integer DELETE_ERR = 20020;
    public static final Integer UPDATE_ERR = 20030;
    public static final Integer GET_ERR = 20040;
    public static final Integer LOGIN_ERR = 20050;

    public static final Integer PHONENUMBER_EXIST = 20060;

    public static final Integer VERIFICATION_CODE_ERR = 20070;
    public static final Integer SYSTEM_UNKNOW_ERR = 59999;

    public static final Integer SYSTEM_TIMEOUT_ERR = 50002;

    public static final Integer BUSINESS_ERR = 60002;

    public static final Integer SYSTEM_ERR = 50001;



}
