package dao;

import entity.City;
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
     * 查询省级数据
     * @param id 城市编号
     * @param pid 城市上级编号
     * @param type 城市级别
     */
    List<City> queryCity(@Param("id") int id,
                         @Param("pid") int pid,
                         @Param("cityName") String cityName,
                         @Param("type") int type);
}
