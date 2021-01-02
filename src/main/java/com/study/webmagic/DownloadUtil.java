package com.study.webmagic;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import javax.imageio.ImageIO;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * @author zhangzhen
 * @date 2020/12/18 20:47
 * 通过URL下载图片
 */
public class DownloadUtil {

    public static void downloadPic(String url, String picId) {
        try {
            CloseableHttpClient client = HttpClients.createDefault();

            HttpGet get = new HttpGet(url);
            get.setHeader("Cookie","__cfduid=d0c51146a8d3eecb38067f4877597d1211607182724; PHPSESSID=448lbb8gll19gknb00lvq52oi2; zkhanreturnurl=http%3A%2F%2Fpic.netbian.com%2Fnew%2F; zkhanecookieclassrecord=%2C66%2C65%2C63%2C67%2C62%2C58%2C59%2C56%2C60%2C53%2C54%2C55%2C; zkhanmlusername=qq_%CE%D2%BF%C9%D2%D4%CD%E4%D1%FC288; zkhanmluserid=2321528; zkhanmlgroupid=3; zkhanmlrnd=PYYSff7wcxTIPOHgYqIa; zkhanmlauth=9b3336b935c6bad0a13f1ab8e88401a8");
            get.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36 Edg/87.0.664.60");
            get.setHeader("Referer","http://pic.netbian.com/e/memberconnect/qq/loginend.php?code=AD67AC3F247D41265B36274B9A1ABDCE&state=3aaeeaebc1615aca6acbe3f308d0ccf0");
            CloseableHttpResponse execute = client.execute(get);
            System.out.println("----------------------------响应类型"+execute.getEntity().getContentType());
            InputStream content = execute.getEntity().getContent();
            FileOutputStream outputStream = new FileOutputStream(new File("C:\\Users\\risen\\Desktop\\图片\\"+picId+".jpg"));
            byte[] bytes = new byte[1024];
            int len;
            while ((len = content.read(bytes))>0) {
                outputStream.write(bytes,0,len);
            }
            outputStream.close();
            content.close();
            System.out.println("文件下载成功");

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
