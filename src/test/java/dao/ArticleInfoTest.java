package dao;

import entity.ArticleInfo;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import utils.StringUtils;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

/**
 * @author HABIN
 * @date 2020/2/19 20:32
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ArticleInfoTest extends BaseTest {
    @Autowired
    private ArticleInfoDao articleInfoDao;

    @Test
    public void insert() {
//        Date date = StringUtils.strTodate(1582123397000);
//        System.out.println(date);
        int id = (int) ((Math.random() * 9 + 1) * 10000000);
        int userId = 6111956;
        int typeId = 2;
        String phone = "1553010155";
        String address_content = "广东省汕头市潮南区ddd";
        String description = "物品详细3";
        int status = 2;
        int recstatus = 2;
        ArticleInfo articleInfo = new ArticleInfo();
        articleInfo.setId(id);
        articleInfo.setUserId(userId);
        articleInfo.setTypeId(typeId);
        articleInfo.setFindTime(new java.sql.Date(System.currentTimeMillis()));
        articleInfo.setAddressContent(address_content);
        articleInfo.setPhone(phone);
        articleInfo.setDescription(description);
        articleInfo.setStatus(status);
        articleInfo.setRecordStatus(recstatus);
        articleInfo.setCreateTime(new Date(System.currentTimeMillis()));
        int insert = articleInfoDao.insert(articleInfo);
        assertEquals(1, insert);

    }
    @Test
    public void query() {
        List<ArticleInfo> artList = articleInfoDao.searchArInfo( "汕头",null,1,20);
        if (!artList.isEmpty() && artList != null){
            for (ArticleInfo articleInfo : artList) {
                System.out.println("类型status："+articleInfo.getStatus()+"类型status:"+articleInfo.getRecordStatus()+"--");
            }
        } else {
            System.out.println("获取失败");
        }
//        String phone = "13415083273";
//        Integer decode = Integer.decode(phone);
//        System.out.println(decode);
    }
}
