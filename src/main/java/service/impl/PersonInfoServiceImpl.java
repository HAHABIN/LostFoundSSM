package service.impl;

import dao.PersonInfoDao;
import entity.PersonInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.PersonInfoService;

import java.sql.Date;
import java.util.List;

/**
 * @author HABIN
 * @date 2020/2/7 15:39
 *
 * 用户信息管理业务层接口实现类
 */
@Service
public class PersonInfoServiceImpl implements PersonInfoService {

    @Autowired
    private PersonInfoDao personInfoDao;

    @Override
    public PersonInfo queryPersonInfoById(long userId) {
        return personInfoDao.queryPersonInfoById(userId);
    }

    @Override
    public PersonInfo insertPersonInfo(int userId, String username, String email) {
        //设置用户信息
        PersonInfo personInfo = new PersonInfo();
        personInfo.setUserId(userId);
        personInfo.setNickname(username);
        personInfo.setEmail(email);
        personInfo.setUserType(1);
        personInfo.setHelpTimes(0);
        personInfo.setCreateTime(new Date(System.currentTimeMillis()));
        personInfoDao.insertPersonInfo(personInfo);
        return personInfo;
    }

    @Override
    public int updatePersonInfo(PersonInfo personInfo) {
        return personInfoDao.updatePersonInfo(personInfo);
    }

    @Override
    public List<PersonInfo> query() {
        return personInfoDao.query();
    }

    @Override
    public List<PersonInfo> queryByIdOrName(long userId, String nickname) {
        return personInfoDao.queryByIdOrName(userId,nickname);
    }

    @Override
    public int delete(long userId) {
        return personInfoDao.deleteById(userId);
    }

    @Override
    public int batchDeletePsrsonInfo(List<Long> lineIdList) {
        return 0;
    }

}
