package ru.job4j.assertj;

import org.junit.jupiter.api.Test;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

/**
 * 2024-03-30
 * Напишите тесты, в которых проверьте генерацию всех исключений, которые должен генерировать класс NameLoad.
 * Проверьте, что в сообщении об ошибке передаются параметры, которые эту ошибку вызвали.
 * Например, если передать в метод parse строку с нарушением формата, например, такую "key:value",
 * то будет сгенерировано исключение и в сообщении об ошибке будет строка "key:value",
 * которая это исключение вызвала. Этот факт надо проверить в тесте. Так же и для других исключений.
 */
class NameLoadTest {
    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void checkNoData() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::parse)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("array is empty");
    }

    @Test
    void checkThrowNoEqualsSymbolException() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse("key=value", "key1=value1", "Val"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("not contain the symbol '='");
    }

    @Test
    void checkThrowKeyException() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse("key=value", "key1=value1", "=Val"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("does not contain a key");
    }

    @Test
    void checkThrowValueException() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse("key=value", "key1=value1", "key2="))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("does not contain a value");
    }

    @Test
    void checkValidateNoException() {
        NameLoad nameLoad = new NameLoad();
        nameLoad.parse("key=value", "key1=value1", "key2=value2");

        Map<String, String> expected = Map.of(
                "key", "value",
                "key1", "value1",
                "key2", "value2");
        assertDoesNotThrow(nameLoad::getMap);
        assertThat(expected).isEqualTo(nameLoad.getMap());
    }
}
