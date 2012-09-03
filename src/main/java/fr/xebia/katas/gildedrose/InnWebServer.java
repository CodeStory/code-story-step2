package fr.xebia.katas.gildedrose;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;

public class InnWebServer implements HttpHandler {
  private final Inn inn = new Inn();

  public void handle(HttpExchange exchange) throws IOException {
    if ("/update".equals(exchange.getRequestURI().getPath())) {
      inn.updateQuality();

      exchange.getResponseHeaders().add("Location", "/");
      exchange.sendResponseHeaders(HttpURLConnection.HTTP_SEE_OTHER, 0);
    } else {
      StringBuilder page = new StringBuilder("<!DOCTYPE html><html lang=\"en\"><body><table>");

      for (Item item : inn.getItems()) {
        page.append(String.format("<tr><td>%s</td><td>%s</td><td>%s</td></tr>", item.getName(), item.getQuality(), item.getSellIn()));
      }

      page.append("</table><form action=\"/update\"><input type=\"submit\"></form></body>");

      byte[] response = page.toString().getBytes();

      exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, response.length);
      exchange.getResponseBody().write(response);
    }

    exchange.close();
  }

  public static void main(String[] args) throws IOException {
    int port = Integer.valueOf(System.getenv("PORT"));

    HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
    server.createContext("/", new InnWebServer());
    server.start();
  }
}
