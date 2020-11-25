package com.paperfly.classUtils.controller.classPeople;

import java.util.*;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.paperfly.classUtils.entity.PeopleInfoEntity;
import com.paperfly.classUtils.service.PeopleInfoService;
import com.paperfly.classUtils.utils.MyFileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.paperfly.classUtils.entity.PeopleGroupEntity;
import com.paperfly.classUtils.service.PeopleGroupService;

import javax.servlet.http.HttpServletResponse;


/**
 * 
 *
 * @author paperfly
 * @email 1430978392@qq.com
 * @date 2020-09-08 21:16:50
 */
@RequestMapping("people/paperfly/group")
@Controller
public class PeopleGroupController {
    @Autowired
    private PeopleGroupService peopleGroupService;

    @Autowired
    private PeopleInfoService peopleInfoService;



    /**
     * 保存组员信息
     */
    @RequestMapping("/save/{groupId}/{groupName}")
    //@RequiresPermissions("paperfly:group:save")
    public String  save(String name[] , String no[],@PathVariable("groupName") String groupName,
                        @PathVariable("groupId") Long groupId,Map map){
        List list=new ArrayList();
        list.add("");
        if(name!=null&&no!=null&&name.length==no.length){

            PeopleGroupEntity peopleGroupEntity = new PeopleGroupEntity();
            String myName=name[0];
            for (int i = 0; i < name.length; i++) {
                if(i==0){
                    myName=name[i];
                }else {
                    myName=myName+","+name[i];
                }
            }

            String myNo=no[0];
            for (int i = 0; i < no.length; i++) {
                if(i==0){
                    myNo=no[i];
                }else {
                    myNo=myNo+","+no[i];
                }
            }
            peopleGroupEntity.setName(myName);
            peopleGroupEntity.setNo(myNo);
            peopleGroupEntity.setOrderTime(System.currentTimeMillis());
            peopleGroupEntity.setGroupId(groupId);
            peopleGroupService.save(peopleGroupEntity);
        }


        QueryWrapper<PeopleGroupEntity> queryWrapper1 = new QueryWrapper<>();

        queryWrapper1.eq("group_id", groupId);
        List<PeopleGroupEntity> groupList = peopleGroupService.list(queryWrapper1);
        List<PeopleInfoEntity> list1 = peopleNoIn(groupId);
        map.put("data",list1);
        map.put("groupName",groupName);
        map.put("groupId",groupId);
        //已经保存的组员信息
        map.put("showGroup",groupList);
        return "people/group";
    }





    @GetMapping("downloadExcel/{groupId}")
    public void down(HttpServletResponse response,@PathVariable("groupId") Long groupId){
        QueryWrapper<PeopleGroupEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("group_id",groupId);
        List<PeopleGroupEntity> list = peopleGroupService.list(queryWrapper);
        List<PeopleGroupEntity> peopleGroupEntityList=new ArrayList<>(30);
        int size = list.size();
        //随机组合
        for (int i=0;i<size;i++){
            PeopleGroupEntity peopleGroupEntity = RandomUtil.randomEle(list);
            list.remove(peopleGroupEntity);
            peopleGroupEntityList.add(peopleGroupEntity);
        }
        MyFileUtil.downloadExcel(response,peopleGroupEntityList,PeopleGroupEntity.class,"group.xls");
    }


    public List peopleNoIn(Long groupId){
        QueryWrapper<PeopleInfoEntity> queryWrapper = new QueryWrapper<>();
        List list=new ArrayList();
        list.add("");
        QueryWrapper<PeopleGroupEntity> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("group_id", groupId);
        List<PeopleGroupEntity> groupList = peopleGroupService.list(queryWrapper1);

        for (int i = 0; i < groupList.size(); i++) {
            String no = groupList.get(i).getNo();
            String[] split = no.split(",");
            for (String s : split) {
                list.add(s);
            }
        }
        queryWrapper.notIn("no",list);

        List<PeopleInfoEntity> list1 = peopleInfoService.list(queryWrapper);
        return list1;
    }
}
