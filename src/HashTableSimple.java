public class HashTableSimple extends HashTable<String> {
    @Override
    public int hashFunction(String key) {
        return key.length() - 1;
    }
}
