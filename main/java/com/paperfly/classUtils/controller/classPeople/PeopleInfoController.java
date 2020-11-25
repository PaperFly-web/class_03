package com.paperfly.classUtils.controller.classPeople;

import java.util.ArrayList;
import java.util.List;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import cn.hutool.core.util.RandomUtil;
import com.paperfly.classUtils.utils.MyFileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.paperfly.classUtils.entity.PeopleInfoEntity;
import com.paperfly.classUtils.service.PeopleInfoService;

import javax.servlet.http.HttpServletResponse;


/**
 * 
 *
 * @author paperfly
 * @email 1430978392@qq.com
 * @date 2020-09-08 21:16:50
 */
@RestController
@RequestMapping("people/paperfly/peopleinfo")
public class PeopleInfoController {
    @Autowired
    private PeopleInfoService peopleInfoService;


    @GetMapping("/randomExcel/{n}")
    public void randomExcel(@PathVariable("n") Integer n, HttpServletResponse response){
        List<PeopleInfoEntity> list = peopleInfoService.list();
        for (PeopleInfoEntity peopleInfoEntity : list) {
            peopleInfoEntity.setPhone(null);
        }
        List randomList=new ArrayList(n);
        for (int i=0;i<n;i++){
            PeopleInfoEntity peopleInfoEntity = RandomUtil.randomEle(list);
            list.remove(peopleInfoEntity);
            randomList.add(peopleInfoEntity);
        }
        MyFileUtil.downloadExcel(response,randomList,PeopleInfoEntity.class,"random.xls");
    }
}
