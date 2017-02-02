package RFA;

import io.swagger.client.model.Article;
import io.swagger.client.model.Feed;
import io.swagger.client.model.InlineResponse2001;
import io.swagger.client.model.InlineResponse2002;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

public class FakeApiClient
{
    /*
    * Feed
    */

    // generates a feeds list
    static public List<InlineResponse2001> feedsGet()
    {
        ArrayList<InlineResponse2001> l = new ArrayList<>();

        for (int i = 0; i < 512; i += (i % 2 == 0) ? 3 : 7)
        {
            InlineResponse2001 f = new InlineResponse2001();

            f.setId(i);
            f.setTitle("Some random [" + i + "] feed");
            l.add(f);
        }
        return l;
    }

    // generates a feed's details
    static public Feed feedsFeedIdGet(Long id)
    {
        Feed f =  new Feed();

        f.setId(Math.toIntExact(id));
        f.setCategory("Political News");
        f.setLanguage("Navajo");
        f.setLink("http://google.com");
        f.setDescription("In computing, a news aggregator, also termed a feed aggregator, feed reader, news reader, RSS reader or simply aggregator, is client software or a web application which aggregates syndicated web content such as online newspapers, blogs, podcasts, and video blogs (vlogs) in one location for easy viewing. RSS is a synchronized subscription system.");
        return f;
    }

    /*
    * Article
    */

    // generates an articles list
    static public List<InlineResponse2002> articlesFeedIdGet(String id)
    {
        ArrayList<InlineResponse2002> l = new ArrayList<>();

        for (int i = 0; i < 512; i += (i % 2 == 0) ? 3 : 7)
        {
            InlineResponse2002 a = new InlineResponse2002();

            a.setId(i);
            a.setTitle("Some random [" + i + "] article");
            l.add(a);
        }
        return l;
    }

    // generates an article
    static public Article articlesFeedIdArticleIdGet(String fId, String aId)
    {
        Article a = new Article();

        a.setId(Integer.parseInt(aId));
//        a.setTitle(this.articleTitle());
        a.setAuthor("Alexandre Paillier");
        a.setDescription("Technology companies including Facebook, Google, Microsoft, Uber, Netflix, and others have responded with varying...");
        a.setPubdate(DateTime.now());
        a.setLink("http://www.epitech.eu");
        return a;
    }
}
