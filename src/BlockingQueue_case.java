import java.util.concurrent.*;

public class BlockingQueue_case {

   public  BlockingQueue blockingQueue=null;

    BlockingQueue_case(BlockingQueue blockingQueue){
        this.blockingQueue=blockingQueue;
    }
    public void myAdd() throws InterruptedException {

        System.out.println(blockingQueue.add("A"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.element());

    }

    private void myOffer() {
        System.out.println(blockingQueue.offer("C"));//插入成功返回true失败false
        System.out.println(blockingQueue.offer("B"));//插入成功返回true失败false
        System.out.println(blockingQueue.offer("C"));//插入成功返回true失败false
        System.out.println(blockingQueue.poll());//移除成功返回队列元素  失败返回null
        blockingQueue.peek();//查看队列队首元素
    }

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue_case bk=new BlockingQueue_case(new ArrayBlockingQueue(3));
//        bk.myAdd();
        bk.myOffer();



    }
}
