package RFA.home;

import RFA.LocalStorage;
import RFA.Main;
import RFA.Page;
import RFA.FakeApiClient;
import io.swagger.client.api.ArticleApi;
import io.swagger.client.api.FeedApi;
import io.swagger.client.api.UserApi;
import io.swagger.client.model.Article;
import io.swagger.client.model.Feed;
import io.swagger.client.model.InlineResponse2001;
import io.swagger.client.model.InlineResponse2002;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebView;
import javafx.util.Callback;
import org.joda.time.DateTime;

import java.math.BigDecimal;
import java.util.*;

public class Controller extends Page
{
    private FeedApi _fApi;
    private ArticleApi _aApi;
    private Application _app;
    private LocalStorage _ls;
    private InlineResponse2001 _selectedFeed;
    private InlineResponse2002 _selectedArticle;
    @FXML
    private ListView<InlineResponse2001> _feedsList;
    @FXML
    private ListView<InlineResponse2002> _articlesList;
//
    @FXML
    private Label _feedTitle;
    @FXML
    private Label _feedCategory;
    @FXML
    private Label _feedDescription;
    @FXML
    private Label _feedLanguage;
    @FXML
    private Hyperlink _feedLink;
    @FXML
    private TextField _feedToAdd;
//
    @FXML
    private Label _articleTitle;
    @FXML
    private Label _articleDate;
    @FXML
    private Label _articleAuthor;
    @FXML
    private Label _articleDescription;
    @FXML
    private WebView _articleView;
//
    private boolean _isFirstFeedsUpdate;
//
    private double _articlePaneOriginalHeight;
//
    @FXML
    private Label _accountEmail;
    @FXML
    private CheckBox _bigArticle;


    @Override
    protected Settings getSettings()
    {
        return new Settings("home.fxml", Main.WIN_WIDTH, Main.WIN_HEIGHT);
    }

    @Override
    protected void passData(Object[] data)
    {
        this._app = (Application)data[0];
        this._uApi = (UserApi)data[1];
        this._ls = (LocalStorage)data[2];
    }

//
    private void feedTitle(String s)
    {
        this._feedTitle.setText(s);
    }

    private String feedTitle()
    {
        return this._selectedFeed.getTitle();
    }

    private void feedCategory(String s)
    {
        this._feedCategory.setText(s);
    }

    private void feedDescription(String s)
    {
        this._feedDescription.setText(s);
    }

    private void feedLanguage(String s)
    {
        this._feedLanguage.setText(s);
    }

    private void feedLink(String s)
    {
        this._feedLink.setText(s);
    }

    private String feedLink()
    {
        return this._feedLink.getText();
    }

//
    private void articleTitle(String s)
    {
        this._articleTitle.setText(s);
    }

    private String articleTitle()
    {
        return this._selectedArticle.getTitle();
    }

    private void articleDate(DateTime d)
    {
        this._articleDate.setText(d.toString("MM/dd/yyyy HH:mm"));
    }

    private void articleAuthor(String s)
    {
        this._articleAuthor.setText(s);
    }

    private void articleDescription(String s)
    {
        this._articleDescription.setText(s);
    }

    private void accountEmail(String s)
    {
        this._accountEmail.setText(s);
    }

    private String accountEmail()
    {
        return this._accountEmail.getText();
    }

    private void feedToAdd(String s)
    {
        this._feedToAdd.setText(s);
    }

//
    private void updateFeeds()
    {
        List<InlineResponse2001> feeds;

        try
        {
            // clear
            this._selectedFeed = null;
            this._feedsList.getItems().clear();
            this._articlesList.getItems().clear();
            if (!this._isFirstFeedsUpdate) this.articleTitle("");
            else this._isFirstFeedsUpdate = false;

            feeds = this._fApi.feedsGet();
            while (feeds.size() > 0)
            {
                this._feedsList.getItems().add(feeds.get(0));
                feeds.remove(0);
            }
        }
        catch (Exception ignored)
        {
            System.err.println("Couldn't update the feeds");
        }
    }

    private void updateFeed()
    {
        Feed fDetails;
        List<InlineResponse2002> articles;

        this.feedTitle(this.feedTitle());
        try
        {
            // clear
            this._articlesList.getItems().clear();

            fDetails = this._fApi.feedsFeedIdGet(Integer.toUnsignedLong(this._selectedFeed.getId()));
            // set feed details
            this.feedCategory(fDetails.getCategory());
            this.feedDescription(fDetails.getDescription());
            this.feedLanguage(fDetails.getLanguage());
            this.feedLink(fDetails.getLink());

            articles = this._aApi.articlesFeedIdGet(fDetails.getId().toString());
            while  (articles.size() > 0)
            {
                this._articlesList.getItems().add(articles.get(0));
                articles.remove(0);
            }
        }
        catch (Exception ignored)
        {
            System.err.println("Couldn't update the feed");
        }
    }

