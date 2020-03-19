package dao;

import entity.Comment;
import org.apache.ibatis.annotations.Param;

/**
 * @author HABIN
 * @date 2020/3/19 0:38
 */
public interface CommentDao {

    /**
     * 添加评论
     * */
    int insertComment(Comment comment);


}
