package com.paperfly.classUtils.controller.classPeople;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.paperfly.classUtils.entity.GroupEntity;
import com.paperfly.classUtils.entity.PeopleGroupEntity;
import com.paperfly.classUtils.entity.PeopleInfoEntity;
import com.paperfly.classUtils.service.GroupService;
import com.paperfly.classUtils.service.PeopleGroupService;
import com.paperfly.classUtils.service.PeopleInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class GroupController {

    @Autowired
    GroupService groupService;

    @Autowired
    PeopleGroupService peopleGroupService;

    @Autowired
    PeopleInfoService peopleInfoService;

    /**
     * 添加分组
     * @param groupName
     * @param map
     * @return
     */
    @PostMapping("people/addGroup")
    public String addGroup(String groupName, Map map){
        GroupEntity groupEntity = new GroupEntity();
        groupEntity.setGroupName(groupName);
        boolean save = groupService.save(groupEntity);
        List<GroupEntity> list = groupService.list();
        map.put("data",list);
        return "people/index";
    }
    @GetMapping("people/delGroup/{groupId}")
    public String delGroup(@PathVariable("groupId") Long groupId, Map map){

        Wrapper<GroupEntity> queryWrapper = new QueryWrapper<GroupEntity>().eq("group_id", groupId);
        groupService.remove(queryWrapper);
        List<GroupEntity> list = groupService.list();
        map.put("data",list);
        return "people/index";
    }
    /**
     * 展示具体分组信息
     * @param groupId
     * @param map
     * @return
     */
    @GetMapping("/user/people/toGroup/{groupId}/{groupName}")
    public String toGroup(@PathVariable("groupId") Long groupId,
                          @PathVariable("groupName")String groupName, Map map){
        QueryWrapper<GroupEntity> groupEntityQueryWrapper = new QueryWrapper<GroupEntity>().eq("group_id", groupId);
        GroupEntity group = groupService.getOne(groupEntityQueryWrapper);
        //如果数据库没有这个分组，就转回去
        if(group==null ||!group.getGroupName().equals(groupName)){
            List<GroupEntity> list = groupService.list();
            map.put("data",list);
            return "people/index";
        }
        QueryWrapper<PeopleInfoEntity> queryWrapper = new QueryWrapper<>();

        QueryWrapper<PeopleGroupEntity> queryWrapper1 = new QueryWrapper<>();

        queryWrapper1.eq("group_id", groupId);
        List<PeopleGroupEntity> groupList = peopleGroupService.list(queryWrapper1);

        List list=new ArrayList();
        list.add("");
        for (int i = 0; i < groupList.size(); i++) {
            String no = groupList.get(i).getNo();
            String[] split = no.split(",");
            for (String s : split) {
                list.add(s);
            }
        }
        queryWrapper.notIn("no",list);
        List<PeopleInfoEntity> list1 = peopleInfoService.list(queryWrapper);
        map.put("data",list1);
        map.put("groupId",groupId);
        map.put("groupName",groupName);
        //已经保存的组员信息
        map.put("showGroup",groupList);
        return "people/group";

    }

}
