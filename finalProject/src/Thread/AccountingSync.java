package Thread;

public class AccountingSync implements Runnable{
    //共享资源(临界资源)
    static int i=0;
//当一个线程正在访问一个对象的 synchronized 实例方法，那么其他线程不能访问该对象的其他 synchronized 方法，
//毕竟一个对象只有一把锁，当一个线程获取了该对象的锁之后，其他线程无法获取该对象的锁
    /**
     * synchronized 修饰实例方法
     */
    public synchronized void increase(){
        i++;
    }

    /**
     * 作用于静态方法,锁是当前class对象,也就是
     * AccountingSyncClass类对应的class对象
     */
    public static synchronized void increase22(){
        i++;
    }

    @Override
    public void run() {
        for(int j=0;j<1000000;j++){
//            System.out.println(i);
            increase22();
        }
    }
    public static void main(String[] args) throws InterruptedException {
//        AccountingSync instance=new AccountingSync();
//        Thread t1=new Thread(instance);
//        Thread t2=new Thread(instance);
//        t1.start();
//        t2.start();
//        t1.join();  //join含义:当前线程A等待thread线程终止之后才能从thread.join()返回
//        t2.join();
//        System.out.println(i);

        //new新实例
        Thread t1=new Thread(new AccountingSync());
        //new心事了
        Thread t2=new Thread(new AccountingSync());
        //启动线程
        t1.start();t2.start();

        t1.join();t2.join();
        System.out.println(i);
    }
}
