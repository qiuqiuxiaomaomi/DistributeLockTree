package com.bonaparte.service;

import org.redisson.RedissonMultiLock;
import org.redisson.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Created by yangmingquan on 2018/9/25.
 */
@Service
public class DistributeLockService {
    @Autowired
    RedissonClient redissonClient;

    // 锁的同步执行
    public void syncReentrantLock(){
        System.out.println("111111111111");
        RLock rLock = redissonClient.getLock("redissonlock");
        try{
            System.out.println("222222222222");
            boolean res = rLock.tryLock(3, 10, TimeUnit.SECONDS);
            if (res == true){
                System.out.println("333333333333");
            }
            System.out.println("55555555555");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            System.out.println("666666666666");
            rLock.unlock();
        }
        System.out.println("7777777777777");
    }

    //锁的异步执行
    public void asyncReentrantLock(){
        RLock rLock = redissonClient.getLock("redissonlock");
        try{
            System.out.println("111111111");
            rLock.lockAsync();
            System.out.println("2222222222");
            rLock.lockAsync(10, TimeUnit.SECONDS);
            System.out.println("3333333333");
            Future<Boolean> future = rLock.tryLockAsync(3, 10, TimeUnit.SECONDS);
            System.out.println("5555555555");
            if (future.get()) {
                System.out.println("6666666666");
            }
            System.out.println("777777777");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }finally {
            System.out.println("888888888");
            rLock.unlock();
        }
        System.out.println("99999999");
    }

    public void fairLock(){
        RLock rLock = redissonClient.getFairLock("redissonlock");
        try{
            rLock.lock();
            rLock.lock(10, TimeUnit.SECONDS);
            boolean res = rLock.tryLock(100, 10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            rLock.unlock();
        }
    }

    public void fairAsyn(){
        RLock rLock = redissonClient.getFairLock("redissonlock");
        rLock.lockAsync();
        rLock.lockAsync(10, TimeUnit.SECONDS);
        Future<Boolean> future = rLock.tryLockAsync(100, 10, TimeUnit.SECONDS);
    }

    public void multiLock(){
        RLock rLock1 = redissonClient.getLock("lock1");
        RLock rLock2 = redissonClient.getLock("lock2");
        RLock rLock3 = redissonClient.getLock("lock3");
        RedissonMultiLock redissonMultiLock = new RedissonMultiLock(rLock1, rLock2, rLock3);
        try{
            redissonMultiLock.lock();
            boolean res = redissonMultiLock.tryLock(100, 10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            redissonMultiLock.unlock();
        }
    }

    public void readWriteLock(){
        RReadWriteLock rReadWriteLock = redissonClient.getReadWriteLock("redissonlock");

    }

    public void semaphore(){
        RSemaphore semaphore = redissonClient.getSemaphore("rediss");
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void permitExpireSemaphore(){
        RPermitExpirableSemaphore semaphore = redissonClient.getPermitExpirableSemaphore("semaphore");
        try {
            String permitId = semaphore.acquire();
            semaphore.release(permitId);
        } catch (InterruptedException e){

        }
    }

    public void countDownLatch(){
        RCountDownLatch latch = redissonClient.getCountDownLatch("countDownLatch");
        try {
            latch.countDown();
            latch.await();
        }catch (InterruptedException e){

        }
    }
}
