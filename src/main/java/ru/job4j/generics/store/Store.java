package ru.job4j.generics.store;

/**
 * 2024-04-04
 * 2. Реализовать Store<T extends Base> [#157]
 */
public interface Store<T extends Base> {

    void add(T model);

    boolean replace(String id, T model);

    void delete(String id);

    T findById(String id);
}