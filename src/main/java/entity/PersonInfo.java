package entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户信息实体类
 * 实体层 数据库在项目中的类
 */
public class PersonInfo implements Serializable {

    //ID
    private Integer userId;
    //名字
    private String nickname;
    //头像
    private String profileImg;
    //邮箱
    private String email;
    //性别
    private String gender;
    //1.店家 2.顾客 3.超级管理员
    private Integer userType;
    //创建时间
    private Date createTime;
    //上次更新时间，。
    private Date lastEditTime;
    //帮助次数
    private Integer helpTimes;
    public PersonInfo(){

    }
    public PersonInfo(Integer userId,String nickname, String profileImg, String email, String gender, Date lastEditTime, Integer helpTimes) {
        this.userId = userId;
        this.nickname = nickname;
        this.profileImg = profileImg;
        this.email = email;
        this.gender = gender;
        this.lastEditTime = lastEditTime;
        this.helpTimes = helpTimes;
    }

    public PersonInfo(Integer userId, String nickName, String profileImg, String email, String gender, Integer userType, Date createTime, Date lastEditTime, Integer helpTimes) {
        this.userId = userId;
        this.nickname = nickName;
        this.profileImg = profileImg;
        this.email = email;
        this.gender = gender;
        this.userType = userType;
        this.createTime = createTime;
        this.lastEditTime = lastEditTime;
        this.helpTimes = helpTimes;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
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

    public Integer getHelpTimes() {
        return helpTimes;
    }

    public void setHelpTimes(Integer helpTimes) {
        this.helpTimes = helpTimes;
    }

    @Override
    public String toString() {
        return "PersonInfo{" +
                "userId=" + userId +
                ", nickname='" + nickname + '\'' +
                ", profileImg='" + profileImg + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", userType=" + userType +
                ", createTime=" + createTime +
                ", lastEditTime=" + lastEditTime +
                ", helpTimes=" + helpTimes +
                '}';
    }
}
