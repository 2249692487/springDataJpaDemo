package com.test.pojo;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 描述：待描述
 * </p>
 *
 * @author QinLiNa
 * @data 2019/3/18
 */
@Entity
@Table(name = "cst_customer_ext")
public class CustomerExt implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long extId;
    private String info;
    /*
    @OneToOne : 一对一
        mappedBy : 当前实体在对方实体中,表达一对一关系的属性名
     */
    @OneToOne(mappedBy = "ext")
    private Customer customer;

    public long getExtId() {
        return extId;
    }

    public void setExtId(long extId) {
        this.extId = extId;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
