package net.locplus.sdk.wechat.model.resp;

import net.locplus.sdk.wechat.model.Articles;

/**
 * 回复图文消息
 * Created by Administrator on 2014/4/18.
 */
public class NewsResponseMessage extends BaseResponseMessage {

    /**
     * 图文消息个数，限制为10条以内
     */
    private int ArticleCount;
    /**
     * 多条图文消息信息，默认第一个item为大图,注意，如果图文数超过10，则将会无响应
     */
    private Articles Articles;

    public int getArticleCount() {
        return ArticleCount;
    }

    public void setArticleCount(int articleCount) {
        ArticleCount = articleCount;
    }

    public Articles getArticles() {
        return Articles;
    }

    public void setArticles(Articles articles) {
        Articles = articles;
    }
}
