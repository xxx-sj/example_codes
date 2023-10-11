# Thread.sleep

```java
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
```
thread1을 sleep 시키면 thread1 이 가장 늦게끝날 것 같지만 sleep은 현재 실행중인 스레드에 대해 적동하기 때문에 실제로는 main method를 실행하는    
main 스레드가 영향을 받아 가장 늦게 끝난다.  따라서 sleep을 쓸 때는 참조변수를 이용하기 보다는 Thread.sleep() 을 사용하자.