package methods;

//he second thread will try to access infinityMethod()
// but this method is in use by the first thread — the second thread will be BLOCKED
public class blockThread {
    public static void main(String[] args) throws InterruptedException {
        Thread threadA = new Thread(new InfiniteClass());
        Thread threadB = new Thread(new InfiniteClass());

        threadA.start();
        threadB.start();
        threadA.join();
        threadB.join();

        Thread.sleep(1000);
    }
}

class InfiniteClass implements Runnable {

    @Override
    public void run() {
        infinityMethod();
    }

    public static synchronized void infinityMethod() {
        while(true) {
            // Infinite loop to mimic heavy processing
            // only one thread can run at the same time
        }
    }
}
