package com.imooc.sell.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
@Slf4j
public class RedisLock {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
    * 加锁
    * @Param key
    * @Param value 当前时间+超时时间
    * return
    **/
    public boolean lock(String key, String value) {
        if (stringRedisTemplate.opsForValue().setIfAbsent(key, value)) {
            return true;
        }
        /**
         * 下面那些代码很重要，必须加下，可以防止出现死锁情况。
         * 假如lock（）和unlock（）之间的数据库操作出现了偶然间异常，抛出异常，
         * unlock（）就会不会执行，
         * 而又没有下面代码，就会出现死锁。
         */
        String currentValue = stringRedisTemplate.opsForValue().get(key);
        //如果锁过期
        if (!StringUtils.isEmpty(currentValue) &&
                Long.parseLong(currentValue) < System.currentTimeMillis()) {
            //获取上一个锁的时间,并重新设value
            String oldValue = stringRedisTemplate.opsForValue().getAndSet(key, value);
            if (!StringUtils.isEmpty(oldValue) && oldValue.equals(currentValue)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 解锁
     * @Param key
     * @Param value
     */
    public void unlock(String key, String value) {
        String currentVaule = stringRedisTemplate.opsForValue().get(key);
        try {
            if (!StringUtils.isEmpty(currentVaule) && currentVaule.equals(value)) {
                stringRedisTemplate.opsForValue().getOperations().delete(key);
            }
        } catch (Exception e) {
            log.error("【redis分布式锁】解锁异常，{}",e);
        }

    }
}
