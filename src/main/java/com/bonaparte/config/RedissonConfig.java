package com.bonaparte.config;

import com.bonaparte.constant.RedisProps;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by yangmingquan on 2018/9/25.
 */
@Configuration
public class RedissonConfig {
    @Autowired
    private RedisProps redisProps;

    @Bean
    public RedissonClient redissonClient(){
        Config config = new Config();
        SingleServerConfig serversConfig = config.useSingleServer().setAddress(redisProps.getAddressname())
                .setTimeout(redisProps.getCommandTimeout())
                .setConnectionPoolSize(redisProps.getPoolMaxTotal())
                .setConnectionMinimumIdleSize(redisProps.getPoolMaxIdle());
        return Redisson.create(config);
    }
}
