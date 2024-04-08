package ru.job4j.simplearraylist;

import java.util.*;

/**
 * 2024-04-07
 * 1. Динамический список на массиве. [#158]
 * В этом задании мы создадим динамический массив, аналог ArrayList.
 * Напомню, что в Java все массивы статические, то есть при создании массива указывается его размер, который потом нельзя изменить.
 * По сути массив - это группа ячеек памяти расположение друг за другом.
 * Если элементов становится больше чем размер массива,
 * то нам нужно создать новый массив большего размера и скопировать туда элементы из предыдущего массива.
 * По такому образу работает ArrayList.
 * 1. Внутри контейнер должен базироваться на массиве Object[] container.
 * 2. Использовать стандартные коллекции JDK (ArrayList, LinkedList и т.д.) запрещено.
 * 3. Контейнер должен быть динамическим, т.е. при полном заполнении увеличиваться в два раза,
 * потому что расширение это тяжелая операция. Ссылка при этом остается той же, но ссылается на другой массив,
 * для этого удобно использовать метод Arrays.copyOf(). Расширение массива вынесите в отдельный метод.
 * 4. Итератор должен реализовывать fail-fast поведение, т.е. если с момента создания итератора
 * в коллекцию добавили новый элемент, итератор должен кидать ConcurrentModificationException.
 * Это достигается через введение счетчика изменений - modCount. Каждая операция, которая структурно
 * модифицирует (добавление и удаление элементов) коллекцию должна увеличивать этот счетчик.
 * В свою очередь итератор запоминает значение этого счетчика на момент своего создания (expectedModCount),
 * а затем на каждой итерации сравнивает сохраненное значение, с текущим значением поля modCount,
 * если они отличаются, то генерируется исключение
 * 5. Списки работают с индексами, поэтому важно понимать, как делается валидация индекса. Валидация индекса это проверка,
 * что индекс находится в диапазоне [0, size - 1].
 * Если находится вне этого диапазона, то происходит выброс исключения IndexOutOfBoundsException.
 * Objects.checkIndex(index, array.length);
 */
public class SimpleArrayList<T> implements SimpleList<T> {

    private T[] container;
    private int size;
    private int modCount;

    public SimpleArrayList(int capacity) {
        container = (T[]) new Object[capacity];
        size = 0;
        modCount = 0;
    }

    @Override
    public void add(T value) {
        if (size == container.length) {
            capacityUp();
        }
        container[size++] = value;
        modCount++;
    }

    private void capacityUp() {
        int newCapacity = container.length == 0 ? 3 : container.length * 2;
        container = Arrays.copyOf(container, newCapacity);
    }

    @Override
    public T set(int index, T newValue) {
        Objects.checkIndex(index, size);
            T oldValue = container[index];
            container[index] = newValue;
            return oldValue;
    }

    @Override
    public T remove(int index) {
        Objects.checkIndex(index, size);
        T removed = container[index];
        System.arraycopy(container, index + 1, container, index, container.length - index - 1);
        size--;
        modCount++;
        return removed;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int expectedModCount = modCount;
            int current = 0;
            @Override
            public boolean hasNext() {
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
                return current < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[current++];
            }
        };
    }
}