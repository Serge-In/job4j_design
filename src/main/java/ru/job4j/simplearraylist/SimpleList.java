package ru.job4j.simplearraylist;

public interface SimpleList<T> extends Iterable<T> {
    void add(T value);
    T set(int index, T newValue);
    T remove(int index);
    T get(int index);
    int size();
}