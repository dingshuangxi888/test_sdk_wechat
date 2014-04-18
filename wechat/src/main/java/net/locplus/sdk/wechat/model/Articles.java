package net.locplus.sdk.wechat.model;

import java.util.List;

/**
 * 多条图文消息信息
 * Created by Administrator on 2014/4/18.
 */
public class Articles {
    /**
     * 图文内容列表
     */
    private List<Article> item;

    public List<Article> getItem() {
        return item;
    }

    public void setItem(List<Article> item) {
        this.item = item;
    }
}
