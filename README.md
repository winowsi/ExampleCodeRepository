# 阻塞队列 ：BlockingQueue

 1、总共有（**7种** ）常用的有（**3种**）
 
    常用的：
    
        有界阻塞队列：ArrayBlockingQueue：由数组构成
        
        有界（Integer.MAX_VALUE）阻塞队列：LinkedBlockingQueue：由链表构成
        
        不储存元素的阻塞队列：synchronousQueue，也就是单个元素的队列
        
     
    不常用：
    
       支持优先级排序的无界阻塞队列： PriorityBlockingQueue
       
       使用优先级队列实现的延迟阻塞队列：DelayQueue
       
       由链表结构组成的无界阻塞队列：LinkedTransferQueue
       
       由链表结构组成的双向阻塞队列：LinkedBlockingDeque
       


