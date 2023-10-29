package design_patterns.java_design_pattern_Introduction.template_method_pattern;

public class CharDisplay extends AbstractDisplay{

    private char ch;
    public CharDisplay(char ch) {
        this.ch = ch;
    }
    @Override
    public void open() {
        System.out.print("<<");
    }

    @Override
    public void print() {
        System.out.print(ch);
    }

    @Override
    public void close() {
        System.out.println(">>");
    }

    public static void main(String[] args) {

    }
}
