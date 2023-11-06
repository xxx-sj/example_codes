package design_patterns.java_design_pattern_Introduction.prototype.framework;

public interface Product extends Cloneable {
    public abstract void use(String s);
    public abstract Product createClone();
}
