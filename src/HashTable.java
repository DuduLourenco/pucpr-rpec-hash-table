public abstract class HashTable<Z> {
    private Object[] keys;
    private int size = 32;

    public HashTable() {
        this.keys = new Object[this.size];
    }

    public abstract int hashFunction(Z key);

    public void insert(Z key) {
        int index = this.hashFunction(key);

        if(index <= size - 1) {
            keys[index] = key;
        }
    }

    public void showKeys() {
        for (int i = 0; i < size; i++) {
            Object key = keys[i];
            if(key != null) {
                System.out.printf("keys[%d]: %s\n", i, keys[i]);
            }
        }
    }
}
