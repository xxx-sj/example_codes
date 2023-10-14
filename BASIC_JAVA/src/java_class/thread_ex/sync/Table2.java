package java_class.thread_ex.sync;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Table2 {
    String[] dishNames = {"donut", "donut", "burger"};
    final int MAX_FOOD = 6;
    private ArrayList<String> dishes = new ArrayList<>();

    private ReentrantLock lock = new ReentrantLock();
    private Condition forCook = lock.newCondition();
    private Condition forCust = lock.newCondition();

    public void add(String dish) {
        lock.lock();

        try {
            while(dishes.size() >= MAX_FOOD) {
                String name = Thread.currentThread().getName();
                System.out.println(name + " is waiting");
                try {
                    forCook.wait();
                    Thread.sleep(500);
                } catch (InterruptedException e) {}
            }

            dishes.add(dish);
            forCust.signal();
            System.out.println("Dishes: " + dishes.toString());
        } finally {
            lock.unlock();
        }
    }

    public void remove(String dishName) {
        lock.lock();
        String name = Thread.currentThread().getName();
        try {
            while(dishes.size() == 0) {
                System.out.println(name + " is waiting.");
                try {
                    forCust.await();
                    Thread.sleep(500);
                } catch (InterruptedException e) {}
            }

            while(true) {
                for(int i = 0; i < dishes.size(); i++) {
                    if(dishName.equals(dishes.get(i))) {
                        dishes.remove(i);
                        forCook.signal();
                        return;
                    }
                }

                try {
                    System.out.println(name + " is waiting");
                    forCust.await();
                    Thread.sleep(500);
                } catch (InterruptedException e) {}
            }
        } finally {
            lock.unlock();
        }
    }

    public int dishNum() { return dishNames.length; }



    static class Customer implements Runnable {
        private Table table;
        private String food;

        public Customer(Table table, String food) {
            this.table = table;
            this.food = food;
        }

        public void run() {
            while (true) {
                try {Thread.sleep(100);} catch (InterruptedException e) {}
                String name = Thread.currentThread().getName();

                table.remove(food);
                System.out.println(name + " ate a " + food);

            }
        }
    }

    static class Cook implements Runnable {
        private Table table;

        public Cook(Table table) {
            this.table = table;
        }

        public void run() {
            while(true) {
                int idx = (int)(Math.random() * table.dishNum());
                table.add(table.dishNames[idx]);
                try {Thread.sleep(10);} catch (InterruptedException e) {}
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Table table = new Table(); // 공유

        new Thread(new Table.Cook(table), "COOK1").start();
        new Thread(new Table.Customer(table, "donut"), "CUST1").start();
        new Thread(new Table.Customer(table, "burger"), "CUST2").start();

        Thread.sleep(2000);
        System.exit(0);
    }
}
