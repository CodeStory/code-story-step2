package fr.xebia.katas.gildedrose;

import com.google.common.collect.*;
import com.sun.jersey.api.container.httpserver.*;
import lombok.*;

import javax.activation.*;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.io.*;
import java.net.*;
import java.util.*;

@Path("/")
public class InnHttpServer {
	private static Inn inn = new Inn();

	@GET
	@Produces("text/html")
	public Response index() {
		return staticResource("index.html");
	}

	@GET
	@Path("/web/{what: .*}")
	public Response staticResource(@PathParam("what") String what) {
		File image = new File("web", what);
		if (!image.exists()) {
			throw new WebApplicationException(404);
		}
		String mimeType = new MimetypesFileTypeMap().getContentType(image);
		return Response.ok(image, mimeType).build();
	}

	@GET
	@Path("/updateQuality")
	public Response updateQuality() {
		inn.updateQuality();
		return Response.temporaryRedirect(URI.create("/")).build();
	}

	@GET
	@Path("/reset")
	public Response reset() {
		inn = new Inn();
		return Response.temporaryRedirect(URI.create("/")).build();
	}

	@GET
	@Produces("application/json")
	@Path("/inventory.json")
	public List<IndexedItem> inventory() {
		List<IndexedItem> items = Lists.newArrayList();

		int index = 0;
		for (Item item : inn.getItems()) {
			items.add(new IndexedItem(item, index++));
		}

		return items;
	}

	@Data
	static class IndexedItem {
		@Delegate
		public final Item item;
		public final int index;
	}

	public static void main(String[] args) throws Exception {
		HttpServerFactory.create("http://localhost:8080/").start();
	}
}
