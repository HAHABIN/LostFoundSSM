package controller;

import com.aliyun.oss.OSSClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.misc.BASE64Decoder;
import utils.AliyunOSSClientUtil;
import utils.HttpServletRequestUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static utils.OSSClientConstants.BACKET_NAME;
import static utils.OSSClientConstants.FOLDER;

/**
 * @author HABIN
 * @date 2020/2/11 10:49
 * 上传图片保存服务器
 */
@Controller
@RequestMapping("/saveToImgByStr")
public class UpdatePhoto {

    /**
     * 更新用户信息
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/uploadPhoto", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> loginCheck(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        // 获取前端传递过来的
        String imgStr = HttpServletRequestUtil.getString(request, "imgStr");
        String resourceType = HttpServletRequestUtil.getString(request, "resourceType");


        //将接收的base64字符图片转化为字节数组
        BASE64Decoder decoder =  new BASE64Decoder();
        byte[] imageByte = null;
        try{
            imageByte = decoder.decodeBuffer(imgStr);
            for (int i = 0; i < imageByte.length; ++i) {
                if (imageByte[i] < 0) {// 调整异常数据
                    imageByte[i] += 256;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return modelMap;
        }

        if (imageByte.length>0) {
            try {
//                String lastpath = getLocalImg(imageByte);
                //初始化OSSClient
                OSSClient ossClient=AliyunOSSClientUtil.getOSSClient();
                String lastpath = AliyunOSSClientUtil.uploadObject1OSS(ossClient,imageByte,BACKET_NAME,FOLDER);
                modelMap.put("success", true);
                modelMap.put("message", "上传成功");
                modelMap.put("code", 1);
                modelMap.put("result",lastpath);
                modelMap.put("timestamp",new Date());
            } catch (Exception e) {
                e.printStackTrace();
                modelMap.put("success", false);
                modelMap.put("message", "上传失败");
                modelMap.put("code", 2);
                modelMap.put("timestamp",new Date());
            } finally {
            }
        }

        return modelMap;
    }

    private String getLocalImg(byte[] imageByte) throws IOException {
        //虚拟路径
        String rootpath = "upload/image";
        //获取文件上传的真实路径
        String localpath = "D:\\upload\\image";
        //保存目录文件的路径
        String filepath = "/" + createNewDir();
        //新建目录
        File destfile = new File(localpath + filepath);
        //如果文件目录不存在就新建
        if (!destfile.exists()) {
            destfile.mkdirs();
        }
        //文件新名称
        String fileNameNew = getFileNameNew() + ".jpeg";
        //
        File f = new File(destfile.getAbsoluteFile() + "/" + fileNameNew);
        // 将字符串转换成二进制，用于显示图片
        // 将上面生成的图片格式字符串 imgStr，还原成图片显示
        InputStream in = new ByteArrayInputStream(imageByte);
        FileOutputStream fos = new FileOutputStream(f);
        byte[] buf = new byte[1024];
        int length;
        length = in.read(buf, 0, buf.length);

        while (length != -1) {
            fos.write(buf,0,length);
            length = in.read(buf);
        }
        fos.flush();
        fos.close();
        in.close();
        String lastpath = rootpath + filepath + "/" + fileNameNew;
        System.out.println("返回图片路径：" + lastpath);
        return lastpath;
    }


    /**
     * 为文件重新命名，命名规则为当前系统时间毫秒数
     *
     * @return string
     */
    private static String getFileNameNew() {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return fmt.format(new Date());
    }

    /**
     * 以当前日期为名，创建新文件夹
     *
     * @return
     */
    private static String createNewDir() {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
        return fmt.format(new Date());
    }

}
