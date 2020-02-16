package dao;

import entity.City;
import entity.County;
import entity.Province;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author HABIN
 * @date 2020/2/15 21:15
 *
 * 省市县接口类
 *  持久层
 */
public interface CountyDao extends BaseDao<County> {

    /**
     * 查询所有城市信息
     */
    @Override
    List<County> query();

    /**
     * 查询区县数据
     * @param cityId 城市上级编号
     */
    List<County> queryCounty(@Param("cityId") int cityId);



}
