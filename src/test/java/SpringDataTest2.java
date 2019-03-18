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

import java.util.Set;

/**
 * 描述：测试一对一
 * </p>
 *
 * @author QinLiNa
 * @data 2019/3/18
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class SpringDataTest2 {
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private LinkManDao linkManDao;

    @Test
    @Transactional
    @Commit
    //保存客户以及关联的联系人
    public void fun1() {
        //1 创建客户
        Customer customer = new Customer();
        customer.setCustName("Hello  World!!!");
        //2 创建联系人
        LinkMan lm1 = new LinkMan();
        lm1.setLkmName("张三");
        LinkMan lm2 = new LinkMan();
        lm2.setLkmName("李四四");
        //3 维护关系
        //表达多对一
        lm1.setCustomer(customer);
        lm2.setCustomer(customer);
        //4 调用Dao保存
        customerDao.save(customer);
        linkManDao.save(lm1);
        linkManDao.save(lm2);
    }


    @Test
    @Transactional
    @Commit
    //删除客户以及关联的联系人
    public void fun2() {
        //1.删除客户
        customerDao.delete(9l);
        //2 删除客户关联的联系人
        linkManDao.delete(3l);
        linkManDao.delete(4l);
    }

    @Test
    @Transactional
    @Commit
    //删除客户以及关联的联系人
    public void fun3() {
        //0.查询出要删除的客户
        Customer customer = customerDao.findOne(8L);
        //通过客户获得客户关联的联系人对象
        Set<LinkMan> linkMen = customer.getLinkMen();
        //1.删除客户
        customerDao.delete(customer);
        //2 删除客户关联的联系人
        linkManDao.delete(linkMen);
    }

    @Test
    @Transactional
    @Commit
    //移除关系,李四四不再担任联系人
    public void fun4() {
        //1 查询要操作的联系人
        LinkMan lm = linkManDao.findOne(6l);
        //2 表达关系
        lm.setCustomer(null);
        //3 修改
        linkManDao.save(lm);
    }

    @Test
    @Transactional
    @Commit
    //建立关系,李四四重新担任联系人
    public void fun5() {
        //1 查询要操作的联系人
        LinkMan lm = linkManDao.findOne(6l);
        //2 查询关联的客户
        Customer customer = customerDao.findOne(10L);
        //3 表达关系
        lm.setCustomer(customer);
        //4 修改
        linkManDao.save(lm);
    }
}
