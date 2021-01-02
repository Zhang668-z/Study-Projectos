package com.study.webmagic;

import org.apache.commons.lang3.StringUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

/**
 * @author zhangzhen
 * @date 2020/12/18 18:42
 */
public class PicProcessor implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setRetrySleepTime(1000);

    @Override
    // process是定制爬虫逻辑的核心接口，在这里编写抽取逻辑
    public void process(Page page) {
        if (page.getUrl().toString().startsWith(SiteUrls.DETAILPAGE.getUrl())) { // 详情页
            // 通过css样式定位到具体所需要的信息标签 获取属性中的信息
            String picId = page.getHtml().css("div.downpic a", "data-id").get();
            DownloadUtil.downloadPic(SiteUrls.DOWNURL.getUrl() + picId, picId);
        } else {  // 表示列表页
            // 拿到一个列表页中多个图片的不同图片详情网址列表
            List<String> hrefs = page.getHtml().css("ul.clearfix>li>a", "href").all();
            for (String href : hrefs) {
                // 加入任务队列
                page.addTargetRequest(href);
            }
            // 获取当前列表url 进行字符串切割 获取当前页码
            String url = page.getUrl().get();
            String s = StringUtils.substringAfter(url,"_");
            String i = StringUtils.substringBefore(s, ".");
            if ("".equals(i)) {
                // 为空表示当前页为首页 进行第二页跳转
                i = "2";
            }else {
                // 页码加1
                i = (Integer.parseInt(i)+1)+"";
            }
            // 将下一页列表页加入到任务队列
            page.addTargetRequest(SiteUrls.PAGEURLPREFIX.getUrl() + i + ".html");
        }
    }

    @Override
    public Site getSite() {
        return site;
    }
}
