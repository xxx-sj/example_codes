package java_ex.generics_ex.type_casting;

public class MarkerPencil extends Pencil {

    private Color color;

    public MarkerPencil() {
        color = Color.RED;
    }

    @Override
    public void write() {
        System.out.println("write using markerPen");
    }

    public void underLine() {
        System.out.println("under line");
    }
}
