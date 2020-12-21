package com.study.webmagic;

/**
 * @author zhangzhen
 * @date 2020/12/19 10:27
 * 爬取网站的url信息
 */
public enum SiteUrls {

    SITEURL("http://pic.netbian.com"),
    INDEXPAGE("http://pic.netbian.com/4kdongman"),
    DETAILPAGE("http://pic.netbian.com/tupian"),
    DOWNURL("http://pic.netbian.com/downpic.php?id=");

    private String url;

    SiteUrls(String url) {
        this.url = url;
    }

    public String getUrl() {
        return this.url;
    }

}
