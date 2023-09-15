package java_class.collections.list.singly_linkedList;

import java_class.collections.list.CustomList;
import java_class.collections.node.Node;

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
    }

}
