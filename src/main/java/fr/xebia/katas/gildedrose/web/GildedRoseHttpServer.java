package fr.xebia.katas.gildedrose.web;

import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.jersey.spi.container.servlet.ServletContainer;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.servlet.Context;
import org.mortbay.jetty.servlet.DefaultServlet;
import org.mortbay.jetty.servlet.ServletHolder;

public class GildedRoseHttpServer {

    public Server createServer() {
        Server server = new Server(4001);
        Context root = new Context(server, "/", Context.NO_SESSIONS);
        root.setResourceBase("./web/");
        root.addServlet(new ServletHolder(new DefaultServlet()), "/*");
        ResourceConfig resourceConfig = new PackagesResourceConfig("fr.xebia.katas.gildedrose","org.codehaus.jackson.jaxrs");
        root.addServlet(new ServletHolder(new ServletContainer(resourceConfig)), "/inn/*");
        return server;
    }

    public static void main(String[] args) throws Exception {
        Server server = new GildedRoseHttpServer().createServer();
        server.start();
    }

}
