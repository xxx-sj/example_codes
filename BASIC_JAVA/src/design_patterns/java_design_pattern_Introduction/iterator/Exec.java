package design_patterns.java_design_pattern_Introduction.iterator;

public class Exec {
    public static void main(String[] args) {
        BookShelf bookShelf = new BookShelf(4);
        bookShelf.appendBook(new Book("A"));
        bookShelf.appendBook(new Book("B"));
        bookShelf.appendBook(new Book("C"));
        bookShelf.appendBook(new Book("D"));
        Iterator it = bookShelf.iterator();
        while(it.hasNext()) {
            Book next = (Book)it.next();
            System.out.println(next.getName());
        }
    }
}
