package ru.job4j.generics.animal;

import java.util.*;

/**
 * 2024-04-03
 * 1. Что такое обобщенные типы (generics) [#4952]
 * Добавьте 3 модели данных, которые образуют иерархию наследования: Animal - Predator - Tiger.
 * Ниже представлен class Generics, который использует эти модели.
 * Он содержит ошибки компиляции.
 * Вам необходимо их поправить: закомментировать многострочным комментарием строки, которые их вызывают,
 * а также правильно применить wildcards.
 * При этом:
 * 1-ый метод - работает без ограничений, т.е. в него можно передавать коллекцию, которая хранит любые типы.
 * 2-ой метод - должен иметь ограничение сверху и ограничиваться классом Predator.
 * 3-ий метод - должен иметь ограничение снизу и ограничиваться классом Predator.
 */
public class Generics {
    public static void main(String[] args) {
        Generics generics = new Generics();
        List<Animal> first = new ArrayList<>();
        List<Predator> second = new ArrayList<>();
        List<Tiger> third = new ArrayList<>();
        first.add(new Animal());
        second.add(new Predator());
        third.add(new Tiger());

        generics.printObject(first);
        generics.printObject(second);
        generics.printObject(third);
        System.out.println();

        /**
         *  generics.printBoundedWildCard(first);
         */
        generics.printBoundedWildCard(second);
        generics.printBoundedWildCard(third);
        System.out.println();

        generics.printLowerBoundedWildCard(first);
        generics.printLowerBoundedWildCard(second);
        /**
         * generics.printLowerBoundedWildCard(third);
         */
    }

    public void printObject(List<?> list) {
        for (Object next : list) {
            System.out.println("Текущий элемент: " + next);
        }
    }

    public void printBoundedWildCard(List<? extends Predator> list) {
        for (Object next : list) {
            System.out.println("Текущий элемент: " + next);
        }
    }

    public void printLowerBoundedWildCard(List<? super Predator> list) {
        for (Object next : list) {
            System.out.println("Текущий элемент: " + next);
        }
    }
}
