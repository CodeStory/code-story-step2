package fr.xebia.katas.gildedrose;

import org.fest.util.VisibleForTesting;

import java.util.ArrayList;
import java.util.List;

public class Inn {
    private List<InnItem> items;

    public Inn() {
        items = new ArrayList<InnItem>();
        items.add(new InnItem("+5 Dexterity Vest", 10, 20));
        items.add(new InnItem("Aged Brie", 2, 0));
        items.add(new InnItem("Elixir of the Mongoose", 5, 7));
        items.add(new InnItem("Sulfuras, Hand of Ragnaros", 0, 80));
        items.add(new InnItem("Backstage passes to a TAFKAL80ETC concert", 15, 20));
        items.add(new InnItem("Conjured Mana Cake", 3, 6));
    }

    @VisibleForTesting
    protected Inn(InnItem item) {
        items = new ArrayList<InnItem>();
        items.add(item);
    }

    @VisibleForTesting
    protected Item getItem(int i) {
        return items.get(i);
    }

    public void updateQualityAndSellIn() {
        for(InnItem currentItem : items) {
            if (currentItem.is("Sulfuras")) {
                continue;
            }
            currentItem.decreaseSellIn();
            if (currentItem.is("Aged Brie")) {
                handleAgedBrie(currentItem);
            } else if (currentItem.is("Backstage passes")) {
                handleBackstagePasses(currentItem);
            } else if (currentItem.is("Conjured")) {
                handleConjured(currentItem);
            } else {
                handleDefaultObject(currentItem);
            }
        }
    }

    private boolean is(Item currentItem, String name) {
        return currentItem.getName().startsWith(name);
    }

    private void handleAgedBrie(InnItem agedBrie) {
        if (agedBrie.isSellInDayPassed()) {
            agedBrie.increaseQuality(2);
        } else {
            agedBrie.increaseQuality();
        }
    }

    private void handleBackstagePasses(InnItem backstagePasses) {
        if (backstagePasses.isSellInDayPassed()) {
            backstagePasses.setQuality(0);
        } else if (backstagePasses.getSellIn() < 5) {
            backstagePasses.increaseQuality(3);
        } else if (backstagePasses.getSellIn() < 10) {
            backstagePasses.increaseQuality(2);
        } else {
            backstagePasses.increaseQuality();
        }
    }

    private void handleConjured(InnItem conjuredItem) {
        handleDefaultObject(conjuredItem);
        handleDefaultObject(conjuredItem);
    }

    private void handleDefaultObject(InnItem defaultItem) {
        if (defaultItem.isSellInDayPassed()) {
            defaultItem.decreaseQuality(2);
        } else {
            defaultItem.decreaseQuality();
        }
    }

    public static void main(String[] args) {
        System.out.println("OMGHAI!");
        new Inn().updateQualityAndSellIn();
    }

    public List<InnItem> getItems() {
        return items;
    }
}
