package java_ex.basic_class.polymorphism_ex;

public class Main {
    public static void main(String[] args) {
        //다형성
        /**
         * 인스턴스를 담는 객체가 조상 클래스여도 parentMethod를 호출해도
         * 부모 메서드가 아닌 자식 메서드가 호출된다.
         * 단, 조상 클래서에 선언하지 않은 메서드는 사용불가하다.
         */
        Parent p1 = new Child();
        p1.parentMethod();
        //사용불가
//        p1.childMethod();

        //컴파일 단계에서 오류는 나지 않지만 런타임환경에서 오류발생
        /**
         * class java_class.basic_class.polymorphism_ex.Parent cannot be cast to class java_class.basic_class.polymorphism_ex.Child
         * 라는 오류 발생한다.
         */
//        Child c1 = (Child) new Parent();
//        c1.parentMethod();

    }
}
