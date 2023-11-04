package design_patterns.java_design_pattern_Introduction.factory_method_pattern.idcard;

import design_patterns.java_design_pattern_Introduction.factory_method_pattern.framework.Product;

public class IDCard extends Product {
    private String owner;
    IDCard(String owner) {
        System.out.println(owner + "의 카드를 만듭니다.");
        this.owner = owner;
    }

    @Override
    public void use() {
        System.out.println(owner + "의 카드를 사용합니다.");
    }

    public String getOwner() {
        return owner;
    }
}
