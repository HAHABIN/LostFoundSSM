package controller;

import com.google.gson.reflect.TypeToken;
import entity.ArticleInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.ArticleInfoService;
import utils.AliyunOSSClientUtil;
import utils.HttpServletRequestUtil;
import utils.OSSClientConstants;
import utils.StringUtils;

import javax.servlet.http.HttpServletRequest;
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
            modelMap.put("timestamp", new Date());
        } else {
            modelMap.put("success", false);
            modelMap.put("message", "新增物品信息失败");
            modelMap.put("code", 2);
            modelMap.put("timestamp", new Date());
        }

        return modelMap;
    }

    /**
     * 更新物品信息
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/updateArticle")
    @ResponseBody
    public Map<String,Object> updateArticle(HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<>();
        int id =  HttpServletRequestUtil.getInt(request, "id");
        int userId = HttpServletRequestUtil.getInt(request, "userId");
        int typeId = HttpServletRequestUtil.getInt(request, "typeId");
        String phone = HttpServletRequestUtil.getString(request, "phone");
        long findTimeStr = HttpServletRequestUtil.getLong(request, "findTime");
        String imgStr = HttpServletRequestUtil.getString(request, "imgStr");
        String addressContent = HttpServletRequestUtil.getString(request, "addressContent");
        String description = HttpServletRequestUtil.getString(request, "description");
        int recordStatus = HttpServletRequestUtil.getInt(request, "recordStatus");
        ArticleInfo articleInfo = new ArticleInfo(id,userId,typeId,phone,new Date(findTimeStr),
                imgStr,addressContent,description,recordStatus,new Date());
        ArticleInfo delete = articleInfoService.queryArticleById(id);
        int i = 0;
        try {
            i = articleInfoService.updateArticle(articleInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (imgStr!=null) {
            List<String> imgStrList1 = StringUtils.fromJson(delete.getImgStr(), new TypeToken<List<String>>() {
            });
            for (String str : imgStrList1) {
                AliyunOSSClientUtil.deleteFile(str);
            }
        }
        if (i==1){
            modelMap.put("success", true);
            modelMap.put("message", "物品信息更新成功");
            modelMap.put("code", 1);
            modelMap.put("timestamp",new Date());
        } else {
            modelMap.put("success", false);
            modelMap.put("message", "物品信息更新失败");
            modelMap.put("code", 2);
            modelMap.put("timestamp",new Date());
        }

        return modelMap;
    }

    /**
     * 更新物品状态
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/updateArticleStatus")
    @ResponseBody
    public Map<String, Object> updateArticleStatus(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        int id = HttpServletRequestUtil.getInt(request, "id");
        int recordStatus = HttpServletRequestUtil.getInt(request, "recordStatus");
        int ss;
        if (recordStatus == 3) {
            ss = articleInfoService.updateArticleStatus(id, recordStatus, new Date(), new Date());
        } else {
            ss = articleInfoService.updateArticleStatus(id, recordStatus, null, new Date());
        }
        if (ss==1){
            modelMap.put("success", true);
            modelMap.put("message", "更新成功");
            modelMap.put("code", 1);
            modelMap.put("timestamp", new Date());
        } else {
            modelMap.put("success", false);
            modelMap.put("message", "更新失败");
            modelMap.put("code", 1);
            modelMap.put("timestamp", new Date());
        }
        return modelMap;
    }

    /**
     * 查询列表 地址模糊查询
     *
     * @param request
     * @return
     */
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
        if (pageSize <= 2 || pageSize > 20) pageSize = 10;
        if (pageNo <= 0) {
            pageNo = 0;
        } else {
            pageNo--;
        }
        int start = pageNo * pageSize;
        List<ArticleInfo> articleInfoList = articleInfoService.queryArticleInfo(id, userId, typeId, addressContent
                , description, status, recordStatus, start, pageSize);

        if (articleInfoList.size() == 0) {
            modelMap.put("success", true);
            modelMap.put("message", "未查询到");
            modelMap.put("code", 1);
            modelMap.put("result", articleInfoList);
            modelMap.put("timestamp", new Date());
            return modelMap;
        }
        BooleanInfo(modelMap, articleInfoList);
        return modelMap;
    }

    private void BooleanInfo(Map<String, Object> modelMap, List<ArticleInfo> articleInfoList) {
        if (articleInfoList != null && !articleInfoList.isEmpty()) {
            modelMap.put("success", true);
            modelMap.put("message", "查询物品信息成功");
            modelMap.put("code", 1);
            modelMap.put("result", articleInfoList);
            modelMap.put("timestamp", new Date());
        } else {
            modelMap.put("success", true);
            modelMap.put("message", "查询物品信息失败");
            modelMap.put("code", 1);
            modelMap.put("timestamp", new Date());
        }
    }

    /**
     * 搜索
     */
    @RequestMapping(value = "/searchArInfo", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> searchArInfo(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        String addressContent = HttpServletRequestUtil.getString(request, "addressContent");
        String description = HttpServletRequestUtil.getString(request, "description");
        int pageNo = HttpServletRequestUtil.getInt(request, "pageNo");
        int pageSize = HttpServletRequestUtil.getInt(request, "pageSize");
        //分页  最少两行
        if (pageSize <= 2 || pageSize > 20) pageSize = 10;
        if (pageNo <= 0) {
            pageNo = 0;
        } else {
            pageNo--;
        }
        int start = pageNo * pageSize;
        List<ArticleInfo> articleInfoList = articleInfoService.searchArInfo(addressContent, description, start, pageSize);

        if (articleInfoList.size() == 0) {
            //模糊查询  内容查询不到的话就查询地址
            if (description != null) {
                articleInfoList = articleInfoService.searchArInfo(description, null, start, pageSize);
                if (articleInfoList.size() == 0) {
                    modelMap.put("success", true);
                    modelMap.put("message", "未查询到");
                    modelMap.put("code", 1);
                    modelMap.put("result", articleInfoList);
                    modelMap.put("timestamp", new Date());
                    return modelMap;
                }
                BooleanInfo(modelMap, articleInfoList);
                return modelMap;
            }
            modelMap.put("success", true);
            modelMap.put("message", "未查询到");
            modelMap.put("code", 1);
            modelMap.put("result", articleInfoList);
            modelMap.put("timestamp", new Date());
            return modelMap;
        }
        BooleanInfo(modelMap, articleInfoList);
        return modelMap;
    }

    /**
     * 批量删除用户
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/batchdeleteArticle", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> batchdeleteArticle(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        String DeleteIndexArr = HttpServletRequestUtil.getString(request, "DeleteIndexArr");
        int deleteFruit = 0;
        String[] ids = DeleteIndexArr.split(",");

        for (String aid : ids) {
            int id = Integer.parseInt(aid);
            ArticleInfo articleInfo = articleInfoService.queryArticleById(id);
            List<String> imgStrList = StringUtils.fromJson(articleInfo.getImgStr(), new TypeToken<List<String>>() {
            });
            for (String imgStr : imgStrList) {
                AliyunOSSClientUtil.deleteFile(imgStr);
            }
            int delete = articleInfoService.deleteById(id);
            if (delete == 1) {
                deleteFruit++;
            }
        }
        modelMap.put("success", true);
        modelMap.put("message", "删除成功");
        modelMap.put("code", 1);
        modelMap.put("DeleteCounts", deleteFruit);
        modelMap.put("timestamp", new Date());
        return modelMap;
    }

    @RequestMapping(value = "/deleteArticle", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> deleteArticle(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        int id = HttpServletRequestUtil.getInt(request, "id");

        ArticleInfo articleInfo = articleInfoService.queryArticleById(id);
        List<String> imgStrList = StringUtils.fromJson(articleInfo.getImgStr(), new TypeToken<List<String>>() {
        });
        for (String imgStr : imgStrList) {
            AliyunOSSClientUtil.deleteFile(imgStr);
        }
        int delete = articleInfoService.deleteById(id);
        if (delete == 1) {
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