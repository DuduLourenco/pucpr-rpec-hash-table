//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        HashTable<String> ht = new HashTable<String>() {
            @Override
            public int hashFunction(String key) {
                return key.length();
            }
        };

        ht.insert("Eduardo");
        ht.insert("Eduard0");

        ht.showKeys();
    }
}
