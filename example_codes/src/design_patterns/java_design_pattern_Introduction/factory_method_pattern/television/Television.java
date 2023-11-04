package design_patterns.java_design_pattern_Introduction.factory_method_pattern.television;

import design_patterns.java_design_pattern_Introduction.factory_method_pattern.framework.Product;

public class Television extends Product {
    private String brand;

    public Television(String brand) {
        System.out.println(brand + "의 티비가 만들어집니다.");
        this.brand = brand;
    }
    @Override
    public void use() {
        System.out.println(brand + " 텔레비전을 사용합니다.");
    }

    public String getBrand() {
        return brand;
    }
}
