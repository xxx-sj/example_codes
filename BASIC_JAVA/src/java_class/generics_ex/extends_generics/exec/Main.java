package java_class.generics_ex.extends_generics.exec;

import java_class.generics_ex.extends_generics.ArmyUnit;
import java_class.generics_ex.extends_generics.Marine;

import java.util.List;

public class Main{
    public static void main(String[] args) {
        Marine marine1 = new Marine();
        Marine marine2 = new Marine();
        Marine marine3 = new Marine();
        Marine marine4 = new Marine();
        Marine marine5 = new Marine();

        ArmyUnit<Marine> armyUnit = new ArmyUnit<>();
        armyUnit.add(marine1);
        armyUnit.add(marine2);
        armyUnit.add(marine3);
        armyUnit.add(marine4);
        armyUnit.add(marine5);

        List<Marine> units = armyUnit.getUnits();

        for (Marine unit : units) {
            unit.attack();
        }
    }
}
