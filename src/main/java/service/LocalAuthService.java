package service;

import entity.LocalAuth;
import entity.PersonInfo;

import java.util.Date;
/**
 * 用户登录管理业务层接口
 */
public interface LocalAuthService {
    /**
     * 通过帐号和密码获取平台帐号信息
     *
     * @param userName
     * @return
     */
    LocalAuth getLocalAuthByUsernameAndPwd(String userName, String password);

    /**
     * 通过userId获取平台帐号信息
     *
     * @param userId
     * @return
     */
    LocalAuth getLocalAuthByUserId(long userId);
    /**
     * 通过userId获取平台帐号信息
     *
     * @param userName
     * @return
     */
    LocalAuth getLocalAuthByUserName(String userName);

    /**
     *插入新用户
     * @param localAuth
     * @return
     */
    int insertLocalAuth(LocalAuth localAuth);
    /**
     * 修改平台帐号的登录密码
     * @param password
     * @param newPassword
     * @return
     */
    int updatePassword(String username, String password, String newPassword);

    int insertLocalAuth(int userId, String userName, String password);
    /**
     * 修改平台帐号的登录密码
     *
     * @param localAuthId
     * @param userName
     * @param password
     * @param newPassword
     * @param lastEditTime
     * @return
     */
//    LocalAuthExecution modifyLocalAuth(Long userId, String username, String password, String newPassword)
//            throws LocalAuthOperationException;
}
