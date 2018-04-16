package com.wolfmatemark.wmmuonlite.inventory;

import com.wolfmatemark.wmmuonlite.exception.InventoryIsFullException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Inventory {
    private static final int INVENTORY_MAX_SIZE = 20;

    private final List<Item> items;

    public Inventory() {
        this.items = new ArrayList<>();
    }

    public void addItem(Item item) throws InventoryIsFullException {
        if (items.size() < INVENTORY_MAX_SIZE) {
            items.add(item);
        } else {
            throw new InventoryIsFullException();
        }
    }

    public Optional<Item> getItemByName(String name) {
        return items.stream().filter(item -> item.getName().toUpperCase().equals(name.toUpperCase())).findFirst();
    }

    public void removeItemByName(String name) {
        Optional<Item> itemToRemove = getItemByName(name);
        if (itemToRemove.isPresent()) {
            items.remove(itemToRemove.get());
        }
    }

    public boolean isFull() {
        return items.size() < INVENTORY_MAX_SIZE;
    }

    public int getItemNumber() {
        return items.size();
    }

    public List<Item> getItems() {
        return items;
    }
}
