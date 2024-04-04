package ru.job4j.generics.store;

import java.util.HashMap;
import java.util.Map;

/**
 * 2024-04-04
 * 2. Реализовать Store<T extends Base> [#157]
 * В этом задании необходимо реализовать контейнеры для хранения объектов.
 * Структура контейнеров будет одинаковая. Все ограничения хранимых типов мы должны задать с помощью Generics.
 * Сделаем каркас универсального хранилища.
 * Хранить данные мы будем в Map:
 * private final Map<String, T> storage = new HashMap<>();
 * ключом будет являться id нашей модели,
 * а значением - объект, который мы будем хранить в хранилище, в данном случае это обобщенный тип T.
 * Требуется реализовать методы:
 * - add() - метод добавляет в хранилище объект типа T, при этом метод ничего не возвращает.
 * Если добавляемый объект уже присутствует в хранилище, то добавления не происходит.
 * - replace() - метод выполняет замену объекта по id, на объект который передается
 * в параметрах метода и возвращает true, если замена выполнена успешно;
 * - delete() - метод удаляет пару ключ-значение по id, который передается в метод;
 * - findById() - метод возвращает объект по ключу, который равен id, передаваемый в качестве параметра
 * метода или возвращает null если по такому ключу в Map еще нет пару ключ-значение.
 * Для реализации используйте методы из интерфейса  java.util.Map, наиболее подходящие под требования задания.
 * Выбирайте методы, которые выполняют нужное действие и возвращают результат требуемого типа.
 * Задание:
 * 1. Реализуйте методы в классе MemStore.
 */
public final class MemStore<T extends Base> implements Store<T> {

    private final Map<String, T> storage = new HashMap<>();

    @Override
    public void add(T model) {
        if (!storage.containsKey(model.getId())) {
            storage.put(model.getId(), model);
        }
    }

    @Override
    public boolean replace(String id, T model) {
        if (storage.containsKey(id)) {
        storage.replace(id, model);
        return true;
        }
        return false;
    }

    @Override
    public void delete(String id) {
        if (storage.containsKey(id)) {
            storage.remove(id);
        }
    }

    @Override
    public T findById(String id) {
        return storage.getOrDefault(id, null);
    }
}