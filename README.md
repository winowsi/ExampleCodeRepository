# countDownLath:倒计时 



   指定一些前提任务完成了以后 ，才能完成最后的任务。

   倒计时形似体现 ，CountDownLatch countDownLatch = new CountDownLatch(count);

   count 总共倒数的次数 .

   countDownLatch.countDown();倒数次数减一

   countDownLatch.await 阻塞挂起 倒数次数以外的线程



#### WriteReadData

```Java
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
```
#### Unifled_the_six_countries 统一六国

```java
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
```

#### Enum

```java
public enum Enum_final {

    ONE(1,"齐国"),TWO(2,"楚国"),
    THREE(3,"燕国"),FUR(4,"赵国"),
    FIVE(5,"魏国"),SIX(6,"韩国");

   private Integer id;
   private String name;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    Enum_final(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
    public static  Enum_final forEach_Enum_final(int index){
        Enum_final[] values = Enum_final.values();
        for (Enum_final e:values) {
            if (index==e.getId()){
                return e;
            }
        }
        return null;
    }

}
```

#### 执行结果

	三国中齐国已战败o(n_n)o
	三国中韩国已战败o(n_n)o
	三国中楚国已战败o(n_n)o
	三国中赵国已战败o(n_n)o
	三国中燕国已战败o(n_n)o
	三国中魏国已战败o(n_n)o
	正在重新分配国土........
	正在重新分配国土................
	三国中秦一统天下o(n_n)o
	线程0写入数据完成o(n_n)o
	线程2写入数据完成o(n_n)o
	线程1写入数据完成o(n_n)o
	线程4写入数据完成o(n_n)o
	线程3写入数据完成o(n_n)o
	线程5写入数据完成o(n_n)o
	线程6写入数据完成o(n_n)o
	线程7写入数据完成o(n_n)o
	线程9写入数据完成o(n_n)o
	线程8写入数据完成o(n_n)o
	线程main开始读取数据o(n_n)o
	线程main读取数据完成o(n_n)o


# 	CycliBarrier: 周期性阻塞



#### 代码Code Case

​	

```java
import java.util.concurrent.CyclicBarrier;

/**
 * @author 赵元君
 * @Create 2021年5月21日13:10:45
 * <p>
 * CyclicBarrier: Cyclic 可循环 Barrier 屏障
 * 做什么事用： 让一组线程到达屏障（也可以叫同步点）被 阻塞 直到最后一个线程到达屏障时屏障才会打开 所有被屏障拦接的线程才会干活 线程进入屏障用 CyclicBarrier.await（），method。
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
```

#### Enum

```java
public enum Enum_schoolFellow {

    ONE(1,"小明"),TWO(2,"小王"),
    THREE(3,"小郭"),FUR(4,"小赵"),
    FIVE(5,"小伟"),SIX(6,"韩韩");
    private int id;
    private  String name;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    //耻辱记录：构造方法没有传值 一直报错NullPointException
    Enum_schoolFellow(int id ,String name){
        this.id=id;
        this.name=name;
    }


    public static Enum_schoolFellow   find_SchoolFellow(int i){
        Enum_schoolFellow[] values = Enum_schoolFellow.values();
        for (Enum_schoolFellow e : values) {
            if (i==e.getId()){
                return e;
            }

        }

        return null;
    }
}
```

#### 执行结果

	同学小王正在上车o(n_n)o
	同学小郭正在上车o(n_n)o
	同学小明正在上车o(n_n)o
	人员到齐 开始回家

