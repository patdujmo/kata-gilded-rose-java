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

            items[i] = switch (items[i].name) {
                case ITEM_AGED_BRIE -> updateAgedBried(items[i]);
                case ITEM_BACKSTAGE_PASSES -> updateBackstagePasses(items[i]);
                case ITEM_SULFURAS -> updateSulfuras(items[i]);
                default -> updateGeneralItem(items[i]);
            };
        }
    }

    private Item updateAgedBried(Item item) {
        item.sellIn = item.sellIn - 1;

        if (item.sellIn < 0) {
            item.quality = boundedAddition(item.quality, 2, QUALITY_BOUND);
        } else {
            item.quality = boundedAddition(item.quality, 1, QUALITY_BOUND);
        }

        return item;
    }

    private Item updateBackstagePasses(Item item) {

        item.sellIn = item.sellIn - 1;

        if (item.sellIn < 0) {
            item.quality = 0;
        } else if (item.sellIn < 5) {
            item.quality = boundedAddition(item.quality, 3, QUALITY_BOUND);
        } else if (item.sellIn < 10) {
            item.quality = boundedAddition(item.quality, 2, QUALITY_BOUND);
        } else {
            item.quality = boundedAddition(item.quality, 1, QUALITY_BOUND);
        }

        return item;
    }

    private Item updateSulfuras(Item item) {
        return item;
    }

    private Item updateGeneralItem(Item item) {
        item.sellIn = item.sellIn - 1;

        item.quality = zeroBoundedDecrement(item.quality);

        if (item.sellIn < 0) {
            item.quality = zeroBoundedDecrement(item.quality);
        }

        return item;
    }

    private int boundedAddition(int a, int b, int upperBound) {
        return a + b < upperBound ? a + b : upperBound;
    }

    private int zeroBoundedDecrement(int value) {
        return value > 0 ? value - 1 : value;
    }

}