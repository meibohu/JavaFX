package methods;


public class getIDThread extends Thread {

    Thread testThread;

    public getIDThread() {
        testThread = new Thread(this, "Wendy Thread");
//        testThread = new Thread(this);
        testThread.setPriority(1);
        System.out.println("thread  = " + testThread);
        testThread.start();
    }

    public void run() {
        System.out.println("Name = " + testThread.getName());    //firstly instance
        System.out.print("Id = " + testThread.getId());
    }

    public static void main(String args[]) {
        new getIDThread();   //can directly new
    }
}
