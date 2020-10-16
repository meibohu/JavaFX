package methods;

//main is paused and give processor to MyThread
class MyThread extends Thread {
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + " in control");
        }
    }
}
public class yieldThread {
    public static void main(String[] args) {
        MyThread threadA = new MyThread();
        threadA.start();
        for (int i = 0; i < 3; i++) {
            Thread.yield();
            System.out.println(Thread.currentThread().getName() + " in control");
        }
    }
}