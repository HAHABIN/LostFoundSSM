package entity;

import org.apache.ibatis.annotations.Param;

import java.io.Serializable;

/**
 * @author HABIN
 * @date 2020/2/15 21:12
 * 省市县表
 * 实体层 数据库在项目中的类
 */
public class County implements Serializable {

    //id 城市编号
    private Integer id;
    //城市上级id 数据库pid
    private Integer cityId;
    //城市名
    private String countyName;
    //城市类别  省级1 市级2 县区级3
    private Integer type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

}
