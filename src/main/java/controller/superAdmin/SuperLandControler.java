package controller.superAdmin;

import entity.LocalAuth;
import entity.PersonInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.LocalAuthService;
import service.PersonInfoService;
import utils.HttpServletRequestUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author HABIN
 * @date 2020/3/3 15:58
 * 超级管理员控制层
 */
@Controller
@RequestMapping("/superadmin")
public class SuperLandControler {

    @Autowired
    private LocalAuthService localAuthService;

    @Autowired
    private PersonInfoService personInfoService;
    /**
     * 登录验证
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/logincheck", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> loginCheck(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        // 获取前端传递过来的帐号和密码
        String userName = HttpServletRequestUtil.getString(request, "username");
        String password = HttpServletRequestUtil.getString(request, "password");
        // 空值判断
        if (userName != null && password != null) {
            // 获取本地帐号授权信息
            LocalAuth localAuth = localAuthService.getLocalAuthByUsernameAndPwd(userName, password);
            if (localAuth != null) {
                // 若帐号密码正确，则验证用户的身份是否为超级管理员
                if (localAuth.getPersonInfo().getUserType() == 2) {
                    modelMap.put("success", true);
                    modelMap.put("message", "登录成功");
                    modelMap.put("code", 1);
                    modelMap.put("result", localAuth.getPersonInfo());
                    modelMap.put("timestamp", new Date());
//					// 登录成功则设置上session信息
                    //将用户对象添加到Session中
                    request.getSession().setAttribute("user", localAuth.getPersonInfo());
                } else {
                    modelMap.put("success", false);
                    modelMap.put("message", "非管理员没有权限访问");
                    modelMap.put("code", 10);
                    modelMap.put("timestamp", new Date());
                }
            } else {
                modelMap.put("success", false);
                modelMap.put("message", "用户名或密码错误");
                modelMap.put("code", 11);
                modelMap.put("timestamp", new Date());
            }
        } else {
            modelMap.put("success", false);
            modelMap.put("message", "用户名和密码均不能为空");
            modelMap.put("code", 12);
            modelMap.put("timestamp", new Date());
        }
        return modelMap;
    }

    @RequestMapping(value = "/logout")
    @ResponseBody
    public String logout(HttpServletRequest request) {
        //清除session
        request.getSession().invalidate();
        //重定向到登录页面的跳转方法
        return "redirect:login";
    }

    @RequestMapping(value = "/showpersoninfo" , method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> showPersonInfo(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        // 获取前端传递过来的帐号和密码
        long userId = HttpServletRequestUtil.getLong(request, "userId");
        String nickname = HttpServletRequestUtil.getString(request, "nickname");
        List<PersonInfo> personInfoList = personInfoService.queryByIdOrName(userId,nickname);
        if (personInfoList!=null){
            modelMap.put("success", true);
            modelMap.put("message", "用户信息请求成功");
            modelMap.put("code", 1);
            modelMap.put("result",personInfoList);
            modelMap.put("timestamp", new Date());
        }else {
            modelMap.put("success", false);
            modelMap.put("message", "用户信息列表请求失败");
            modelMap.put("code", 11);
            modelMap.put("timestamp", new Date());
        }
        return modelMap;
    }

}
