import com.test.dao.CustomerDao;
import com.test.dao.LinkManDao;
import com.test.pojo.Customer;
import com.test.pojo.LinkMan;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Join;
import java.util.Set;

/**
 * 描述：多表查询
 * </p>
 *
 * @author QinLiNa
 * @data 2019/3/18
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class SpringDataTest3 {
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private LinkManDao linkManDao;

    @Test
    @Transactional
    @Commit
    //查询id为10的客户关联的联系人
    /*
        对象属性导航查询的查询策略:
            延迟加载(默认)
            立即加载
     */
    public void fun1() {
        //1 查询id为10的客户
        Customer customer = customerDao.findOne(10l);
        System.out.println("---------------------------------------------------------------------------------");
        //2 根据客户中的联系人属性获得联系人对象
        Set<LinkMan> linkMen = customer.getLinkMen();
        //3 使用
        System.out.println(linkMen);
    }


    @Test
    @Transactional
    @Commit
    //spec查询: 查询名称中包含健字的联系人所属的客户
    public void fun2() {

        customerDao.findAll((root,query,builder)->{
            //获得与客户关联的联系人
            Join<Object, Object> join = root.join("linkMen");
            //根据联系人对象lkmName属性执行查询
            return  builder.like(join.get("lkmName").as(String.class),"%张%");
        }).forEach(c -> System.out.println(c));

    }
}
