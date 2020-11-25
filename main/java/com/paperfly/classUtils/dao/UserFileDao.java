package com.paperfly.classUtils.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.paperfly.classUtils.entity.UserFileEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper

public interface UserFileDao  extends BaseMapper<UserFileEntity> {

}
