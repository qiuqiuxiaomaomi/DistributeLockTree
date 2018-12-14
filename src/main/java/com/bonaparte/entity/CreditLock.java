package com.bonaparte.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "credit_lock")
public class CreditLock {
    /**
     * 自增索引id
     */
    @Id
    private Integer id;

    /**
     * 锁名key值
     */
    @Column(name = "lock_name")
    private String lockName;

    /**
     * 计数器
     */
    private Integer count;

    /**
     * 锁过期时间
     */
    private Date deadline;

    /**
     * 值描述
     */
    private String description;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 修改时间
     */
    @Column(name = "modify_time")
    private Date modifyTime;

    /**
     * 记录状态，0：无效，1：有效
     */
    private Byte status;

    /**
     * 获取自增索引id
     *
     * @return id - 自增索引id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置自增索引id
     *
     * @param id 自增索引id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取锁名key值
     *
     * @return lock_name - 锁名key值
     */
    public String getLockName() {
        return lockName;
    }

    /**
     * 设置锁名key值
     *
     * @param lockName 锁名key值
     */
    public void setLockName(String lockName) {
        this.lockName = lockName;
    }

    /**
     * 获取计数器
     *
     * @return count - 计数器
     */
    public Integer getCount() {
        return count;
    }

    /**
     * 设置计数器
     *
     * @param count 计数器
     */
    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     * 获取锁过期时间
     *
     * @return deadline - 锁过期时间
     */
    public Date getDeadline() {
        return deadline;
    }

    /**
     * 设置锁过期时间
     *
     * @param deadline 锁过期时间
     */
    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    /**
     * 获取值描述
     *
     * @return description - 值描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置值描述
     *
     * @param description 值描述
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取修改时间
     *
     * @return modify_time - 修改时间
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * 设置修改时间
     *
     * @param modifyTime 修改时间
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * 获取记录状态，0：无效，1：有效
     *
     * @return status - 记录状态，0：无效，1：有效
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 设置记录状态，0：无效，1：有效
     *
     * @param status 记录状态，0：无效，1：有效
     */
    public void setStatus(Byte status) {
        this.status = status;
    }
}