package com.paperfly.classUtils.controller.classPeople;

import com.paperfly.classUtils.entity.GroupEntity;
import com.paperfly.classUtils.service.GroupService;
import com.paperfly.classUtils.service.PeopleGroupService;
import com.paperfly.classUtils.service.PeopleInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@Controller
public class PeopleIndexController {

    @Autowired
    PeopleInfoService peopleInfoService;

    @Autowired
    PeopleGroupService peopleGroupService;

    @Autowired
    GroupService groupService;


    /**
     * 查看所有分组
     * @param map
     * @return
     */
    @GetMapping("/user/people")
    public String index(Map map){
        List<GroupEntity> list = groupService.list();
        map.put("data",list);
        return "people/index";
    }



}
