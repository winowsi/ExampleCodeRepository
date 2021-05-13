package com.company;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class MySpinLock {

    AtomicReference<Thread> atomicReference=new AtomicReference<>();

   public void getLock(){
       Thread thread=Thread.currentThread();
       System.out.println(Thread.currentThread().getName()+"\to(n_n)o");
       while (!atomicReference.compareAndSet(null,thread)){
           System.out.println("线程"+Thread.currentThread().getName()+"正在等待⌛️");
       }
   }

   public void nuLock(){
       Thread thread=Thread.currentThread();
       atomicReference.compareAndSet(thread,null);
   }


    public static void main(String[] args) {

       new Thread(()->{
           MySpinLock spinLock=new MySpinLock();
           spinLock.getLock();
           try {
               TimeUnit.SECONDS.sleep(5);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
           spinLock.nuLock();

       },"AAA").start();


        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        new Thread(()->{
            MySpinLock spinLock=new MySpinLock();
            spinLock.getLock();
            spinLock.nuLock();
        },"KKK").start();


    }
}
