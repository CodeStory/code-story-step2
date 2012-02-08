package fr.xebia.katas.gildedrose;

import org.fest.util.VisibleForTesting;

import java.util.ArrayList;
import java.util.List;

public class Inn {
    private List<Item> items;

    public Inn() {
        items = new ArrayList<Item>();
        items.add(new Item("+5 Dexterity Vest", 10, 20));
        items.add(new Item("Aged Brie", 2, 0));
        items.add(new Item("Elixir of the Mongoose", 5, 7));
        items.add(new Item("Sulfuras, Hand of Ragnaros", 0, 80));
        items.add(new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20));
        items.add(new Item("Conjured Mana Cake", 3, 6));
    }

    @VisibleForTesting
    protected Inn(Item item) {
        items = new ArrayList<Item>();
        items.add(item);
    }

    @VisibleForTesting
    protected Item getItem(int i) {
        return items.get(i);
    }

    public void updateQualityAndSellIn() {
        for(Item currentItem : items) {
            if (is(currentItem, "Sulfuras")) {
                continue;
            }
            currentItem.setSellIn(currentItem.getSellIn() - 1);
            if (is(currentItem, "Aged Brie")) {
                handleAgedBrie(currentItem);
            } else if (is(currentItem, "Backstage passes")) {
                handleBackstagePasses(currentItem);
            } else if (is(currentItem, "Conjured")) {
                handleConjured(currentItem);
            } else {
                handleDefaultObject(currentItem);
            }
            ensureQualityIsWithinBounds(currentItem);
        }
    }

    private boolean is(Item currentItem, String name) {
        return currentItem.getName().startsWith(name);
    }

    private void handleConjured(Item conjuredItem) {
        handleDefaultObject(conjuredItem);
        handleDefaultObject(conjuredItem);
    }

    private void ensureQualityIsWithinBounds(Item currentItem) {
        if (currentItem.getQuality() < 0) {
            currentItem.setQuality(0);
        }
        if (currentItem.getQuality() > 50) {
            currentItem.setQuality(50);
        }
    }

    private void handleDefaultObject(Item defaultItem) {
        if (defaultItem.getSellIn() < 0) {
            defaultItem.setQuality(defaultItem.getQuality() - 2);
        } else {
            defaultItem.setQuality(defaultItem.getQuality() - 1);
        }
    }

    private void handleBackstagePasses(Item backstagePasses) {
        if (backstagePasses.getSellIn() < 0) {
            backstagePasses.setQuality(0);
        } else if (backstagePasses.getSellIn() < 5) {
            backstagePasses.setQuality(backstagePasses.getQuality() + 3);
        } else if (backstagePasses.getSellIn() < 10) {
            backstagePasses.setQuality(backstagePasses.getQuality() + 2);
        } else {
            backstagePasses.setQuality(backstagePasses.getQuality() + 1);
        }
    }

    private void handleAgedBrie(Item agedBrie) {
        if (agedBrie.getSellIn() >= 0) {
            agedBrie.setQuality(agedBrie.getQuality() + 1);
        } else {
            agedBrie.setQuality(agedBrie.getQuality() + 2);
        }
    }

    public static void main(String[] args) {
        System.out.println("OMGHAI!");
        new Inn().updateQualityAndSellIn();
    }

}
