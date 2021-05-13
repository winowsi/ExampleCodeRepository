import java.util.HashMap;
import java.util.Map;

public class ReadWriteLockDemo {
    //资源类
    class MyCache{
        private volatile Map<String,Object> map=new HashMap<>();

        private void put(String key ,Object value){
            //获取正在写入线程的名字
            System.out.println(Thread.currentThread().getName()+"\t正在写入");

        }


    }


    public static void main(String[] args) {

    }
}
