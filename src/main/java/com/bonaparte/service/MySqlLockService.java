package com.bonaparte.service;

import com.bonaparte.dao.mapper.CreditLockMapper;
import com.bonaparte.entity.CreditLock;
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

    @Transactional
    public void getMysqlLock(){
        String prefix = "center_lock_";
        CRC32 crc32 = new CRC32();
        Long hash = crc32.getValue();
        Integer len = hash.toString().length();
        String slot = hash.toString().substring(len - 1);
        String centerLockKey = prefix + slot;
        creditLockMapper.queryForupdate(centerLockKey);
        CreditLock creditLock = new CreditLock();
        creditLock.setCount(1);
        creditLock.setCreateTime(new Date());
        creditLock.setDescription("mysql锁");
        creditLockMapper.insert(creditLock);

    }
}
