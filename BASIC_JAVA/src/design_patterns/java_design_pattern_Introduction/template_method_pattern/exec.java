package design_patterns.java_design_pattern_Introduction.template_method_pattern;

public class exec {
    public static void main(String[] args) {
        AbstractDisplay d1 = new CharDisplay('H');
        AbstractDisplay d2 = new StringDisplay("hello world!");
        AbstractDisplay d3 = new StringDisplay("안녕하세요");

        d1.display();
        d2.display();
        d3.display();
    }
}
