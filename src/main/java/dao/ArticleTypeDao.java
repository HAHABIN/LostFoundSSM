package dao;

import entity.ArticleType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author HABIN
 * @date 2020/2/15 23:28
 * 物品类型接口类
 * 持久层
 */
public interface ArticleTypeDao extends BaseDao<ArticleType> {
    @Override
    List<ArticleType> query();

    @Override
    int insert(ArticleType propertyType);

    List<ArticleType> queryArticleType(@Param("typeId") int typeId,
                                    @Param("typeName") String typeName,
                                    @Param("priority") int priority);
}
