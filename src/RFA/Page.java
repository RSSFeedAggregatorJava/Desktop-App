package RFA;

import io.swagger.client.api.UserApi;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class Page
{
    protected class Settings
    {
        public String fxml;
        public int width, height;
        public Settings(String fxml, int width, int height)
        {
            this.fxml = fxml;
            this.width = width;
            this.height = height;
        }
    }
    protected Stage _window;
    protected Scene _scene;
    protected Application _app;
    protected UserApi _uApi;

    abstract protected Settings getSettings();
    abstract protected void passData(Object[] data);
    abstract protected void start();

    public void switchScene(Stage win, Object[] data)
    {
        Page self = this;
        Settings s;

        s = self.getSettings();
        try
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(s.fxml));
            //
            Parent root = loader.load();
            self = loader.getController();
            self._scene = new Scene(root, s.width, s.height);
            self._window = win;
            self._window.setScene(self._scene);
            //
            self.passData(data);
            self.start();
        }
        catch (Exception ignored) {}
    }
}
