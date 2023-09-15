package java_class.collections.list;


import java_class.basic_class.TV;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        CustomArrayList<Number> list = new CustomArrayList<>();

        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);

        TV[] arr = new TV[]{new TV(), new TV(), new TV()};
//        int[] arr = new int[]{2,3,4};
        TV[] array = list.<TV>toArray(arr);
//        Object[] array = list.toArray(arr);

        System.out.println(Arrays.toString(array));

        new ArrayList<Integer>().toArray();

    }
}
