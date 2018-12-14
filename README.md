# DistributeLockTree

分布式锁
   1:zookeeper实现分布式锁
   2:redission实现分布式锁
   3:mysql实现分布式锁

<pre>
Zookeeper分布式锁

零时节点监听：
      
      一旦当前这个节点的ID最小就获取锁。
</pre>

<pre>
Mysql

     实现锁的逻辑

         1）在Mysql中建一个表，这个表主键唯一。
         2）一个系统去处理业务时就往数据库表中添加数据。两个系统的业务ID是一致的，业务ID又是唯一键值，所以当一个系统添加数据时，另
      一个系统是无法往表中添加数据的。插入数据成功的系统就可以操作业务逻辑，插入失败的系统就不能操作执行业务逻辑了，这就实现了数据库锁。
            存在问题及决绝思路：
                  1）插入数据成功的系统宕机了，这条数据就无法删除了，就死锁了。
                     解决：
                         可以有一个定时跑批的方案，这个数据插入的时间要有记录，跑批任务定时去删除已经加锁很久的数据行记录。
                  2）这个锁不可重入，也就是说这个系统只能加一次锁，同一个提供再次进行加过时是不行的。
                         加一个字段用来记录加锁的次数，每次加锁加一，每次解锁减一，释放锁时就是该字段为0才可以
                  3）数据库宕机了怎么处理：
                         数据库复制。不允许单点部署。
</pre>

<pre>
Redisson

          Redisson提供了基于Redis无缝整合到Spring框架的能力，redisson是一个用于连
      接redis的java客户端工作，相对于jedis，大量整合了Netty框架，是一个采用异步模型，
      大量使用netty promise编程的客户端框架。
      
      Redisson功能：
          1）支持同步/异步/异步流/管道流方式连接。
          2）多样化数据序列化
          3）集合数据分片
          5）分布式对象
          6）分布式集合
          7）分布式锁和同步器
          8）分布式服务
          9）独立节点模式
          10）三方框架整合

      与Jedis客户端对比：
          1）Jedis是Redis的Java实现的客户端，其API提供了比较全面的Redis命令的支持；Redis
             实现了分布式和可扩展的Java数据结构，和Jedis相比，功能较为简单，不支持字符串操作，
             不支持排序，事务，管道，分区等Redis特性，Redisson的宗旨是促进使用者对Redis的
             关注分离，从而让使用者能够将精力集中的放在处理业务逻辑上。
          2）编程模型
             Jedis中的方法调用时比较底层的暴露的Redis的API，也就是Jedis中的Java方法基本和
             Redis的api保持一致，了解Redis的API，也就能熟练的使用Jedis，而Redisson中的方法
             则是进行比较高的抽象，每个方法调用可能进行了一个或多个Redis方法调用。
          3）可伸缩性
             Jedis使用阻塞IO,且其方法调用都是同步的，程序流需要等到sockets处理完IO才能执行，
             不支持异步，Jedis客户端实例不是线程安全的;
             Redisson使用非阻塞的IO和基于Netty框架的事件驱动的通信层，其方法调用时异步的。
             Redisson的API是线程安全的。

             public V set(int index, V element) {
                  checkIndex(index);
                  RedisConnection<String, Object> conn = connectionManager.connection();
				        try {
				            while (true) {
				                conn.watch(getName());
				                V prev = (V) conn.lindex(getName(), index);
				 
				                conn.multi();
				                conn.lset(getName(), index, element);
				                if (conn.exec().size() == 1) {
				                    return prev;
				                }
				            }
				        } finally {
				            connectionManager.release(conn);
				        }
    	     }

             通过使用: watch multi exec来保证线程安全

          5）数据结构
             Jedis仅仅支持基本的数据类型：如: String,Hash,List,Set,SortedSet
             Redisson不仅提供了一系列分布式Java常用对象，基本可以预Java的基本数据结构通用，还提供了许多分布式服务：其中包括：
                         BitSet,
                         Set,
                         MultiMap,
                         SortedSet,
                         Map,
                         List,
                         Queue,
                         BlockingQueue,
                         Deque,
                         BlockingDeque,
                         Semaphore,
                         Lock,
                         AtomicLong,
                         CountDownLatch,
                         Publish/Subscribe,
                         Bloom filter,
                         Remote Service,
                         Spring cache,
                         Executor Service,
                         Live Object service,
                         Scheduler Service
             在开发中，Redisson能提供更便捷的方法
</pre>
