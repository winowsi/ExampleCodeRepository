# Read Write Lock读写锁
**适用于高并发环境使用**

1、不加锁在多线程环境下读写
    
    static  class  MyCache{
        //ReadWriteLock的实现类ReentrantReadWriteLock
            private ReentrantReadWriteLock readWriteLock=new ReentrantReadWriteLock();
            //volatile 保证线程的可见性 不保证原子性 禁止指令重排
            private static volatile Map<String,Object> map=new HashMap<>();
            //不加锁的写入方法
            private  static void set(String key ,Object value){
                //获取正在写入线程的名字
                System.out.println("线程"+Thread.currentThread().getName()+"\t正在写入...o(U_U)o...");
                map.put(key,value);
                //模拟网络延迟
                try { TimeUnit.MILLISECONDS.sleep(300); }catch (InterruptedException e) { e.printStackTrace(); }
                System.out.println("线程"+Thread.currentThread().getName()+"\t写入完成...o(n_n)o...✅");
            }
            //不加锁的读取方法
            private static void unLockGet(String key){
                System.out.println("线程"+Thread.currentThread().getName()+"\t正在读取...o(K_K)o...");
                map.get(key);
                try { TimeUnit.MILLISECONDS.sleep(300); }catch (InterruptedException e) { e.printStackTrace(); }
                System.out.println("线程"+Thread.currentThread().getName()+"\t读取完成...o(p_p)o...✅");
            }
    }
执行结果:

    线程8	正在写入...o(U_U)o...
    线程3	正在写入...o(U_U)o...
    线程9	正在写入...o(U_U)o...
    线程6	正在写入...o(U_U)o...
    线程5	正在写入...o(U_U)o...
    线程2	正在写入...o(U_U)o...
    线程1	正在写入...o(U_U)o...
    线程7	正在写入...o(U_U)o...
    线程0	正在写入...o(U_U)o...
    线程4	正在写入...o(U_U)o...
    线程5	写入完成...o(n_n)o...✅
    线程9	写入完成...o(n_n)o...✅
    线程1	写入完成...o(n_n)o...✅
    线程7	写入完成...o(n_n)o...✅
    线程3	写入完成...o(n_n)o...✅
    线程5	正在读取...o(K_K)o...
    线程2	写入完成...o(n_n)o...✅
    线程1	正在读取...o(K_K)o...
    线程6	写入完成...o(n_n)o...✅
    线程8	写入完成...o(n_n)o...✅
    线程0	写入完成...o(n_n)o...✅
    线程8	正在读取...o(K_K)o...
    线程7	正在读取...o(K_K)o...
    线程6	正在读取...o(K_K)o...
    线程2	正在读取...o(K_K)o...
    线程9	正在读取...o(K_K)o...
    线程3	正在读取...o(K_K)o...
    线程4	写入完成...o(n_n)o...✅
    线程4	正在读取...o(K_K)o...
    线程0	正在读取...o(K_K)o...
    线程1	读取完成...o(p_p)o...✅
    线程3	读取完成...o(p_p)o...✅
    线程2	读取完成...o(p_p)o...✅
    线程7	读取完成...o(p_p)o...✅
    线程9	读取完成...o(p_p)o...✅
    线程0	读取完成...o(p_p)o...✅
    线程4	读取完成...o(p_p)o...✅
    线程5	读取完成...o(p_p)o...✅
    线程6	读取完成...o(p_p)o...✅
    线程8	读取完成...o(p_p)o...✅
2、加锁在多线程环境下读写

       static  class  MyCache{
        //ReadWriteLock的实现类ReentrantReadWriteLock
            private ReentrantReadWriteLock readWriteLock=new ReentrantReadWriteLock();
            //volatile 保证线程的可见性 不保证原子性 禁止指令重排
            private volatile Map<String,Object> map=new HashMap<>();
            //写入方法
            private void put(String key ,Object value){
                readWriteLock.writeLock().lock();
                try{
                    //获取正在写入线程的名字
                    System.out.println("线程"+Thread.currentThread().getName()+"\t正在写入...o(U_U)o...");
                    map.put(key,value);
                    //模拟网络延迟
                    try { TimeUnit.MILLISECONDS.sleep(300); }catch (InterruptedException e) { e.printStackTrace(); }
                    System.out.println("线程"+Thread.currentThread().getName()+"\t写入完成...o(n_n)o...✅");
                }catch(Exception e){
                    e.printStackTrace();
                }finally{
                    readWriteLock.writeLock().unlock();
                }
            }
            //读取方法
            public void get(String key){
                readWriteLock.readLock().lock();
                try{
                    System.out.println("线程"+Thread.currentThread().getName()+"\t正在读取...o(K_K)o...");
                    map.get(key);
                    try { TimeUnit.MILLISECONDS.sleep(300); }catch (InterruptedException e) { e.printStackTrace(); }
                    System.out.println("线程"+Thread.currentThread().getName()+"\t读取完成...o(p_p)o...✅");
                }catch(Exception e){
                    e.printStackTrace();
                }finally{
                     readWriteLock.readLock().unlock();
                }
            }
        }
