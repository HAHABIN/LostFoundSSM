package service;

import entity.PersonInfo;

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

}
