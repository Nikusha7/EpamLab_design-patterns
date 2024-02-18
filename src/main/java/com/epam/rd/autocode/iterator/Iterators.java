package com.epam.rd.autocode.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

class Iterators {
    public static Iterator<Integer> intArrayTwoTimesIterator(int[] array) {
        return new Iterator<>() {
            private int count = 0;
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < array.length;
            }

            @Override
            public Integer next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (count == 0) {
                    count++;
                    return array[index];
                } else {
                    count = 0;
                    return array[index++];
                }
            }
        };
    }

    public static Iterator<Integer> intArrayThreeTimesIterator(int[] array) {
        return new Iterator<>() {
            int count = 0;
            int index = 0;

            @Override
            public boolean hasNext() {
                return index < array.length;
            }

            @Override
            public Integer next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (count < 2) {
                    count++;
                    return array[index];
                } else {
                    count = 0;
                    return array[index++];
                }
            }
        };
    }

    public static Iterator<Integer> intArrayFiveTimesIterator(int[] array) {
        return new Iterator<>() {
            int count = 0;
            int index = 0;

            @Override
            public boolean hasNext() {
                return index < array.length;
            }

            @Override
            public Integer next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (count < 4) {
                    count++;
                    return array[index];
                } else {
                    count = 0;
                    return array[index++];
                }
            }
        };
    }


    public static Iterable<String> table(String[] columns, int[] rows) {
        return new TableIterable(columns, rows);
    }

    private static class TableIterable implements Iterable<String> {
        private final String[] columns;
        private final int[] rows;

        public TableIterable(String[] columns, int[] rows) {
            this.columns = columns;
            this.rows = rows;
        }

        @Override
        public Iterator<String> iterator() {
            return new TableIterator(columns, rows);
        }


    }

    private static class TableIterator implements Iterator<String> {
        private final String[] columns;
        private final int[] rows;

        private int index = 0;
        private int count = 0;

        public TableIterator(String[] columns, int[] rows) {
            this.columns = columns;
            this.rows = rows;
        }

        @Override
        public boolean hasNext() {
            return index < columns.length;
        }

        @Override
        public String next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            if (count++ < rows.length - 1) {
                return columns[index] + rows[count - 1];
            } else {
                count = 0;
                return columns[index++] + rows[rows.length - 1];
            }
        }
    }

}