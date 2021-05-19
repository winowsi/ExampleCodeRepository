# 自旋锁 SpinLockLock

#### code

    import java.util.concurrent.TimeUnit;
    import java.util.concurrent.atomic.AtomicReference;

    /**
     * 自旋锁
     * 优点： 保证了多线程并发修改
     * 缺点： 会给CPU带来很大的开销
     *
     * @author zY
     * @多线程的环境下AAA线程拿到锁KKK线程一直循环查看AAA线程是否释放锁
     *
     * */


    public class Mycode {
        //原子引用线程
        //默认不填则为null
        AtomicReference<Thread> atomicReference=new AtomicReference<>();

        public  void myLock(){
            /**获取当前线程的引用，一般都是在没有线程对象又需要获得线程信息时通过Thread.currentThread()获取当前代码段所在线程的引用
             * 简单来说是指获取当前运行的线程对象
             */
            Thread thread = Thread.currentThread();

            System.out.println(Thread.currentThread().getName()+"\t come in o(N_N)o");
            /**
             *
             * atomicReference.compareAndSet(null,thread) 比较并交换
             * 如果期望值为null，则替换为当前线程应用对象thread 。
             * */
            while (!atomicReference.compareAndSet(null,thread)){
                System.out.println("请稍后"+thread.getName()+"正在尝试。。。");
            }
        }
        public void myUnlock(){
            Thread thread=Thread.currentThread();
            atomicReference.compareAndSet(thread,null);
            System.out.println(Thread.currentThread().getName()+"\t invoked myUnLock() o(U_U)o");
        }

        public static void main(String[] args) {
            Mycode mycode =new Mycode();
            new Thread(
                    ()->{
                        mycode.myLock();
                        /*
                        模拟线程的业务
                        * */
                        try {
                            TimeUnit.MILLISECONDS.sleep(1200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        mycode.myUnlock();
                        },"AAA"
            ).start();

            //保证AAA线程最先执行
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            new Thread(
                    ()->{
                        mycode.myLock();
                        mycode.myUnlock();
                    },"KKK"
            ).start();
        }
    }
#### 执行结果

    AAA	 come in o(N_N)o
    KKK	 come in o(N_N)o
    请稍后KKK正在尝试。。。
    请稍后KKK正在尝试。。。
    请稍后KKK正在尝试。。。
    请稍后KKK正在尝试。。。
    请稍后KKK正在尝试。。。
    请稍后KKK正在尝试。。。
    请稍后KKK正在尝试。。。
    请稍后KKK正在尝试。。。
    请稍后KKK正在尝试。。。
    请稍后KKK正在尝试。。。
    请稍后KKK正在尝试。。。
    请稍后KKK正在尝试。。。
    请稍后KKK正在尝试。。。
    请稍后KKK正在尝试。。。
    请稍后KKK正在尝试。。。
    请稍后KKK正在尝试。。。
    请稍后KKK正在尝试。。。
    请稍后KKK正在尝试。。。
    请稍后KKK正在尝试。。。
    请稍后KKK正在尝试。。。
    请稍后KKK正在尝试。。。
    请稍后KKK正在尝试。。。
    请稍后KKK正在尝试。。。
    请稍后KKK正在尝试。。。
    请稍后KKK正在尝试。。。
    请稍后KKK正在尝试。。。
    请稍后KKK正在尝试。。。
    请稍后KKK正在尝试。。。
    请稍后KKK正在尝试。。。
    请稍后KKK正在尝试。。。
    KKK	 invoked myUnLock() o(U_U)o
    AAA	 invoked myUnLock() o(U_U)o



