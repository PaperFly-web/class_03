package com.paperfly.classUtils.dao;

import com.paperfly.classUtils.entity.PeopleGroupEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * 
 * @author paperfly
 * @email 1430978392@qq.com
 * @date 2020-09-08 21:16:50
 */
@Mapper
public interface PeopleGroupDao extends BaseMapper<PeopleGroupEntity> {
	PeopleGroupEntity testSelectOne(String name);
}
