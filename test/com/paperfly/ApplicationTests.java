package com.paperfly;


import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.paperfly.classUtils.dao.PeopleGroupDao;
import com.paperfly.classUtils.entity.PeopleGroupEntity;
import com.paperfly.classUtils.entity.PeopleInfoEntity;
import com.paperfly.classUtils.service.PeopleGroupService;
import com.paperfly.classUtils.service.PeopleInfoService;
import com.paperfly.classUtils.utils.MyDateUtil;
import com.paperfly.classUtils.utils.MyRandom;
import com.paperfly.classUtils.utils.PeopleUitl;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.HttpPost;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.*;

@Slf4j
@SpringBootTest
class ApplicationTests {
    @Autowired
    PeopleGroupDao groupDao;

    @Autowired
    private PeopleGroupService peopleGroupService;

    @Autowired
    private PeopleInfoService peopleInfoService;

    @Test
   public void t1(){
        List<PeopleInfoEntity>  peopleInfoEntityList = peopleNoIn();
        List random = MyRandom.random(peopleInfoEntityList);
        int size = random.size();
        for (int i = 0; i < size/5; i++) {
            List list=new ArrayList();
            for (int j=0;j<5;j++){
                 Object o = random.get(0);
                 random.remove(0);
                 list.add(o);
            }

            PeopleGroupEntity peopleGroupEntity = PeopleUitl.uti(list);
            peopleGroupService.save(peopleGroupEntity);
        }
    }

    public List<PeopleInfoEntity>  peopleNoIn(){
        QueryWrapper<PeopleInfoEntity> queryWrapper = new QueryWrapper<>();
        List list=new ArrayList();
        list.add("");
        QueryWrapper<PeopleGroupEntity> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.gt("order_time", MyDateUtil.getDay(new Date()).getTime());
        List<PeopleGroupEntity> groupList = peopleGroupService.list(queryWrapper1);

        for (int i = 0; i < groupList.size(); i++) {
            String name1 = groupList.get(i).getName();
            String[] split = name1.split(",");
            for (String s : split) {
                list.add(s);
            }
        }
        queryWrapper.notIn("name",list);

        List<PeopleInfoEntity> list1 = peopleInfoService.list(queryWrapper);
        return list1;
    }



    @Test
    public void t4(){
        PeopleGroupEntity a = groupDao.testSelectOne("1'OR'1=1");
        System.out.println(a);
    }

    @Test
    //测试连接校园网
    public void scheduling(){
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
