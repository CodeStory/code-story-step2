package fr.xebia.katas.gildedrose;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.fest.assertions.Assertions.assertThat;

public class InnTest {
  Inn inn;

  @Before
  public void setUp() {
    inn = new Inn();
  }

  @Test
  public void should_have_initial_quantities() {
    List<Item> items = inn.getItems();

    assertThat(items).onProperty("name").containsExactly("+5 Dexterity Vest", "Aged Brie", "Elixir of the Mongoose", "Sulfuras, Hand of Ragnaros", "Backstage passes to a TAFKAL80ETC concert", "Conjured Mana Cake");
    assertThat(items).onProperty("quality").containsExactly(20, 0, 7, 80, 20, 6);
    assertThat(items).onProperty("sellIn").containsExactly(10, 2, 5, 0, 15, 3);
  }

  @Test
  public void should_update_quantities() {
    inn.updateQuality();

    List<Item> items = inn.getItems();

    assertThat(items).onProperty("quality").containsExactly(19, 1, 6, 80, 21, 5);
    assertThat(items).onProperty("sellIn").containsExactly(9, 1, 4, 0, 14, 2);
  }

  @Test
  public void should_update_quantities_twice() {
    inn.updateQuality();
    inn.updateQuality();

    List<Item> items = inn.getItems();

    assertThat(items).onProperty("quality").containsExactly(18, 2, 5, 80, 22, 4);
    assertThat(items).onProperty("sellIn").containsExactly(8, 0, 3, 0, 13, 1);
  }

  @Test
  public void should_update_quantities_a_lot() {
    for (int day = 0; day < 50; day++) {
      inn.updateQuality();
    }

    List<Item> items = inn.getItems();

    assertThat(items).onProperty("quality").containsExactly(0, 50, 0, 80, 0, 0);
    assertThat(items).onProperty("sellIn").containsExactly(-40, -48, -45, 0, -35, -47);
  }

  @Test
  public void should_cross_check_with_legacy_implementation() {
    LegacyInn legacyInn = new LegacyInn();

    for (int day = 0; day < 100; day++) {
      legacyInn.updateQuality();
      inn.updateQuality();

      for (int i = 0; i < legacyInn.getItems().size(); i++) {
        assertThat(inn.getItems().get(i).getName()).isEqualTo(legacyInn.getItems().get(i).getName());
        assertThat(inn.getItems().get(i).getSellIn()).isEqualTo(legacyInn.getItems().get(i).getSellIn());
        assertThat(inn.getItems().get(i).getQuality()).isEqualTo(legacyInn.getItems().get(i).getQuality());
      }
    }
  }
}

