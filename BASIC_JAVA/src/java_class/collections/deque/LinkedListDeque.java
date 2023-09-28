package java_class.collections.deque;

import java_class.collections.list.double_linked_list.DoubleNode;
import java_class.collections.queue.Queue;

import java.util.NoSuchElementException;

public class LinkedListDeque<E> implements Queue<E> {

    private DoubleNode<E> head;

    private DoubleNode<E> tail;

    private int size;

    public LinkedListDeque() {
        head = tail = null;
        size = 0;
    }

    public boolean offerFirst(E value) {
        DoubleNode<E> newNode = new DoubleNode<E>(value);
        newNode.next = head;

        /**
         * head가 null이 아닐 경우에만 기존 head노드의 prev 변수가
         * 새 노드를 가리키도록 한다.
         * 이유는 기존 head노드가 없는 경우(null)는 데이터가
         * 아무 것도 없던 상태였으므로 head.prev를 하면 잘못된 참조가 된다.
         */
        if (head != null) {
            head.prev = newNode;
        }

        head = newNode;	// head가 가리키는 노드가 새 노드를 가리키도록 한다.
        size++;

        /**
         * 다음에 가리킬 노드가 없는 경우(=데이터가 새 노드밖에 없는 경우 = 이전의 head가 null인경우)
         * 데이터가 한 개(새 노드)밖에 없으므로 새 노드는 처음 시작노드이자
         * 마지막 노드다. 즉 tail = head 다.
         */
        if (head.next == null) {
            tail = head;
        }
        return true;

    }

    @Override
    public boolean offer(E value) {
        return offerLast(value);
    }

    public boolean offerLast(E value) {


        if(size == 0)
            return offerFirst(value);

        DoubleNode<E> newNode = new DoubleNode<>(value);

        tail.next = newNode;
        newNode.prev = tail;
        tail = newNode;
        size++;

        return true;
    }

    @Override
    public E poll() {
        return pollLast();
    }

    public E pollLast() {
        if (size == 0)
            return null;

        E element = head.data;

        DoubleNode<E> nextNode = head.next;

        head.data = null;
        head.next = null;

        if(nextNode != null) {
            nextNode.prev = null;
        }

        head = null;
        head = nextNode;
        size--;

        /**
         * 삭제된 요소가 덱의 유일한 요소였을 경우
         * 그 요소는 head 이자 tail이었으므로
         * 삭제되면서 tail도 가리킬 요소가 없기 때문에
         * size가 0일경우 tail도 null로 변환
         */
        if(size == 0) {
            tail = null;
        }

        return element;
    }

    public E remove() {
        return removeFirst();
    }

    public E removeFirst() {
        E element = poll();

        if(element == null) {
            throw new NoSuchElementException();
        }
        return element;
    }
}
