package com.bonaparte.dao.mapper;

import com.bonaparte.entity.CreditLock;
import com.bonaparte.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface CreditLockMapper extends MyMapper<CreditLock> {

    public Map<String, Object> queryForupdate(@Param("lockName") String lockName);
}