package legacy;

import com.google.common.base.Joiner;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class InnWeb implements HttpHandler {
  private final Inn inn = new Inn();

  public static void main(String[] args) throws IOException {
    int port = Integer.parseInt(System.getenv("PORT"));

    HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
    server.createContext("/", new InnWeb());
    server.start();
  }

  @Override
  public void handle(HttpExchange exchange) throws IOException {
    String path = exchange.getRequestURI().getPath();

    if ("/update".equals(path)) {
      inn.updateQuality();

      exchange.sendResponseHeaders(200, 0);
    } else {
      String json = "[" + Joiner.on(',').join(inn.getItems()) + "]";

      String uri = exchange.getRequestURI().toString();
      if (uri.contains("?callback")) {
        String callback = uri.split("[=?&]")[2];
        json = callback + "(" + json + ")";
      }

      byte[] response = json.getBytes();

      exchange.getResponseHeaders().add("Content-type", "text/javascript");
      exchange.sendResponseHeaders(200, response.length);
      exchange.getResponseBody().write(response);
    }

    exchange.close();
  }
}