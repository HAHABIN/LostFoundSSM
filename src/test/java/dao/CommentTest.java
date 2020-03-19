package dao;

import entity.Comment;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * @author HABIN
 * @date 2020/3/19 0:51
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CommentTest extends BaseTest{
    @Autowired
    private CommentDao commentDao;

    @Test
    public void insertComment(){
        Comment comment = new Comment();
        comment.setUserId(1);
        comment.setArticleId(72877122);
        comment.setContent("测试23");
        comment.setCreateTime(new Date());
        comment.setNickName("测试用户名");
        comment.setUserImg("[\"http://habin-picload.oss-cn-beijing.aliyuncs.com/upload/image/20200307/20200307213536314.jpeg\"]");
        int i = commentDao.insertComment(comment);
        assertEquals(1, i);

    }
}
