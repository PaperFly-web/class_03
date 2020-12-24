package com.paperfly.classUtils.controller;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.paperfly.classUtils.entity.OlaMi;
import org.apache.commons.codec.digest.Md5Crypt;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class UtilController {

    @RequestMapping("/getMd5")
    public String getMd5(String parm){
//        String md5 = SecureUtil.md5(parm);
        String md5=null;
        RestTemplate restTemplate=new RestTemplate();
        OlaMi olaMi = new OlaMi();
        String jsonStr = JSONUtil.toJsonStr(olaMi);
        String url="https://cn.olami.ai/cloudservice/api?appkey=0fc7a3a6032b4d76ae86f2306a71554e&api=nli&timestamp="+System.currentTimeMillis()+"&sign="+getSign()+"&rq="+jsonStr+"&cusid="+ RandomUtil.randomNumbers(5);
        HttpEntity httpEntity = new HttpEntity("");
        try {
            //使用 exchange 发送请求，以String的类型接收返回的数据
            //ps，我请求的数据，其返回是一个json
            ResponseEntity<String> strbody = restTemplate.exchange(url, HttpMethod.POST,httpEntity,String.class);
            //解析返回的数据
            JSONObject jsonObject = JSONUtil.parseObj(strbody);
            md5=jsonObject.toString();
            System.out.println(jsonObject);
        }catch (Exception e){
            System.out.println(e);
        }
        return md5;
    }

    public String  getSign(){
        String sec="599ab0cdf73f41dda951f3c8cce0097d";
        String appkey="0fc7a3a6032b4d76ae86f2306a71554e";
        String s = sec + "api=nli" + "appkey=" + appkey + "timestamp=" + System.currentTimeMillis() + sec;
        String md5 = SecureUtil.md5(s);
        return md5;
    }



}
