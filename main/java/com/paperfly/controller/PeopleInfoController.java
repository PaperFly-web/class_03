package com.paperfly.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.paperfly.utils.PageUtils;
import com.paperfly.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.paperfly.entity.PeopleInfoEntity;
import com.paperfly.service.PeopleInfoService;




/**
 * 
 *
 * @author paperfly
 * @email 1430978392@qq.com
 * @date 2020-09-08 21:16:50
 */
@RestController
@RequestMapping("paperfly/peopleinfo")
public class PeopleInfoController {
    @Autowired
    private PeopleInfoService peopleInfoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("paperfly:peopleinfo:list")
    public R list(){

        List<PeopleInfoEntity> list = peopleInfoService.list();

        return R.ok().put("data",list);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
   // @RequiresPermissions("paperfly:peopleinfo:info")
    public R info(@PathVariable("id") Long id){
		PeopleInfoEntity peopleInfo = peopleInfoService.getById(id);

        return R.ok().put("peopleInfo", peopleInfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("paperfly:peopleinfo:save")
    public R save(@RequestBody PeopleInfoEntity peopleInfo){
		peopleInfoService.save(peopleInfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("paperfly:peopleinfo:update")
    public R update(@RequestBody PeopleInfoEntity peopleInfo){
		peopleInfoService.updateById(peopleInfo);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
   // @RequiresPermissions("paperfly:peopleinfo:delete")
    public R delete(@RequestBody Long[] ids){
		peopleInfoService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
