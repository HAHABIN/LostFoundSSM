package controller;

import entity.ArticleInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.ArticleInfoService;
import utils.HttpServletRequestUtil;
import utils.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author HABIN
 * @date 2020/2/19 22:12
 * 物品管理控制层
 */

@Controller
@RequestMapping("/articleInfo")
public class ArticleInfoControler {

    @Autowired
    private ArticleInfoService articleInfoService;

    @RequestMapping(value = "/InsertArInfo", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> InsertArInfo(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        int id = (int) ((Math.random() * 9 + 1) * 10000000);
        int userId = HttpServletRequestUtil.getInt(request, "userId");
        int typeId = HttpServletRequestUtil.getInt(request, "typeId");
        String phone = HttpServletRequestUtil.getString(request, "phone");
        long findTimeStr = HttpServletRequestUtil.getLong(request, "findTime");
        String imgStr = HttpServletRequestUtil.getString(request, "imgStr");
        String addressContent = HttpServletRequestUtil.getString(request, "addressContent");
        String description = HttpServletRequestUtil.getString(request, "description");
        int status = HttpServletRequestUtil.getInt(request, "status");
        int recordStatus = HttpServletRequestUtil.getInt(request, "recordStatus");
        /*设置发布物品信息*/
        ArticleInfo articleInfo = new ArticleInfo();
        articleInfo.setId(id);
        articleInfo.setUserId(userId);
        articleInfo.setTypeId(typeId);
        articleInfo.setFindTime(new Date(findTimeStr));
        articleInfo.setAddressContent(addressContent);
        articleInfo.setImgStr(imgStr);
        articleInfo.setPhone(phone);
        articleInfo.setDescription(description);
        articleInfo.setStatus(status);
        articleInfo.setRecordStatus(recordStatus);
        articleInfo.setCreateTime(new Date());

        //新增
        int count = articleInfoService.insert(articleInfo);
        if (count == 1) {
            modelMap.put("success", true);
            modelMap.put("message", "新增物品信息成功");
            modelMap.put("code", 1);
            modelMap.put("timestamp", new Date().getTime());
        } else {
            modelMap.put("success", false);
            modelMap.put("message", "新增物品信息失败");
            modelMap.put("code", 2);
            modelMap.put("timestamp", new Date().getTime());
        }

        return modelMap;
    }

    @RequestMapping(value = "/QueryArticleInfo", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> QueryArticleInfo(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        int id = HttpServletRequestUtil.getInt(request, "id");
        int userId = HttpServletRequestUtil.getInt(request, "userId");
        int typeId = HttpServletRequestUtil.getInt(request, "typeId");
        String addressContent = HttpServletRequestUtil.getString(request, "addressContent");
        String description = HttpServletRequestUtil.getString(request, "description");
        int status = HttpServletRequestUtil.getInt(request, "status");
        int recordStatus = HttpServletRequestUtil.getInt(request, "recordStatus");
        int pageNo = HttpServletRequestUtil.getInt(request, "pageNo");
        int pageSize = HttpServletRequestUtil.getInt(request, "pageSize");
        //分页  最少两行
        if (pageSize <=2 || pageSize > 20) pageSize = 10;
        if (pageNo <= 0) {
            pageNo = 0;
        } else {
            pageNo--;
        }
        int start = pageNo * pageSize;
        List<ArticleInfo> articleInfoList = articleInfoService.queryArticleInfo(id, userId, typeId, addressContent
                , description, status, recordStatus, start, pageSize);
        if (articleInfoList != null && !articleInfoList.isEmpty()) {
            modelMap.put("success", true);
            modelMap.put("message", "查询物品信息成功");
            modelMap.put("code", 1);
            modelMap.put("result", articleInfoList);
            modelMap.put("timestamp", new Date().getTime());
        } else {
            modelMap.put("success", false);
            modelMap.put("message", "查询物品信息失败");
            modelMap.put("code", 2);
            modelMap.put("timestamp", new Date().getTime());
        }
        return modelMap;
    }
}
