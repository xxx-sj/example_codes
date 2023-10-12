package java_class.thread_ex.sync;

import java.util.ArrayList;

public class Table {

    String[] dishNames = {"donut", "donut", "burger"};
    final int MAX_FOOD = 6;
    private ArrayList<String> dishes = new ArrayList<>();

    public synchronized void add(String dish) {
//        if(dishes.size() >= MAX_FOOD) {
//            return;
//        }
//
//        dishes.add(dish);
//        System.out.println("Dishes: " + dishes.toString());

        while(dishes.size() >= MAX_FOOD) {
            String name = Thread.currentThread().getName();
            System.out.println(name + " is waiting");
            try {
                wait(); //COOK 스레드를 기다리게 한다.
                Thread.sleep(500);
            } catch (InterruptedException e) {}
        }

        dishes.add(dish);
        notify();
        System.out.println("Dishes: " + dishes.toString());
    }

    public void remove(String dishName) {
        synchronized (this) {
            String name = Thread.currentThread().getName();

            //size가 0이면 무한정 기다리게된다.
            while(dishes.size() == 0) {
                System.out.println(name + "is waiting");

                try {
                    wait(); //CUST 스레드를 기다리게 한다.
                    Thread.sleep(500);
                } catch (InterruptedException e) {}
            }

            while(true) {
                //0이 아니면 제거한다.
                for(int i = 0; i < dishes.size(); i++) {
                    if(dishName.equals(dishes.get(i))) {
                        dishes.remove(i);
                        notify();
                        return;
                    }
                } //for end

                try {
                    System.out.println(name + " is waiting.");
                    wait();
                    Thread.sleep(500);
                } catch (InterruptedException e) {}
            }// while end
        } //sync
    }

    public int dishNum() {
        return dishNames.length;
    }


    static class Customer implements Runnable {
        private Table table;
        private String food;

        public Customer(Table table, String food) {
            this.table = table;
            this.food = food;
        }

        public void run() {
            while(true) {
                try {Thread.sleep(100);} catch (InterruptedException e) {}
                String name = Thread.currentThread().getName();

//                if(eatFood()) {
//                    System.out.println(name + " ate a " + food);
//                } else {
//                    System.out.println(name + " failed to eat . :(");
//                }

                table.remove(food);
                System.out.println(name + " ate a " + food);
            }
        }

//        boolean eatFood() {
//            return table.remove(food);
//        }
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


    public static void main(String[] args) throws Exception{
        Table table = new Table(); // 공유

        new Thread(new Cook(table), "COOK1").start();
        new Thread(new Customer(table, "donut"), "CUST1").start();
        new Thread(new Customer(table, "burger"), "CUST2").start();

        Thread.sleep(2000);
        System.exit(0);
    }
}

