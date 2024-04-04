package ru.job4j.generics.store;

/**
 * Все модели наследуются от базовой модели Base.
 * Например, модель данных User, которая будет использоваться ниже и наследовать класс Base, может выглядеть следующим образом:
 */
public class User extends Base {

    private final String username;

    public User(String id, String name) {
        super(id);
        this.username = name;
    }

    public String getUsername() {
        return username;
    }
}