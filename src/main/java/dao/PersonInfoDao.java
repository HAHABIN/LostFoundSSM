package dao;

import entity.PersonInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户信息管理接口类
 * 持久层
 */
public interface PersonInfoDao extends BaseDao<PersonInfo> {

    /**
     * 通过用户Id 查询用户
     *
     * @param userId
     * @return
     */
    PersonInfo queryPersonInfoById(@Param("userId")long userId);

    /**
     * 添加用户信息
     *
     * @param personInfo
     * @return
     */
    int insertPersonInfo(PersonInfo personInfo);

    /**
     *更新用户信息
     * @param personInfo
     */
    int updatePersonInfo(PersonInfo personInfo);

    /**
     *查询用户列表
     *
     **/
    @Override
    List<PersonInfo> query();
    /**
     * 通过用户Id 查询用户
     *
     **/
    List<PersonInfo> queryByIdOrName(@Param("userId")long userId, @Param("nickname")String nickname);

    int deleteById(@Param("userId")long userId);


    /**
     * 批量删除头条
     * @param lineIdList
     * @return
     */
    int batchDeletePsrsonInfo(List<Long> lineIdList);
}
