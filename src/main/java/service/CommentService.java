package service;

import entity.Comment;

/**
 * @author HABIN
 * @date 2020/3/19 1:04
 * 评论业务层接口
 */
public interface CommentService {
    /**
     * 添加评论
     * */
    int insertComment(Comment comment);
}
