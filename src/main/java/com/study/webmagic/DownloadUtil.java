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
 */
public class DownloadUtil {

    public static void downloadPic(String url, String picId) {
        try {
            CloseableHttpClient client = HttpClients.createDefault();

            HttpGet get = new HttpGet(url);
            get.setHeader("Cookie","__cfduid=d0c51146a8d3eecb38067f4877597d1211607182724; zkhanecookieclassrecord=%2C66%2C; PHPSESSID=448lbb8gll19gknb00lvq52oi2; zkhanreturnurl=http%3A%2F%2Fpic.netbian.com%2Fnew%2F; zkhanmlusername=qq_%B0%D7%BA%FC; zkhanmlgroupid=2; zkhanmluserid=383901; zkhanmlrnd=V07PdwW46Jv9egHNnW5M; zkhanmlauth=c94c0e9cf9f33a01492323e64e3a15e6");
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

    public static byte[] readInputStream(InputStream inStream) throws Exception{
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        //创建一个Buffer字符串
        byte[] buffer = new byte[1024];
        //每次读取的字符串长度，如果为-1，代表全部读取完毕
        int len = 0;
        //使用一个输入流从buffer里把数据读取出来
        while( (len=inStream.read(buffer)) != -1 ){
            //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
            outStream.write(buffer, 0, len);
        }
        //关闭输入流
        inStream.close();
        //把outStream里的数据写入内存
        return outStream.toByteArray();
    }


}
