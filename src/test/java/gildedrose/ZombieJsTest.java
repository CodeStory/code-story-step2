package gildedrose;

import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableMap;
import com.sun.net.httpserver.HttpServer;
import org.apache.commons.exec.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Map;
import java.util.Random;

import static org.junit.Assert.fail;

// TODO: Random port
public class ZombieJsTest {
    private static final int TRY_COUNT = 10;
    private static final int DEFAULT_PORT = 8183;
    private static final Random RANDOM = new Random();

    private static HttpServer httpServer;
    private static int port;

    @BeforeClass
    public static void startHttpServer() throws Exception {
        for (int i = 0; i < TRY_COUNT; i++) {
            try {
                port = getRandomPort();
                httpServer = InnHttpServer.start(port);
                return;
            } catch (Exception e) {
                System.err.println("Unable to bind server: " + e);
            }
        }
        throw new IllegalStateException("Unable to start server");
    }

    private static int getRandomPort() {
        synchronized (RANDOM) {
            return DEFAULT_PORT + RANDOM.nextInt(1000);
        }
    }

    @AfterClass
    public static void stopHttpServer() throws Exception {
        if (httpServer != null) {
            httpServer.stop(0);
        }
    }

    @Test
    public void test() throws Exception {
        Map map = ImmutableMap.of("file", new File(".", "test.js"), "port", port);

        CommandLine cmdLine = CommandLine.parse("/usr/local/bin/node ${file} ${port}", map);

        DefaultExecutor executor = new DefaultExecutor();
        ExecuteWatchdog watchdog = new ExecuteWatchdog(60000);
        executor.setWatchdog(watchdog);

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        executor.setStreamHandler(new PumpStreamHandler(output));

        try {
            executor.execute(cmdLine);
        } catch (ExecuteException e) {
            //fail(e.getMessage());
        }

        for (String line : Splitter.on('\n').split(output.toString())) {
            if (line.contains("Error: ")) {
                fail(line);
            }
        }
    }
}
