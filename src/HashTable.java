public abstract class HashTable<Z> {
    private final Object[] keys;
    private final int capacity = 32;

    public HashTable() {
        this.keys = new Object[this.capacity];
    }

    protected int capacity() {
        return capacity;
    }

    public abstract int hashFunction(Z key);

    public void insert(Z key) {
        int index = this.hashFunction(key);

        if(index <= capacity - 1) {
            keys[index] = key;
        }
    }

    public void showKeys() {
        for (int i = 0; i < capacity; i++) {
            Object key = keys[i];
            if(key != null) {
                System.out.printf("keys[%d]: %s\n", i, keys[i]);
            }
        }
    }
}
