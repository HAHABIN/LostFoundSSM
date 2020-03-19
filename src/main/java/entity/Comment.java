package entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author HABIN
 * @date 2020/3/18 23:22
 * 评论表实体类
 */
public class Comment implements Serializable {

    private int cId;

    private int userId;

    private String nickName;

    private String userImg;

    private String content;

    private Date createTime;

    private int articleId;

//    private int status;
//
//    private int parentId;

    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

//    public int getStatus() {
//        return status;
//    }
//
//    public void setStatus(int status) {
//        this.status = status;
//    }
//
//    public int getParentId() {
//        return parentId;
//    }
//
//    public void setParentId(int parentId) {
//        this.parentId = parentId;
//    }
}
