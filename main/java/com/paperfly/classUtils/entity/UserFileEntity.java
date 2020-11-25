package com.paperfly.classUtils.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("user_file")
public class UserFileEntity {
    @TableId
    private Long fileId;
    private String fileName;
    private String filePath;
    private Long taskId;
}
