package java_ex.collections.list.array_list;

import java_ex.collections.list.CustomList;

import java.util.Arrays;

public class CustomArrayList<E> implements CustomList<E>, Cloneable {

    private static final int DEFAULT_CAPACITY = 10;
    private static final Object[] EMPTY_ARRAY = {};

    //배열에 담긴 요소의 개수 [용적크기가 아니다.]
    private int size;

    Object[] array;

    public CustomArrayList() {
        this.array = EMPTY_ARRAY;
        this.size = 0;
    }

    public CustomArrayList(int capacity) {
        this.array = new Object[capacity];
        this.size = 0;
    }
    
    //size [요소 갯수]가 capacity에 얼만큼 차있는지 확인하고 적절한 크기에 맞게 capacity 변경
    private void resize() {
        int array_capacity = array.length;

        //Arrays.equals 내부를 살펴보면 for문을 돌면서 요소하나씩 비교한다.
        if (Arrays.equals(array, EMPTY_ARRAY)) {
            array = new Object[DEFAULT_CAPACITY];
            return;
        }

        if(size == array_capacity) {
            int new_capacity = array_capacity * 2;

            array = Arrays.copyOf(array, new_capacity);

            return;
        }


        //불필요한 공간으로 인해 메모리 차지하는 것을 방지하기 위해
        //하지만 최소한의 용적크기와 비교하여 더 큰 값을 배열로 복사하는 것.
        if(size < (array_capacity / 2)) {
            int new_capacity = array_capacity / 2;

            array = Arrays.copyOf(array, Math.max(new_capacity, DEFAULT_CAPACITY));
            return;
        }
    }

    @Override
    public boolean add(E value) {
        addLast(value);
        return true;
    }

    public void addLast(E value) {

        if(size == array.length) {
            resize();
        }

        array[size] = value;
        size++;
    }

    public void addFirst(E value) {
        this.add(0, value);
    }

    @Override
    public void add(int index, E value) {

        //index가 size보다 클 경우 배열의 끝 값보다 크다는 의미
        //만약에 index가 size를 넘어갈 경우, 예를들어 size가 5인데, index 10일 경우
        // [1,2,3,4,5] 인 배열에 [1,2,3,4,5,null,null,null,6] 이렇게 값이 들어가 버려서 값의 연속성이 떨어져 버린다.
        //index > size 인 이유는 remove, set, get과는 다르게 add에서는 배열이 resize로 늘어날 수 있는점 + 마지막에 값을 추가 (array[size])가 되기 때문에.
        if(index > size || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }

        //index와 size가 같다면, 배열의 마지막 위치에 값을 넣을 수 있다는 것. 배열의 크기를 늘릴필요가 없다.
        if(index == size) {
            addLast(value);
        }
        else {
            //size와 array.length가 같다는 것은 현재 배열에 모든값이 들어있다는 의미로 배열의 사이즈를 증가시킨다.
            if(size == array.length) {
                resize();
            }

            /**
             *해당 인덱스를 비워두기 위해 인덱스 이후의 값들을 한칸 씩 뒤로 민다.
             * 여기서 앞에서 부터 뒤로 한칸씩 밀 경우, 밀린 값은 사라지게된다.
             * 예를들어 index = 1을 인자로 받았을 경우, index = 1의 값부터 한칸 씩 밀게되는데, index = 1의 값을 2로 밀경우
             * 2의 값을 저장할 임시 temp 변수가 필요하게 된다는 것이다.
             * 다르게 생각하면 이 문제를 해결할 수 있다. 앞에서 뒤로 미는게 아니라, 뒤에서 시작하여 앞의 값을 땡겨오는 것이다.
             * size 부터 시작하여 index까지 순회하며 값을 뒤에서 앞의 값을 가져오는 것이다.
             */
            for(int i = size; i > index; i--) {
                array[i] = array[i - 1];
            }

            //배열을 모두 민 다음 마지막으로 해당 인덱스에 값을 넣는다.
            array[index] = value;
            size++;
        }
    }

//    @SuppressWarnings("unchecked")
    @Override
    public E get(int index) {
        //index == size가 안되는 이유는, size(요소갯수)는 입력받은 값을 array에 넣을 때 사용되게 되는데, size와 같을경우 index 를 벗어나기 때문이다.
        if(index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        return (E) array[index];
    }

    @Override
    public void set(int index, E value) {
        //add와는 다르게 index == size가 되지 않는 이유는, set은 이미 존재하는 요소를 바꾸는 것이기때문이다. add처럼 추가가 아닌.
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        array[index] = value;
    }

    @Override
    public int indexOf(Object value) {
        int i = 0;

        for(i = 0; i < size; i++) {
            if(array[i].equals(value)) {
                return i;
            }
        }

        return -1;
    }

    public int lastIndexOf(Object value) {
        for(int i = size - 1; i >= 0; i--) {
            if(array[i].equals(value)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public boolean contains(Object value) {

        if(indexOf(value) >= 0) {
            return true;
        }

        return false;
    }

    @SuppressWarnings("unckecked")
    @Override
    public E remove(int index) {

        if(index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        E element = (E) array[index];
        array[index] = null;

        for(int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
            array[i + 1] = null;
        }

        size--;
        resize();

        return element;
    }

    @Override
    public boolean remove(Object value) {

        int index = indexOf(value);

        if(index == -1) {
            return false;
        }

        remove(index);
        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 새로운 배열로 초기화하지 않는 이유는 이전에 들어온 요소갯수만큼 또 들어올 수 있기 때문에 새로 초기화하지않고,
     * 배열의 값을 모두 null 처리한다.
     */
    @Override
    public void clear() {
        for(int i = 0; i < size; i++) {
            array[i] = null;
        }

        size = 0;
        resize();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {

        //해당 인스턴스를 clone한다. 어떠한 값이 올 지 모르기때문에 <?>를 사용한다, <?>의미는 <? extends Object>와 동일하다.
        CustomArrayList<?> cloneList = (CustomArrayList<?>) super.clone();


        cloneList.array = new Object[size];

        System.arraycopy(array, 0, cloneList.array, 0, size);

        return cloneList;
    }

    public Object[] toArray(int[] arr) {
        return Arrays.copyOf(array, size);
    }

    @SuppressWarnings("unchecked")
//    public <T> T[] toArray(T[] a) {
//        if (a.length < size) {
//            // copyOf(원본 배열, 복사할 길이, Class<? extends T[]> 타입)
//            return (T[]) Arrays.copyOf(array, size, (Class<? extends T[]>)a.getClass());
//        }
//        // 원본배열, 원본배열 시작위치, 복사할 배열, 복사할배열 시작위치, 복사할 요소 수
//        System.arraycopy(array, 0, a, 0, size);
//        return a;
//    }

    public <T> T[] toArray(T[] a) {
        if (a.length < size)
            // Make a new array of a's runtime type, but my contents:
            return (T[]) Arrays.copyOf(array, size, a.getClass());
        System.arraycopy(array, 0, a, 0, size);
        if (a.length > size)
            a[size] = null;
        return a;
    }

}
