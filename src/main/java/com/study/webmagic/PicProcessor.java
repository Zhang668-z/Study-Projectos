package com.study.webmagic;

import org.apache.commons.lang3.StringUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author zhangzhen
 * @date 2020/12/18 18:42
 */
public class PicProcessor implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setRetrySleepTime(1000)
            .addCookie("Hm_lvt_14b14198b6e26157b7eba06b390ab763","1600736030")
            .addCookie("_cfduid","dcee4b839a8bafeb2d5da67f04980fce01608293115")
            .addCookie("Hm_lvt_526caf4e20c21f06a4e9209712d6a20e","1608293220")
            .addCookie("PHPSESSID","gb0fi0ciu7o3q2es5i58lh46c6")
            .addCookie("zkhanmlgroupid","2")
            .addCookie("zkhanreturnurl","http%3A%2F%2Fpic.netbian.com%2F")
            .addCookie("Hm_lpvt_526caf4e20c21f06a4e9209712d6a20e","1608293676")
            .addCookie("zkhanmlusername","qq_%B0%D7%BA%FC")
            .addCookie("zkhanmluserid","383901")
            .addCookie("zkhanmlrnd","V8lJosbMsSki7JqnUrsU")
            .addCookie("zkhanmlauth","b4804fc11bd38dea563d6906b4f95800");

    @Override
    // process是定制爬虫逻辑的核心接口，在这里编写抽取逻辑
    public void process(Page page) {
        if (page.getUrl().toString().startsWith(SiteUrls.DETAILPAGE.getUrl())) { // 详情页
            String picId = page.getHtml().css("div.downpic a", "data-id").get();
            DownloadUtil.downloadPic(SiteUrls.DOWNURL.getUrl() + picId, picId);
        } else {  // 表示列表页
            List<String> hrefs = page.getHtml().css("ul.clearfix>li>a", "href").all();
            for (String href : hrefs) {
                page.addTargetRequest(href);
            }
            String url = page.getUrl().get();
//            url.substring(url.length(),url)
            String s = StringUtils.substringAfter(url,"_");
            String i = StringUtils.substringBefore(s, ".");
            if ("".equals(i)) {
                i = "2";
            }else {
                i = (Integer.parseInt(i)+1)+"";
            }
            System.out.println(i);
            page.addTargetRequest("http://pic.netbian.com/4kdongman/index_" + i + ".html");
        }
    }

    @Override
    public Site getSite() {
        return site;
    }
}
