package service;

import entity.ArticleType;

import java.util.List;

/**
 * @author HABIN
 * @date 2020/2/15 23:29
 * 物品类型管理业务层接口
 */
public interface ArticleTypeService {

    List<ArticleType> query();

    int insert(ArticleType propertyType);

    List<ArticleType> queryArticleType(int typeId, String typeName, int priority);
}
