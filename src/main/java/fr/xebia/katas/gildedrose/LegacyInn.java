package fr.xebia.katas.gildedrose;

import java.util.ArrayList;
import java.util.List;

public class LegacyInn {
   private List<LegacyItem> LegacyItems;

  public List<LegacyItem> getItems() {
    return LegacyItems;
  }

  public LegacyInn() {
      LegacyItems = new ArrayList<LegacyItem>();
      LegacyItems.add(new LegacyItem("+5 Dexterity Vest", 10, 20));
      LegacyItems.add(new LegacyItem("Aged Brie", 2, 0));
      LegacyItems.add(new LegacyItem("Elixir of the Mongoose", 5, 7));
      LegacyItems.add(new LegacyItem("Sulfuras, Hand of Ragnaros", 0, 80));
      LegacyItems.add(new LegacyItem("Backstage passes to a TAFKAL80ETC concert", 15, 20));
      LegacyItems.add(new LegacyItem("Conjured Mana Cake", 3, 6));
   }

   public void updateQuality() {
      for (int i = 0; i < LegacyItems.size(); i++) {
         if (!LegacyItems.get(i).getName().equals("Aged Brie") && !LegacyItems.get(i).getName().equals("Backstage passes to a TAFKAL80ETC concert")) {
            if (LegacyItems.get(i).getQuality() > 0) {
               if (!LegacyItems.get(i).getName().equals("Sulfuras, Hand of Ragnaros")) {
                  LegacyItems.get(i).setQuality(LegacyItems.get(i).getQuality() - 1);
               }
            }
         } else {
            if (LegacyItems.get(i).getQuality() < 50) {
               LegacyItems.get(i).setQuality(LegacyItems.get(i).getQuality() + 1);

               if (LegacyItems.get(i).getName().equals("Backstage passes to a TAFKAL80ETC concert")) {
                  if (LegacyItems.get(i).getSellIn() < 11) {
                     if (LegacyItems.get(i).getQuality() < 50) {
                        LegacyItems.get(i).setQuality(LegacyItems.get(i).getQuality() + 1);
                     }
                  }

                  if (LegacyItems.get(i).getSellIn() < 6) {
                     if (LegacyItems.get(i).getQuality() < 50) {
                        LegacyItems.get(i).setQuality(LegacyItems.get(i).getQuality() + 1);
                     }
                  }
               }
            }
         }

         if (!LegacyItems.get(i).getName().equals("Sulfuras, Hand of Ragnaros")) {
            LegacyItems.get(i).setSellIn(LegacyItems.get(i).getSellIn() - 1);
         }

         if (LegacyItems.get(i).getSellIn() < 0) {
            if (!LegacyItems.get(i).getName().equals("Aged Brie")) {
               if (!LegacyItems.get(i).getName().equals("Backstage passes to a TAFKAL80ETC concert")) {
                  if (LegacyItems.get(i).getQuality() > 0) {
                     if (!LegacyItems.get(i).getName().equals("Sulfuras, Hand of Ragnaros")) {
                        LegacyItems.get(i).setQuality(LegacyItems.get(i).getQuality() - 1);
                     }
                  }
               } else {
                  LegacyItems.get(i).setQuality(LegacyItems.get(i).getQuality() - LegacyItems.get(i).getQuality());
               }
            } else {
               if (LegacyItems.get(i).getQuality() < 50) {
                  LegacyItems.get(i).setQuality(LegacyItems.get(i).getQuality() + 1);
               }
            }
         }
      }

}

   public static void main(String[] args) {
      System.out.println("OMGHAI!");
      new LegacyInn().updateQuality();
   }

}
