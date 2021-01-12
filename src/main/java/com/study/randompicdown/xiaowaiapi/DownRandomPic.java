package com.study.randompicdown.xiaowaiapi;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author zhangzhen
 * @date 2021/1/11 14:25
 */
public class DownRandomPic {

    public static void main(String[] args) {
       getPicDownUrl();
    }

    private static Set<String> getPicDownUrl() {
        RestTemplate template = new RestTemplate();
        // 将请求返回的不支持的Content-type类型 text/json 手动加入到http消息对象的媒体类型列表
        List<HttpMessageConverter<?>> converterList = template.getMessageConverters();
        for (HttpMessageConverter<?> httpMessageConverter : converterList) {
            if (httpMessageConverter instanceof MappingJacksonHttpMessageConverter) {
                try {
                    ArrayList<MediaType> mediaTypeArrayList = new ArrayList<>(httpMessageConverter.getSupportedMediaTypes());
                    mediaTypeArrayList.add(MediaType.parseMediaType("text/json"));
                    ((MappingJacksonHttpMessageConverter) httpMessageConverter).setSupportedMediaTypes(mediaTypeArrayList);
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        HashSet<String> set = new HashSet<>();
        HttpHeaders headers = new HttpHeaders();
        headers.add("user-agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.141 Safari/537.36 Edg/87.0.664.75");
        HttpEntity<String> httpEntity = new HttpEntity<>(headers);
        int i = 0;
        while (i <= 50) {
            String url = "https://api.ixiaowai.cn/api/api.php?return=json";
            PicInfo picInfo = template.exchange(url, HttpMethod.GET, httpEntity, PicInfo.class).getBody();
            if (!set.contains(picInfo.getImgurl())) {
                byte[] bytes = template.getForObject(picInfo.getImgurl(), byte[].class);
                try {
                    BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File("C:\\Users\\risen\\Desktop\\图片/"+System.currentTimeMillis()+".webp")));
                    stream.write(bytes, 0, bytes.length);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                set.add(picInfo.getImgurl());
            }else {
                System.err.println("当前数组长度为："+set.size());
                continue;
            }
            i++;
        }
        return null;
    }

}
