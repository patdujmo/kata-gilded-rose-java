package com.gildedrose;

class GildedRose {

    private static int QUALITY_BOUND = 50;

    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {

        for (int i = 0; i < items.length; i++) {

            items[i] = switch (items[i].name) {
                case "Aged Brie" -> updateAgedBried(items[i]);
                case "Backstage passes to a TAFKAL80ETC concert" -> updateBackstagePasses(items[i]);
                case "Sulfuras, Hand of Ragnaros" -> updateHand(items[i]);
                default -> updateGeneralItem(items[i]);
            };
        }
    }

    private Item updateAgedBried(Item item) {
        item.quality = boundedIncrement(item.quality, QUALITY_BOUND);

        item.sellIn = item.sellIn - 1;

        if (item.sellIn < 0) {
            item.quality = boundedIncrement(item.quality, QUALITY_BOUND);
        }

        return item;
    }

    private Item updateBackstagePasses(Item item) {
        item.quality = boundedIncrement(item.quality, QUALITY_BOUND);

        if (item.sellIn < 11) {
            item.quality = boundedIncrement(item.quality, QUALITY_BOUND);
        }

        if (item.sellIn < 6) {
            item.quality = boundedIncrement(item.quality, QUALITY_BOUND);
        }

        item.sellIn = item.sellIn - 1;

        if (item.sellIn < 0)
            item.quality = 0;

        return item;
    }

    private Item updateHand(Item item) {
        return item;
    }

    private Item updateGeneralItem(Item item) {
        item.quality = zeroBoundedDecrement(item.quality);

        item.sellIn = item.sellIn - 1;

        if (item.sellIn < 0) {
            item.quality = zeroBoundedDecrement(item.quality);
        }

        return item;
    }

    private int boundedIncrement(int value, int QUALITY_BOUND) {
        return value < QUALITY_BOUND ? value + 1 : value;    }

    private int zeroBoundedDecrement(int value) {
        return value > 0 ? value - 1 : value;
    }

}