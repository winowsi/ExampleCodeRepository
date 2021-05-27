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

# Semaphore_Case：信号灯

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
#### Enum
    package Semaphore.src;

    public enum PlateNumber_Enum {

        A(1 ,"粤A10110"),B(2,"川Y125OQ6"),
        C(3, "贵B10110"),D(4,"京Y125OQ6"),
        E(5,"桂c10110"),F(6,"杭D125OQ6");

        private  Integer id;
        private  String name;

        public Integer getId(){
            return id;
        }
        public String getName(){
            return name;
        }

        PlateNumber_Enum(Integer id, String name){
            this.id=id;
            this.name=name;
        }

        public static PlateNumber_Enum getPlateNumber_Enum(Integer id){
            PlateNumber_Enum[] values = PlateNumber_Enum.values();
            for (PlateNumber_Enum e: values) {
                if (e.id==id){
                    return e;
                }
            }
            return null;
        }
    }

#### 执行结果
     车位不足
     欢迎车牌:川Y125OQ62这里是碧桂园停车场
     欢迎车牌:贵B101103这里是碧桂园停车场
     欢迎车牌:粤A101101这里是碧桂园停车场
     车位不足
     车位不足
     车牌:川Y125OQ62开车离开
     车牌:粤A101101开车离开
     欢迎车牌:京Y125OQ64这里是碧桂园停车场
     欢迎车牌:桂c101105这里是碧桂园停车场
     车牌:贵B101103开车离开
     欢迎车牌:杭D125OQ66这里是碧桂园停车场
     车牌:桂c101105开车离开
     车牌:京Y125OQ64开车离开
     车牌:杭D125OQ66开车离开
