package com.paperfly.campusNetworkUtils.schedule;

import cn.hutool.http.Method;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.apache.http.Header;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.service.MediaTypes;

import javax.annotation.PostConstruct;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class NetworkScheduling {
    @PostConstruct
    public void init(){
        System.out.println("网站启动了,开始联网......");
        execuNetwork();
    }
    @Scheduled(cron = "30 1 6 * * ?")
    public void scheduling(){
        execuNetwork();
    }


    @Scheduled(cron = "30 1 7 * * ?")
    public void scheduling2(){
       execuNetwork();
    }

    @Scheduled(cron = "30 1 0 * * ?")
    public void scheduling3(){
       execuNetwork();
    }

    @Scheduled(cron = "30 20 23 * * ?")
    public void scheduling4(){
      execuNetwork();
    }

    @Scheduled(cron = "30 20 1 * * ?")
    public void scheduling5(){
       execuNetwork();
    }
    public void execuNetwork(){
        RestTemplate restTemplate=new RestTemplate();
        String url="http://10.28.200.56:8080/eportal/InterFace.do?method=login";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentLength(699);
        headers.setContentType(MediaType.valueOf(MediaType.APPLICATION_FORM_URLENCODED_VALUE));
        List<Charset> charsets=new ArrayList<>();
        Charset charset = Charset.forName("UTF-8");
        charsets.add(charset);
        headers.setAcceptCharset(charsets);
        List<MediaType> mediaTypes=new ArrayList<>();
        mediaTypes.add(MediaType.valueOf(MediaType.ALL_VALUE));
        headers.setAccept(mediaTypes);
        headers.setOrigin("http://10.28.200.56:8080");
        headers.set( "User-Agent" ,  "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:50.0) Gecko/20100101 Firefox/50.0" );



        // 请求体，包括请求数据 body 和 请求头 headers
        HttpEntity httpEntity = new HttpEntity("userId=092818106&password=PaperFly916924&service=%25E7%25A7%25BB%25E5%258A%25A8&queryString=wlanuserip%253D419f4ad9b1c0e383a75f70f312014cc1%2526wlanacname%253Dfdce1bc241f634bf1bb1c06a02479fe6%2526ssid%253D%2526nasip%253D30694bc990ef8fb530d9b634c75f78f0%2526snmpagentip%253D%2526mac%253Dcfb1ad60573dd740f2c77c30e96a1ab2%2526t%253Dwireless-v2%2526url%253D63dd0e45e34314b31f5fe54250d8b3ba0328c9bbafeb9fbf7081d7b4785a729d%2526apmac%253D%2526nasid%253Dfdce1bc241f634bf1bb1c06a02479fe6%2526vid%253D78263ede2c6c1f01%2526port%253D7e202b12eef557c0%2526nasportid%253Dc5e1bd3f32f8f6ac8e41a216cd1c8e2d44a43f6bde48dd3386f8ba0f5ef950dc9ebc76cfb8aee516&operatorPwd=&operatorUserId=&validcode=&passwordEncrypt=false",headers);

        try {
            //使用 exchange 发送请求，以String的类型接收返回的数据
            //ps，我请求的数据，其返回是一个json
            ResponseEntity<String> strbody = restTemplate.exchange(url, HttpMethod.POST,httpEntity,String.class);
            //解析返回的数据
            JSONObject jsonObject = JSONUtil.parseObj(strbody);
            System.out.println(jsonObject);
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
