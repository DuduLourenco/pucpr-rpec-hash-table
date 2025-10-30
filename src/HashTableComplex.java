public class HashTableComplex extends HashTable<String> {
    @Override
    public int hashFunction(String key) {
        if (key == null) return 0;

        int h = key.hashCode();
        h ^= (h >>> 16);

        return h & (capacity() - 1);
    }

}
