package service.impl;

import dao.ArticleInfoDao;
import entity.ArticleInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.ArticleInfoService;

import java.util.List;

/**
 * @author HABIN
 * @date 2020/2/19 19:40
 */
@Service
public class ArticleInfoServiceImpl implements ArticleInfoService {


    @Autowired
    private ArticleInfoDao articleInfoDao;

    @Override
    public List<ArticleInfo> queryArticleInfo(int id,int userId, int typeId,
                                              String addressContent, String description,
                                              int status, int recordStatus,
                                              int start, int pageSize) {
        return articleInfoDao.queryArticleInfo(id,userId,typeId,addressContent,description,status,recordStatus,start,pageSize);
    }

    @Override
    public List<ArticleInfo> searchArInfo(String addressContent, String description, int start, int pageSize) {
        return articleInfoDao.searchArInfo(addressContent,description,start,pageSize);
    }


    @Override
    public int insert(ArticleInfo articleInfo) {
        return articleInfoDao.insert(articleInfo);
    }

    @Override
    public int update(ArticleInfo articleInfo) {
        return articleInfoDao.update(articleInfo);
    }
}
