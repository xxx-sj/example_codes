package java_class.thread_ex;

public class ThreadEx2 {
    public static void main(String[] args) {
        ThreadEx2_1 td = new ThreadEx2_1();
//        td.start(); //새로운 call stack에서 오류가 발생해 stackTrace를 보면 .run에서 발생한다.
        td.run(); //위와는 반대로 main call stack에서 불려와 .main에서 발생한다.
    }

    static class ThreadEx2_1 extends Thread {
        @Override
        public void run() {
            throwException();
        }

        public void throwException() {
            try {
                throw new Exception();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
