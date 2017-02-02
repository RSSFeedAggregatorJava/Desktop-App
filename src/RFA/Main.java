package RFA;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application
{
    public static int WIN_WIDTH = 1280, WIN_HEIGHT = 720;

    @Override
    public void start(Stage window) throws Exception
    {
        Image icon;

        window.setTitle("RSS Feed Aggregator");
        try
        {
            icon = new Image(Main.class.getResourceAsStream("/icon.png"));
        }
        catch (Exception ignored)
        {
            icon = new Image("file:icon.png");
        }
        window.getIcons().add(icon);
        window.show();
        window.setResizable(false);
        (new RFA.sign_in_up.Controller()).switchScene(window, new Object[] {this});
    }


    public static void main(String[] args)
    {
        launch(args);
    }
}
