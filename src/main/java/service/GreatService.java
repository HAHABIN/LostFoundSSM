package service;

import entity.Great;
import org.apache.ibatis.annotations.Param;

/**
 * @author HABIN
 * @date 2020/3/20 3:52
 * 点赞业务层接口
 */
public interface GreatService {
    /**
     * 新增点赞数
     * */
    int insert(Great great);
    /**
     * 取消点赞
     * */
    int deleteByUserId(@Param("acId") int acId, @Param("userId") int userId);
}
