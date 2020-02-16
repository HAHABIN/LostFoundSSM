package controller;

import entity.City;
import entity.Province;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.CityService;
import utils.HttpServletRequestUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author HABIN
 * @date 2020/2/15 22:02
 */

@Controller
@RequestMapping("/chinaAddress")
public class CityController {

    @Autowired
    private CityService cityService;

    @RequestMapping(value = "/queryAll", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> queryAll(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        List<City> cityList = cityService.query();
        if (cityList != null && !cityList.isEmpty()) {
            modelMap.put("success", true);
            modelMap.put("message", "获取城市信息成功");
            modelMap.put("code", 1);
            modelMap.put("data", cityList);
            modelMap.put("timestamp", new Date());
        } else {
            modelMap.put("success", false);
            modelMap.put("message", "获取城市信息失败");
            modelMap.put("code", 2);
            modelMap.put("timestamp", new Date());
        }
        return modelMap;
    }


    @RequestMapping(value = "/City", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> queryCity(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();

        int id = HttpServletRequestUtil.getInt(request, "id");
        int pid = HttpServletRequestUtil.getInt(request, "pid");
        String cityname = HttpServletRequestUtil.getString(request, "cityname");
        int type = HttpServletRequestUtil.getInt(request, "type");

        List<Province> cityList = cityService.queryProvince(id,pid,cityname,type);
//        String typelevel;
//        if (type == 1) {
//            typelevel = "省份列表";
//        } else if (type == 2) {
//            typelevel = "市级列表";
//        } else {
//            typelevel = "区县级列表";
//        }
            if (cityList != null && !cityList.isEmpty()) {
                modelMap.put("success", true);
                modelMap.put("message", "获取省市级列表信息成功");
                modelMap.put("code", 1);
                modelMap.put("data", cityList);
                modelMap.put("timestamp", new Date());
            } else {
                modelMap.put("success", false);
                modelMap.put("message", "获取省市级列表信息失败");
                modelMap.put("code", 2);
                modelMap.put("timestamp", new Date());
            }

        return modelMap;
    }
}
