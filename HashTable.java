package phonebook;

public class HashTable<T> {
    private int size;
    private TableEntry[] table;

    public HashTable(int size) {
        this.size = size;
        table = new TableEntry[size];
    }

    public void put(String key, T value) {
        int idx = findKey(key);

//        if (idx == -1) {
//            rehash();
//            idx = findKey(key);
//        }

        table[idx] = new TableEntry(key, value);
    }

    public int get(String key) {
        int idx = findKey(key);

        if (idx == -1 || table[idx] == null) {
            return 0;
        }

        return 1;
    }

//    public void remove(String key) {
//        int idx = findKey(key);
//
//        if (!(idx == -1 || table[idx] == null)) {
//            table[idx].remove();
//        }
//    }

    private int findKey(String key) {
        return Math.abs(key.hashCode() % size);
    }

//    private void rehash() {
//        this.size *= 2;
//        TableEntry[] temp = this.table;
//        this.table = new TableEntry[this.size];
//        for (TableEntry tableEntry : temp) {
//            put(tableEntry.getKey(), (T) tableEntry.getValue());
//        }
//
//    }

//    @Override
//    public String toString() {
//        StringBuilder tableStringBuilder = new StringBuilder();
//
//        for (int i = 0; i < table.length; i++) {
//            if (table[i] == null) {
//                tableStringBuilder.append(i + ": null");
//            } else {
//                tableStringBuilder.append(i + ": key=" + table[i].getKey()
//                        + ", value=" + table[i].getValue()
//                        + ", removed=" + table[i].isRemoved());
//            }
//
//            if (i < table.length - 1) {
//                tableStringBuilder.append("\n");
//            }
//        }
//
//        return tableStringBuilder.toString();
//    }


    private class TableEntry<T> {
        private final String key;
        private final T value;
        private boolean removed;

        public TableEntry(String key, T value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public T getValue() {
            return value;
        }

        public void remove() {
            removed = true;
        }

        public boolean isRemoved() {
            return removed;
        }
    }
}
