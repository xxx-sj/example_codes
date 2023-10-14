package java_class.generics_ex.type_casting;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ClassBox<T extends Writable> {

    private Map<Class<? extends Writable>, Set<? extends Writable>> classSetMap = new HashMap<>();
    public ClassBox() {
        classSetMap.put(Pencil.class, new HashSet<Pencil>());
        classSetMap.put(MarkerPencil.class, new HashSet<MarkerPencil>());
    }

    @SuppressWarnings("unchecked")
    public <Q extends Writable> Set<Q> getClass(Class<Q> key) {
        return (Set<Q>) classSetMap.get(MarkerPencil.class);
    }

    @SuppressWarnings("unchecked")
    public Set<T> getClass2() {
        return (Set<T>) classSetMap.get(MarkerPencil.class);
    }
}
