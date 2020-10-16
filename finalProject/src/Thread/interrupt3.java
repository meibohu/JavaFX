package Thread;

public class interrupt3 {
    public static void main(String[] args)  throws InterruptedException {
        HelloThread2 t = new HelloThread2();
        t.start();
        Thread.sleep(1);
        t.running = false; // 标志位置为false
    }
}

class HelloThread2 extends Thread {
    //线程间共享变量需要使用volatile关键字标记，确保每个线程都能读取到更新后的变量值。
    public volatile boolean running = true;
    public void run() {
        int n = 0;
        while (running) {
            n ++;
            System.out.println(n + " hello!");
        }
        System.out.println("end!");
    }
}
