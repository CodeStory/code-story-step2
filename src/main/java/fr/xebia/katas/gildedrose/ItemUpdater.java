package fr.xebia.katas.gildedrose;

import lombok.*;

import static java.lang.Math.*;

@AllArgsConstructor
class ItemUpdater {
	@Delegate Item item;

	void update() {
		if ("Sulfuras, Hand of Ragnaros".equals(getName())) {
			return;
		}

		item.setSellIn(getSellIn() - 1);
		int sellIn = getSellIn();

		if ("Aged Brie".equals(getName())) {
			increaseQualityBy(1).when(sellIn >= 0);
			increaseQualityBy(2).when(sellIn < 0);
		} else if ("Backstage passes to a TAFKAL80ETC concert".equals(getName())) {
			increaseQualityBy(1).when(sellIn >= 10);
			increaseQualityBy(2).when(sellIn >= 5 && sellIn < 10);
			increaseQualityBy(3).when(sellIn < 5);
			changeQuality(0).when(sellIn < 0);
		} else if ("Conjured Mana Cake".equals(getName())) {
			decreaseQualityBy(2).when(sellIn >= 0);
			decreaseQualityBy(4).when(sellIn < 0);
		} else {
			decreaseQualityBy(1).when(sellIn >= 0);
			decreaseQualityBy(2).when(sellIn < 0);
		}
	}

	ConditionEvaluator increaseQualityBy(int increment) {
		return new ConditionEvaluator(getQuality() + increment);
	}

	ConditionEvaluator decreaseQualityBy(int increment) {
		return new ConditionEvaluator(getQuality() - increment);
	}

	ConditionEvaluator changeQuality(int quality) {
		return new ConditionEvaluator(quality);
	}

	@AllArgsConstructor
	class ConditionEvaluator {
		int newQuality;

		void when(boolean condition) {
			if (condition) {
				item.setQuality(max(0, min(newQuality, 50)));
			}
		}
	}
}
