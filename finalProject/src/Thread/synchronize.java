package Thread;

public class synchronize {
    public static void main(String[] args) throws Exception {
        Thread temp = new Thread(new AddStudentThread());    //现转换为Thread
        Thread[] ts = new Thread[] {
                temp, new DecStudentThread(), new AddTeacherThread(), new DecTeacherThread() };
        for (Thread t : ts) {
            t.start();
        }
        for (Thread t : ts) {
            t.join();
        }
        System.out.println(Counter.studentCount);
        System.out.println(Counter.teacherCount);
    }
}

class Counter {
    public static final Object lockStudent = new Object();
    public static final Object lockTeacher = new Object();
    public static int studentCount = 0;
    public static int teacherCount = 0;
}

class AddStudentThread implements Runnable {
    public void run() {
        for (int i=0; i<10000; i++) {
            synchronized(Counter.lockStudent) {
                Counter.studentCount += 1;
            }
        }
    }
}

class DecStudentThread extends Thread {
    public void run() {
        for (int i=0; i<10000; i++) {
            synchronized(Counter.lockStudent) {
                Counter.studentCount -= 1;
            }
        }
    }
}

class AddTeacherThread extends Thread {
    public void run() {
        for (int i=0; i<10000; i++) {
            synchronized(Counter.lockTeacher) {    //锁的对象是Counter.lockTeacher
                Counter.teacherCount += 1;
            }
        }
    }
}

class DecTeacherThread extends Thread {
    public void run() {
        for (int i=0; i<10000; i++) {
            synchronized(Counter.lockTeacher) {  //锁的对象是Counter.lockTeacher
                Counter.teacherCount -= 1;
            }
        }
    }
}
