package java_ex.generics_ex.type_casting;

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
