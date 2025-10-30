public abstract class HashTable<Z> {
    private final EArrayList<Z>[] keys;
    private final int capacity = 32;

    @SuppressWarnings("unchecked")
    public HashTable() {
        this.keys = (EArrayList<Z>[]) new EArrayList<?>[capacity];
    }

    protected int capacity() {
        return capacity;
    }

    public abstract int hashFunction(Z key);

    public int insert(Z key) {
        if (key == null) return -1;

        int collided = 0;
        final int index = Math.floorMod(hashFunction(key), capacity);

        EArrayList<Z> bucket = keys[index];
        if (bucket == null) {
            bucket = new EArrayList<>();
            keys[index] = bucket;
        } else {
            collided = 1;
        }
        bucket.add(key);
        return collided;
    }

    public boolean search(Z key) {
        if (key == null) return false;

        final int index = Math.floorMod(hashFunction(key), capacity);

        EArrayList<Z> bucket = keys[index];
        if (bucket == null) {
            return false;
        }

        return bucket.contains(key);
    }

    public void printBucketSizes() {
        int total = 0, empty = 0, max = 0, maxIdx = -1;

        for (int i = 0; i < capacity; i++) {
            int bucketSize = (keys[i] == null) ? 0 : keys[i].size();
            total += bucketSize;
            if (bucketSize == 0) empty++;
            if (bucketSize > max) { max = bucketSize; maxIdx = i; }
            System.out.printf("bucket[%02d]: %d%n", i, bucketSize);
        }

        double loadFactor = total / (double) capacity;
        System.out.println("----");
        System.out.printf("Total itens: %d | Buckets: %d | Load factor (média/bucket): %.3f\n",
                total, capacity, loadFactor);
        System.out.printf("Buckets vazios: %d (%.1f%%)\n",
                empty, 100.0 * empty / capacity);
        System.out.printf("Maior bucket: %d (tam=%d)\n", maxIdx, max);
    }

    public int[] bucketSizes() {
        int[] sizes = new int[capacity];
        for (int i = 0; i < capacity; i++) {
            sizes[i] = (keys[i] == null) ? 0 : keys[i].size();
        }
        return sizes;
    }
}
