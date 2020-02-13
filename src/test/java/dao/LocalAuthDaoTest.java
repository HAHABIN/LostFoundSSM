package dao;


import entity.LocalAuth;
import entity.PersonInfo;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static org.junit.Assert.assertEquals;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LocalAuthDaoTest extends BaseTest {

    @Autowired
    private LocalAuthDao localAuthDao;
    @Autowired
    private PersonInfoDao personInfoDao;

    private static final String username = "testadmin";
    private static final String password = "testpassword";


    //TODO:出现问题，无法插入
    //TODO:已经解决，由于entity实体类和mapper文件命名不统一
    /**
     * 插入新用户
     * @throws Exception
     */
    @Test
    public void testAInsertLocalAuth() throws Exception{
        //新增一台平台信息
        PersonInfo personInfo = new PersonInfo();
        LocalAuth localAuth = localAuthDao.queryLocalByUserName(username);
        if (localAuth==null){
            System.out.println("用户名已存在");
            return;
        }
        Integer userId = (int)((Math.random()*9+1)*1000000);
        Date date = new Date();
        personInfo.setUserId(userId);
        personInfo.setName(userId+"name");
        personInfo.setUserType(2);
        personInfo.setHelpTimes(0);
        personInfo.setCreateTime(date);

        int effectedNum1 = personInfoDao.insertPersonInfo(personInfo);
        assertEquals(1, effectedNum1);
        //给平台账号绑定上用户信息
        localAuth.setPersonInfo(personInfo);
        //设置上用户名和密码
        localAuth.setUserId(userId);
        localAuth.setUsername(username);
        localAuth.setPassword(password);
        localAuth.setCreateTime(new Date());
        int effectedNum = localAuthDao.insertLocalAuth(localAuth);
        assertEquals(1,effectedNum);
    }


    @Test
    public void testQueryLocalByUserNameAndPwd() throws Exception{
        //按照账号和密码查询用户信息
            LocalAuth localAuth = localAuthDao.queryLocalByUserNameAndPwd(username+3,password);
            assertEquals("4240981name",localAuth.getPersonInfo().getName());
    }


    @Test
    public void testCQueryLocalByUserId() throws Exception{
        //按照用户Id查询平台账号，进而获取用户信息
        LocalAuth localAuth = localAuthDao.queryLocalByUserId(1L);
        assertEquals("4240981name",localAuth.getPersonInfo().getName());
    }



   @Test
    public void testUpdateLocalAuth() throws Exception{

        //依据用户ID，平台账号，以及旧密码修改平台账号密码
       Date now = new Date();
       //判断受影响的行数
//       int effectedNum = localAuthDao.updateLocalAuth(1L,username,password,password+"new",now);
//       assertEquals(1,effectedNum);
       //查询出该条平台账号的最新消息
       LocalAuth localAuth =  localAuthDao.queryLocalByUserId(1L);
       //打印出信息
       System.out.println(localAuth);

   }
}
