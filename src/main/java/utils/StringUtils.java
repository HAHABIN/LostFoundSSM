package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author HABIN
 * @date 2020/2/19 22:20
 * 时间字符串转换
 */
public class StringUtils {
    /*
     * 将时间戳转换为时间
     *
     * s就是时间戳
     */

    public static String stampToDate(String s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //如果它本来就是long类型的,则不用写这一步
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }
    /**
     * String str = "2019-03-13 13:54:00";
     * 时间字符串转为时间戳
     * */
    public static long  dateToStamp(String str)  {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = simpleDateFormat.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();

    }
    /**
     * String str = "2019-03-13 13:54:00";
     * 时间字符串转为时间戳
     * */
    public static long  dateToStamp2(String str)  {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = simpleDateFormat.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();

    }
    /**
     * String str = "2019-03-13 13:54:00";
     * 时间字符串转为时间
     * */
    public static Date  strTodate(long str)  {
        Date date = new Date(str);
        return date;

    }

}
