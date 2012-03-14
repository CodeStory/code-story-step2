package fr.xebia.katas.gildedrose;

import lombok.*;

class ItemUpdater {
	@Delegate private Item item;

	ItemUpdater(Item item) {
		this.item = item;
	}

	void updateItemQuality() {
		if ("Sulfuras, Hand of Ragnaros".equals(getName())) {
			return;
		}

		setSellIn(getSellIn() - 1);

		if ("Aged Brie".equals(getName())) {
			increaseQuality();
			increaseQualityWhen(getSellIn() < 0);
		} else if ("Backstage passes to a TAFKAL80ETC concert".equals(getName())) {
			increaseQuality();
			increaseQualityWhen(getSellIn() < 10);
			increaseQualityWhen(getSellIn() < 5);
			setQualityWhen(0, getSellIn() < 0);
		} else if ("Conjured Mana Cake".equals(getName())) {
			decreaseQuality();
			decreaseQuality();
			decreaseQualityWhen(getSellIn() < 0);
			decreaseQualityWhen(getSellIn() < 0);
		} else {
			decreaseQuality();
			decreaseQualityWhen(getSellIn() < 0);
		}
	}

	private void increaseQuality() {
		increaseQualityWhen(true);
	}

	private void decreaseQuality() {
		decreaseQualityWhen(true);
	}

	private void increaseQualityWhen(boolean condition) {
		setQualityWhen(getQuality() + 1, condition);
	}

	private void decreaseQualityWhen(boolean condition) {
		setQualityWhen(getQuality() - 1, condition);
	}

	private void setQualityWhen(int quality, boolean condition) {
		if (condition) {
			if (quality <= 50 && quality >= 0) {
				setQuality(quality);
			}
		}
	}
}
