package fr.xebia.katas.gildedrose;

import static java.lang.Math.max;
import static java.lang.Math.min;

class ItemUpdater {
  Item item;

  ItemUpdater(Item item) {
    this.item = item;
  }

  void updateQuality() {
    String name = item.getName();

    if ("Sulfuras, Hand of Ragnaros".equals(name)) {
      return;
    }

    item.setSellIn(getSellIn() - 1);

    if ("Aged Brie".equals(name)) {
      addQualityIf(+1, true);
      addQualityIf(+1, getSellIn() < 0);
    } else if ("Backstage passes to a TAFKAL80ETC concert".equals(name)) {
      addQualityIf(+1, true);
      addQualityIf(+1, getSellIn() < 10);
      addQualityIf(+1, getSellIn() < 5);
      addQualityIf(-getQuality(), getSellIn() < 0);
    } else {
      addQualityIf(-1, true);
      addQualityIf(-1, getSellIn() < 0);
    }
  }

  int getQuality() {
    return item.getQuality();
  }

  int getSellIn() {
    return item.getSellIn();
  }

  void addQualityIf(int add, boolean condition) {
    if (condition) {
      item.setQuality(max(0, min(getQuality() + add, 50)));
    }
  }
}
