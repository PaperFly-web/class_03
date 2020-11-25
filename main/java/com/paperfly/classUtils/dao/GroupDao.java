package com.paperfly.classUtils.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.paperfly.classUtils.entity.GroupEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GroupDao extends BaseMapper<GroupEntity> {
}
