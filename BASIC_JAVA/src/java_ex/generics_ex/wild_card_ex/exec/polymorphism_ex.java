package java_ex.generics_ex.wild_card_ex.exec;

import java_ex.generics_ex.wild_card_ex.objects.Food;
import java_ex.generics_ex.wild_card_ex.objects.Fruit;

public class polymorphism_ex {
    /**
     *
     * 여기서 볼 수 있듯이 new Fruit()가 Food로 업 캐스팅 되었다가
     * 다시 Fruit로 다운 캐스팅 될 때는 오류가 나지 않는다.
     * 메모리에 저장되어 있는 new Fruit()를 떠올리면 이해하기 쉽다
     * 메모리에 new Fruit()가 저장되어있는데, 업 캐스팅으로 제한된 method, field만을
     * 사용하다가 다시 다운캐스팅으로 Fruit로 돌아올때는 문제가 없다.
     *
     * 하지만 처음부터 Food()를 Fruit로 다운캐스팅은 불가능 하다
     * Fruit 에는 Food 보다 더 많은 method, field 를 갖고 있을 텐데
     * 부모인 Food 를 바로 다운캐스팅할 경우, Fruit만 갖고있는 method, field가 없기 때문이다.
     */
    public static void main(String[] args) {
        Food food = new Fruit();
//        Fruit fruit = (Fruit) new Food();
        Fruit fruit = (Fruit) food;

        System.out.println(fruit.toString());
    }
}
