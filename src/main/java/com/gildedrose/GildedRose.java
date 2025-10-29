package com.gildedrose;

class GildedRose {
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
        item.quality = increaseQualityBelowThreshold(item.quality);
        item.sellIn = item.sellIn - 1;
        if (item.sellIn < 0) {
            item.quality = increaseQualityBelowThreshold(item.quality);
        }

        return item;
    }

    private Item updateBackstagePasses(Item item) {
        item.quality = increaseQualityBelowThreshold(item.quality);

        if (item.sellIn < 11) {
            item.quality = increaseQualityBelowThreshold(item.quality);
        }

        if (item.sellIn < 6) {
            item.quality = increaseQualityBelowThreshold(item.quality);
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
        if (item.quality > 0)
            item.quality = item.quality - 1;
        item.sellIn = item.sellIn - 1;
        if (item.sellIn < 0 && item.quality > 0) {
            item.quality = item.quality - 1;
        }
        return item;
    }

    private int increaseQualityBelowThreshold(int quality) {
        if (quality < 50)
            return quality + 1;
        else
            return quality;
    }

}