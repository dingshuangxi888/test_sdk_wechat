package net.locplus.sdk.wechat.model.resp;

import net.locplus.sdk.wechat.model.Articles;

import java.util.List;

/**
 * 回复图文消息
 * Created by Administrator on 2014/4/18.
 */
public class NewsResponseMessage extends BaseResponseMessage {

    /**
     * 图文消息个数，限制为10条以内
     */
    private int ArticleCount;

    private List<Articles> Articles;

    public int getArticleCount() {
        return ArticleCount;
    }

    public void setArticleCount(int articleCount) {
        ArticleCount = articleCount;
    }

    public List<Articles> getArticles() {
        return Articles;
    }

    public void setArticles(List<Articles> articles) {
        Articles = articles;
    }
}
