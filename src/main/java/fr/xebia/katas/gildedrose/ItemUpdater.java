package fr.xebia.katas.gildedrose;

@lombok.AllArgsConstructor
class ItemUpdater {
	@lombok.Delegate Item item;

	void updateQuality() {
		setSellIn(getSellIn() - ("Sulfuras, Hand of Ragnaros".equals(getName()) ? 0 : (("Aged Brie".equals(getName()) && updateQuality(getQuality() + 1 + (getSellIn() <= 0 ? 1 : 0))) || ("Backstage passes to a TAFKAL80ETC concert".equals(getName()) && updateQuality(getSellIn() <= 0 ? 0 : (getQuality() + 1 + (getSellIn() <= 10 ? 1 : 0) + (getSellIn() <= 5 ? 1 : 0)))) || ("Conjured Mana Cake".equals(getName()) && updateQuality(getQuality() - 2 - (getSellIn() <= 0 ? 2 : 0))) || updateQuality(getQuality() - 1 - (getSellIn() <= 0 ? 1 : 0)) ? 1 : 1)));
	}

	boolean updateQuality(int quality) {
		setQuality(java.lang.Math.max(0, java.lang.Math.min(quality, 50)));
		return true;
	}
}
