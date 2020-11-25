package com.paperfly.classUtils.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("user_task")
public class UserTaskEntity {
    @TableId
    private Long taskId;
    private String taskName;
    private Integer taskIsCompleted;
}
