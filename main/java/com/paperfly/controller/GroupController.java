package com.paperfly.controller;

import java.util.*;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.paperfly.entity.PeopleInfoEntity;
import com.paperfly.service.PeopleInfoService;
import com.paperfly.utils.MyDateUtil;
import com.paperfly.utils.MyFileUtil;
import com.paperfly.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.paperfly.entity.PeopleGroupEntity;
import com.paperfly.service.GroupService;

import javax.servlet.http.HttpServletResponse;


/**
 * 
 *
 * @author paperfly
 * @email 1430978392@qq.com
 * @date 2020-09-08 21:16:50
 */
@RequestMapping("paperfly/group")
@Controller
public class GroupController {
    @Autowired
    private GroupService groupService;

    @Autowired
    private PeopleInfoService peopleInfoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("paperfly:group:list")
    public R list(@RequestParam Map<String, Object> params){


        return R.ok();
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
   // @RequiresPermissions("paperfly:group:info")
    public R info(@PathVariable("id") Long id){
		PeopleGroupEntity group = groupService.getById(id);

        return R.ok().put("group", group);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("paperfly:group:save")
    public String  save( String name[] ,String no[],Map map){
        QueryWrapper<PeopleInfoEntity> queryWrapper = new QueryWrapper<>();
        List list=new ArrayList();
        list.add("");

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
        peopleGroupEntity.setOrderTime(MyDateUtil.getDay(new Date()));
        groupService.save(peopleGroupEntity);

        //查询还没有被记录的人员
        List<PeopleGroupEntity> groupList = groupService.list();

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

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("paperfly:group:update")
    public R update(@RequestBody PeopleGroupEntity group){
		groupService.updateById(group);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
   // @RequiresPermissions("paperfly:group:delete")
    public R delete(@RequestBody Long[] ids){
		groupService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

    @GetMapping("downloadExcel")
    public void down(HttpServletResponse response){
        List<PeopleGroupEntity> list = groupService.list();
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
}
