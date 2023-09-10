package java_class.basic_class.overloading_ex;

public class OverloadingClass {


    public int methodA(int a, long b) {

        return 0;
    }

    //인자의 이름이 다르다고 해서 오버로딩 되지 않는다.
//    public void methodA(int q, long r) {}

    // 리턴타입이 다르다고해서 오버로딩 되지 않는다.
//    public long methodA(int a, long b) {
//        return 1L;
//    }

    public int methodA(long a, int b) {
        return 0;
    }

    public int methodA(int a, long b, int c) {
        return 0;
    }
}
