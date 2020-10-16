package methods;
/*
        We’ve created and started a threadA
        Move to sleep main thread
        threadA start to processing
        Method sleep is called — threadA in TIMED_WAITING   */

public class TimedWaiting {
    static class Sleeping implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
//                Log.error("Interrupted exception: ", e);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread threadA = new Thread(new Sleeping());
        threadA.start();
        // This will start processing threadA
        Thread.sleep(1000);
    }
}