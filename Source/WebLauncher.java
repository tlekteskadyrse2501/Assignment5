import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.awt.Desktop;
import java.awt.HeadlessException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class WebLauncher {

    private static final int PORT = 8080;
    private static HttpServer server;

    public void launch() {
        startServer();
        System.out.println("localhost: 8080");
        try {
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(URI.create("http:" + "/" + "/" + "localhost:" + PORT));
            }
        } catch (HeadlessException exception) {
        } catch (UnsupportedOperationException exception) {
        } catch (IOException exception) {
        }
        System.out.println();
    }

    public static void stop() {
        if (server != null) {
            server.stop(0);
            server = null;
        }
    }

    private void startServer() {
        if (server != null) {
            return;
        }
        try {
            server = HttpServer.create(new InetSocketAddress(PORT), 0);
        } catch (IOException exception) {
            try {
                server = HttpServer.create(new InetSocketAddress(0), 0);
            } catch (IOException fallbackException) {
                return;
            }
        }
        server.createContext("/", new StaticFileHandler());
        server.setExecutor(null);
        server.start();
    }

    private static class StaticFileHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String requestPath = exchange.getRequestURI().getPath();
            if (requestPath == null || requestPath.equals("/") || requestPath.isEmpty()) {
                requestPath = "/index.html";
            }
            if (requestPath.startsWith("/")) {
                requestPath = requestPath.substring(1);
            }

            Path filePath = Path.of("Website", requestPath).toAbsolutePath();
            byte[] content;
            String contentType;

            if (Files.exists(filePath) && !Files.isDirectory(filePath)) {
                content = Files.readAllBytes(filePath);
                contentType = detectContentType(requestPath);
            } else {
                String html = "<!DOCTYPE html><html><head><meta charset=\"UTF-8\"><title>Route Planner</title></head><body><h1>Route Planner</h1><p>localhost: 8080</p></body></html>";
                content = html.getBytes(StandardCharsets.UTF_8);
                contentType = "text/html; charset=UTF-8";
            }

            exchange.getResponseHeaders().set("Content-Type", contentType);
            exchange.sendResponseHeaders(200, content.length);
            OutputStream outputStream = exchange.getResponseBody();
            outputStream.write(content);
            outputStream.close();
        }

        private String detectContentType(String path) {
            if (path.endsWith(".html")) {
                return "text/html; charset=UTF-8";
            }
            if (path.endsWith(".css")) {
                return "text/css; charset=UTF-8";
            }
            if (path.endsWith(".js")) {
                return "application/javascript; charset=UTF-8";
            }
            if (path.endsWith(".json")) {
                return "application/json; charset=UTF-8";
            }
            if (path.endsWith(".png")) {
                return "image/png";
            }
            if (path.endsWith(".jpg") || path.endsWith(".jpeg")) {
                return "image/jpeg";
            }
            if (path.endsWith(".svg")) {
                return "image/svg+xml";
            }
            return "text/plain; charset=UTF-8";
        }
    }
}
