package java_class.generics_ex.type_casting.exec;

import java_class.generics_ex.type_casting.ClassBox;
import java_class.generics_ex.type_casting.MarkerPencil;
import java_class.generics_ex.type_casting.Pencil;
import java_class.generics_ex.type_casting.Writable;

import java.util.Set;

public class Exec {

    public static void main(String[] args) {
        //MarkerPencil upcasting
        Set<Writable> writableSet = new ClassBox().getClass(Writable.class);
        writableSet.add(new Pencil());
        writableSet.add(new MarkerPencil());

        Set<Writable> writables = new ClassBox<Writable>().getClass2();
        writables.add(new Pencil());
        writables.add(new MarkerPencil());

        for (Writable writable : writableSet) {
            writable.write();
            System.out.println("writable.getClass().getSimpleName() = " + writable.getClass().getSimpleName());
            System.out.println("writable.getClass().getTypeName() = " + writable.getClass().getTypeName());
        }
        System.out.println("=============================================================================");

//        ClassNodeBox<?> classNodeBox = new ClassNodeBox<MarkerPencil>();
//        ClassNodeBox<Writable> classNodeBox1 = (ClassNodeBox<Writable>) classNodeBox;
//        classNodeBox1.add(new Pencil());
//        classNodeBox1.add(new MarkerPencil());
//        classNodeBox1.print();
//        classNodeBox1.create().set(new Pencil());

        MarkerPencil markerPencil = new MarkerPencil();
    }
}
