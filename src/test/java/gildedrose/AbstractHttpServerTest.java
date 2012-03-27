package gildedrose;

import com.sun.net.httpserver.HttpServer;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import java.util.Random;

public abstract class AbstractHttpServerTest {
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

    protected int port() {
        return port;
    }
}
