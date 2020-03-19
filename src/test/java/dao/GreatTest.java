package dao;

import entity.Great;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;

/**
 * @author HABIN
 * @date 2020/3/20 3:41
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GreatTest extends BaseTest{


    @Autowired
    private GreatDao greatDao;
    @Test
    public void test(){
        Great great = new Great();
        great.setAcId(17352172);
        great.setUserId(1);
        int insert = greatDao.insert(great);
        assertEquals(insert,1);
    }
    @Test
    public void delete(){
        greatDao.deleteByUserId(17352172,1);
    }

}
