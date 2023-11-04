package design_patterns.java_design_pattern_Introduction.factory_method_pattern.television;

import design_patterns.java_design_pattern_Introduction.factory_method_pattern.framework.Factory;
import design_patterns.java_design_pattern_Introduction.factory_method_pattern.framework.Product;

import java.util.ArrayList;
import java.util.List;

public class TelevisionFactory extends Factory {
    private List<Product> televisions = new ArrayList<>();
    @Override
    protected Product createProduct(String brand) {
        return new Television(brand);
    }

    @Override
    protected void registerProduct(Product product) {
        televisions.add(product);
    }

    public List<Product> getTelevisions() {
        return televisions;
    }
}
