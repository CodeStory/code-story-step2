package fr.xebia.katas.gildedrose;

import com.sampullara.mustache.*;
import org.simpleframework.http.*;
import org.simpleframework.http.core.*;
import org.simpleframework.transport.connect.*;

import java.io.*;
import java.net.*;

import static com.google.common.io.Files.*;
import static org.simpleframework.http.Status.*;

public class InnHttpServer implements Container {
	private static final String WEB = "web";

	private final int port;
	private Inn inn;

	public InnHttpServer(int port) {
		this.port = port;
		inn = new Inn();
	}

	private void run() throws IOException {
		new SocketConnection(this).connect(new InetSocketAddress(port));
	}

	@Override
	public void handle(Request req, Response resp) {
		String path = req.getPath().getPath();

		try {
			if (path.matches(".*\\.((css)|(js)|(ico))")) {
				copy(new File(WEB, path), resp.getOutputStream());
			} else if (path.equals("/updateQuality")) {
				inn.updateQuality();
				resp.setCode(TEMPORARY_REDIRECT.getCode());
				resp.add("Location", "/");
			} else if (path.equals("/reset")) {
				inn = new Inn();
				resp.setCode(TEMPORARY_REDIRECT.getCode());
				resp.add("Location", "/");
			} else {
				Mustache template = new MustacheBuilder(new File(WEB)).parseFile("index.html");
				template.execute(new OutputStreamWriter(resp.getOutputStream()), new Scope(inn));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				resp.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws Exception {
		new InnHttpServer(8080).run();
	}
}
