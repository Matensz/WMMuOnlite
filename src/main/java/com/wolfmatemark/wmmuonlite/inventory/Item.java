package com.wolfmatemark.wmmuonlite.inventory;

import com.wolfmatemark.wmmuonlite.StatSet;

import java.util.Objects;

public final class Item {
    private final String name;
    private final ItemType itemType;
    private final StatSet statSet;

    private Item(ItemBuilder builder) {
        this.name = builder.name;
        this.itemType = builder.itemType;
        this.statSet = builder.statSet;
    }

    public String getName() { return name; }

    public ItemType getItemType() {
        return itemType;
    }

    public StatSet getStatSet() {
        return statSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(name, item.name) &&
                itemType == item.itemType &&
                Objects.equals(statSet, item.statSet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, itemType, statSet);
    }

    @Override
    public String toString() {
        return "Item name: " + name +
                "\nitem type: " + itemType +
                "\nstats: " + statSet + "\n";
    }

    public static final class ItemBuilder {
        private String name;
        private ItemType itemType;
        private StatSet statSet;

        public ItemBuilder() {
        }

        public ItemBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public ItemBuilder withItemType(ItemType itemType) {
            this.itemType = itemType;
            return this;
        }

        public ItemBuilder withStatSet(StatSet statSet) {
            this.statSet = statSet;
            return this;
        }

        public Item build() {
            return new Item(this);
        }
    }
}
