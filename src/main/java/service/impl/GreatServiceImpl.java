package service.impl;

import dao.GreatDao;
import entity.Great;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.GreatService;

/**
 * @author HABIN
 * @date 2020/3/20 3:53
 * 点赞业务层实现类
 */
@Service
public class GreatServiceImpl implements GreatService {

    @Autowired
    private GreatDao greatDao;

    @Override
    public int insert(Great great) {
        return greatDao.insert(great);
    }

    @Override
    public int deleteByUserId(int acId, int userId) {
        return greatDao.deleteByUserId(acId,userId);
    }
}
