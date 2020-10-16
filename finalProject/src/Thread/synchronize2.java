package Thread;

/**
 * 线程同步的运用
 *
 * @author XIEHEJUN
 *
 */
public class synchronize2 implements Runnable{
        private static int account = 100;

        public int getAccount() {
            return account;
        }

        /**
         * 用同步方法实现
         *
         * @param money
         */
        public synchronized static void save(int money) {
            account += money;
        }

        /**
         * 用同步代码块实现
         *
         * @param money
         */
        public void save1(int money) {
            synchronized (this) {
                account += money;
            }
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                  save1(10);
//                save(10);
                System.out.println(i + "账户余额为：" + getAccount());
            }
        }


    /**
     * 建立线程，调用内部类
     */
    public void useThread() throws InterruptedException {

        System.out.println("线程1");
        Thread thread1 = new Thread(new synchronize2());
        thread1.start();
        System.out.println("线程2");
        Thread thread2 = new Thread(new synchronize2());
        thread2.start();

        thread1.join();
        thread2.join();

    }

    public static void main(String[] args) throws InterruptedException{
        synchronize2 st = new synchronize2();
        st.useThread();
    }

}