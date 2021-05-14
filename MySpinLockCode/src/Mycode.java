import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 自旋锁
 * 优点： 保证了多线程并发修改
 * 缺点： 会给CPU带来很大的开销
 *
 *
 * */


public class Mycode {
    //原子引用线程
    //默认不填则为null
    AtomicReference<Thread> atomicReference=new AtomicReference<>();

    public  void myLock(){
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName()+"\t come in o(N_N)o");
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
                    try {
                        TimeUnit.SECONDS.sleep(5);
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
