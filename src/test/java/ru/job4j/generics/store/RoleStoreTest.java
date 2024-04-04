package ru.job4j.generics.store;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class RoleStoreTest {

    @Test
    void whenAddAndFindThenRoleNameIsRudolf() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Rudolf"));
        Role result = store.findById("1");
        assertThat(result.getRoleName()).isEqualTo("Rudolf");
    }

    @Test
    void whenAddAndFindThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Rudolf"));
        Role result = store.findById("10");
        assertThat(result).isNull();
    }

    @Test
    void whenAddDuplicateAndFindRoleNameIsRudolf() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Rudolf"));
        store.add(new Role("1", "Carlo"));
        Role result = store.findById("1");
        assertThat(result.getRoleName()).isEqualTo("Rudolf");
    }

    @Test
    void whenReplaceThenRoleNameIsCarlo() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Rudolf"));
        store.replace("1", new Role("1", "Carlo"));
        Role result = store.findById("1");
        assertThat(result.getRoleName()).isEqualTo("Carlo");
    }

    @Test
    void whenNoReplaceRoleThenNoChangeRoleName() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Rudolf"));
        store.replace("10", new Role("10", "Carlo"));
        Role result = store.findById("1");
        assertThat(result.getRoleName()).isEqualTo("Rudolf");
    }

    @Test
    void whenDeleteRoleThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Rudolf"));
        store.delete("1");
        Role result = store.findById("1");
        assertThat(result).isNull();
    }

    @Test
    void whenNoDeleteRoleThenRoleNameIsRudolf() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Rudolf"));
        store.delete("10");
        Role result = store.findById("1");
        assertThat(result.getRoleName()).isEqualTo("Rudolf");
    }

    @Test
    void whenReplaceOkThenTrue() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Rudolf"));
        boolean result = store.replace("1", new Role("1", "Carlo"));
        assertThat(result).isTrue();
    }

    @Test
    void whenReplaceNotOkThenFalse() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Rudolf"));
        boolean result = store.replace("10", new Role("10", "Carlo"));
        assertThat(result).isFalse();
    }
}