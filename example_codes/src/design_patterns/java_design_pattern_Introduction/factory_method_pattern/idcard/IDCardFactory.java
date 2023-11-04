package design_patterns.java_design_pattern_Introduction.factory_method_pattern.idcard;

import design_patterns.java_design_pattern_Introduction.factory_method_pattern.framework.Factory;
import design_patterns.java_design_pattern_Introduction.factory_method_pattern.framework.Product;

import java.util.ArrayList;
import java.util.List;

public class IDCardFactory extends Factory {

    private List<String> owners = new ArrayList();
    @Override
    protected Product createProduct(String owner) {
        return new IDCard(owner);
    }

    @Override
    protected void registerProduct(Product product) {
        owners.add(((IDCard)product).getOwner());
    }

    public List<String> getOwners() {
        return owners;
    }
}
