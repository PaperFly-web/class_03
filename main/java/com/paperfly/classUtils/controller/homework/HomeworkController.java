package com.paperfly.classUtils.controller.homework;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.paperfly.classUtils.entity.HomeworkEntity;
import com.paperfly.classUtils.service.HomeworkService;
import com.paperfly.classUtils.service.UserTaskService;
import com.paperfly.classUtils.utils.MyFileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("homework")
public class HomeworkController {

    @Autowired
    UserTaskService userTaskService;

    @Autowired
    HomeworkService homeworkService;
    Set<String> params=new HashSet<>();
    QueryWrapper<HomeworkEntity> homeworkList = new QueryWrapper<HomeworkEntity>();

    //添加作业
    @PostMapping("/plus")
    @PreAuthorize("hasRole('admin')")
    public ModelAndView plusHomework(ModelAndView mv,String plus,@RequestParam("endTime") String  endTimeStr){
        endTimeStr=endTimeStr+" 21:00";
        DateTime dateTime = DateUtil.parse(endTimeStr);
        LocalDateTime endTime = LocalDateTime.ofInstant(dateTime.toInstant(), ZoneId.systemDefault());

        HomeworkEntity homeworkEntity = new HomeworkEntity();
        homeworkEntity.setHomeworkName(plus);
        homeworkEntity.setIsDel(0);
        homeworkEntity.setEndTime(endTime);
        homeworkService.save(homeworkEntity);
        List<HomeworkEntity> homeworks = getList(homeworkService.list(homeworkList));
        mv.addObject("data",homeworks);
        mv.setViewName("homework/index");
        return mv;
    }

    //删除作业
    @GetMapping("/desc/{param}")
    @PreAuthorize("hasRole('admin')")
    public ModelAndView descHomework(ModelAndView mv,@PathVariable("param") String desc){
        QueryWrapper homeworkName =new QueryWrapper<HomeworkEntity>().eq("homework_name", desc);
        homeworkService.remove(homeworkName);
        List<HomeworkEntity> homeworks = getList(homeworkService.list(homeworkList));
        mv.addObject("data",homeworks);
        mv.setViewName("homework/index");
        return mv;
    }

    @GetMapping("/index")
    public ModelAndView toHomeworkIndex(ModelAndView mv){
        List<HomeworkEntity> homeworks = getList(homeworkService.list(homeworkList));
        mv.addObject("data",homeworks);
        mv.setViewName("homework/index");
        return mv;
    }
    @PostMapping("/upload/{param}")
    public String  uploadHomework(@RequestParam("file") MultipartFile file
            , Map<String,Object> map
            ,@PathVariable("param") String param){
        List<HomeworkEntity> list = getList(homeworkService.list(homeworkList));
        List<HomeworkEntity>  homeworks = list.stream().filter(x->{
            if (x.getHomeworkName().equals(param)){
                return true;
            }else {
                return false;
            }
        }).collect(Collectors.toList());
        if(homeworks==null||homeworks.size()==0||homeworks.get(0).getFlag()==1){
            map.put("msg","对不起,此作业已经停止接收");
            return "homework/success";
        }
        String filePath="D:/1workfiles/classFile/2020-2021-1/homework/"+param+"/";
        filePath=filePath+file.getOriginalFilename();
        MyFileUtil.uploadFile(filePath,file);
        log.info(file.getOriginalFilename());
        map.put("msg","作业上传成功");
        return "homework/success";
    }

    @GetMapping("/toHomeworkUpload/{param}")
    public String toHomeworkUpload(Map<String,Object> map
            ,@PathVariable("param") String param){
        //如果不是我设定的那几个作业参数，就跳转到指定页面
        List<HomeworkEntity> list = getList(homeworkService.list(homeworkList));
        List<HomeworkEntity> homeworks = list.stream().filter(x -> {
            if (x.getHomeworkName().equals(param)) {
                return true;
            } else {
                return false;
            }
        }).collect(Collectors.toList());
        if (homeworks.size()==0){
            map.put("data",list);
            return "homework/index";
        }
        File file = FileUtil.file("D:/1workfiles/classFile/2020-2021-1/homework/"+param+"/");
        if (!file.exists()) {
            file.mkdirs();
        }
        File[] files = file.listFiles();
        //查询总共交了多少个作业
        List<String> collect = Arrays.stream(files)
                .map((x) ->
                        x.getName()).collect(Collectors.toList());
        map.put("data",collect);
        map.put("curHomework",homeworks.get(0));
        //当前是哪个作业
        map.put("href",param);
        //显示完成了多少人的进度
        Double completed=collect.size()/40.0*100;

        map.put("completed",completed);
        return "homework/upload";
    }

    public List<HomeworkEntity> getList(List<HomeworkEntity> list){
        List<HomeworkEntity> homeworkEntityList = list.stream().map(x -> {
            if (x.getEndTime().isBefore(LocalDateTime.now())) {
                x.setFlag(1);
            }else {
                x.setFlag(0);
            }
            return x;
        }).collect(Collectors.toList());
        return homeworkEntityList;
    }
}
