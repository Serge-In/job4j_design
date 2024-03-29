package ru.job4j.assertj;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

/**
 * 2024-03-29
 * 3. Утверждения с примитивными типами [#504883]
 * Создайте тестовый класс BoxTest. Добавьте минимум по два теста на каждый метод класса Box.
 * В каждом тесте используйте несколько утверждений, доступных для конкретного типа оцениваемого результата.
 * Хорошо зарекомендовал себя шаблон тестов, получивший название AAA - Arrange, Act, Assert.
 * На этапе Arrange производится подготовка данных для проверяемого действия
 * - создаются нужные объекты, поля объектов наполняются тестовыми данными.
 * Этап Act - это выполнение действия объекта, которое должно будет оцениваться, и сохранение результата этого действия.
 * Этап Assert - это проверка соответствие фактического результата действия ожидаемому результату.
 */
class BoxTest {

    @Test
    void isThisSphere() {
        Box box = new Box(0, 3);
        String result = box.whatsThis();
        assertThat(result).isEqualTo("Sphere")
                .contains("S")
                .hasSize(6);
    }

    @Test
    void isThisCube() {
        Box box = new Box(8, 2);
        String result = box.whatsThis();
        assertThat(result).isEqualTo("Cube")
                .contains("b")
                .hasSize(4);
    }

    @Test
    void isThisUnknown() {
        Box box = new Box(2, 2);
        String result = box.whatsThis();
        assertThat(result).isEqualTo("Unknown object");
    }

    @Test
    void getNumberOfVertices() {
        Box box = new Box(8, 2);
        int result = box.getNumberOfVertices();
        assertThat(result).isEqualTo(8);
    }

    @Test
    void getNumberOfVerticesForUnknown() {
        Box box = new Box(2, 2);
        int result = box.getNumberOfVertices();
        assertThat(result).isEqualTo(-1);
    }

    @Test
    void isExist() {
        Box box = new Box(8, 2);
        boolean result = box.isExist();
        assertThat(result).isEqualTo(true);
    }

    @Test
    void isNoExist() {
        Box box = new Box(-1, 2);
        boolean result = box.isExist();
        assertThat(result).isEqualTo(false);
    }

    @Test
    void getAreaForSphere() {
        int edge = 2;
        Box box = new Box(0, edge);
        double result = box.getArea();
        double expected = 4 * Math.PI * Math.pow(edge, 2);
        assertThat(result).isEqualTo(expected, withPrecision(0.001d));
    }

    @Test
    void getAreaForUnknown() {
        Box box = new Box(5, 2);
        double result = box.getArea();
        assertThat(result).isEqualTo(0, withPrecision(0.001d));
    }
}