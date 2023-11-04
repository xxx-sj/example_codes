package java_ex.generics_ex.extends_generics;

import java.util.ArrayList;
import java.util.List;

public class ArmyUnit<T extends Unit & Attackable> {
    private List<T> units;

    private static final int MAX_UNIT_SIZE = 12;

    public ArmyUnit() {
        units = new ArrayList<>();
    }


    public List<T> getUnits() {
        return units;
    }
    public void add(T t) {
        if (units.size() == MAX_UNIT_SIZE) {
            throw new ArrayIndexOutOfBoundsException("더이상 추가할 수 없습니다.");
        }

        units.add(t);
    }

    public void remove(T t) {
        units.remove(t);
    }

    public int unitSize() {
        return units.size();
    }

}
