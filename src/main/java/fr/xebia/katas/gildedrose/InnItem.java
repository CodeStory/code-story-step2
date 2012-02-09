package fr.xebia.katas.gildedrose;

import static java.lang.Math.*;

public class InnItem extends Item {

    public InnItem(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    public void increaseQuality() {
        increaseQuality(1);
    }

    public void increaseQuality(int increase) {
        setQuality(getQuality() + increase);
    }

    public void decreaseSellIn() {
        setSellIn(getSellIn() - 1);
    }

    public void decreaseQuality() {
        decreaseQuality(1);
    }

    public boolean isSellInDayPassed() {
        return getSellIn() < 0;
    }

    public boolean is(String lambda) {
        return getName().startsWith(lambda);
    }

    public void decreaseQuality(int decrease) {
        setQuality(getQuality() - decrease);
    }

    @Override
    public void setQuality(int quality) {
        super.setQuality(min(50,max(quality, 0)));
    }
}
