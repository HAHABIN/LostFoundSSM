package service.impl;

import dao.PersonInfoDao;
import entity.PersonInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.PersonInfoService;

import java.util.Date;

/**
 * @author HABIN
 * @date 2020/2/7 15:39
 */
@Service
public class PersonInfoServiceImpl implements PersonInfoService {

    @Autowired
    private PersonInfoDao personInfoDao;

    @Override
    public PersonInfo queryPersonInfoById(long userId) {
        return null;
    }

    @Override
    public PersonInfo insertPersonInfo(int userId, String username, String email) {
        //设置用户信息
        PersonInfo personInfo = new PersonInfo();
        personInfo.setUserId(userId);
        personInfo.setName(username);
        personInfo.setEmail(email);
        personInfo.setUserType(2);
        personInfo.setHelpTimes(0);
        personInfo.setCreateTime(new Date());
        personInfoDao.insertPersonInfo(personInfo);
        return personInfo;
    }

    @Override
    public int updatePersonInfo(PersonInfo personInfo) {
        return personInfoDao.updatePersonInfo(personInfo);
    }

}
