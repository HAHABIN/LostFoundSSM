package controller;

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
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/loadadmin")
public class LoginController {

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
                    modelMap.put("data", localAuth.getPersonInfo());
                    modelMap.put("timestamp",new Date());
//					// 登录成功则设置上session信息
//					request.getSession().setAttribute("user", localAuth.getPersonInfo());
                } else {
                    modelMap.put("success", false);
                    modelMap.put("message", "非管理员没有权限访问");
                    modelMap.put("code", 10);
                    modelMap.put("timestamp",new Date());
                }
            } else {
                modelMap.put("success", false);
                modelMap.put("message", "用户名或密码错误");
                modelMap.put("code", 11);
                modelMap.put("timestamp",new Date());
            }
        } else {
            modelMap.put("success", false);
            modelMap.put("message", "用户名和密码均不能为空");
            modelMap.put("code", 12);
            modelMap.put("timestamp",new Date());
        }
        return modelMap;
    }

    /**
     * 注册
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/regincheck", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> reginCheck(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<String, Object>();

        // 获取前端传递过来的帐号和密码
        String userName = HttpServletRequestUtil.getString(request, "username");
        String password = HttpServletRequestUtil.getString(request, "password");
        String email = HttpServletRequestUtil.getString(request, "email");
        // 空值判断
        if (userName != null && password != null && email != null) {
            //判断用户名是否存在
            LocalAuth localAuth = localAuthService.getLocalAuthByUserName(userName);
            if (localAuth != null) {
                modelMap.put("success", false);
                modelMap.put("message", "用户名已存在");
                modelMap.put("code", 9);
                modelMap.put("timestamp",new Date());
                return modelMap;
            }
            //新增一台平台信息
            int userId = (int) ((Math.random() * 9 + 1) * 1000000);
            PersonInfo personInfo = personInfoService.insertPersonInfo(userId, userName, email);
            int i = localAuthService.insertLocalAuth(userId,userName,password);
            if (i == 1) {
                modelMap.put("success", true);
                modelMap.put("message", "注册成功");
                modelMap.put("code", 1);
                modelMap.put("data", personInfo);
                modelMap.put("timestamp",new Date());
            } else {
                modelMap.put("success", false);
                modelMap.put("message", "注册失败");
                modelMap.put("code", 2);
                modelMap.put("timestamp",new Date());
            }

        } else {
            modelMap.put("success", false);
            modelMap.put("message", "用户名和密码均不能为空");
            modelMap.put("code", 12);
            modelMap.put("timestamp",new Date());
        }
        return modelMap;
    }
    /**
     * 更新用户密码
     * @param request
     * @return
     */
    @RequestMapping(value = "/updatePasswordAuth", method = RequestMethod.POST)
    @ResponseBody
    private Map<String,Object> updatePasswordAuth(HttpServletRequest request){
        Map<String, Object> modelMap = new HashMap<String, Object>();
        // 获取前端传递过来的帐号和密码
        String userName = HttpServletRequestUtil.getString(request, "username");
        String password = HttpServletRequestUtil.getString(request, "password");
        String newpassword = HttpServletRequestUtil.getString(request, "newpassword");
        // 空值判断
        if (userName != null && password != null && newpassword != null) {
            //判断用户名是否存在
            LocalAuth localAuth = localAuthService.getLocalAuthByUserName(userName);
            if (localAuth == null){
                modelMap.put("success", false);
                modelMap.put("message", "用户名不存在");
                modelMap.put("code", 15);
                modelMap.put("timestamp",new Date());
                return modelMap;
            }
            if (localAuth.getPassword().equals(password)){
                int i = localAuthService.updatePassword(userName,password,newpassword);
                if (i == 1) {
                    modelMap.put("success", true);
                    modelMap.put("message", "密码修改成功");
                    modelMap.put("code", 1);
                    modelMap.put("timestamp",new Date());
                } else {
                    modelMap.put("success", false);
                    modelMap.put("message", "密码修改失败");
                    modelMap.put("code", 2);
                    modelMap.put("timestamp",new Date());
                }
            } else {
                modelMap.put("success", false);
                modelMap.put("message", "密码错误");
                modelMap.put("code", 2);
                modelMap.put("timestamp",new Date());
            }
        } else {
            modelMap.put("success", false);
            modelMap.put("message", "请全部填完");
            modelMap.put("code", 13);
            modelMap.put("timestamp",new Date());
        }
        return modelMap;
    }

}
