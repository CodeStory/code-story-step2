package fr.xebia.katas.gildedrose.web;

import fr.xebia.katas.gildedrose.Inn;
import fr.xebia.katas.gildedrose.InnItem;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;


@Path("/items")
@Produces("application/json")
public class ItemsResource {
    public static Inn inn = new Inn();

    @GET
    public List<InnItem> list() {
        return inn.getItems();
    }

    @GET
    @Path("/roll")
    public List<InnItem> roll() {
        inn.updateQualityAndSellIn();
        return inn.getItems();
    }

    @GET
    @Path("/reset")
    public List<InnItem> reset() {
        inn = new Inn();
        return inn.getItems();
    }

}
