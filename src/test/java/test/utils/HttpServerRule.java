package test.utils;

import com.sun.net.httpserver.*;
import gildedrose.*;
import org.junit.rules.*;

import java.util.*;

public class HttpServerRule extends ExternalResource {
	private static final int TRY_COUNT = 10;
	private static final int DEFAULT_PORT = 8183;

	private final Random RANDOM = new Random();
	private HttpServer httpServer;

	@Override
	protected void before() {
		httpServer = startHttpServer();
	}

	@Override
	protected void after() {
		stopHttpServer();
	}

	public HttpServer getHttpServer() {
		return httpServer;
	}

	private HttpServer startHttpServer() {
		for (int i = 0; i < TRY_COUNT; i++) {
			try {
				return InnHttpServer.start(getRandomPort());
			} catch (Exception e) {
				System.err.println("Unable to bind server: " + e);
			}
		}
		throw new IllegalStateException("Unable to start server");
	}

	private void stopHttpServer() {
		if (httpServer != null) {
			httpServer.stop(0);
		}
	}

	private synchronized int getRandomPort() {
		return DEFAULT_PORT + RANDOM.nextInt(1000);
	}
}
