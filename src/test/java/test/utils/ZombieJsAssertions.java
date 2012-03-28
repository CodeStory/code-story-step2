package test.utils;

import com.google.common.base.*;
import com.google.common.collect.*;
import org.apache.commons.exec.*;

import java.io.*;
import java.util.*;

import static org.junit.Assert.*;

public final class ZombieJsAssertions {
	private ZombieJsAssertions() {
		// Static class
	}

	public static HttpServerAssert assertThat(HttpServerRule httpServer) {
		return new HttpServerAssert(httpServer);
	}

	public static class HttpServerAssert {
		private final HttpServerRule httpServer;

		HttpServerAssert(HttpServerRule httpServer) {
			this.httpServer = httpServer;
		}

		public HttpServerAssert canRun(String jsPath) {
			try {
				ByteArrayOutputStream output = new ByteArrayOutputStream();

				Map map = ImmutableMap.of("file", new File(jsPath), "port", getPort());

				Executor executor = new DefaultExecutor();
				executor.setWatchdog(new ExecuteWatchdog(60000));
				executor.setStreamHandler(new PumpStreamHandler(output));
				executor.execute(CommandLine.parse("/usr/local/bin/node ${file} ${port}", map));

				for (String line : Splitter.on('\n').split(output.toString())) {
					if (line.contains("Error: ")) {
						fail(line);
					}
				}
			} catch (Exception e) {
				fail(e.getMessage());
			}

			return this;
		}

		private int getPort() {
			return httpServer.getHttpServer().getAddress().getPort();
		}
	}
}
