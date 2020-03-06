package utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
    /**
     * 将Json格式的字符串转换成指定对象组成的List返回
     * <br>例如：List<"String"> list = json2List("……", new TypeToken<"List<"String">">(){});
     * <br>     List<"Map<"Integer, Object">"> maplist = json2List("……", new TypeToken<"List<"Map<"Integer, Object">">">(){});
     *
     * @param <T>        泛型标识
     * @param jsonString JSON数据格式字符串
     * @param typeToken  目标类型器，标识需要转换成的目标List对象
     * @return
     */
    public static <T> List<T> fromJson(String jsonString, TypeToken<List<T>> typeToken) {
        Type type = typeToken.getType();
        Gson gson = new Gson();
        return gson.fromJson(jsonString, type);
    }
}
