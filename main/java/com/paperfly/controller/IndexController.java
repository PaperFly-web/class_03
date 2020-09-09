package com.paperfly.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.paperfly.entity.PeopleGroupEntity;
import com.paperfly.entity.PeopleInfoEntity;
import com.paperfly.service.GroupService;
import com.paperfly.service.PeopleInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class IndexController {

    @Autowired
    PeopleInfoService peopleInfoService;

    @Autowired
    GroupService groupService;

    @GetMapping("/")
    public String index(Map map){
        QueryWrapper<PeopleInfoEntity> queryWrapper = new QueryWrapper<>();
        List<PeopleGroupEntity> groupList = groupService.list();
        List list=new ArrayList();
        list.add("");
        for (int i = 0; i < groupList.size(); i++) {
            String name1 = groupList.get(i).getName();
            String[] split = name1.split(",");
            for (String s : split) {
                list.add(s);
            }
        }
        queryWrapper.notIn("name",list);
        List<PeopleInfoEntity> list1 = peopleInfoService.list(queryWrapper);
        map.put("data",list1);
        return "index";
    }
}
