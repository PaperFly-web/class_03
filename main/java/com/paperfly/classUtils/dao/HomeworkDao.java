package com.paperfly.classUtils.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.paperfly.classUtils.entity.HomeworkEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HomeworkDao extends BaseMapper<HomeworkEntity> {
}
