package fr.xebia.katas.gildedrose;

import com.google.common.io.*;
import com.google.common.util.concurrent.*;
import org.apache.commons.lang.*;
import org.simpleframework.http.*;
import org.simpleframework.http.core.*;
import org.simpleframework.transport.connect.*;
import org.stringtemplate.v4.*;

import java.io.*;
import java.net.*;

import static com.google.common.base.Charsets.*;
import static com.google.common.io.Files.*;
import static org.simpleframework.http.Status.*;

public class InnHttpServer extends AbstractIdleService implements Container {
	private static final String[] STATIC_EXTENSIONS = new String[]{".css", ".js", ".png", "ico"};

	private final int port;
	private Inn inn;
	private SocketConnection socketConnection;

	public InnHttpServer(int port) {
		this.port = port;
		inn = new Inn();
	}

	@Override
	public void handle(Request req, Response resp) {
		String path = req.getPath().getPath();

		try {
			if (StringUtils.endsWithAny(path, STATIC_EXTENSIONS)) {
				copy(new File("web/..", path), resp.getOutputStream());
			} else if (path.equals("/updateQuality")) {
				inn.updateQuality();
				resp.setCode(TEMPORARY_REDIRECT.getCode());
				resp.add("Location", "/");
			} else if (path.equals("/reset")) {
				inn = new Inn();
				resp.setCode(TEMPORARY_REDIRECT.getCode());
				resp.add("Location", "/");
			} else {
				resp.getPrintStream().append(html());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				resp.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private String html() throws IOException {
		String html = Files.toString(new File("web", "index.html"), UTF_8);

		ST template = new ST(html, '$', '$');
		template.add("items", inn.getItems());

		return template.render();
	}

	public static void main(String[] args) throws Exception {
		new InnHttpServer(8080).startAndWait();
	}

	private void run() throws IOException {
		socketConnection = new SocketConnection(this);
		socketConnection.connect(new InetSocketAddress(port));
	}

	@Override
	protected void startUp() throws Exception {
		run();
	}

	@Override
	protected void shutDown() throws Exception {
		socketConnection.close();
	}
}
