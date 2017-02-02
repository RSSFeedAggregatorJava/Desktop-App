package RFA.sign_in_up;

import RFA.LocalStorage;
import RFA.Main;
import RFA.Page;
import io.swagger.client.api.UserApi;
import io.swagger.client.model.Credentials;
import io.swagger.client.model.Credentials1;
import io.swagger.client.model.InlineResponse200;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;

public class Controller extends Page
{
    @FXML
    private TextField _email;
    @FXML
    private PasswordField _password;
    @FXML
    private Text _result;
    private Application _app;
    private LocalStorage _ls;


    @Override
    protected Settings getSettings()
    {
        return new Settings("sign_in_up.fxml", Main.WIN_WIDTH, Main.WIN_HEIGHT);
    }

    @Override
    protected void passData(Object[] data)
    {
        this._app = (Application)data[0];
        if (data.length > 1)
        {
            this._uApi = (UserApi)data[1];
            this._ls = (LocalStorage)data[2];
        }
    }

    @Override
    protected void start()
    {
        if (this._uApi == null)
        {
            this._uApi = new UserApi();
            this._ls = new LocalStorage();
        }
        if (this._ls.getAsString("api_key") != null)
        {
            (new RFA.home.Controller()).switchScene(this._window, new Object[] {
                    this._app,
                    this._uApi,
                    this._ls
            });
        }
    }

    private String emailS()
    {
        return this._email.getText();
    }

    private void emailS(String s)
    {
        this._email.setText(s);
    }

    private String passwordS()
    {
        return this._password.getText();
    }

    private void passwordS(String s)
    {
        this._password.setText(s);
    }

    private String resultS()
    {
        return this._result.getText();
    }

    private void resultS(String s)
    {
        this._result.setText(s);
    }

    @FXML
    private void signInButtonAction(ActionEvent actionEvent)
    {
        InlineResponse200 res;

        this.resultS("");
        try
        {
            res = this._uApi.usersLoginPost(new Credentials1().email(this.emailS()).password(this.passwordS()));

            this._ls.store("api_key", res.getApiKey()).store("email", this.emailS()).flush();
            (new RFA.home.Controller()).switchScene(this._window, new Object[] {
                    this._app,
                    this._uApi,
                    this._ls
            });
        }
        catch (Exception ignored)
        {
            this.resultS("Couldn't sign in");
        }
    }

    @FXML
    private void signUpButtonAction(ActionEvent actionEvent)
    {
        this.resultS("");
        try
        {
            this._uApi.usersSignupPost(new Credentials().email(this.emailS()).password(this.passwordS()));
            this.resultS("Account creation successful");
        }
        catch (Exception ignored)
        {
            this.resultS("Couldn't sign up");
        }
    }
}