执行结果：

    线程0	正在写入...o(U_U)o...
    线程0	写入完成...o(n_n)o...✅
    线程1	正在写入...o(U_U)o...
    线程1	写入完成...o(n_n)o...✅
    线程2	正在写入...o(U_U)o...
    线程2	写入完成...o(n_n)o...✅
    线程3	正在写入...o(U_U)o...
    线程3	写入完成...o(n_n)o...✅
    线程4	正在写入...o(U_U)o...
    线程4	写入完成...o(n_n)o...✅
    线程5	正在写入...o(U_U)o...
    线程5	写入完成...o(n_n)o...✅
    线程6	正在写入...o(U_U)o...
    线程6	写入完成...o(n_n)o...✅
    线程7	正在写入...o(U_U)o...
    线程7	写入完成...o(n_n)o...✅
    线程8	正在写入...o(U_U)o...
    线程8	写入完成...o(n_n)o...✅
    线程9	正在写入...o(U_U)o...
    线程9	写入完成...o(n_n)o...✅
    线程0	正在读取...o(K_K)o...
    线程2	正在读取...o(K_K)o...
    线程8	正在读取...o(K_K)o...
    线程3	正在读取...o(K_K)o...
    线程5	正在读取...o(K_K)o...
    线程9	正在读取...o(K_K)o...
    线程7	正在读取...o(K_K)o...
    线程6	正在读取...o(K_K)o...
    线程4	正在读取...o(K_K)o...
    线程1	正在读取...o(K_K)o...
    线程5	读取完成...o(p_p)o...✅
    线程0	读取完成...o(p_p)o...✅
    线程3	读取完成...o(p_p)o...✅
    线程2	读取完成...o(p_p)o...✅
    线程6	读取完成...o(p_p)o...✅
    线程7	读取完成...o(p_p)o...✅
    线程4	读取完成...o(p_p)o...✅
    线程9	读取完成...o(p_p)o...✅
    线程8	读取完成...o(p_p)o...✅
    线程1	读取完成...o(p_p)o...✅

#### 总结 ：
    可以😌看出在不加锁的多线程环境下 不能保证**写入数据**的原子性，这样是不可行的。
    加锁后可以保证**写入数据**的原子性，不被其他线程打断。

    
####Demo整体Test优化

    public class ReadWriteLockDemo {
        //资源类
        static  class  MyCache{
        //ReadWriteLock的实现类ReentrantReadWriteLock
            private ReentrantReadWriteLock readWriteLock=new ReentrantReadWriteLock();
            //volatile 保证线程的可见性 不保证原子性 禁止指令重排
            private static volatile Map<String,Object> map=new HashMap<>();
            //不加锁的写入方法
            private  static void set(String key ,Object value){
                //获取正在写入线程的名字
                System.out.println("线程"+Thread.currentThread().getName()+"\t正在写入...o(U_U)o...");
                map.put(key,value);
                //模拟网络延迟
                try { TimeUnit.MILLISECONDS.sleep(300); }catch (InterruptedException e) { e.printStackTrace(); }
                System.out.println("线程"+Thread.currentThread().getName()+"\t写入完成...o(n_n)o...✅");
            }
            //不加锁的读取方法
            private static void unLockGet(String key){
                System.out.println("线程"+Thread.currentThread().getName()+"\t正在读取...o(K_K)o...");
                map.get(key);
                try { TimeUnit.MILLISECONDS.sleep(300); }catch (InterruptedException e) { e.printStackTrace(); }
                System.out.println("线程"+Thread.currentThread().getName()+"\t读取完成...o(p_p)o...✅");
            }
    
            //加锁的写入方法
            private void put(String key ,Object value){
                readWriteLock.writeLock().lock();
                try{
                    MyCache.set(key,value);
                }catch(Exception e){
                    e.printStackTrace();
                }finally{
                    readWriteLock.writeLock().unlock();
                }
            }
            //加锁的读取方法
            public void get(String key){
                readWriteLock.readLock().lock();
                try{
                    MyCache.unLockGet(key);
                }catch(Exception e){
                    e.printStackTrace();
                }finally{
                     readWriteLock.readLock().unlock();
                }
            }
        }
    
    
        public static void main(String[] args) {
            //创建资源类对象
            MyCache myCache=new MyCache();
            //创建10个线程进行读写
            for (int i = 0; i < 10; i++) {
                int finalI = i;
                int finalI1 = i;
                new Thread(()->{
    //                myCache.put(String.valueOf(finalI), finalI);
                    MyCache.set(String.valueOf(finalI), finalI);
    //                myCache.get(String.valueOf(finalI1));
                    MyCache.unLockGet(String.valueOf(finalI1));
                },String.valueOf(i)).start();
            }
    
    
            //上面👆的十个线程先执行
            try { TimeUnit.SECONDS.sleep(2); }catch (InterruptedException e) { e.printStackTrace(); }
    
        }
    }
时间
    2021年5月17日 上午24:44
>>>>>>>  提交 Java 读写锁 case
