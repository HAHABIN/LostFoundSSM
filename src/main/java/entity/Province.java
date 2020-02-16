package entity;

import java.io.Serializable;
import java.util.List;

/**
 * @author HABIN
 * @date 2020/2/15 21:12
 * 省级表
 * 实体层 数据库在项目中的类
 */
public class Province implements Serializable {

    //id 城市编号
    private Integer provinceId;
    //城市上级id
    private Integer pid;
    //城市名
    private String provinceName;
    //城市类别  省级1 市级2 县区级3
    private Integer type;
    //
    private List<City> cityList;

    public List<City> getCityList() {
        return cityList;
    }

    public void setCityList(List<City> cityList) {
        this.cityList = cityList;
    }

    public Province() {
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Province{" +
                "provinceId=" + provinceId +
                ", pid=" + pid +
                ", provinceName='" + provinceName + '\'' +
                ", type=" + type +
                ", cityList=" + cityList +
                '}';
    }
}
