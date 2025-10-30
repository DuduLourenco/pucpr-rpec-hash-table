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
        int collidedCount = 0;
        final long insStart = System.nanoTime();
        for (String line : lines) {
            int collided = hashTable.insert(line);
            if (collided == 1) collidedCount++;
        }
        final long insEnd = System.nanoTime();

        printMetrics("Inserção", insStart, insEnd, lines.length);
        System.out.printf("Qtd. de colisões: %d (%.2f%%)\n",
                collidedCount,
                100.0 * collidedCount / Math.max(lines.length, 1));

        int foundHits = 0;
        final long hitStart = System.nanoTime();
        for (String line : lines) {
            if (hashTable.search(line)) foundHits++;
        }
        final long hitEnd = System.nanoTime();

        printMetrics("Busca", hitStart, hitEnd, lines.length);
        System.out.printf("Encontrados: %d de %d\n", foundHits, lines.length);
    }

    private static void printMetrics(String label, long startNs, long endNs, int nOps) {
        long elapsedNs = endNs - startNs;
        double ms = elapsedNs / 1_000_000.0;
        double sec = elapsedNs / 1_000_000_000.0;
        double opsPerSec = nOps / Math.max(sec, 1e-9);

        System.out.printf(
                "%s -> %.3f ms | %.2f ops/s\n",
                label, ms, opsPerSec
        );
    }
}
