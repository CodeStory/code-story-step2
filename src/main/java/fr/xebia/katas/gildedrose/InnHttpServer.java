package fr.xebia.katas.gildedrose;

import com.google.common.collect.*;
import com.sun.jersey.api.container.httpserver.*;
import lombok.*;

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
	public File index() {
		return new File("web/index.html");
	}

	@GET
	@Produces("text/css")
	@Path("/web/css/{what}")
	public File css(@PathParam("what") String what) {
		return new File("web/css", what);
	}

	@GET
	@Produces("application/javascript")
	@Path("/web/js/{what}")
	public File js(@PathParam("what") String what) {
		return new File("web/js", what);
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

	@GET
	@Produces("image/jpg")
	@Path("/img/{what}")
	public File image(@PathParam("what") String what) {
		return new File("web/img", what + ".jpg");
	}

	@Data
	static class IndexedItem {
		@Delegate
		public final Item item;
		public final int index;
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

	public static void main(String[] args) throws Exception {
		HttpServerFactory.create("http://localhost:8080/").start();
	}
}
