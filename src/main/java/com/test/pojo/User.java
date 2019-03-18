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
@Entity
@Table(name = "sys_user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name = "user_id")
    private long userId;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;

    //多对多 => 用户有多个角色
    @ManyToMany
    /*
    @JoinTable配置中间表|外键列
        name属性:中间表表名
        joinColumns属性: 配置中间表中一个外键列的信息
        inverseJoinColumns属性:配置中间表中另一个外键列的信息

     */
    @JoinTable(name = "t_user_role",
            joinColumns = @JoinColumn(name = "uid",referencedColumnName = "user_id"),
            inverseJoinColumns =@JoinColumn(name = "rid",referencedColumnName = "role_id") )
    private Set<Role> roles = new HashSet<>();

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
