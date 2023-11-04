package java_ex.basic_class.abstract_ex;

public class Main {
    public static void main(String[] args) {
        Character[] character = new Character[2];

        character[0] = new John();
        character[1] = new MinSoo();

        for (Character c : character) {
            c.printJob();
            c.attack();
        }
    }
}
