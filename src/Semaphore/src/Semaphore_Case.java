package Semaphore.src;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author zhaoyao
 *
 * Semaphore:信号
 * 1、主要作用多个共享资源的互斥适 用
 * 2、用于并发线程数的控制
 *
 *  先拿到令牌才可以停车
 *  ******6辆车3个停车位
 *  主要体现并发线程数的控制
 *
 *
 * */
public class Semaphore_Case {
    //3个车位
    private static Semaphore semaphore=new Semaphore(3);

    public static void main(String[] args) {

        //6辆车
        for (int i = 1; i <=6; i++) {
            int finalI = i;
            new Thread(()->{
                if (0==semaphore.availablePermits()){
                    System.out.println("车位不足");
                }
                try {
                    //占据停车位
                    semaphore.acquire();
                    //具体车辆
                    System.out.println("欢迎车牌:"+PlateNumber_Enum.getPlateNumber_Enum(finalI).getName()+Thread.currentThread().getName()+"这里是碧桂园停车场");
                    //停车5秒
                    try {TimeUnit.SECONDS.sleep(5); } catch (InterruptedException e) { e.printStackTrace(); }
                    //离开
                    System.out.println("车牌:"+PlateNumber_Enum.getPlateNumber_Enum(finalI).getName()+Thread.currentThread().getName()+"开车离开");
                } catch (InterruptedException e) {e.printStackTrace();}finally{ semaphore.release();}
            },String.valueOf(i)).start();
        }

    }

}
