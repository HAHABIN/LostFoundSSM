package service.impl;

import dao.ArticleTypeDao;
import entity.ArticleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.ArticleTypeService;

import java.util.List;

/**
 * @author HABIN
 * @date 2020/2/15 23:30
 *
 * 物品类型管理业务层接口实现类
 */
@Service
public class ArticleTypeServiceImpl implements ArticleTypeService {

    @Autowired
    private ArticleTypeDao articleTypeDao;

    @Override
    public List<ArticleType> query() {
        return articleTypeDao.query();
    }

    @Override
    public int insert(ArticleType propertyType) {
        return articleTypeDao.insert(propertyType);
    }

    @Override
    public List<ArticleType> queryArticleType(int typeId, String typeName, int priority) {
        return articleTypeDao.queryArticleType(typeId,typeName,priority);
    }
}
