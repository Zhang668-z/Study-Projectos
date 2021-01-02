package com.study.webmagic;

import us.codecraft.webmagic.Spider;

/**
 * @author zhangzhen
 * @date 2020/12/18 18:47
 */
public class Run {
    public static void main(String[] args) {
        Spider.create(new PicProcessor()).addUrl(SiteUrls.SITEURL.getUrl())
                .thread(5).run();
    }
}

