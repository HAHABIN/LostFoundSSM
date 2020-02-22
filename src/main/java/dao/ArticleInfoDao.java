package dao;

import entity.ArticleInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author HABIN
 * @date 2020/2/19 19:11
 */
public interface ArticleInfoDao extends BaseDao<ArticleInfo> {

    /**
     * @param  userId 发布者id
     * @param  typeId 物品类型id
     * @param  addressContent 找寻地址
     * @param  status 发布类型
     * @param  recordStatus 发布状态
     * @param  pageNo 第几页
     * @param  pageSize 每页最多行
     *
     * */
    List<ArticleInfo> queryArticleInfo(@Param("id")int id,
                                       @Param("userId")int userId,
                                       @Param("typeId") int typeId,
                                       @Param("addressContent") String addressContent,
                                       @Param("description")String description,
                                       @Param("status") int status,
                                       @Param("recordStatus")int recordStatus,
                                       @Param("start") int start,
                                       @Param("pageSize") int pageSize);
    /**
     * 添加发布物品
     *
     * */
    @Override
    int insert(ArticleInfo articleInfo);

    /**
     * 更新物品信息
     *
     * */
    @Override
    int update(ArticleInfo articleInfo);
}
