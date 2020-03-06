package controller;

import com.google.gson.reflect.TypeToken;
import entity.PersonInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.PersonInfoService;
import utils.AliyunOSSClientUtil;
import utils.HttpServletRequestUtil;
import utils.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author HABIN
 * @date 2020/2/10 14:05
 * 用户信息控制层
 */
@Controller
@RequestMapping("/user")
public class PersonInfoController {

    @Autowired
    private PersonInfoService personInfoService;


    /**
     * 更新用户信息
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/updateInfo")
    @ResponseBody
    private Map<String, Object> updateInfo(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        // 获取前端传递过来要更新的数据
        int userId = HttpServletRequestUtil.getInt(request,"userId");
        String nickname = HttpServletRequestUtil.getString(request, "nickname");
        String profile_img = HttpServletRequestUtil.getString(request,"profileimg");
        String email = HttpServletRequestUtil.getString(request,"email");
        String gender = HttpServletRequestUtil.getString(request,"gender");
        int helpTimes = HttpServletRequestUtil.getInt(request,"helpTimes");
        PersonInfo personInfo = new PersonInfo(userId,nickname,profile_img,email,gender,new Date(),helpTimes);
        int i = personInfoService.updatePersonInfo(personInfo);
        if (i==1){
            modelMap.put("success", true);
            modelMap.put("message", "用户信息更新成功");
            modelMap.put("code", 1);
            modelMap.put("timestamp",new Date());
        } else {
            modelMap.put("success", false);
            modelMap.put("message", "用户信息更新失败");
            modelMap.put("code", 2);
            modelMap.put("timestamp",new Date());
        }
        return modelMap;
    }

    /**
     * 批量删除用户
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/batchdeleteUser", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> batchdeletePhoto(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        String DeleteIndexArr = HttpServletRequestUtil.getString(request, "DeleteIndexArr");
        int deleteFruit = 0;
        String[] ids = DeleteIndexArr.split(",");

        for (String id : ids) {
            long userId = Integer.parseInt(id);
            PersonInfo personInfo = personInfoService.queryPersonInfoById(userId);
            List<String> imgStrList = StringUtils.fromJson(personInfo.getProfileImg(), new TypeToken<List<String>>() {
            });
            for (String imgStr : imgStrList) {
                AliyunOSSClientUtil.deleteFile(imgStr);
            }
            int delete = personInfoService.delete(userId);
            if (delete==1){
                deleteFruit++;
            }
        }
        modelMap.put("success", true);
        modelMap.put("message", "删除成功");
        modelMap.put("code", 1);
        modelMap.put("DeleteCounts",deleteFruit);
        modelMap.put("timestamp", new Date());
        return modelMap;
    }

    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> deletePhoto(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        long userId = HttpServletRequestUtil.getLong(request, "userId");

        PersonInfo personInfo = personInfoService.queryPersonInfoById(userId);
        List<String> imgStrList = StringUtils.fromJson(personInfo.getProfileImg(), new TypeToken<List<String>>() {
        });
        for (String imgStr : imgStrList) {
            AliyunOSSClientUtil.deleteFile(imgStr);
        }
        int delete = personInfoService.delete(userId);
        if (delete==1) {
            modelMap.put("success", true);
            modelMap.put("message", "删除成功");
            modelMap.put("code", 1);
            modelMap.put("timestamp", new Date());
        } else {
            modelMap.put("success", false);
            modelMap.put("message", "删除失败");
            modelMap.put("code", 2);
            modelMap.put("timestamp", new Date());
        }

        return modelMap;
    }
}
