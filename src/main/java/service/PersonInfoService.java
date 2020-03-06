package service;

import entity.PersonInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author HABIN
 * @date 2020/2/7 14:11
 * 用户信息管理业务层接口
 */
public interface PersonInfoService {


    /**
     * 通过用户Id查询用户
     *
     * @param userId
     * @return
     */
    PersonInfo queryPersonInfoById(long userId);

    /**
     * 添加用户信息
     *
     * @param userId
     * @param username
     * @param email
     * @return
     */
    PersonInfo insertPersonInfo(int userId, String username, String email);


    int updatePersonInfo(PersonInfo personInfo);

    List<PersonInfo> query();
    /**
     * 通过用户Id 查询用户
     *
     **/
    List<PersonInfo> queryByIdOrName(@Param("userId")long userId, @Param("nickname")String nickname);

    /**
     * 单个删除
     * @param userId
     * @return
     */
    int delete(long userId);

    /**
     * 批量删除头条
     * @param lineIdList
     * @return
     */
    int batchDeletePsrsonInfo(List<Long> lineIdList);
}
