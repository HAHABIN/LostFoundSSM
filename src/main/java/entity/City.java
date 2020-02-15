package entity;

/**
 * @author HABIN
 * @date 2020/2/15 21:12
 * 省市县表
 * 实体层 数据库在项目中的类
 */
public class City {

    //id 城市编号
    private Integer id;
    //城市上级id
    private Integer pid;
    //城市名
    private String cityName;
    //城市类别  省级1 市级2 县区级3
    private Integer type;

    public City() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
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
                "id=" + id +
                ", pid=" + pid +
                ", cityName='" + cityName + '\'' +
                ", type=" + type +
                '}';
    }
}
