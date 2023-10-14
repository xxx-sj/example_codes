package design_patterns.head_first_design_patterns.iterator;

/**
 * 서가를 나타내는 클래스
 */
public class BookShelf implements Aggregate {
    private Book[] books;
    private int last = 0;
    public BookShelf(int maxsize) {
        this.books = new Book[maxsize];
    }

    public Book getBookAt(int index) {
        if(index < 0 || index > books.length - 1) {
            throw new IndexOutOfBoundsException();
        }

        return books[index];
    }

    public void appendBook(Book book) {
        this.books[last] = book;
        last++;
    }

    public int getLength() {
        return last;
    }
    @Override
    public Iterator iterator() {
        return new BookShelfIterator(this);
    }
}
