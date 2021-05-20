# countDownLath

   指定一些前提任务完成了以后 ，才能完成最后的任务。

   倒计时形似体现 ，CountDownLatch countDownLatch = new CountDownLatch(count);

   count 总共倒数的次数 .

   countDownLatch.countDown();倒数次数减一

   countDownLatch.await 阻塞挂起 倒数次数以外的线程



####WriteReadData

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
####Unifled_the_six_countries 统一六国

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

####Enum

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