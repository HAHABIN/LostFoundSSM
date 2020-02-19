package dao;

import entity.ArticleInfo;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import utils.StringUtils;

import java.util.Date;
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
//        int id = (int) ((Math.random() * 9 + 1) * 10000000);
//        int userId = 6111956;
//        int typeId = 2;
//        String name = "商品标题5";
//        Date findTime = new Date();
//        String address_content = "广东省汕头市潮南区ddd";
//        String description = "物品详细3";
//        int status = 2;
//        int recstatus = 2;
//        Date createTime = new Date();
//        ArticleInfo articleInfo = new ArticleInfo();
//        articleInfo.setId(id);
//        articleInfo.setUserId(userId);
//        articleInfo.setTypeId(typeId);
//        articleInfo.setFindTime(findTime);
//        articleInfo.setAddressContent(address_content);
//        articleInfo.setName(name);
//        articleInfo.setDescription(description);
//        articleInfo.setStatus(status);
//        articleInfo.setRecordStatus(recstatus);
//        articleInfo.setCreateTime(createTime);
//        int insert = articleInfoDao.insert(articleInfo);
//        assertEquals(1, insert);

    }
    @Test
    public void query() {
        List<ArticleInfo> artList = articleInfoDao.queryArticleInfo(-1, -1, -1, "福",null, 1, 1);
        if (!artList.isEmpty() && artList != null){
            for (ArticleInfo articleInfo : artList) {
                System.out.println(articleInfo.toString());
            }
        } else {
            System.out.println("获取失败");
        }

    }
}
