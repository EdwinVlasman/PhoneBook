package phonebook;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        ArrayList<String> dir = scanFile("P:\\directory.txt");
        ArrayList<String> find = scanFile("P:\\find.txt");

        System.out.println("Start searching (linear search)...");
        long timeTaken = Search.linearSearch(find, dir);
        System.out.println("Time taken: " + duration(timeTaken) + "\n");
        bubbleSortJumpSearch(find, dir, timeTaken);
        dir = scanFile("P:\\directory.txt");
        find = scanFile("P:\\find.txt");
        quickSortBinarySearch(find, dir);
        dir = scanFile("P:\\directory.txt");
        find = scanFile("P:\\find.txt");
        hashTable(find, dir);

    }

    public static void hashTable(ArrayList<String> find, ArrayList<String> dir) {
        System.out.println("Start searching (hash table)...");
        int count = 0;
        HashTable<Integer> phoneBook = new HashTable<>(dir.size());
        long start = System.currentTimeMillis();
        for (String s : dir) {
            int value = Integer.parseInt(s.replaceAll("[^\\d+]", ""));
            String key = s.replaceAll("\\d+", "").trim();
            phoneBook.put(key, value);
        }
        long finished = System.currentTimeMillis();
        long sort = finished - start;
        long startSearch = System.currentTimeMillis();
        for (String s : find) {
            count += phoneBook.get(s);
        }
        long finishedSearch = System.currentTimeMillis();
        long totalSearchTime = finishedSearch - startSearch;

        System.out.printf("Found %d / %d entries. ", count, find.size());
        System.out.println("Time taken: " + duration(totalSearchTime + sort));
        System.out.println("Creating time: " + duration(sort));
        System.out.println("Searching time: " + duration(totalSearchTime) + "\n");
    }

    public static void quickSortBinarySearch(ArrayList<String> find, ArrayList<String> dir) {
        System.out.println("Start searching (quick sort + binary search)...");
        int count = 0;
        long sort = Sort.quickSort(dir, 0, dir.size() - 1);
        long start = System.currentTimeMillis();
        for (String s : find) {
            count += Search.binarySearch(dir, s, 0, dir.size());
        }
        long finished = System.currentTimeMillis();
        long totalSearchTime = finished - start;

        System.out.printf("Found %d / %d entries. ", count, find.size());
        System.out.println("Time taken: " + duration(totalSearchTime + sort));
        System.out.println("Sorting time: " + duration(sort));
        System.out.println("Searching time: " + duration(totalSearchTime) + "\n");
    }

    public static void bubbleSortJumpSearch(ArrayList<String> find, ArrayList<String> dir, long time) {
        System.out.println("Start searching (bubble sort + jump search)...");
        long sort = Sort.bubbleSort(dir, time);
        long linear;
        int count = 0;
        if (sort >= (10 * time)) {
            linear = Search.linearSearch(find, dir);
            System.out.println("Time taken: " + duration(linear + sort));
            System.out.println("Sorting time: " + duration(sort) + "" +
                    " - STOPPED, moved to linear search");
            System.out.println("Searching time: " + duration(linear) + "\n");
        } else {
            long start = System.currentTimeMillis();
            for (String s : find) {
                if (Search.jumpSearch(dir, s) == 1) {
                    count++;
                }
            }
            long finished = System.currentTimeMillis();
            long jump = finished - start;
            System.out.printf("Found %d / %d entries. ", count, find.size());
            System.out.println("Time taken: " + duration(jump + sort));
            System.out.println("Sorting time: " + duration(sort));
            System.out.println("Searching time: " + duration(jump) + "\n");
        }
    }

    public static String duration(long time) {
        Duration duration = Duration.ofMillis(time);
        int min = duration.toMinutesPart();
        int sec = duration.toSecondsPart();
        int milisec = duration.toMillisPart();
        return min + " min. " + sec + " sec. " + milisec + " ms.";
    }

    public static ArrayList<String> scanFile(String dir) {
        ArrayList<String> array = new ArrayList<>();
        File file = new File(dir);
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                array.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return array;
    }
}
