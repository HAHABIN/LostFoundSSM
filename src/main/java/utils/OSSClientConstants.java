package utils;

/**
 * @author HABIN
 * @date 2020/2/29 23:59
 */
public class OSSClientConstants {
    //阿里云API的外网域名
    public static final String ENDPOINT = "oss-cn-beijing.aliyuncs.com";//"oss-cn-shanghai.aliyuncs.com";
    //阿里云API的密钥Access Key ID
    public static final String ACCESS_KEY_ID = "LTAI4FmCg7YJm4ZQk1xZPmxw";
    //阿里云API的密钥Access Key Secret
    public static final String ACCESS_KEY_SECRET = "mA7nspJWV1cccYu9MGvvaM9NFRiMYQ";
    //阿里云API的bucket名称
    public static final String BACKET_NAME = "habin-picload";//"uploadpicture";
    //阿里云API的文件夹名称
    public static final String FOLDER = "upload/image/";
    //阿里云API图片访问前缀
    public static final String HTTPADDRES = "http://"+BACKET_NAME+"."+ENDPOINT+"/";

}
