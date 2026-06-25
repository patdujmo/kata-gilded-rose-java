package com.gildedrose;

import java.util.Arrays;

class GildedRose {
    private final String AGED_BRIE = "Aged Brie";
    private final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    private final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        Arrays.stream(items).forEach(item -> {
            item = tempUpdateItem(item);
        });
    }

    private Item tempUpdateItem(Item item) {
        if (!item.name.equals(AGED_BRIE)
                && !item.name.equals(BACKSTAGE_PASSES)) {
            if (item.quality > 0) {
                if (!item.name.equals(SULFURAS)) {
                    item.quality = decreaseByOne(item.quality);
                }
            }
        } else {
            if (item.quality < 50) {
                item.quality = increaseByOne(item.quality);
                if (item.name.equals(BACKSTAGE_PASSES)) {
                    updateBackstagePasses(item);
                }
            }
        }

        if (!item.name.equals(SULFURAS)) {
            item.sellIn = decreaseByOne(item.sellIn);
        }

        if (isExpired(item)) {
            if (!item.name.equals(AGED_BRIE)) {
                if (!item.name.equals(BACKSTAGE_PASSES)) {
                    if (item.quality > 0) {
                        if (!item.name.equals(SULFURAS)) {
                            item.quality = decreaseByOne(item.quality);
                        }
                    }
                } else {
                    item.quality = 0;
                }
            } else {
                increaseQualityUnder50(item);
            }
        }
        return item;
    }

    private Item updateItem(Item item) {
        return switch (item.name) {
            case AGED_BRIE -> updateAgedBrie(item);
            case BACKSTAGE_PASSES -> updateBackstagePasses(item);
            case SULFURAS -> updateSulfuras(item);
            default -> item;
        };
    }

    private Item updateAgedBrie(Item item) {
        return item;
    }

    private Item updateSulfuras(Item item) {
        item.quality = decreaseByOne(item.quality);
        return item;
    }

    private Item updateBackstagePasses(Item item) {
        if (item.sellIn < 11) {
            item = increaseQualityUnder50(item);
        }

        if (item.sellIn < 6) {
            item = increaseQualityUnder50(item);
        }
        return item;
    }

    private Item increaseQualityUnder50(Item item) {
        if (item.quality < 50) {
            item.quality = increaseByOne(item.quality);
        }
        return item;
    }

    private int increaseByOne(int itemElement) {
        return itemElement + 1;
    }

    private int decreaseByOne(int itemElement) {
        return itemElement - 1;
    }

    private boolean isExpired(Item item) {
        return item.sellIn < 0;
    }
}