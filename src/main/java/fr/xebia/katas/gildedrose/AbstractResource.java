package fr.xebia.katas.gildedrose;

import javax.activation.*;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.io.*;
import java.net.*;

/**
 * Base class for Jersey resources.
 */
public abstract class AbstractResource {
	protected Response file(String path) {
		File file = new File("web", path);
		if (!file.exists()) {
			throw new WebApplicationException(404);
		}
		String mimeType = new MimetypesFileTypeMap().getContentType(file);
		return Response.ok(file, mimeType).build();
	}

	protected Response redirect(String url) {
		return Response.temporaryRedirect(URI.create(url)).build();
	}
}
