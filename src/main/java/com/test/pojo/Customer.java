package com.test.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 描述：待描述
 * </p>
 *
 * @author QinLiNa
 * @data 2019/3/18
 */

/**
 * @Entity：表示当前类是实体类
 */
@Entity
/**
 * @Table: 表达实体类与哪张表对应
 *  name属性：表名
 */
@Table(name = "cst_customer")
public class Customer implements Serializable {
    /**
     * @Id: 标示性注解, 标识当前类中哪个是OID(Object ID)
     * 注意: JPA中不允许没有主键的表存在.
     */
    @Id
    /**
     @Column: 用于表达属性与列对应的注解
     name属性: 属性具体与哪一个列对应,可选属性 => 默认值使用属性名作为列名
     updatable属性: 指定该属性在生成update语句时是否包含. 默认值:true
     insertable属性:指定该属性在生成insert语句时是否包含. 默认值:true
     unique属性: 该属性是否使用唯一约束 默认值:false
     nullable属性:该属性是否使用非空约束 默认值:false
     length属性:指定该属性最大长度(与数据库对应) 默认值:最大长度
     */
    @Column
    /**
     @GeneratedValue 用于指定主键生成策略
      *一. GenerationType.IDENTITY : 使用数据库的主键自增
     @GeneratedValue(strategy = GenerationType.IDENTITY)
      *二. GenerationType.SEQUENCE: 使用Oracle中的序列生成主键值
     @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "abc")
     //指定序列使用seq_customer,并且创建时从1开始计数
     @SequenceGenerator(name = "abc",sequenceName = "seq_customer",initialValue = 1)
     三.GenerationType.TABLE : 使用表来记录主键值,性质类似于sequence.但是主键值由表记录,所以该策略不受数据库品牌限制
     @GeneratedValue(strategy = GenerationType.TABLE,generator = "abc")
     @TableGenerator(name = "abc",table = "table_sequence",pkColumnName = "gen_table" ,valueColumnName = "gen_id",pkColumnValue = "customer_id",initialValue = 0,allocationSize = 1)
     四.GenerationType.AUTO : 自动从上面3个策略选择,一般都会选择Table策略
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long custId;
    private String custName;
    private String custSource;
    private String custIndustry;
    private String custLevel;
    private String custAddress;
    private String custPhone;

    /*一对一
    cascade属性: 级联操作策略配置
        CascadeType.PERSIST:级联保存. 保存A时级联保存关联的B
        CascadeType.REMOVE: 级联删除. 删除A时级联删除A关联的B
        CascadeType.ALL: 包含所有级联操作
     */
    @OneToOne(cascade = {CascadeType.ALL})
    /*
        @JoinColumn:外键配置
            name:外键列列名
            referencedColumnName:外键列引用的主键列名
     */
    @JoinColumn(name = "ext_id", referencedColumnName = "extId")
    private CustomerExt ext;

    /*
    一对多关系
    mappedBy: 填写"我"在对方实体中表达关系的属性名
        fetch:指定客户加载关联联系人集合的加载策略
                FetchType.LAZY : 默认值,懒加载 .查询客户时,不会立即查询客户关联的联系人,等使用联系人时才进行查询
                FetchType.EAGER: 立即加载,查询客户时,立即查询客户关联的联系人,不论是否使用.
     */
    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
    private Set<LinkMan> linkMen = new HashSet<>();


    public Set<LinkMan> getLinkMen() {
        return linkMen;
    }

    public void setLinkMen(Set<LinkMan> linkMen) {
        this.linkMen = linkMen;
    }

    public CustomerExt getExt() {
        return ext;
    }

    public void setExt(CustomerExt ext) {
        this.ext = ext;
    }

    public long getCustId() {
        return custId;
    }

    public void setCustId(long custId) {
        this.custId = custId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustSource() {
        return custSource;
    }

    public void setCustSource(String custSource) {
        this.custSource = custSource;
    }

    public String getCustIndustry() {
        return custIndustry;
    }

    public void setCustIndustry(String custIndustry) {
        this.custIndustry = custIndustry;
    }

    public String getCustLevel() {
        return custLevel;
    }

    public void setCustLevel(String custLevel) {
        this.custLevel = custLevel;
    }

    public String getCustAddress() {
        return custAddress;
    }

    public void setCustAddress(String custAddress) {
        this.custAddress = custAddress;
    }

    public String getCustPhone() {
        return custPhone;
    }

    public void setCustPhone(String custPhone) {
        this.custPhone = custPhone;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "custId=" + custId +
                ", custName='" + custName + '\'' +
                '}';
    }
}
