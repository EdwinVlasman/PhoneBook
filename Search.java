package phonebook;

import java.util.ArrayList;

public final class Search {

    public static long linearSearch (ArrayList<String> find, ArrayList<String> dir) {
        int count = 0;
        long start = System.currentTimeMillis();
//        System.out.println("Start searching (linear search)...");
        for (String name : find) {
            for (String s : dir) {
                if (s.contains(name)) {
                    count++;
                    break;
                }
            }
        }
        long finished = System.currentTimeMillis();
        long timeTaken = finished - start;

        System.out.printf("Found %d / %d entries. ", count, find.size());
        return timeTaken;
    }

    public static int jumpSearch(ArrayList<String> array, String target) {
        int currentRight = 0; // right border of the current block
        int prevRight = 0; // right border of the previous block

        /* If array is empty, the element is not found */
        if (array.size() == 0) {
            return 0;
        }

        /* Check the first element */
        if (array.get(currentRight).equals(target)) {
            return 1;
        }

        /* Calculating the jump length over array elements */
        int jumpLength = (int) Math.sqrt(array.size());

        /* Finding a block where the element may be present */
        while (currentRight < array.size() - 1) {

            /* Calculating the right border of the following block */
            currentRight = Math.min(array.size() - 1, currentRight + jumpLength);

            if (array.get(currentRight).compareTo(target) >= 0) {
                break; // Found a block that may contain the target element
            }

            prevRight = currentRight; // update the previous right block border
        }

        /* If the last block is reached and it cannot contain the target value => not found */
        if ((currentRight == array.size() - 1) && array.get(currentRight).compareTo(target) < 0) {
            return 0;
        }

        /* Doing linear search in the found block */
        return backwardSearch(array, target, prevRight, currentRight);
    }
    // part of jump search
    private static int backwardSearch(ArrayList<String> array, String target, int leftExcl, int rightIncl) {
        for (int i = rightIncl; i > leftExcl; i--) {
            if (array.get(i).compareTo(target) == 0) {
                return 1;
            }
        }
        return 0;
    }

    public static int binarySearch(ArrayList<String> array, String elem, int left, int right) {
        if (left > right) {
            return 0; // search interval is empty, the element is not found
        }

        int mid = left + (right - left) / 2; // the index of the middle element

        if (array.get(mid).contains(elem)) {
            return 1; // the element is found, return its index
        } else if (elem.replaceAll("\\d+", "").trim().compareTo(array.get(mid).replaceAll("\\d+", "").trim()) < 0) {
            return binarySearch(array, elem, left, mid - 1); // go to the left subarray
        } else {
            return binarySearch(array, elem, mid + 1, right); // go the the right subarray
        }
    }
}
