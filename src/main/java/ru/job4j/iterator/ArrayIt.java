package ru.job4j.iterator;

import java.util.Iterator;

/**
 * 2024-03-29
 * 2. Подключение библиотеки AssertJ [#504881 #488708]
 */
public class ArrayIt implements Iterator<Integer> {
    private final int[] data;
    private int point = 0;

    public ArrayIt(int[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        return point < data.length;
    }

    @Override
    public Integer next() {
        return data[point++];
    }
}