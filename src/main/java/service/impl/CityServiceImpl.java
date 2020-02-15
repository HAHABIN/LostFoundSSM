package service.impl;

import dao.CityDao;
import entity.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.CityService;

import java.util.List;

/**
 * @author HABIN
 * @date 2020/2/15 21:26
 * 省市级管理业务层接口实现类
 */

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityDao cityDao;

    @Override
    public List<City> query() {
        return cityDao.query();
    }

    @Override
    public List<City> queryCity(int id, int pid,String cityname, int type) {
        return cityDao.queryCity(id,pid,cityname,type);
    }
}
