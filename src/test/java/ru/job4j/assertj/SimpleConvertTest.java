package ru.job4j.assertj;

import org.junit.jupiter.api.Test;
import org.assertj.core.data.Index;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 2024-03-30
 * 5. Утверждения с коллекциями [#504885]
 * Добавьте тестовые методы на каждый метод класса SimpleConvert,
 * в которых покажите возможности проверки содержимого коллекции и отдельных ее элементов.
 */
class SimpleConvertTest {
    @Test
    void checkConvertStringsToArray() {
        String[] expected = {"first", "second", "three", "four", "five"};
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1))
                .doesNotContainNull()
                .isNotEmpty()
                .endsWith("five")
                .isEqualTo(expected);
    }

    @Test
    void checkConvertStringsToList() {
        List<String> expected = List.of("first", "second", "three", "four", "five");

        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("first", "second", "three", "four", "five");
        assertThat(list)
                .isEqualTo(expected)
                .hasSize(5)
                .contains("second")
                .containsAnyOf("zero", "second", "six")
                .doesNotContainNull()
                .isNotEmpty()
                .endsWith("five")
                .isEqualTo(expected);
    }

    @Test
    void checkConvertStringsToSet() {
        Set<String> expected = Set.of("first", "second", "three", "four", "five");

        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert.toSet("first", "second", "three", "four", "five");
        assertThat(set)
                .isEqualTo(expected)
                .hasSize(5)
                .contains("second")
                .containsAnyOf("zero", "second", "six")
                .doesNotContainNull()
                .isNotEmpty()
                .isEqualTo(expected);
    }

    @Test
    void checkConvertStringsToMap() {
        Map<String, Integer> expected = Map.of(
                 "first",  0,
                 "second", 1,
                 "three",  2,
                 "four", 3,
                 "five", 4);
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap("first", "second", "three", "four", "five");
        assertThat(map).hasSize(5)
                .isNotEmpty()
                .isEqualTo(expected)
                .containsAnyOf(
                        Map.entry("whatever", 0),
                        Map.entry("four", 3));
    }
}