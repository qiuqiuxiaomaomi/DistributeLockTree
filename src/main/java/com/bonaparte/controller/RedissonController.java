package com.bonaparte.controller;

import com.bonaparte.service.DistributeLockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yangmingquan on 2018/9/25.
 */
@RestController
public class RedissonController {
    @Autowired
    public DistributeLockService distributeLockService;

    @RequestMapping("/redisson")
    public Object redissonLock(){
        distributeLockService.syncReentrantLock();
        distributeLockService.asyncReentrantLock();
        return null;
    }
}
