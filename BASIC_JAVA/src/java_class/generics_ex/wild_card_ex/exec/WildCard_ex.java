package java_class.generics_ex.wild_card_ex.exec;

import java_class.generics_ex.wild_card_ex.objects.Apple;
import java_class.generics_ex.wild_card_ex.objects.Banana;
import java_class.generics_ex.wild_card_ex.objects.Food;
import java_class.generics_ex.wild_card_ex.objects.Fruit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class WildCard_ex {

    public static void main(String[] args) {
        //extends
        List<Banana> bananas = new ArrayList<>(
                Arrays.asList(new Banana(),new Banana(),new Banana())
        );
        extends_method(bananas);


        //super
        List<Food> foods = new ArrayList<>(
                Arrays.asList(new Food(), new Food(), new Food())
        );
        super_method(foods);
    }


    /**
     * 상한경계 [GET]
     * "<\? extends Fruit\> 는 Fruit 를 포함하여 Fruit 를 상속한 하위 클래스들만 인자로 받겠다는 뜻이다.
     * [fruit, apple, banana]
     * extends 로 받은 객체는 꺼낼 때 상한경계로 꺼내야 한다. [가장 상위인 Fruit로 꺼낸다.]
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
     * 상한경계에서 set은 null만 허용한다. 이유는 다음과 같다.
     * 예를들어서
     * 인자로 Apple 이 들어오는 경우 형제 객체인 Banana를 저장할 수 없다 [형제관계]
     * 인자로 Fruit이 들어오면 다형성으로 인해 문제는 없지만 위의 논리오류로 인해 컴파일 에러를 낸다.
     *
     */
    public static void extends_method2(List<? extends Fruit> e) {
//        e.add(new Fruit());
//        e.add(new Apple());
//        e.add(new Banana());
//        e.add(new Object());
        e.add(null);
    }

    /**
     * 하한경계 [GET]
     * ? super Fruit 는  Fruit를 포함하여 조상 객체인 Food, Object 만 인자로 받겠다는 뜻이다.
     * [Fruit, Food, Object]
     * super로 인자로 받을 경우 꺼낼 때는 가장 상위인 Object로 꺼내야 한다.
     * 예를들어
     * 인자가 Food 로 들어올 경우 Fruit로 캐스팅이 불가능 하다 [cast error [부모에서 자식으로 캐스팅 불가능]]
     * 동일하게 Object 로 들어올 경우 Fruit, Food로 캐스팅이 불가능하다. [cast error]
     */
    public static void super_method(List<? super Fruit> e) {
        Object object = e.get(0);

//        Food food = (Food) e.get(0);
//        Fruit fruit = (Fruit) e.get(0);

    }

    /**
     * 하한경계 [SET]
     * ? super Fruit 의 인자로 들어올 수 있는 타입은 다음과 같다.
     * [Fruit, Food, Object]
     * 그렇다면 다형성을 통해 인자로 넘어온 객체에 넣을 수 있는 값들은 Fruit, Food, Object의 자손들이 가능할 것이다. 왜냐하면
     * 인자가 만약 Food 라면 Object는 저장할 수 없기 때문이다. [cast error [부모에서 자식으로 캐스팅 불가능]]
     * incompatible types: java.lang.Object cannot be converted to java_class.generics_ex.wild_card_ex.objects.Food
     */
    public static void super_method2(List<? super Fruit> e) {
        e.add(new Fruit());
        e.add(new Apple());
        e.add(new Banana());

//        e.add(new Object());
//        e.add(new Food());
    }

    /**
     * 와일드카드 </?> [GET]
     * 와일드 카드 ?는 </? extends Object> 와 같은 의미이다.
     * 즉, 모든 객체를 수용할 수 있다는 의미이다.
     * 따라서 올 수 있는 것은 모든 클래스가 될 수 있다.
     * 하지만 꺼낼 때[GET]는 가장 위의 클래스로 꺼내야 한다 [Object]
     */
    public static void wild_card_method(List<?> e) {
        Object object = e.get(0);
    }

    /**
     * 와일드카드 [SET]
     * 와일드 카드에서는 모든 클래스가 올 수 있기 때문에 가장 최 하위 클래스를 알 수 없다.
     * 즉, add를 통해 무언가를 넣으려고 할 때, null 이외의 다른 값을 넣을 수 없다.
     */
    public static void wild_card_method2(List<?> e) {
        e.add(null);
    }


    /**
     *
     * T 가 fruit 라면?
     *
     *
     * PECS [producers Extends, Consumers Super]
     * 의미를 내마음대로 해석하자면,
     * class 내부의 필드를 생성한다면 extends를 사용하고 [extends 인자로 받은 것으로 class 내부의 필드를 생성한다면]
     * class 내부의 필드를 소비한다면 super를 사용한다. [class 내부의 필드를 어딘가에 소비한다면 Super]
     */
    static class MyArrayList<T> { // T = Fruit
        Object[] element = new Object[5];
        int count = 0;

        //<? extends T> 에 올 수 있는 타입은 Fruit, Apple, Banana, 추가로 apple를 상속한 red Apple 까지
        //super가 아니라 extends 인 이유는 적어도 Fruit를 구현한 객체가 들어와야 하기 때문에
        //super라면 Fruit, Food, Object가 오게 될텐데, 현재 <T>의 값은 Fruit 그래서 안됨.
        public MyArrayList(Collection<? extends T> in) {
            for (T t : in) {
                //하나씩 추가됨.
                element[count++] = t;
            }
        }

        @SuppressWarnings("unchecked")
        //<? super T> 에 올 수 있는 타입은 Fruit, Food, Object
        //현재 out에 element를 복사해서 넣는다. 이 때 (T)는 Fruit
        //extends가 안되는 이유는 extends면 add할 때 null 이외에 불가능.
        public void clone(Collection<? super T> out) {
            //for문을 돌리는 이유는 element 갯수만큼 out에 추가하기 위해서
            //object가 이용되지 않는 이유중 하나 임.
            for (Object object : out) {
                out.add((T) element);
            }
        }
    }
}
