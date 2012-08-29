package fr.xebia.katas.gildedrose;

import lombok.AllArgsConstructor;
import lombok.Delegate;

import static java.lang.Math.max;
import static java.lang.Math.min;

@AllArgsConstructor
class ItemUpdater {
  static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
  static final String BRIE = "Aged Brie";
  static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";

  @Delegate
  Item item;

  void updateQuality() {
    if (is(SULFURAS)) {
      return;
    }

    decreaseSellIn();

    if (is(BRIE)) {
      increaseQuality();
      if (getSellIn() < 0) increaseQuality();
    } else if (is(BACKSTAGE_PASSES)) {
      increaseQuality();
      if (getSellIn() < 10) increaseQuality();
      if (getSellIn() < 5) increaseQuality();
      if (getSellIn() < 0) setQuality(0);
    } else {
      decreaseQuality();
      if (getSellIn() < 0) decreaseQuality();
    }
  }

  boolean is(String name) {
    return getName().equals(name);
  }

  void decreaseSellIn() {
    setSellIn(getSellIn() - 1);
  }

  void increaseQuality() {
    setQuality(min(getQuality() + 1, 50));
  }

  void decreaseQuality() {
    setQuality(max(0, getQuality() - 1));
  }
}