    private void updateArticle()
    {
        Article a;

        this.articleTitle(this.articleTitle());
        try
        {
            a = this._aApi.articlesFeedIdArticleIdGet(this._selectedFeed.getId().toString(), this._selectedArticle.getId().toString());
//            this.articleDate(a.getPubdate());
            this.articleAuthor(a.getAuthor());
            this.articleDescription(a.getDescription());
            this._articleView.getEngine().load(a.getLink());
            if (this._bigArticle.isDisable()) this._bigArticle.setDisable(false);
        }
        catch (Exception ignored)
        {
            System.err.println("Couldn't update the article");
        }
    }

    private void feedSelectionEvent()
    {
        InlineResponse2001 feed;

        if ((feed = this._feedsList.getSelectionModel().getSelectedItem()) != null)
        {
            if (feed != this._selectedFeed)
            {
                this._selectedFeed = feed;
                this.updateFeed();
            }
        }
    }

    @FXML
    private void feedSelectionMouseEvent(MouseEvent event)
    {
        this.feedSelectionEvent();
    }

    @FXML
    private void feedSelectionKeyEvent(KeyEvent event)
    {
        KeyCode kc = event.getCode();

        if (kc != null)
        {
            if (kc == KeyCode.ENTER || kc == KeyCode.UP || kc == KeyCode.DOWN)
            {
                this.feedSelectionEvent();
            }
            else if (kc == KeyCode.DELETE)
            {
                this.removeFeedButtonEvent(null);
            }
        }
    }

    private void articleSelectionEvent()
    {
        InlineResponse2002 article;

        if ((article = this._articlesList.getSelectionModel().getSelectedItem()) != null)
        {
            if (article != this._selectedArticle)
            {
                this._selectedArticle = article;
                this.updateArticle();
            }
        }
    }

    @FXML
    private void articleSelectionMouseEvent(MouseEvent event)
    {
        this.articleSelectionEvent();
    }

    @FXML
    private void articleSelectionKeyEvent(KeyEvent event)
    {
        KeyCode kc = event.getCode();

        if (kc != null && (kc == KeyCode.ENTER || kc == KeyCode.UP || kc == KeyCode.DOWN))
        {
            this.articleSelectionEvent();
        }
    }

    @FXML
    private void feedLinkEvent(ActionEvent event)
    {
        this._app.getHostServices().showDocument(this.feedLink());
    }

    @FXML
    private void toggleBigArticle(ActionEvent event)
    {
        BorderPane bp = (BorderPane)this._articleView.getParent();

        this._articlesList.setVisible(!((CheckBox)event.getSource()).isSelected());
        if (((CheckBox)event.getSource()).isSelected())
        {
            bp.setPrefHeight(bp.getHeight() + this._articlesList.getHeight());
        }
        else bp.setPrefHeight(this._articlePaneOriginalHeight);
    }

    @FXML
    private void addFeedButtonEvent(ActionEvent event)
    {
        try
        {
            this._fApi.feedsPost(this._feedToAdd.getText());
            this.updateFeeds();
            this.feedToAdd(null);
        }
        catch (Exception ignored)
        {
            System.err.println("Couldn't add new feed");
        }
    }

    @FXML
    private void removeFeedButtonEvent(ActionEvent event)
    {
        if (this._selectedFeed != null)
        {
            try
            {
                this._fApi.feedsDelete(this._selectedFeed.getId());
                this.updateFeeds();
            }
            catch (Exception ignored) {}
        }
    }

    @FXML
    private void logoutButtonEvent(ActionEvent event)
    {
        try
        {
            this._uApi.usersLogoutGet();
            this._ls.delete("api_key").delete("email").flush();
            (new RFA.sign_in_up.Controller()).switchScene(this._window, new Object[] {
                    this._app,
                    this._uApi,
                    this._ls
            });
        }
        catch (Exception ignored)
        {
            System.err.println("Couldn't logout");
        }
    }

    @Override
    protected void start()
    {
        this._uApi.getApiClient().setApiKey(this._ls.getAsString("api_key"));
        this.accountEmail(this._ls.getAsString("email"));
        this._fApi = new FeedApi(this._uApi.getApiClient());
        this._aApi = new ArticleApi(this._uApi.getApiClient());
        this._isFirstFeedsUpdate = true;
        this._articlePaneOriginalHeight = ((BorderPane)this._articleView.getParent()).getHeight();

        this._feedsList.setCellFactory(new Callback<ListView<InlineResponse2001>, ListCell<InlineResponse2001>>()
        {
            @Override
            public ListCell<InlineResponse2001> call(ListView<InlineResponse2001> param)
            {
                return new ListCell<InlineResponse2001>()
                {
                    @Override
                    protected void updateItem(InlineResponse2001 item, boolean empty)
                    {
                        super.updateItem(item, empty);
                        if (item != null) setText(item.getTitle());
                        else setText("");
                    }
                };
            }
        });
        this._feedsList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        this.updateFeeds();

        this._articlesList.setCellFactory(new Callback<ListView<InlineResponse2002>, ListCell<InlineResponse2002>>()
        {
            @Override
            public ListCell<InlineResponse2002> call(ListView<InlineResponse2002> param)
            {
                return new ListCell<InlineResponse2002>()
                {
                    @Override
                    protected void updateItem(InlineResponse2002 item, boolean empty)
                    {
                        super.updateItem(item, empty);
                        if (item != null) setText(item.getTitle());
                        else setText("");
                    }
                };
            }
        });
        this._articlesList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }
}