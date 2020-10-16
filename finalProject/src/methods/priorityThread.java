package methods;

public class priorityThread extends Thread {

    Thread thread1;
    Thread thread2;
    public priorityThread(){
        thread1 = new Thread();
        thread2 = new Thread();

        thread1.setPriority(1);
        thread2.setPriority(2);
        System.out.println("threadA thread priority : " + thread1.getPriority());
        System.out.println("threadB thread priority : " + thread2.getPriority());
        System.out.println(" ");

        thread1.start();
        thread2.start();
        System.out.println(" ");
    }

    public void run() {
        System.out.println("running thread name is:" + Thread.currentThread().getName());
        System.out.println("running thread id is:" + Thread.currentThread().getId());

    }

    public static void main(String args[]) {
        new priorityThread();
        //cannot write: thread1.start()
        System.out.println("running thread state is: : " + Thread.currentThread().getState());
    }
}