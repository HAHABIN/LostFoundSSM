package dao;


import entity.PersonInfo;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.sql.Date;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PersonInfoTest extends BaseTest {


//    @Autowired
//    private PersonInfoDao personInfoDao;


    /**
     * 新增配置用户
     */
    @Test
    public void testInsertPersonInfo() throws Exception{
        //设置新增的用户信息
        PersonInfo personInfo = new PersonInfo();
        personInfo.setNickname("JackyTallow");
        personInfo.setGender("男");
        personInfo.setUserType(1);
        personInfo.setLastEditTime(new Date(System.currentTimeMillis()));
        personInfo.setCreateTime(new Date(System.currentTimeMillis()));
        personInfo.setHelpTimes(1);
//        int effectedNum = personInfoDao.insertPersonInfo(personInfo);
//        assertEquals(1, effectedNum);
    }


    /**
     * 查询操作测试
     */
    @Test
    public void testBQueryPersonInfoById() {
        long userId = 1;
        // 查询Id为1的用户信息
//        PersonInfo person = personInfoDao.queryPersonInfoById(userId);
//        System.out.println(person.getName());
    }

}
