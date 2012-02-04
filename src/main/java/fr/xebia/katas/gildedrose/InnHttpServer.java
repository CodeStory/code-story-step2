package fr.xebia.katas.gildedrose;

import com.sun.jersey.api.container.httpserver.*;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.io.*;
import java.net.*;

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
	@Path("/inn.json")
	public Inn inn() {
		return inn;
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
