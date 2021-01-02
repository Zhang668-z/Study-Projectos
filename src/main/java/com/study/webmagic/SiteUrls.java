package com.study.webmagic;

/**
 * @author zhangzhen
 * @date 2020/12/19 10:27
 * 爬取网站的url信息
 */
public enum SiteUrls {

    SITEURL("http://pic.netbian.com"), // 首页网址前缀
    PAGEURLPREFIX("http://pic.netbian.com/4kdongman/index_"), // 多页网址前缀(第一页、第二页等)
    DETAILPAGE("http://pic.netbian.com/tupian"), // 当前图片详情页网址前缀
    DOWNURL("http://pic.netbian.com/downpic.php?id="); // 图片下载地址前缀

    private String url;

    SiteUrls(String url) {
        this.url = url;
    }

    public String getUrl() {
        return this.url;
    }

}
