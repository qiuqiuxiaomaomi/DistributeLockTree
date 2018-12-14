package com.bonaparte.service;

import com.bonaparte.dao.mapper.CreditLockMapper;
import com.bonaparte.entity.CreditLock;
import lombok.Data;
import org.apache.catalina.security.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.zip.CRC32;

/**
 * Created by yangmingquan on 2018/9/25.
 * 利用ConcurrentHashmap
 */
@Service
public class MySqlLockService {
    @Autowired
    private CreditLockMapper creditLockMapper;

    public void getMysqlLock(Integer id){
        CreditLock creditLock = new CreditLock();
        creditLock.setId(id);
        creditLock.setCreateTime(new Date());
        creditLock.setDeadline(new Date());
        creditLock.setDescription("锁");
        creditLock.setLockName("锁" + id);
        creditLockMapper.insert(creditLock);
    }

    public void releaseMysqlLock(Integer id){
        creditLockMapper.deleteByPrimaryKey(id);
    }
}
