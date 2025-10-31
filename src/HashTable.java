public abstract class HashTable<Z> {
    private EArrayList<Z>[] table;
    private int capacity = 32;
    private int size = 0;

    private static final double MAX_LOAD = 0.75; // fator de carga

    @SuppressWarnings("unchecked")
    public HashTable() {
        this.table = (EArrayList<Z>[]) new EArrayList[capacity];
    }

    protected int capacity() { return capacity; }
    protected int size()     { return size; }

    public abstract int hashFunction(Z key);

    public int insert(Z key) {
        if (key == null) return -1;

        final int idx = indexFor(key, capacity);
        int collided = 0;

        EArrayList<Z> bucket = table[idx];
        if (bucket == null) {
            bucket = new EArrayList<>();
            table[idx] = bucket;
        } else {
            collided = 1;
            if (bucket.contains(key)) return collided;
        }

        bucket.add(key);
        size++;

        if (loadFactor() > MAX_LOAD) {
            grow(capacity * 2);
        }
        return collided;
    }

    public boolean search(Z key) {
        if (key == null) return false;
        final int idx = indexFor(key, capacity);
        EArrayList<Z> bucket = table[idx];

        return bucket != null && bucket.contains(key);
    }

    public void printBucketSizes() {
        int empty = 0, max = 0, maxIdx = -1;
        for (int i = 0; i < capacity; i++) {
            int bucketSize = (table[i] == null) ? 0 : table[i].size();
            if (bucketSize == 0) empty++;
            if (bucketSize > max) { max = bucketSize; maxIdx = i; }
            //System.out.printf("bucket[%02d]: %d%n", i, bucketSize);
        }
        System.out.printf("Total itens: %d | Buckets: %d | Load factor: %.3f%n",
                size, capacity, loadFactor());
        System.out.printf("Buckets vazios: %d (%.1f%%)%n",
                empty, 100.0 * empty / capacity);
        System.out.printf("Maior bucket: %d (tam=%d)%n", maxIdx, max);
    }

    private double loadFactor() {
        return size / (double) capacity;
    }

    private int indexFor(Z key, int cap) {
        int h = hashFunction(key);
        return Math.floorMod(h, cap);
    }

    @SuppressWarnings("unchecked")
    private void grow(int newCapacity) {
        EArrayList<Z>[] oldTable = this.table;
        EArrayList<Z>[] newTable = (EArrayList<Z>[]) new EArrayList[newCapacity];

        for (int i = 0; i < oldTable.length; i++) {
            EArrayList<Z> bucket = oldTable[i];
            if (bucket == null || bucket.size() == 0) continue;

            for (int j = 0; j < bucket.size(); j++) {
                Z item = bucket.get(j);
                int idx = indexFor(item, newCapacity);
                EArrayList<Z> b = newTable[idx];
                if (b == null) {
                    b = new EArrayList<>();
                    newTable[idx] = b;
                }
                b.add(item);
            }
        }
        this.table = newTable;
        this.capacity = newCapacity;
    }
}
