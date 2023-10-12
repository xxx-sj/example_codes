package java_class.generics_ex.wild_card_ex.exec;

import java_class.generics_ex.wild_card_ex.objects.Apple;
import java_class.generics_ex.wild_card_ex.objects.Food;
import java_class.generics_ex.wild_card_ex.objects.Fruit;

import java.util.ArrayList;
import java.util.List;

public class WildCard_class {



    //TODO: Unexpected wildcard
//    static class Test<? extends Number> {}
    //TODO: Cannot resolve symbol 'T'
//    static class Test<? extends T> {}

    static class Test<T> {
        public void extends_method(List<? extends T> list) {}

        public void super_method(List<? super T> list) {}

        public void generic_method(List<T> list) {}

        public static <E> void static_generic_method(List<? extends E> list) {}
    }

    public static void main(String[] args) {

        Test<? extends Fruit> extends_fruit_list = new Test<Apple>();
        Test<? super Fruit> super_fruit_list = new Test<Food>();
        Test<Fruit> fruit_list = new Test<>();


        List<Fruit> fruit = new ArrayList<>();
        List<? extends Fruit> extends_fruit = new ArrayList<>();
        List<? super Fruit> super_fruit = new ArrayList<>();
        List<Apple> apple = new ArrayList<>();
        List<Food> food = new ArrayList<>();

        fruit_list.extends_method(fruit);
        fruit_list.extends_method(extends_fruit);
        fruit_list.extends_method(super_fruit);
        fruit_list.extends_method(food);
        fruit_list.extends_method(apple);

        fruit_list.super_method(fruit);
        fruit_list.super_method(extends_fruit);
        fruit_list.super_method(super_fruit);
        fruit_list.super_method(food);
        fruit_list.super_method(apple);

        fruit_list.generic_method(fruit);
        fruit_list.generic_method(extends_fruit);
        fruit_list.generic_method(super_fruit);
        fruit_list.generic_method(food);
        fruit_list.generic_method(apple);

        extends_fruit_list.extends_method(fruit);
        extends_fruit_list.extends_method(extends_fruit);
        extends_fruit_list.extends_method(super_fruit);
        extends_fruit_list.extends_method(food);
        extends_fruit_list.extends_method(apple);

        extends_fruit_list.super_method(fruit);
        extends_fruit_list.super_method(extends_fruit);
        extends_fruit_list.super_method(super_fruit);
        extends_fruit_list.super_method(food);
        extends_fruit_list.super_method(apple);

        extends_fruit_list.generic_method(fruit);
        extends_fruit_list.generic_method(extends_fruit);
        extends_fruit_list.generic_method(super_fruit);
        extends_fruit_list.generic_method(food);
        extends_fruit_list.generic_method(apple);


        super_fruit_list.extends_method(fruit);
        super_fruit_list.extends_method(extends_fruit);
        super_fruit_list.extends_method(super_fruit);
        super_fruit_list.extends_method(food);
        super_fruit_list.extends_method(apple);

        super_fruit_list.super_method(fruit);
        super_fruit_list.super_method(extends_fruit);
        super_fruit_list.super_method(super_fruit);
        super_fruit_list.super_method(food);
        super_fruit_list.super_method(apple);

        super_fruit_list.generic_method(fruit);
        super_fruit_list.generic_method(extends_fruit);
        super_fruit_list.generic_method(super_fruit);
        super_fruit_list.generic_method(food);
        super_fruit_list.generic_method(apple);

//        fruit_list.extends_method(fruit);
//        fruit_list.extends_method(extends_fruit);
//        fruit_list.extends_method(super_fruit);
//        fruit_list.extends_method(food);
//        fruit_list.extends_method(apple);
//
//        fruit_list.super_method(fruit);
//        fruit_list.super_method(extends_fruit);
//        fruit_list.super_method(super_fruit);
//        fruit_list.super_method(food);
//        fruit_list.super_method(apple);
//
//        fruit_list.generic_method(fruit);
//        fruit_list.generic_method(extends_fruit);
//        fruit_list.generic_method(super_fruit);
//        fruit_list.generic_method(food);
//        fruit_list.generic_method(apple);


//        Test.static_generic_method(list1);
//        Test.static_generic_method(list2);
//        Test.static_generic_method(list3);

    }


}
