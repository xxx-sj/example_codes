package design_patterns.java_design_pattern_Introduction.prototype.framework;

import java.util.HashMap;
import java.util.Map;

public class Manager {
    private final Map<String, Product> showcase = new HashMap<>();

    public void register(String name, Product proto) {
        showcase.put(name, proto);
    }

    public Product create(String protoName) {
        Product p = showcase.get(protoName);
        return p.createClone();
    }
}
