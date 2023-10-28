package Junit_test.chap08.hard_coding;

public class PayInfo {

    private String id;
    private String name;
    private int age;
    public PayInfo(String id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
