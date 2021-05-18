import java.sql.Time;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 *
 * ReadWriteLock ：读写锁
 *
 * time：2021年5月15日13:30:57
 *
 * */

public class MyReadWriteLockCase {

   //线程操作资源类
   static class MyReadWriteLockCode{
         //读写容器
         private  volatile  Map<String ,Object> map=new HashMap<>();
         //向map中写入
         public void  put(String key, Object value) throws InterruptedException {
            System.out.println("线程"+Thread.currentThread().getName()+"正在写入o(n_n)o");
             //模拟写入网络延迟
            TimeUnit.MILLISECONDS.sleep(300);
            map.put(key,value);
            System.out.println("线程"+Thread.currentThread().getName()+"写入完成o(V_V)o");
         }
         public  void get (String key) throws InterruptedException {
            System.out.println("线程"+Thread.currentThread().getName()+"正在读取o(z_z)o");
            //模拟读取网络延迟
             TimeUnit.MILLISECONDS.sleep(300);
            Object value= map.get(key);
            System.out.println("线程"+Thread.currentThread().getName()+"读取完成正在打印o(n_n)o"+value);
         }




   }

   public static void main(String[] args) throws InterruptedException {

      MyReadWriteLockCode myReadWriteLockCode = new MyReadWriteLockCode();

      for (int i = 0; i < 10; i++) {
         int finalI = i;
         new Thread(()->{
             try {
                 myReadWriteLockCode.put(String.valueOf(finalI),finalI);
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
         },String.valueOf(i)).start();
      }

      //保证线程AAA优先执行让main线程阻塞
      TimeUnit.SECONDS.sleep(1);

   }

}
