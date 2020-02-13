package controller;

import entity.PersonInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.PersonInfoService;
import utils.HttpServletRequestUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
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
    @RequestMapping(value = "/updateInfo", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> loginCheck(HttpServletRequest request) {
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
}
