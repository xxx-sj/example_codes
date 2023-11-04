package java_ex.collections.list.double_linked_list;

import java_ex.collections.list.CustomList;

import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class DLinkedList<E> implements CustomList<E> {

    private  DoubleNode<E> head;
    private DoubleNode<E> tail;

    private int size;

    public DLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public DoubleNode<E> search(int index) {

        if(index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        // size/2 를 해서 중간을 찾고 index + 1 을 해서 중간보다 크다면, tail쪽에 가깝게 된다.
        //여기서 +1을 하는 이유는 size는 요소 갯수로 1부터 시작이고, index는 0부터 시작하기 때문에
        if(index + 1 > size / 2) {
            //꼬리부터 시작하기 때문에,
            DoubleNode<E> x = tail;

            for(int i = size - 1; i > index; i--) {
                x = x.prev;
            }

            return x;
        }
        else {
            DoubleNode<E> x = head;
            //index보다 작을떄 까지 진행하는 이유는 index의 node전 값을 찾아 next값을 주면 되기 때문에
            for(int i = 0; i < index; i++) {
                x = x.next;
            }

            return x;
        }
    }


    public void addFirst(E value) {
        DoubleNode<E> newNode = new DoubleNode<>(value);

        newNode.next = head;

        if(head != null) {
            head.prev = newNode;
        }

        head = newNode;
        size++;

        if(head.next == null) {
            tail = head;
        }
    }

    @Override
    public boolean add(E value) {
        addLast(value);
        return true;
    }

    public void addLast(E value) {
        DoubleNode<E> newNode = new DoubleNode<>(value);

        if(size == 0) {
            addFirst(value);
            return;
        }

        tail.next = newNode;
        newNode.prev = tail;
        tail = newNode;
        size++;
    }


    public void add(int index, E value) {

        if(index > size || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        if(index == 0) {
            addFirst(value);
            return;
        }

        if(index == size) {
            addLast(value);
            return;
        }

        DoubleNode<E> newNode = new DoubleNode<>(value);

        //prevNode 찾기
        DoubleNode<E> prev_node = this.search(index - 1);

        DoubleNode<E> next_node = prev_node.next;

        prev_node.next = null;
        next_node.prev = null;

        newNode.prev = prev_node;
        newNode.next = next_node;

        prev_node.next = newNode;
        next_node.prev = newNode;
        size++;
    }

    public E remove() {
        //앞에 노드를 삭제하기 때문에
        DoubleNode<E> headNode = head;

        if(headNode == null) {
            throw new NoSuchElementException();
        }

        //반환 값
        E element = headNode.data;

        DoubleNode<E> next_node = headNode.next;

        //prev는 이미 null 이다.
        head.data = null;
        head.next = null;

        //next_node가 null이 아니라면 prev_node 즉, 현재 head인 삭제할 노드의 연결을 끊는다.
        if(next_node != null) {
            next_node.prev = null;
        }

        head = next_node;
        size--;

        if(size == 0) {
            tail = null;
        }

        return element;
    }

    @Override
    public E remove(int index) {

        if(index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }



        if(index == 0) {
            return remove();
        }

        DoubleNode<E> prev_node = search(index - 1);
        DoubleNode<E> removed_node = prev_node.next;
        DoubleNode<E> next_node = removed_node.next;

        E element = removed_node.data;


        //연결 끊기.

        prev_node.next = null;
        removed_node.prev = null;
        removed_node.next = null;
        removed_node.data = null;

        //remove_noed가 있다는 것은 prev_node는 존재한다는 말이지만, next_node는 확실하지 않다.
        //index = 0[head]는 위에서 처리했기 때문에 prev_node의 null상태는 따로 체크하지 않는다.
        if(next_node != null) {
            next_node.prev = null;

            next_node.prev = prev_node;
            prev_node.next = next_node;
        }

        else {
            tail = prev_node;
        }

        size--;

        return element;
    }

    @Override
    public boolean remove(Object value) {
        DoubleNode<E> prev_node = head;
        //value와 일치하는 node
        DoubleNode<E> x = head;

        for(; x != null; x = x.next) {
            if(value.equals(x.data)) {
                break;
            }
            prev_node = x;
        }


        //일치하는 요소가 없음
        if(x == null) {
            return false;
        }

        //삭제하려는 요소가 head일때는 앞에 요소를 삭제하는 remove를 호출한다.
        if(x.equals(head)) {
            remove();
            return true;
        }

        else {
            DoubleNode<E> next_node = x.next;
            //연결 끊기
            prev_node.next = null;
            x.data = null;
            x.next = null;
            x.prev = null;

            if(next_node != null) {
                next_node.prev = null;


                next_node.prev = prev_node;
                prev_node.next = next_node;
            }
            else {
                tail = prev_node;
            }
        }

        size--;

        return true;
    }

    @Override
    public E get(int index) {
        return search(index).data;
    }

    @Override
    public void set(int index, E value) {
        DoubleNode<E> replaceNode = search(index);
        replaceNode.data = null;
        replaceNode.data = value;

    }


    @Override
    public int indexOf(Object c) {
        int index = 0;

        for(DoubleNode<E> x = head; x != null; x = x.next) {
            if(c.equals(x.data)) {
                return index;
            }

            index++;
        }

        return -1;
    }


    public int lastIndexOf(Object c) {
        int index = size;

        for(DoubleNode<E> x = tail; x != null; x = x.prev) {
            index--;
            if(c.equals(x.data)) {
                return index;
            }
        }

        return -1;
    }

    @Override
    public boolean contains(Object item) {
        return indexOf(item) >= 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        for (DoubleNode<E> x = head; x != null;) {
            DoubleNode<E> nextNode = x.next;
            x.data = null;
            x.next = null;
            x.prev = null;
            x = nextNode;
        }
        head = tail = null;
        size = 0;
    }

    public Object clone() throws CloneNotSupportedException {

        @SuppressWarnings("unchecked")
        DLinkedList<? super E> clone = (DLinkedList<? super E>) super.clone();

        clone.head = null;
        clone.tail = null;
        clone.size = 0;

        for (DoubleNode<E> x = head; x != null; x = x.next) {
            clone.addLast(x.data);
        }

        return clone;
    }

    public Object[] toArray() {
        Object[] array = new Object[size];
        int idx = 0;
        for (DoubleNode<E> x = head; x != null; x = x.next) {
            array[idx++] = (E) x.data;
        }
        return array;
    }

    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        if (a.length < size) {
            // Arrya.newInstance(컴포넌트 타입, 생성할 크기)
            a = (T[]) java.lang.reflect.Array.newInstance(a.getClass().getComponentType(), size);
        }
        int i = 0;
        Object[] result = a;
        for (DoubleNode<E> x = head; x != null; x = x.next) {
            result[i++] = x.data;
        }
        return a;
    }

    public void sort() {
        /**
         *  Comparator를 넘겨주지 않는 경우 해당 객체의 Comparable에 구현된
         *  정렬 방식을 사용한다.
         *  만약 구현되어있지 않으면 cannot be cast to class java.lang.Comparable
         *  에러가 발생한다.
         *  만약 구현되어있을 경우 null로 파라미터를 넘기면
         *  Arrays.sort()가 객체의 compareTo 메소드에 정의된 방식대로 정렬한다.
         */
        sort(null);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void sort(Comparator<? super E> c) {
        Object[] a = this.toArray();
        Arrays.sort(a, (Comparator) c);

        int i = 0;
        for (DoubleNode<E> x = head; x != null; x = x.next, i++) {
            x.data = (E) a[i];
        }
    }
}
