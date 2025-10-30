public class HashTableComplex extends HashTable<String> {
    @Override
    public int hashFunction(String key) {
        if (key == null || capacity() == 0) return 0;
        int h = 0;
        for (int i = 0; i < key.length(); i++) {
            h = h * 31 + key.charAt(i);
        }
        return (h & 0x7fffffff) % capacity();
    }
}
