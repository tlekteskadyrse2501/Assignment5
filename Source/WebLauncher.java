import java.awt.Desktop;
import java.awt.HeadlessException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WebLauncher {

    public void launch() {
        Path path = Path.of("Website", "index.html").toAbsolutePath();
        if (!Files.exists(path)) {
            System.out.println("Website not found. Expected file: " + path);
            System.out.println();
            return;
        }

        System.out.println("Opening the website: " + path);
        try {
            if (!Desktop.isDesktopSupported()) {
                throw new UnsupportedOperationException();
            }
            Desktop.getDesktop().browse(path.toUri());
        } catch (HeadlessException exception) {
            System.out.println("Could not open the browser automatically. Open this file manually: " + path);
        } catch (UnsupportedOperationException exception) {
            System.out.println("Could not open the browser automatically. Open this file manually: " + path);
        } catch (IOException exception) {
            System.out.println("Could not open the browser automatically. Open this file manually: " + path);
        }
        System.out.println();
    }
}
