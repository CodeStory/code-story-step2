package fr.xebia.katas.gildedrose;

import org.junit.*;

import java.util.*;

import static org.fest.assertions.Assertions.*;

public class InnTest {
	Inn inn = new Inn();

	@Test
	public void canGetOriginalQuantities() {
		List<Item> items = inn.getItems();

		assertThat(items).onProperty("name").containsExactly("+5 Dexterity Vest", "Aged Brie", "Elixir of the Mongoose", "Sulfuras, Hand of Ragnaros", "Backstage passes to a TAFKAL80ETC concert", "Conjured Mana Cake");
		assertThat(items).onProperty("quality").containsExactly(20, 0, 7, 80, 20, 6);
		assertThat(items).onProperty("sellIn").containsExactly(10, 2, 5, 0, 15, 3);

	}

	@Test
	public void canUpdateQuantities() {
		inn.updateQuality();

		List<Item> items = inn.getItems();

		assertThat(items).onProperty("quality").containsExactly(19, 1, 6, 80, 21, 4);
		assertThat(items).onProperty("sellIn").containsExactly(9, 1, 4, 0, 14, 2);
	}

	@Test
	public void canUpdateQuantitiesMultipleTimes() {
		for (int i = 0; i < 5; i++) {
			inn.updateQuality();
		}

		List<Item> items = inn.getItems();

		assertThat(items).onProperty("quality").containsExactly(15, 8, 2, 80, 25, 0);
		assertThat(items).onProperty("sellIn").containsExactly(5, -3, 0, 0, 10, -2);
	}

	@Test
	public void canUpdateQuantitiesMoreTimes() {
		for (int i = 0; i < 6; i++) {
			inn.updateQuality();
		}

		List<Item> items = inn.getItems();

		assertThat(items).onProperty("quality").containsExactly(14, 10, 0, 80, 27, 0);
		assertThat(items).onProperty("sellIn").containsExactly(4, -4, -1, 0, 9, -3);
	}

	@Test
	public void canUpdateQuantitiesALot() {
		for (int i = 0; i < 16; i++) {
			inn.updateQuality();
		}

		List<Item> items = inn.getItems();

		assertThat(items).onProperty("quality").containsExactly(0, 30, 0, 80, 0, 0);
		assertThat(items).onProperty("sellIn").containsExactly(-6, -14, -11, 0, -1, -13);
	}
}
