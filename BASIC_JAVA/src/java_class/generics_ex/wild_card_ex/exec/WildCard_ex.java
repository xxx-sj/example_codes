package java_class.generics_ex.wild_card_ex.exec;

import java_class.generics_ex.wild_card_ex.objects.Banana;
import java_class.generics_ex.wild_card_ex.objects.Fruit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WildCard_ex {

    public static void main(String[] args) {
        List<Banana> bananas = new ArrayList<>(
                Arrays.asList(new Banana(),new Banana(),new Banana())
        );
        extends_method(bananas);
    }


    /**
     * 상한경계 [GET]
     * "<\? extends Fruit\> 는 Fruit 를 포함하여 Fruit 를 상속한 하위 클래스들만 인자로 받겠다는 뜻이다.
     * [fruit, apple, banana]
     * extends 로 받은 객체는 꺼낼 때 상한경계로 꺼내야 한다.
     * 예를들어
     * 인자로 Apple를 넘겼을 경우 Banana로 꺼낼 수 없다. [형제관계]
     * 인자로 Fruit를 넘길 경우 Banana, Apple로 꺼낼 수 없다 [cast error]
     * 따라서 모두 포함시킬 수 잇는 Fruit로 꺼내야 안전하게 꺼낼 수 있다.
     *
     */
    public static void extends_method(List<? extends Fruit> e) {
        Fruit fruit = e.get(0);

//        Apple apple = e.get(0);
//        Banana banana = e.get(0);
    }

    /**
     * 상한경계 [SET]
     * 상한경계에서 set은 null만 허용한다. 이유는 다음과 같다. 예를들어서
     * 인자로 Apple 이 들어오는 경우 형제 객체인 Banana를 저장할 수 없다 [형제관계]
     * 인자로 Fruit이 들어오면 다형성으로 인해 문제는 없지만 위의 논리오류로 인해 컴파일 에러를 낸다.
     *
     */
    public static void extends_method2(List<? extends  Fruit> e) {
//        e.add(new Fruit());
//        e.add(new Apple());
//        e.add(new Banana());
//        e.add(new Object());
        e.add(null);
    }
}
