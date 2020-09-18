package com.windmill.common.util;

import java.util.regex.Pattern;

/**
 * @Author LHR
 * Create By 2017/8/22
 */
public class Constants {

    public static String UPLOAD_PATH = "";
    //Upload常量
    public static final String UPLOAD_PATH_WINDOWS = "D:\\upload\\";
    public static final String UPLOAD_PATH_LINUX = "/opt/upload/bbs/";

    public static String STATIC_URL = "";
    public static final String STATIC_URL_WINDOWS = "http://192.168.1.185:8087/dowload/";
    public static final String STATIC_URL_LINUX = "http://172.17.50.200/front/images/";

    public static String DOWLOAD = "dowload";
    public static String DOWLOAD_REALPATH = "file:D:\\upload\\";

    //    上传的附件地址
    public static String UPLOADFILE = "uploadFile";
    //    上传是返回错误信息的地址
    public static String ERRORMSG = "errorMsg";
    //    导出
    public static String EXPORT = "export";


    static {
        String osName = System.getProperty("os.name");
        if (Pattern.matches("Linux.*", osName)) {
            UPLOAD_PATH = UPLOAD_PATH_LINUX;
            STATIC_URL = STATIC_URL_LINUX;
        } else if (Pattern.matches("Windows.*", osName)) {
            UPLOAD_PATH = UPLOAD_PATH_WINDOWS;
            STATIC_URL = STATIC_URL_WINDOWS;
        }
    }


}
