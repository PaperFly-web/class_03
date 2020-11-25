package com.paperfly.classUtils.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.paperfly.classUtils.entity.UserTaskEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper

public interface UserTaskDao  extends BaseMapper<UserTaskEntity> {

}
