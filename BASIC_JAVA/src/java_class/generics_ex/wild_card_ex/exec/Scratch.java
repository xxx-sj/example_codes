package java_class.generics_ex.wild_card_ex.exec;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

class Scratch {
    static class FoodBox<T> {
        private final List<? super T> items;

        public FoodBox() {
            this.items = new ArrayList<>();
        }

        public List<? super T> getBox() {
            return items;
        }

        public void print(Collection<? extends T> items) {
            items.stream()
                    .peek(item -> System.out.println(item.getClass().getSimpleName()))
                    .collect(Collectors.toList());
        }

        public void print() {
            this.items.stream()
                    .peek(item -> System.out.println(item.getClass().getSimpleName()))
                    .collect(Collectors.toList());
        }

        public void add(T item) {
            items.add(item);
        }

//        public void addAll(Collection<? super T> items) {
//            this.items.addAll(items.stream()
//                    .map(item -> (T) item)
//                    .collect(Collectors.toList()));
//
//            for (Object item : items) {
//
//            }
//        }

        public void addAll(Collection<? extends T> items) {
            this.items.addAll(items.stream()
                    .map(item -> item)
                    .collect(Collectors.toList())
            );

            for (T item : items) {

            }
        }
    }

    public static void main(String[] args) {
        FoodBox<? super Food> box = new FoodBox<>();
        box.add(new Apple());
        box.add(new Carrot());
        box.add(new Banana());

        box.addAll(List.of(new Carrot(), new Banana()));

        box.print(List.of(new Apple(), new Carrot(), new Banana()));
        box.print();
    }

    static class Food extends Object {
    }

    static class Fruit extends Food {
    }

    static class Vegetable extends Food {
    }

    static class Apple extends Fruit {
    }

    static class Banana extends Fruit {
    }

    static class Carrot extends Vegetable {
    }
}
