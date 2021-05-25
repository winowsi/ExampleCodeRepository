package Semaphore.src;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author zhaoyao
 *
 * Semaphore:信号
 * 1、主要作用多个共享资源的互斥适 用
 * 2、用于并发线程数的控制
 * */
public class Semaphore_Case {


    public static void main(String[] args) {
        //5个车位
   Semaphore semaphore=new Semaphore(5);
        for (int i = 0; i < 9; i++) {
            new Thread(()->{
                System.out.println("停车厂剩余车位"+semaphore.drainPermits());
                System.out.println("车牌:0078"+Thread.currentThread().getName()+"欢迎");
                try {
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println("车牌:0078"+Thread.currentThread().getName()+"离开");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                finally{

                    semaphore.release();
                }


            },String.valueOf(i)).start();
        }

    }

}
