package java_class.generics_ex.wild_card_ex.exec;

import java.util.ArrayList;
import java.util.List;

public class WildCard_class {



    //TODO: Unexpected wildcard
//    static class Test<? extends Number> {}
    //TODO: Cannot resolve symbol 'T'
//    static class Test<? extends T> {}

    static class Test<T> {
        public void test_method(List<? extends T> list) {

        }

        public static <E> void static_test_method(List<? extends E> list) {

        }
    }


    public static void main(String[] args) {

//        Test<?> test1 = new Test<String>();
        Test<? extends Integer> test2 = new Test<Integer>();
//        Test<? super Integer> test3 = new Test<Number>();


        List<Integer> list1 = new ArrayList<>();
//        List<Number> list2 = new ArrayList<>();
//        List<Object> list3 = new ArrayList<>();
//        List<String> list4 = new ArrayList<>();

        test2.test_method(list1);
//        Test.static_test_method(list1);
//        Test.static_test_method(list2);
//        Test.static_test_method(list3);

    }


}
