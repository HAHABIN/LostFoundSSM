package controller;

import entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.CommentService;
import utils.HttpServletRequestUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author HABIN
 * @date 2020/3/19 1:07
 */
@Controller
@RequestMapping("/Comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     * 添加评论
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/addComment", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> addComment(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();

        int userId = HttpServletRequestUtil.getInt(request, "userId");
        int articleId = HttpServletRequestUtil.getInt(request, "articleId");
        String content = HttpServletRequestUtil.getString(request, "content");
        String nickName = HttpServletRequestUtil.getString(request, "nickName");
        String imgStr = HttpServletRequestUtil.getString(request, "imgStr");

        Comment comment = new Comment();

        comment.setUserId(userId);
        comment.setArticleId(articleId);
        comment.setContent(content);
        comment.setNickName(nickName);
        comment.setUserImg(imgStr);
        comment.setCreateTime(new Date());
        int i = commentService.insertComment(comment);
        if (i==1) {
            modelMap.put("success", true);
            modelMap.put("message", "评论成功");
            modelMap.put("code", 1);
            modelMap.put("timestamp",new Date());
        } else {
            modelMap.put("success", false);
            modelMap.put("message", "评论失败");
            modelMap.put("code", 0);
            modelMap.put("timestamp",new Date());
        }
        return modelMap;
    }
    /**
     * 查询
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/queryComment", method = RequestMethod.POST)
    @ResponseBody
    private Map<String,Object> queryCommentByAcId(HttpServletRequest request) {
        Map<String,Object> modelMap = new HashMap<>();
        int articleId = HttpServletRequestUtil.getInt(request, "articleId");
        List<Comment> comments = commentService.queryCommentByAcId(articleId);
        if (comments!=null&&!comments.isEmpty()){
            modelMap.put("success", true);
            modelMap.put("message", "获取评论成功");
            modelMap.put("code", 1);
            modelMap.put("result",comments);
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
