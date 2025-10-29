package com.gildedrose;

class GildedRose {

    private static int QUALITY_BOUND = 50;

    private static final String ITEM_AGED_BRIE = "Aged Brie";
    private static final String ITEM_BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    private static final String ITEM_SULFURAS = "Sulfuras, Hand of Ragnaros";

    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            items[i] = updateItem(items[i]);
        }
    }

    private Item updateItem(Item item) {
        return switch (item.name) {
            case ITEM_AGED_BRIE -> updateAgedBrie(item);
            case ITEM_BACKSTAGE_PASSES -> updateBackstagePasses(item);
            case ITEM_SULFURAS -> updateSulfuras(item);
            default -> updateGeneralItem(item);
        };
    }

    private Item updateAgedBrie(Item item) {
        item.sellIn = item.sellIn - 1;

        if (isExpired(item)) {
            item.quality = upperBoundedAddition(item.quality, 2, QUALITY_BOUND);
        } else {
            item.quality = upperBoundedAddition(item.quality, 1, QUALITY_BOUND);
        }

        return item;
    }

    private Item updateBackstagePasses(Item item) {
        item.sellIn = item.sellIn - 1;

        if (isExpired(item)) {
            item.quality = 0;
        } else if (item.sellIn < 5) {
            item.quality = upperBoundedAddition(item.quality, 3, QUALITY_BOUND);
        } else if (item.sellIn < 10) {
            item.quality = upperBoundedAddition(item.quality, 2, QUALITY_BOUND);
        } else {
            item.quality = upperBoundedAddition(item.quality, 1, QUALITY_BOUND);
        }

        return item;
    }

    private Item updateSulfuras(Item item) {
        return item;
    }

    private Item updateGeneralItem(Item item) {
        item.sellIn = item.sellIn - 1;

        item.quality = zeroBoundedDecrement(item.quality);

        if (isExpired(item)) {
            item.quality = zeroBoundedDecrement(item.quality);
        }

        return item;
    }

    private boolean isExpired(Item item) {
        return item.sellIn < 0;
    }

    private int upperBoundedAddition(int base, int offset, int upperBound) {
        return base >= upperBound ? base : Integer.min(base + offset, upperBound);
    }

    private int zeroBoundedDecrement(int value) {
        return value <= 0 ? value : value - 1;
    }
}