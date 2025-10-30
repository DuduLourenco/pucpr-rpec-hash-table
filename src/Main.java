public class Main {
    public static void main(String[] args) {
        FileReader fileReader = new FileReader();
        String[] lines = fileReader.readLines();
        System.out.println("--Hash Table Teste----------------------");
        System.out.printf("Linhas lidas: %d\n\n", lines.length);

        System.out.println("--Hash Table Simple---------------------");
        HashTableSimple hashTableSimple = new HashTableSimple();
        measure(hashTableSimple, lines);

        System.out.println("\n--Hash Table Complex--------------------");
        HashTableComplex hashTableComplex = new HashTableComplex();
        measure(hashTableComplex, lines);
    }

    private static void measure(HashTable<String> hashTable, String[] lines) {
        final long start = System.nanoTime();
        for (String line : lines) {
            hashTable.insert(line);
        }
        final long end = System.nanoTime();

        long elapsedNs = end - start;
        double ms = elapsedNs / 1_000_000.0;
        double sec = elapsedNs / 1_000_000_000.0;
        double rps = (lines.length / Math.max(sec, 1e-9));

        System.out.printf("Taxa de inserção: %.2f registros por segundo\n", rps);
        System.out.printf("Tempo total para inserção: %.3f ms\n", ms);
    }
}
