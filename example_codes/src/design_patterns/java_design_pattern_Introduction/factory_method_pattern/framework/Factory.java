package design_patterns.java_design_pattern_Introduction.factory_method_pattern.framework;

public abstract class Factory {
    //template method pattern
    public final Product create(String owner) {
        Product p = createProduct(owner);
        registerProduct(p);

        return p;
    }

    //제품을 만드는 추상 메서드 factory method
    protected abstract Product createProduct(String owner);
//    public Product createProduct(String owner) {
//        throw new RuntimeException();
//    };
    //제품을 등록하는 추상 메서드
    protected abstract void registerProduct(Product product);
}
