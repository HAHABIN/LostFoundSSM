package entity;

import java.io.Serializable;
import java.util.List;

/**
 * @author HABIN
 * @date 2020/2/15 21:12
 * 省市县表
 * 实体层 数据库在项目中的类
 */
public class City implements Serializable {

    //id 城市编号
    private Integer cityId;
    //城市上级id
    private Integer provinceId;
    //城市名
    private String cityName;
    //城市类别  省级1 市级2 县区级3
    private Integer type;

    private List<County> countyList;

    public List<County> getCountyList() {
        return countyList;
    }

    public void setCountyList(List<County> countyList) {
        this.countyList = countyList;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "City{" +
                "cityId=" + cityId +
                ", provinceId=" + provinceId +
                ", cityName='" + cityName + '\'' +
                ", type=" + type +
                ", countyList=" + countyList +
                '}';
    }
}
