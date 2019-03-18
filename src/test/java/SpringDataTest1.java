import com.test.dao.CustomerDao;
import com.test.dao.CustomerExtDao;
import com.test.pojo.Customer;
import com.test.pojo.CustomerExt;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * 描述：测试一对一
 * </p>
 *
 * @author QinLiNa
 * @data 2019/3/18
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class SpringDataTest1 {
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private CustomerExtDao extDao;

    @Test
    //保存客户|客户的扩展信息
    @Transactional
    @Commit
    public void fun1() {
        //创建客户
        Customer c = new Customer();
        c.setCustName("Hello World !!!");
        //创建扩展信息
        CustomerExt ext = new CustomerExt();
        ext.setInfo("你好!");
        //表达关系
        c.setExt(ext);
        //保存客户|扩展信息
        customerDao.save(c);
        extDao.save(ext);
    }

    @Test
    //删除客户并删除客户关联的扩展信息
    @Transactional
    @Commit
    public void fun2() {
        customerDao.delete(6L);
        extDao.delete(1L);
    }

}
