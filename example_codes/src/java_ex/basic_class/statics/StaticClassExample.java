package java_ex.basic_class.statics;

public class StaticClassExample {
    static int classVariable;

    int instanceVariable;

    public void instanceMethod() {
        System.out.println("classVariable : " + classVariable);
        System.out.println("instanceVariable = " + instanceVariable);
    }

    public static void classMethod() {
        System.out.println("classVariable = " + classVariable);
        //static method에서는 인스턴스 변수를 사용할 수 없다.
//        System.out.println("instanceVariable = " + instanceVariable);
    }

}
