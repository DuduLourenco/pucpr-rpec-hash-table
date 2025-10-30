import java.lang.reflect.Array;

public class EArrayList<T> {
    private T[] data;
    private int capacity;
    private int size = 0;

    @SuppressWarnings("unchecked")
    public EArrayList() {
        this.capacity = 16;
        this.data = (T[]) new Object[this.capacity];
    }

    @SuppressWarnings("unchecked")
    public EArrayList(int capacity) {
        this.capacity = capacity;
        this.data = (T[]) new Object[this.capacity];
    }

    private void isIndexInBounds(int index) {
        boolean isIndexOutBounds = index < 0 || index >= size;
        if(isIndexOutBounds) {
            throw new IndexOutOfBoundsException(String.format("Index %d out of bounds for length %d", index, size));
        }
    }

    @SuppressWarnings("unchecked")
    private void grow() {
        int capacity = this.data.length;
        int newCapacity = (int) (capacity * 2);

        T[] resizedData = (T[]) new Object[newCapacity];

        for (int i = 0; i < capacity; i++) {
            resizedData[i] = this.data[i];
        }

        this.capacity = newCapacity;
        this.data = resizedData;
    }

    public void add(T element) {
        if(size == this.data.length) {
            grow();
        }

        this.data[size] = element;
        size = size + 1;
    }

    public void add(int index, T element) {
        this.isIndexInBounds(index);
        if(size == this.data.length) {
            grow();
        }

        if(this.data[index] != null) {
            for (int i = size + 1; i > index; i--) {
                this.data[i] = this.data[i - 1];
            }
        }

        this.data[index] = element;
        size = size + 1;
    }

    public T remove(int index) {
        this.isIndexInBounds(index);

        T element = this.data[index];

        for (int i = index; i < size; i++) {
            T nextObject = null;
            if(i + 1 < this.data.length) {
                nextObject = this.data[i + 1];
            }
            this.data[i] = nextObject;
        }

        size = size - 1;
        return element;
    }

    public T remove(T element) {
        int index = indexOf(element);

        if(index == -1) {
            return null;
        }

        return remove(index);
    }

    public T set(int index, T element) {
        this.isIndexInBounds(index);

        T oldElement = this.data[index];
        this.data[index] = element;
        return oldElement;
    }

    public T get(int index) {
        this.isIndexInBounds(index);

        return this.data[index];
    }

    public int indexOf(T element) {
        for (int i = 0; i < size; i++) {
            if(element.equals(this.data[i])) {
                return i;
            }
        }

        return -1;
    }

    public boolean contains(T element) {
        return indexOf(element) >= 0;
    }

    @SuppressWarnings("unchecked")
    public T[] toArray() {
        T[] out = (T[]) new Object[size];
        for (int i = 0; i < size; i++) {
            out[i] = data[i];
        }
        return out;
    }

    @SuppressWarnings("unchecked")
    public <A> A[] toArray(A[] a) {
        // se 'a' for pequeno, crie um novo array do MESMO tipo de 'a'
        if (a.length < size) {
            Class<?> component = a.getClass().getComponentType();
            A[] out = (A[]) Array.newInstance(component, size);
            for (int i = 0; i < size; i++) {
                out[i] = (A) this.data[i];
            }
            return out;
        } else {
            // cabe em 'a': copie e, se sobrar espaço, coloque null sentinela
            for (int i = 0; i < size; i++) {
                a[i] = (A) this.data[i];
            }
            if (a.length > size) a[size] = null;
            return a;
        }
    }
}
