package ru.job4j.memory;

/**
 * 2024-04-09
 * 2. Stack, Heap, Metaspace [#504932]
 */

public class Example {
    public static void main(String[] args) {
        int x = 0;
        Object obj = new Object();
        Example example = new Example();
        example.action(obj);
    }

    public void action(Object parameter) {
        String str = parameter.toString();
        System.out.println(str);
    }
}