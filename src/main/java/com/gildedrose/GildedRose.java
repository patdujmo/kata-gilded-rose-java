package com.gildedrose;

import java.util.Arrays;

class GildedRose {
    private final String CONTENT_1 = "Aged Brie";
    private final String CONTENT_2 = "Backstage passes to a TAFKAL80ETC concert";
    private final String CONTENT_3 = "Sulfuras, Hand of Ragnaros";
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
        if (!item.name.equals(CONTENT_1)
                && !item.name.equals(CONTENT_2)) {
            if (item.quality > 0) {
                if (!item.name.equals(CONTENT_3)) {
                    item.quality = decreaseByOne(item.quality);
                }
            }
        } else {
            if (item.quality < 50) {
                item.quality = increaseByOne(item.quality);
                if (item.name.equals(CONTENT_2)) {
                    updateBackstagePasses(item);
                }
            }
        }

        if (!item.name.equals(CONTENT_3)) {
            item.sellIn = decreaseByOne(item.sellIn);
        }

        if (isExpired(item)) {
            if (!item.name.equals(CONTENT_1)) {
                if (!item.name.equals(CONTENT_2)) {
                    if (item.quality > 0) {
                        if (!item.name.equals(CONTENT_3)) {
                            item.quality = decreaseByOne(item.quality);
                        }
                    }
                } else {
                    item.quality = item.quality - item.quality;
                }
            } else {
                increaseQualityUnder50(item);
            }
        }
        return item;
    }

    private Item updateItem(Item item) {
        return switch (item.name) {
            case CONTENT_1 -> updateAgedBrie(item);
            case CONTENT_2 -> updateBackstagePasses(item);
            case CONTENT_3 -> updateSulfuras(item);
            default -> updateOtherStuff(item);
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

    private Item updateOtherStuff(Item item) {
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