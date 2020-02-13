package entity;

import java.util.Date;

/**
 * 本地账号表
 *
 * @author Jacky
 *
 */
public class LocalAuth {

    // ID
    private Integer localauthId;
    // 用户Id
    private Integer userId;
    // 用户名
    private String username;
    // 密码
    private String password;
    // 创建时间
    private Date createTime;
    // 修改时间
    private Date lastEditTime;
    //个人信息，关系为一一对应
    private PersonInfo personInfo;

    public Integer getLocalauthId() {
        return localauthId;
    }

    public void setLocalauthId(Integer localauthId) {
        this.localauthId = localauthId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastEditTime() {
        return lastEditTime;
    }

    public void setLastEditTime(Date lastEditTime) {
        this.lastEditTime = lastEditTime;
    }

    public PersonInfo getPersonInfo() {
        return personInfo;
    }

    public void setPersonInfo(PersonInfo personInfo) {
        this.personInfo = personInfo;
    }


}
