package dao;

import entity.LocalAuth;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * 本地账号接口类
 * 持久层
 */
public interface LocalAuthDao extends BaseDao<LocalAuth> {


    /**
     * 通过帐号和密码查询对应信息，登录用
     *
     * @param username
     * @param password
     * @return
     */
    LocalAuth queryLocalByUserNameAndPwd(@Param("username") String username, @Param("password") String password);


    /**
     * 添加平台帐号
     *
     * @param localAuth
     * @return
     */
    int insertLocalAuth(LocalAuth localAuth);

    /**
     * 通过用户ID查询对应
     * @param userId
     * @return
     */
    LocalAuth queryLocalByUserId(@Param("userId") long userId);

    LocalAuth queryLocalByUserName(@Param("username") String username);
    /**
     * username,password更改密码queryLocalByUserName
     * @param username
     * @param password
     * @param newPassword
     * @param lastEditTime
     * @return
     */
    int updatePassword( @Param("username") String username,
                        @Param("password") String password,
                         @Param("newPassword") String newPassword,
                        @Param("lastEditTime") Date lastEditTime);
}
