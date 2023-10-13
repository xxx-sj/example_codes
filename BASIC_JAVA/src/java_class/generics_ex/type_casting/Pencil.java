package java_class.generics_ex.type_casting;

public class Pencil implements Writable {

    private String madeBy;

    public Pencil() {
        this.madeBy = "kim";
    }
    @Override
    public void write() {
        System.out.println("write using pencil");
    }
}
