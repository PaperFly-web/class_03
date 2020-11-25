package com.paperfly.classUtils.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.paperfly.classUtils.dao.PeopleGroupDao;
import com.paperfly.classUtils.entity.PeopleGroupEntity;
import com.paperfly.classUtils.service.PeopleGroupService;
import org.springframework.stereotype.Service;

@Service("peopleGroupService")
public class PeopleGroupServiceImpl extends ServiceImpl<PeopleGroupDao, PeopleGroupEntity> implements PeopleGroupService {



}