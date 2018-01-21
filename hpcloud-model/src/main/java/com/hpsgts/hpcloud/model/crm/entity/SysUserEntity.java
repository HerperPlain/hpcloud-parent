package com.hpsgts.hpcloud.model.crm.entity;

import org.hibernate.validator.constraints.Email;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import java.util.Date;

/**
 * @author 黄朴（Hp.Plain)
 * @date 2018-1-21
 * @packageName com.hpsgts.hpcloud.model.crm.entity
 * @projectName hpcloud-parent
 * @company G翔时代技术服务有限公司
 */
@Table(name = "sys_user")
@Entity
public class SysUserEntity {

    /** 主键id */
    @Id
    @Column(name = "id", unique = true)
    private String id;

    /** 用户业务编码 */
    @Column(name = "user_code")
    private String userCode;

    /** 用户登录密码 */
    @Column(name = "password")
    private String password;

    /** 用户登录名 */
    @Column(name = "login_name")
    private String loginName;

    /** 用户名称 */
    @Column(name = "user_name")
    private String userName;
    /** 昵称 */
    @Column(name = "user_nickname")
    private String userNickname;

    /** 年龄 */
    @Column(name = "age",length = 10)
    @Min(18)
    private int age;

    /** 邮箱 */
    @Column(name = "email")
    @Email
    private String email;

    /** 手机号 */
    @Column(name = "phone_num",length = 11)
    private int phoneNum;

    /** 创建人 */
    @Column(name = "create_man")
    private String createMan;

    /** 创建时间 */
    @Column(name = "create_time")
    private Date createTime;

    /** 修改名称 */
    @Column(name = "modify_man")
    private String modifyMan;

    /** 修改时间 */
    @Column(name = "modify_time")
    private Date modifyTime;

    /** 用户当前状态 */
    @Column(name = "state")
    private int state;

    /** 备注说明 */
    @Column(name = "remark")
    private String remark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(int phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getCreateMan() {
        return createMan;
    }

    public void setCreateMan(String createMan) {
        this.createMan = createMan;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getModifyMan() {
        return modifyMan;
    }

    public void setModifyMan(String modifyMan) {
        this.modifyMan = modifyMan;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "SysUserEntity{" +
                "id='" + id + '\'' +
                ", userCode='" + userCode + '\'' +
                ", password='" + password + '\'' +
                ", loginName='" + loginName + '\'' +
                ", userName='" + userName + '\'' +
                ", userNickname='" + userNickname + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", phoneNum=" + phoneNum +
                ", createMan='" + createMan + '\'' +
                ", createTime=" + createTime +
                ", modifyMan='" + modifyMan + '\'' +
                ", modifyTime=" + modifyTime +
                ", state=" + state +
                ", remark='" + remark + '\'' +
                '}';
    }
}
