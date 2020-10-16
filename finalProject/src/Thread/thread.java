package Thread;

public class thread {
    public static void main(String[] args) {
        System.out.println("main start...");
        Thread t = new Thread(() -> {
            System.out.println("thread run...");
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {}
            System.out.println("thread end.");
        });

        t.start();
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {

        }
        System.out.println("main end...");
    }
}
