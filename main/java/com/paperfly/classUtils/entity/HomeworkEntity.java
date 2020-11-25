package com.paperfly.classUtils.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.apache.poi.ss.formula.functions.T;

import java.time.LocalDateTime;

@Data
@TableName("homework")
public class HomeworkEntity {
    @TableId
    Long homeworkId;
    String homeworkName;
    //这个作业是否删除
    @TableLogic(delval = "1",value = "0")
    Integer isDel;

    LocalDateTime endTime;

    @TableField(exist = false)
            //班助判断是否为在截止时间之前
    Integer flag;
}
