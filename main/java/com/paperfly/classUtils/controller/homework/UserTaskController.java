package com.paperfly.classUtils.controller.homework;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.paperfly.classUtils.entity.UserTaskEntity;
import com.paperfly.classUtils.service.UserTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/task")
public class UserTaskController {

    @Autowired
    UserTaskService userTaskService;

    @PostMapping("/addTask")
    public String addTask(String taskName){
        UserTaskEntity userTaskEntity = new UserTaskEntity();
        userTaskEntity.setTaskName(taskName);
        userTaskEntity.setTaskIsCompleted(0);
        boolean save = userTaskService.save(userTaskEntity);
        return "admin/index";
    }

    @PostMapping("/updateTask")
    public String updateTask(UserTaskEntity userTaskEntity){
        UpdateWrapper<UserTaskEntity> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("task_id",userTaskEntity.getTaskId());
        boolean update = userTaskService.update(updateWrapper);
        return "admin/index";
    }

    @GetMapping("/getTasks/{isCompleted}")
    public String getTasks(@PathVariable("isCompleted") Integer isCompleted, Map map){
        QueryWrapper<UserTaskEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("task_is_completed",isCompleted);
        List<UserTaskEntity> list = userTaskService.list(queryWrapper);
        map.put("data",list);
        return "homework/upload";
    }
}
