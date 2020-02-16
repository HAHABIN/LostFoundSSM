package dao;

import entity.City;
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
public interface CityDao extends BaseDao<City> {

    /**
     * 查询所有城市信息
     */
    @Override
    List<City> query();

    /**
     * 查询城市数据
     * @param provinceId 城市上级编号
     */
    List<City> queryCity(@Param("provinceId") int provinceId);


}
