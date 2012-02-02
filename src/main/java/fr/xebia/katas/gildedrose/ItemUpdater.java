package fr.xebia.katas.gildedrose;

import lombok.*;

import static java.lang.Math.*;

@AllArgsConstructor
class ItemUpdater {
	@Delegate Item item;

	void updateQuality() {
		if ("Sulfuras, Hand of Ragnaros".equals(getName())) {
			return;
		}

		setSellIn(getSellIn() - 1);

		if ("Aged Brie".equals(getName())) {
			addQualityIf(+1, true);
			addQualityIf(+1, getSellIn() < 0);
		} else if ("Backstage passes to a TAFKAL80ETC concert".equals(getName())) {
			addQualityIf(+1, true);
			addQualityIf(+1, getSellIn() < 10);
			addQualityIf(+1, getSellIn() < 5);
			addQualityIf(-getQuality(), getSellIn() < 0);
		} else if ("Conjured Mana Cake".equals(getName())) {
			addQualityIf(-2, true);
			addQualityIf(-2, getSellIn() < 0);
		} else {
			addQualityIf(-1, true);
			addQualityIf(-1, getSellIn() < 0);
		}
	}

	void addQualityIf(int add, boolean condition) {
		if (condition) {
			setQuality(max(0, min(getQuality() + add, 50)));
		}
	}
}
