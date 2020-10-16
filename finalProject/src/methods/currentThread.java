package methods;

class ThreadA extends Thread {
    public void run() {
        System.out.println("The name of this thread is " + " " + ThreadA.currentThread().getName());
    }
}

class ThreadB extends Thread {
    public void run() {                             //static method, can directly call without instancing.
        System.out.println("The name of this thread is " + " " + ThreadB.currentThread().getName());
    }
}

public class currentThread {

    public static void main(String[] args) {
        System.out.println("The name of this thread is " + " " + Thread.currentThread().getName());

        Thread threadA = new ThreadA();
        threadA.start();

        Thread threadB = new ThreadB();
        threadB.start();
    }
}