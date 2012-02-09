package fr.xebia.katas.gildedrose;

import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class InnItemTest {

    @Test
    public void givenASimpleIncrease_thenTheQualityIsIncreased() {
        InnItem innItem = new InnItem("Lambda", 0, 0);
        innItem.increaseQuality();
        assertThat(innItem.getQuality()).isEqualTo(1);
    }

    @Test
    public void givenASimpleDecrease_thenTheQualityIsDecreased() {
        InnItem innItem = new InnItem("Lambda", 0, 3);
        innItem.decreaseQuality();
        assertThat(innItem.getQuality()).isEqualTo(2);
    }

    @Test
    public void givenATripleDecrease_thenTheQualityIsTripleDecreased() {
        InnItem innItem = new InnItem("Lambda", 0, 5);
        innItem.decreaseQuality(3);
        assertThat(innItem.getQuality()).isEqualTo(2);
    }


    @Test
    public void givenADoubleIncrease_thenTheQualityIsDoublyIncreased() {
        InnItem innItem = new InnItem("Lambda", 0, 1);
        innItem.increaseQuality(3);
        assertThat(innItem.getQuality()).isEqualTo(4);
    }

    @Test
    public void givenASimpleDecrease_thenTheSellinIsDecreased() {
        InnItem innItem = new InnItem("Lambda", 4, 0);
        innItem.decreaseSellIn();
        assertThat(innItem.getSellIn()).isEqualTo(3);
    }

    @Test
    public void givenAnItemWithAPositiveSellin_thenTheSellinDayIsNotPassed() {
        InnItem innItem = new InnItem("Lambda", 4, 0);
        assertThat(innItem.isSellInDayPassed()).isFalse();
    }

    @Test
    public void givenAnItemWithAZeroSellin_thenTheSellinDayIsNotPassed() {
        InnItem innItem = new InnItem("Lambda", 0, 0);
        assertThat(innItem.isSellInDayPassed()).isFalse();
    }

    @Test
    public void givenAnItemWithANegativeSellin_thenTheSellinDayIsPassed() {
        InnItem innItem = new InnItem("Lambda", -1, 0);
        assertThat(innItem.isSellInDayPassed()).isTrue();
    }

    @Test
    public void givenAnItemWithAName_thenItMatchTheNameExactly() {
        InnItem innItem = new InnItem("Lambda", -1, 0);
        assertThat(innItem.is("Lambda")).isTrue();
    }

    @Test
    public void givenAnItemWithAName_thenItMatchTheNamePartially() {
        InnItem innItem = new InnItem("Lambda object", -1, 0);
        assertThat(innItem.is("Lambda")).isTrue();
    }

    @Test
    public void givenAnItemWithAName_thenItDoesNotMatchTheName() {
        InnItem innItem = new InnItem("Lambda object", -1, 0);
        assertThat(innItem.is("FooBar")).isFalse();
    }


}
