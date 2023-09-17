package java_class.collections.list.singly_linkedList;

import java_class.collections.list.CustomList;
import java_class.collections.node.Node;

import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class SLinkedList<E> implements CustomList<E> {

    private Node<E> head; //노드의 첫 부분
    private Node<E> tail; //노드의 마지막 부분

    private int size;

    public SLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    private Node<E> search(int index) {

        if(index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }


        Node<E> x = head;

        for(int i = 0; i < index; i++) {
            x = x.next;
        }

        return x;
    }

    public void addFirst(E value) {
        //입력받은 value로 새 노드를 만든다.
        Node<E> newNode = new Node<E>(value);

        //가장 앞 쪽에 넣었기 때문에 head부분을 next로 갖는다.
        newNode.next = head;
        //가장 앞쪽으로 왔기 때문에 HEAD에 할당한다.
        head = newNode;
        size++;

        /**
         * 데이터가 한개 뿐일 때는 시작이자 끝이기 때문에
         */
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
        Node<E> newNode = new Node<>(value);

        if (size == 0) {
            addFirst(value);
            return;
        }

        tail.next = newNode;
        this.tail = newNode;
        size++;
    }

    @Override
    public void add(int index, E value) {
        //index가 size보다 크면 안되는 이유는 SIZE보다 크면 중간에 null이 들어간다는 소리랑 같다.
        if(index < 0 || index > size) {
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

        Node<E> prev_node = search(index - 1);
        Node<E> next_node = prev_node.next;
        Node<E> newNode = new Node<>(value);

        prev_node.next = null;
        prev_node.next = newNode;
        newNode.next = next_node;
        size++;
    }


    public E remove() {
        Node<E> headNode = head;

        if(headNode == null )
            throw new NoSuchElementException();

        E element = headNode.data;

        Node<E> nextNode = head.next;

        head.data = null;
        head.next = null;

        //nextNode가 null일경우 head는 null이 된다. element가 1개일 때
        head = nextNode;
        size--;


        /**
         * 삭제한 node가 유일한 요소였을 경우
         * head이자, taul이였으니 삭제되면서 tail도 가리킬 요소가 없기 때문에
         * size가 0일 경우 tail도 null로 변환
         */
        if(size == 0) {
            tail = null;
        }

        return  element;
    }

    @Override
    public E remove(int index) {

        if(index == 0) {
            return remove();
        }

        if(index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        Node<E> prev_node = search(index - 1);
        Node<E> remove_node = prev_node.next;
        Node<E> next_node = remove_node.next;

        E element = remove_node.data; //삭제된 노드의 데이터 반환을 위해


        prev_node.next = next_node;

        //ndex_node를 넣었음에도 null일 경우 remove_node가 맨 끝이었을 경우이다.
        if(prev_node.next == null) {
            tail = prev_node;
        }


        remove_node.data = null;
        remove_node.next = null;
        size--;

        return element;

    }

    @Override
    public boolean remove(Object value) {

        Node<E> prev_node = head;
        boolean hasValue = false;
        Node<E> x = head; //removeNode;

        for(; x != null; x = x.next) {
            if(value.equals(x.data)) {
                hasValue = true;
                break;
            }

            prev_node = x;
        }

        //끝까지 가거나 head가 null일 때 null을 반환하기때문에
        if(x == null) {
            return false;
        }

        if(x.equals(head)) {
            remove();
            return true;
        }

        //삭제하려는 node x의 next를 prev_node.next에 연결한다. x를 삭제하기 위해
        prev_node.next = x.next;

        if(prev_node.next == null) {
            tail = prev_node;
        }

        x.data = null;
        x.next = null;
        size--;

        return true;
    }


    @Override
    public E get(int index) {
        return search(index).data;
    }

    @Override
    public void set(int index, E value) {
        Node<E> replace_node = search(index);

        replace_node.data = null;
        replace_node.data = value;
    }


    @Override
    public int indexOf(Object value) {

        int index = 0;

        for(Node<E> x = head; x.next != null; x = x.next) {
            if(value.equals(x.data)) {
                return index;
            }

            index++;
        }

        return -1;
    }

    @Override
    public boolean contains(Object value) {
        return indexOf(value) >= 0;
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
        for(Node<E> x = head; x!= null;) {
            Node<E> next_node = head.next;
            x.data = null;
            x.next = null;
            x = next_node;
        }

        head = tail = null;
        size = 0;
    }

    public Object clone() throws CloneNotSupportedException {
        /**
         * 설명을 덧붙이자면, super.clone() 자체가 생성자 비슷한 역할이고
         * shallow copy를 통해 사실상 new SLinkedList() 를 호출하는 격이라 제대로
         * 완벽하게 복제하려면 clone한 리스트의 array 또한 새로 생성해서 해당 배열에
         * copy를 해주어야 한다.
         */
        @SuppressWarnings("unchcked")
        SLinkedList<? super E> clone = (SLinkedList<? super E>) super.clone();

        //얕은복사가 되어비리니 새로운 객체를 넣어줘야한다.
        //각 노드를 끊고, 처음부터 끝까지 현재 리스트의 데이터를 clone 리스트에 넣어준다.
        clone.head = null;
        clone.tail = null;
        clone.size = 0;

        for(Node<E> x= head; x.next != null; x = x.next) {
            clone.addLast(x.data);
        }

        return clone;
    }

    public Object[] toArray() {
        Object[] array = new Object[size];
        int idx = 0;
        for(Node<E> x = head; x != null; x = x.next) {
            array[idx++] = (E) x.data;
        }

        return array;
    }

    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        if(a.length < size) {
            a = (T[]) java.lang.reflect.Array.newInstance(a.getClass().getComponentType(), size);
        }

        int i = 0;
        Object[] result = a;
        for(Node<E> x = head; x != null; x = x.next) {
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

    @SuppressWarnings({"unchecked", "rawtypes"})
    public void sort(Comparator<? super E> c) {
        Object[] a= this.toArray();
        Arrays.sort(a, (Comparator) c);


        int i = 0;
        for (Node<E> x = head; x != null; x = x.next, i++) {
            x.data = (E) a[i];
        }
    }



}
