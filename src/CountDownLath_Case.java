import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/***
 * @author 赵元君
 * @version 1.0.0
 * @create 2021年5月20日12:47:14
 * @method Unifled_the_six_countries();:
 * @method writeReadData():读取数据
 *
 * CountDownLAth 向下基数 倒计时
 *
 * 适用场景 ：指定一些前提任务完成了以后 ，才能完成最后的任务。
 *
 *
 */


public class CountDownLath_Case {




    public static void main(String[] args) {
        Unifled_the_six_countries();
        Thread.currentThread().setName("main");
        writeReadData();

    }

    private static void Unifled_the_six_countries() {
        int filInt=6;
        CountDownLatch countDownLatch=new CountDownLatch(filInt);

        for (int i = 1; i <= filInt; i++) {
            String name = Enum_final.forEach_Enum_final(i).getName();
            new Thread(() -> {

                System.out.println("三国中" + Thread.currentThread().getName() + "已战败o(n_n)o");
                countDownLatch.countDown();

            }, name).start();

        }
        try { countDownLatch.await(); } catch (InterruptedException e) { e.printStackTrace(); }
        System.out.println("正在重新分配国土........");
        try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException e) { e.printStackTrace(); }
        System.out.println("正在重新分配国土................");
        try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
        Thread.currentThread().setName("秦");
        System.out.println("三国中"+Thread.currentThread().getName()+"一统天下o(n_n)o");
    }


    private static void writeReadData() {
        int count=10;
        //    倒数总次数
        CountDownLatch countDownLatch = new CountDownLatch(count);
        //创建10个线程
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                System.out.println("线程"+Thread.currentThread().getName()+"写入数据完成o(n_n)o");
                //倒数总数减一 countDownLatch.countDown()
                countDownLatch.countDown();
            },String.valueOf(i)).start();
        }
        //阻塞main线程 countDownLatch.await()
        try { countDownLatch.await(); } catch (InterruptedException e) { e.printStackTrace(); }

        System.out.println("线程"+Thread.currentThread().getName()+"开始读取数据o(n_n)o");
        //模拟读取数据时间
        try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
        System.out.println("线程"+Thread.currentThread().getName()+"读取数据完成o(n_n)o");
    }


}
