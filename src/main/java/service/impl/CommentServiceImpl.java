package service.impl;

import dao.CommentDao;
import entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.CommentService;

import java.util.List;

/**
 * @author HABIN
 * @date 2020/3/19 1:05
 * 评论业务层实现类
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;

    @Override
    public int insertComment(Comment comment) {
        return commentDao.insertComment(comment);
    }

    @Override
    public List<Comment> queryCommentByAcId(int articleId) {
        return commentDao.queryCommentByAcId(articleId);
    }
}
