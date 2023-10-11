package java_class.thread_ex.interrupt;

import javax.swing.*;

public class InterruptThread {

    static class Thread1 extends Thread {
        public void run() {
            int i = 10;
            while (i != 0 && !isInterrupted()) {
                System.out.println(i--);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    interrupt(); // 종료를 위해 interrupt() 추가 sleep 상태에서 interrupt call 시 exception 발생하면서 상태를 interrupted 상태를 false로 바꾸기 때문에 다시 호출해서 true로 변경
                }
            }
        }
    }
    public static void main(String[] args) {
        Thread th1 = new Thread1();
        th1.start();

        String input = JOptionPane.showInputDialog("anyThing");
        System.out.println("input number is = " + input);
        th1.interrupt();
        System.out.println("is Interrupted()" + th1.isInterrupted());
    }
}
