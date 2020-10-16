package methods;

public class joinThread implements Runnable {
    public static Thread threadA;

    public static void main(String[] args) {
        threadA = new Thread(new joinThread());
        threadA.start();
    }

    public void run() {
        Thread threadB = new Thread(new Sleeping());
        threadB.start();
        try {
            threadA.join();
        } catch (InterruptedException e) {
//            Log.error("Interrupt exception: ", e);
            System.out.println("wef");
        }
    }


    public static class Sleeping implements Runnable {
        public void run() {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
//                Log.error("Interrupt exception: ", e);
                System.out.println("wef");
            }
        }
    }

}