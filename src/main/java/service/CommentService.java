package service;

import entity.Comment;

/**
 * @author HABIN
 * @date 2020/3/19 1:04
 */
public interface CommentService {
    /**
     * 添加评论
     * */
    int insertComment(Comment comment);
}
