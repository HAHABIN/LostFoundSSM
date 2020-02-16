package service;

import entity.City;
import entity.County;
import entity.Province;

import java.util.List;

/**
 * @author HABIN
 * @date 2020/2/15 21:25
 *
 * 省市级业务层接口
 */
public interface CityService {

    /**
     * 查询所有城市信息
     */
    List<City> query();



    /**
     * 查询省级数据
     * @param id 城市编号
     * @param pid 城市上级编号
     * @param type 城市级别
     */
    List<Province> queryProvince(int id,int pid,String cityname,int type);
    /**
     * 查询市级数据
     * @param provinceId 城市上级编号
     */
    List<City> queryCity(int provinceId);
    /**
     * 查询区县级数据
     * @param cityId 城市上级编号
     */
    List<County> queryCounty(int cityId);
}
