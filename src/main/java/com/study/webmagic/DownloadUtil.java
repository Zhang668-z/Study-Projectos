package com.study.webmagic;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author zhangzhen
 * @date 2020/12/18 20:47
 * 通过URL下载图片
 */
public class DownloadUtil {

    public static void downloadPic(String url, String picId) {
            FileOutputStream stream = null;
        try {
            RestTemplate template = new RestTemplate();
            // 设置请求头信心 cookie和referer
            HttpHeaders headers = new HttpHeaders();
            headers.add("Cookie","__cfduid=d0c51146a8d3eecb38067f4877597d1211607182724; PHPSESSID=448lbb8gll19gknb00lvq52oi2; zkhanreturnurl=http%3A%2F%2Fpic.netbian.com%2Fnew%2F; zkhanecookieclassrecord=%2C66%2C65%2C63%2C67%2C62%2C58%2C59%2C56%2C60%2C53%2C54%2C55%2C; zkhanmlusername=qq_%CE%D2%BF%C9%D2%D4%CD%E4%D1%FC288; zkhanmluserid=2321528; zkhanmlgroupid=3; zkhanmlrnd=PYYSff7wcxTIPOHgYqIa; zkhanmlauth=9b3336b935c6bad0a13f1ab8e88401a8");
            headers.add("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36 Edg/87.0.664.60");
            headers.add("Referer","http://pic.netbian.com/e/memberconnect/qq/loginend.php?code=AD67AC3F247D41265B36274B9A1ABDCE&state=3aaeeaebc1615aca6acbe3f308d0ccf0");
            HttpEntity<String> httpEntity = new HttpEntity<String>(null, headers);
            ResponseEntity<byte[]> entity = template.exchange(url, HttpMethod.GET, httpEntity, byte[].class);
            // 获取图片信息
            byte[] picData = entity.getBody();
            stream = new FileOutputStream(new File("C:\\Users\\risen\\Desktop\\图片\\"+picId+".jpg"));
            // 信息写入文件
            stream.write(picData);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("图片下载失败");
            }finally {
            try {
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
