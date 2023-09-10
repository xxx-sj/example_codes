package java_class.basic_class.instructors;

public class InstructorsEx {

    int a;
    int b;

    public InstructorsEx(){}
    public InstructorsEx(int a, int b) {
        this.a = a;
        this.b = b;
    }
    //인스턴스를 통한 객체 복사
    public InstructorsEx(InstructorsEx ex) {
        this(ex.a, ex.b);
    }

}
