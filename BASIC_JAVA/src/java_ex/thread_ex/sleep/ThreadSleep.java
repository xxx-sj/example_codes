package java_ex.thread_ex.sleep;

public class ThreadSleep {

    static class Thread1 extends Thread {
        public void run () {
            for(int i = 0; i < 300; i++) {
                System.out.print("-");
            }

            System.out.print("<<Thread1 end>>");
        }

    }

    static class Thread2 extends Thread {
        public void run() {
            for(int i = 0; i < 300; i++) {
                System.out.print("|");
            }

            System.out.print("<<Thread2 end>>");
        }
    }

    public static void main(String[] args) {
        Thread1 thread1 = new Thread1();
        Thread2 thread2 = new Thread2();

        thread1.start();
        thread2.start();
        try {
//            thread1.sleep(2000);
            Thread.sleep(2000);
        } catch (InterruptedException e) {

        }

        for(int i = 0; i < 300; i++) {
            System.out.print("*");
        }

        System.out.print("<<main end>>");
    }
}
