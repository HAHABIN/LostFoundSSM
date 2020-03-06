package dao;

import entity.City;
import entity.Province;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author HABIN
 * @date 2020/2/15 21:40
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CityTest extends BaseTest {

    @Autowired
    private ProviceDao proviceDao;

    @Test
    public void queryTest(){
//        List<City> cityList = cityDao.query();
//        for (City city:cityList){
//            System.out.println(city.toString());
//        }
    }

    @Test
    public void queryCity(){
        int id = 0;
        int pid = 3;
        String cityName = null;
        int type = 0;
        List<Province> cityList = proviceDao.queryProvince(-1,-1,null,1);
        for (Province city:cityList){
            System.out.println(city.toString());
        }
    }
}
