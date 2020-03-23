package controller;

import entity.Comment;
import entity.Great;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.GreatService;
import utils.HttpServletRequestUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author HABIN
 * @date 2020/3/20 3:56
 */
@Controller
@RequestMapping("/Great")
public class GreatController {

    @Autowired
    private GreatService greatService;
    /**
     * 点赞
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/addGreat", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> addGreat(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();

        int userId = HttpServletRequestUtil.getInt(request, "userId");
        int articleId = HttpServletRequestUtil.getInt(request, "articleId");
        Great great = new Great();
        great.setUserId(userId);
        great.setAcId(articleId);
        int i = greatService.insert(great);
        if (i==1) {
            modelMap.put("success", true);
            modelMap.put("message", "点赞成功");
            modelMap.put("code", 1);
            modelMap.put("timestamp",new Date());
        } else {
            modelMap.put("success", false);
            modelMap.put("message", "点赞失败");
            modelMap.put("code", 0);
            modelMap.put("timestamp",new Date());
        }
        return modelMap;
    }
    /**
     * 取消点赞
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/deleteGreat", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> deleteGreat(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();

        int userId = HttpServletRequestUtil.getInt(request, "userId");
        int articleId = HttpServletRequestUtil.getInt(request, "articleId");

        int i = greatService.deleteByUserId(articleId,userId);
        if (i==1) {
            modelMap.put("success", true);
            modelMap.put("message", "取消点赞成功");
            modelMap.put("code", 1);
            modelMap.put("timestamp",new Date());
        } else {
            modelMap.put("success", false);
            modelMap.put("message", "取消点赞失败");
            modelMap.put("code", 0);
            modelMap.put("timestamp",new Date());
        }
        return modelMap;
    }

    /**
     * 查询评论
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/queryGreat", method = RequestMethod.POST)
    @ResponseBody
    private Map<String,Object> queryCommentByAcId(HttpServletRequest request) {
        Map<String,Object> modelMap = new HashMap<>();
        int articleId = HttpServletRequestUtil.getInt(request, "articleId");
        List<Great> greatList = greatService.queryGreatByAcId(articleId);
        if (greatList!=null&&!greatList.isEmpty()){
            modelMap.put("success", true);
            modelMap.put("message", "获取评论成功");
            modelMap.put("code", 1);
            modelMap.put("result",greatList);
            modelMap.put("timestamp",new Date());
        } else {
            modelMap.put("success", false);
            modelMap.put("message", "评论失败");
            modelMap.put("code", 0);
            modelMap.put("timestamp",new Date());
        }
        return modelMap;
    }
}
