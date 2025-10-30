public abstract class HashTable<Z> {
    private final EArrayList[] keys;
    private final int capacity = 32;

    public HashTable() {
        this.keys = new EArrayList[this.capacity];
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

    public void showKeys() {
        for (int i = 0; i < capacity; i++) {
            System.out.printf("-- %d%n", i);
            EArrayList<Z> bucket = keys[i];

            if (bucket == null || bucket.size() == 0) {
                System.out.println("(vazio)");
                System.out.println("------");
                continue;
            }

            for (int j = 0; j < bucket.size(); j++) {
                Z item = bucket.get(j);
                System.out.printf("item: %s%n", String.valueOf(item));
            }
            System.out.println("------");
        }
    }
}
