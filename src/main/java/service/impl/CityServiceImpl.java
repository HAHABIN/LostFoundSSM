package service.impl;

import dao.CityDao;
import dao.CountyDao;
import dao.ProviceDao;
import entity.City;
import entity.County;
import entity.Province;
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
    private ProviceDao proviceDao;
    @Autowired
    private CityDao cityDao;
    @Autowired
    private CountyDao countyDao;

    @Override
    public List<City> query() {
        return cityDao.query();
    }

    @Override
    public List<Province> queryProvince(int id, int pid, String cityname, int type) {
        return proviceDao.queryProvince(id,pid,cityname,type);
    }

    @Override
    public List<City> queryCity(int provinceId) {
        return cityDao.queryCity(provinceId);
    }

    @Override
    public List<County> queryCounty(int cityId) {
        return countyDao.queryCounty(cityId);
    }


}
