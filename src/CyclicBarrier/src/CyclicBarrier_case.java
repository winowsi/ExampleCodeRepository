package CyclicBarrier.src;

import java.util.concurrent.CyclicBarrier;

/**
 * @author 赵元君
 * @Create 2021年5月21日13:10:45
 * <p>
 * CyclicBarrier: Cyclic 可循环 Barrier 屏障
 * 做什么事用： 让一组线程到达屏障（也可以叫同步点）被阻塞直到最后一个线程到达屏障时
 * 屏障才会打开 所有被屏障拦接的线程才会干活 线程进入屏障用 CyclicBarrier.await（），method。
 * <p>
 * case
 * 同学放学座车回家
 * 条件总共有3位同学 必须3位同学都在车上才可以开车出发
 */

public class CyclicBarrier_case {


    public static void main(String[] args)  {


        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, () -> {

            System.out.println("人员到齐 开始回家");

        });
/*data 数据
     HashMap<Integer,String> map =new HashMap<>();
       map.put(1,"张三");
        map.put(2,"王五");
          map.put(3,"李四");*/

        for (int i = 1; i <= 3; i++) {
            new Thread(() -> {
                System.out.println("同学" + Thread.currentThread().getName() + "正在上车o(n_n)o");
                /*开启屏障
                 *
                 * 此方法会抛出
                 * InterruptedException
                 * java.util.concurrent.BrokenBarrierException
                 * 这两个异常
                 * 为了代码规整直接省事抛了最大异常
                 * */
                try {
                    cyclicBarrier.await();
                } catch (Exception e) {
                    System.out.println(e);
                }
            }, Enum_schoolFellow.find_SchoolFellow(i).getName()).start();
        }


    }


}
