package fr.xebia.katas.gildedrose;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;

public class InnWebServer implements HttpHandler {
  public void handle(HttpExchange exchange) throws IOException {
    byte[] response = "Hello World".getBytes();

    exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, response.length);
    exchange.getResponseBody().write(response);
    exchange.close();
  }

  public static void main(String[] args) throws IOException {
    System.out.println("START");
    int port = Integer.valueOf(System.getenv("PORT"));
    System.out.println(port);

    HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
    server.createContext("/", new InnWebServer());
    server.start();

    System.out.println("STARTED");
  }
}
