package controller;

import entity.ArticleType;
import entity.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.ArticleTypeService;
import service.CityService;
import utils.HttpServletRequestUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author HABIN
 * @date 2020/2/16 0:28
 * 物品类型控制层
 */
@Controller
@RequestMapping("/articletype")
public class ArticleTypeController {


    @Autowired
    private ArticleTypeService articleType;

    @RequestMapping(value = "/queryAll")
    @ResponseBody
    private Map<String,Object> queryAll(HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<>();
        List<ArticleType> articleTypeList = articleType.query();
        if (articleTypeList != null && !articleTypeList.isEmpty()){
            modelMap.put("success", true);
            modelMap.put("message", "获取城市信息成功");
            modelMap.put("code", 1);
            modelMap.put("data",articleTypeList);
            modelMap.put("timestamp",new Date());
        } else {
            modelMap.put("success", false);
            modelMap.put("message", "获取城市信息失败");
            modelMap.put("code", 2);
            modelMap.put("timestamp",new Date());
        }
        return modelMap;
    }



    @RequestMapping(value = "/queryType" )
    @ResponseBody
    private Map<String,Object> queryType(HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<>();
        int typeId = HttpServletRequestUtil.getInt(request, "typeId");
        int priority = HttpServletRequestUtil.getInt(request, "priority");
        String typeName = HttpServletRequestUtil.getString(request, "typeName");

        List<ArticleType> articleTypeList =  articleType.queryArticleType(typeId,typeName,priority);

        if (articleTypeList != null && !articleTypeList.isEmpty()){
            modelMap.put("success", true);
            modelMap.put("message", "获取物品类型信息成功");
            modelMap.put("code", 1);
            modelMap.put("data",articleTypeList);
            modelMap.put("timestamp",new Date());
        } else {
            modelMap.put("success", false);
            modelMap.put("message", "获取物品类型信息失败");
            modelMap.put("code", 2);
            modelMap.put("timestamp",new Date());
        }

        return modelMap;
    }
}
