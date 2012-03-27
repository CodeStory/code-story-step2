package gildedrose;

import com.google.common.base.*;
import com.sun.jersey.api.container.httpserver.*;
import com.sun.net.httpserver.HttpServer;
import lombok.*;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.*;

import static java.lang.String.format;
import static net.gageot.listmaker.ListMaker.*;

@Path("/")
public class InnHttpServer extends AbstractResource {
	private static Inn inn = new Inn();

	@GET
	@Produces("text/html")
	public Response index() {
		return file("index.html");
	}

	@GET
	@Path("/updateQuality")
	public Response updateQuality() {
		inn.updateQuality();
		return redirect("/");
	}

	@GET
	@Path("/reset")
	public Response reset() {
		inn = new Inn();
		return redirect("/");
	}

	@GET
	@Produces("application/json")
	@Path("/inventory.json")
	public List<IndexedItem> inventory() {
		return with(inn.getItems()).to(new Function<Item, IndexedItem>() {
			int index;

			@Override
			public IndexedItem apply(Item item) {
				return new IndexedItem(item, index++);
			}
		}).toList();
	}

	@GET
	@Path("/web/{path: .*}")
	public Response staticResource(@PathParam("path") String path) {
		return file(path);
	}

	@AllArgsConstructor
	static class IndexedItem {
		@Delegate Item item;
		@Getter int index;
	}

    public static HttpServer start(int port) throws Exception {
        HttpServer httpServer = HttpServerFactory.create(format("http://localhost:%d/", port));
        httpServer.start();
        return httpServer;
    }

	public static void main(String[] args) throws Exception {
        start(9090);
	}
}
