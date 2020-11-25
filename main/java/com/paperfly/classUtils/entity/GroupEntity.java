package com.paperfly.classUtils.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.models.auth.In;
import lombok.Data;

@Data
@TableName("my_group")
public class GroupEntity {
    @TableId
    Long groupId;
    String groupName;

    @TableLogic(delval = "1",value = "0")
    Integer isCompleted;
}
