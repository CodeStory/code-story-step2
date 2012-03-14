package fr.xebia.katas.gildedrose;

import org.junit.*;

import static org.fest.assertions.Assertions.*;

public class InnTest {
	Inn inn = new Inn();

	@Test
	public void should_have_an_initial_state() {
		assertThat(inn.getItems()).onProperty("name").containsExactly("+5 Dexterity Vest", "Aged Brie", "Elixir of the Mongoose", "Sulfuras, Hand of Ragnaros", "Backstage passes to a TAFKAL80ETC concert", "Conjured Mana Cake");
		assertThat(inn.getItems()).onProperty("quality").containsExactly(20, 0, 7, 80, 20, 6);
		assertThat(inn.getItems()).onProperty("sellIn").containsExactly(10, 2, 5, 0, 15, 3);
	}

	@Test
	public void should_update() {
		inn.updateQuality();

		assertThat(inn.getItems()).onProperty("quality").containsExactly(19, 1, 6, 80, 21, 4);
		assertThat(inn.getItems()).onProperty("sellIn").containsExactly(9, 1, 4, 0, 14, 2);
	}

	@Test
	public void should_update_twice() {
		inn.updateQuality();
		inn.updateQuality();

		assertThat(inn.getItems()).onProperty("quality").containsExactly(18, 2, 5, 80, 22, 2);
		assertThat(inn.getItems()).onProperty("sellIn").containsExactly(8, 0, 3, 0, 13, 1);
	}

	@Test
	public void should_update_twelve_times() {
		for (int i = 0; i < 12; i++) {
			inn.updateQuality();
		}

		assertThat(inn.getItems()).onProperty("quality").containsExactly(6, 22, 0, 80, 41, 0);
		assertThat(inn.getItems()).onProperty("sellIn").containsExactly(-2, -10, -7, 0, 3, -9);
	}

	@Test
	public void should_update_a_lot() {
		for (int i = 0; i < 30; i++) {
			inn.updateQuality();
		}

		assertThat(inn.getItems()).onProperty("quality").containsExactly(0, 50, 0, 80, 0, 0);
		assertThat(inn.getItems()).onProperty("sellIn").containsExactly(-20, -28, -25, 0, -15, -27);
	}
}
