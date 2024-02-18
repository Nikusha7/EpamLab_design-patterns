package com.epam.rd.autocode.decorator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Decorators {
    public static List<String> evenIndexElementsSubList(List<String> sourceList) {

//        List<String> evenIndexElementsSublist = new ArrayList<>();
//
//        for (int i = 0; i < sourceList.size(); i++) {
//            if (i % 2 == 0) {
//                evenIndexElementsSublist.add(sourceList.get(i));
//            }
//        }
//        return evenIndexElementsSublist;

        EvenIndexElementsListDecorator subList = new EvenIndexElementsListDecorator(sourceList);
        return subList.getEvenIndexElementsSublist();
    }

    private static class EvenIndexElementsListDecorator extends ArrayList<String> {
        private final List<String> sourceList;

        public EvenIndexElementsListDecorator(List<String> sourceList) {
            this.sourceList = sourceList;
        }

        @Override
        public String get(int index) {
            if (index < 0 || index >= size()) {
                throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size());
            }
            return sourceList.get(index * 2);
        }

        @Override
        public int size() {
            return (sourceList.size() + 1) / 2;
        }

        @Override
        public Iterator<String> iterator() {
            return new Iterator<String>() {
                private int index = 0;

                @Override
                public boolean hasNext() {
                    return index < size();
                }

                @Override
                public String next() {
                    if (!hasNext()) {
                        throw new IndexOutOfBoundsException("Iterator out of bounds");
                    }
                    return get(index++);
                }
            };
        }

        public List<String> getEvenIndexElementsSublist() {
            List<String> evenIndexElementsSublist = new ArrayList<>();
            for (int i = 0; i < size(); i++) {
                evenIndexElementsSublist.add(get(i));
            }
            return evenIndexElementsSublist;
        }


    }

}