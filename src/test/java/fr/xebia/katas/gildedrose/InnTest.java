package fr.xebia.katas.gildedrose;

import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class InnTest {

   // Lambda Object
   @Test
   public void givenALambdaObject_ThenSellInAndQualityLowerByOneEachDay() throws Exception {
      Inn inn = new Inn(new InnItem("Object Lambda", 10, 20));
      for (int i=0;i<10;i++) {
          inn.updateQualityAndSellIn();
      }
      Item item = inn.getItem(0);
      assertThat(item.getSellIn()).isEqualTo(0);
      assertThat(item.getQuality()).isEqualTo(10);
   }

    @Test
    public void givenALambdaObjectWithSellInDatePassed_ThenQualityDegradeBy2EachDay() throws Exception {
        Inn inn = new Inn(new InnItem("Object Lambda", 0, 10));
        for (int i=0;i<4;i++) {
            inn.updateQualityAndSellIn();
        }
        Item item = inn.getItem(0);
        assertThat(item.getSellIn()).isEqualTo(-4); // 0 - 4
        assertThat(item.getQuality()).isEqualTo(2); // 10 - 4 * 2
    }

    // Quality Limits
    @Test
         public void givenAnyQuality_ThenQualityIsNeverNegative() throws Exception {
        Inn inn = new Inn(new InnItem("Object Lambda", 5, 0));
        for (int i=0;i<5;i++) {
            inn.updateQualityAndSellIn();
        }
        Item item = inn.getItem(0);
        assertThat(item.getSellIn()).isEqualTo(0);
        assertThat(item.getQuality()).isEqualTo(0);
    }

    @Test
    public void givenAnyQuality_ThenQualityIsNeverAbove50() throws Exception {
        Inn inn = new Inn(new InnItem("Aged Brie", 200, 0));
        for (int i=0;i<100;i++) {
            inn.updateQualityAndSellIn();
        }
        Item item = inn.getItem(0);
        assertThat(item.getSellIn()).isEqualTo(100);
        assertThat(item.getQuality()).isEqualTo(50);
    }

    // Aged Brie
    @Test
    public void givenAnAgedBrie_ThenQualityGrow() throws Exception {
        Inn inn = new Inn(new InnItem("Aged Brie", 10, 10));
        for (int i=0;i<5;i++) {
            inn.updateQualityAndSellIn();
        }
        Item item = inn.getItem(0);
        assertThat(item.getSellIn()).isEqualTo(5);
        assertThat(item.getQuality()).isEqualTo(15);
    }

    // Sulfuras
    @Test
    public void givenASulfuras_thenItCantBeSold() throws Exception {
        Inn inn = new Inn(new InnItem("Sulfuras", 0, 42));
        for (int i=0;i<400;i++) {
            inn.updateQualityAndSellIn();
        }
        Item item = inn.getItem(0);
        assertThat(item.getSellIn()).isEqualTo(0);
    }

    @Test
    public void givenASulfuras_thenQualityDoesntDegrade() throws Exception {
        Inn inn = new Inn(new InnItem("Sulfuras", 0, 42));
        for (int i=0;i<400;i++) {
            inn.updateQualityAndSellIn();
        }
        Item item = inn.getItem(0);
        assertThat(item.getQuality()).isEqualTo(42);
    }

    // Backtstage Passes
    @Test
    public void givenABackstagePass_thenQualityGrow() throws Exception {
        Inn inn = new Inn(new InnItem("Backstage passes", 15, 20));
        for (int i=0;i<5;i++) {
            inn.updateQualityAndSellIn();
        }
        Item item = inn.getItem(0);
        assertThat(item.getQuality()).isEqualTo(25);
    }

    @Test
    public void givenABackstagePass_thenQualityGrowTwiceWhenWithin10Days() throws Exception {
        Inn inn = new Inn(new InnItem("Backstage passes", 10, 20));
        for (int i=0;i<5;i++) {
            inn.updateQualityAndSellIn();
        }
        Item item = inn.getItem(0);
        assertThat(item.getQuality()).isEqualTo(30);
    }

    @Test
    public void givenABackstagePass_thenQualityGrow3TimesWhenWithin5Days() throws Exception {
        Inn inn = new Inn(new InnItem("Backstage passes", 5, 20));
        for (int i=0;i<5;i++) {
            inn.updateQualityAndSellIn();
        }
        Item item = inn.getItem(0);
        assertThat(item.getQuality()).isEqualTo(35);
    }

    @Test
    public void givenABackstagePass_thenQualityDropToZeroOnceSellinIsGone() throws Exception {
        Inn inn = new Inn(new InnItem("Backstage passes", 5, 20));
        for (int i=0;i<6;i++) {
            inn.updateQualityAndSellIn();
        }
        Item item = inn.getItem(0);
        assertThat(item.getQuality()).isEqualTo(0);
    }

    // Conjured
    @Test
    public void givenAConjuredItem_thenQualityDropTwiceAsFast() throws Exception {
        Inn inn = new Inn(new InnItem("Conjured Language", 5, 20));
        for (int i=0;i<5;i++) {
            inn.updateQualityAndSellIn();
        }
        Item item = inn.getItem(0);
        assertThat(item.getQuality()).isEqualTo(10);
    }

    @Test
    public void givenAConjuredItemWithSellinDatePasses_thenQualityDropFourTimeAsFast() throws Exception {
        Inn inn = new Inn(new InnItem("Conjured Language", 0, 20));
        for (int i=0;i<2;i++) {
            inn.updateQualityAndSellIn();
        }
        Item item = inn.getItem(0);
        assertThat(item.getQuality()).isEqualTo(12);
    }

}
