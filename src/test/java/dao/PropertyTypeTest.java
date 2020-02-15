package dao;

import entity.ArticleType;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * @author HABIN
 * @date 2020/2/16 0:11
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PropertyTypeTest extends BaseTest {

    @Autowired
    private ArticleTypeDao articleTypeDao;
    /**
     * 新增类型
     */
    @Test
    public void insertType(){
        String[] typeList = {"钱包","钥匙","宠物","卡类/证照","数码产品","手袋/挎包",
                "衣服/鞋帽","首饰/挂件","行李/包裹","书籍/文件","其他"};
        ArticleType articleType = new ArticleType();
        for (String typename : typeList){
            articleType.setTypeName(typename);
            articleType.setPriority(1);
            articleType.setCreateTime(new Date());
            int count = articleTypeDao.insert(articleType);
            if (count == 1){
                System.out.println("增加"+typename+"成功"+"-----");
            } else {
                System.out.println("增加"+typename+"失败"+"-----");
            }

        }
    }
    @Test
    public void queryCity(){
        int id = 1;
        String name = "钱包";
        int pro = 1;
        String cityName = null;
        int type = 0;
        List<ArticleType> cityList = articleTypeDao.queryArticleType(id,name,pro);
        for (ArticleType articleType:cityList){
            System.out.println(articleType.toString());
        }
    }
}
