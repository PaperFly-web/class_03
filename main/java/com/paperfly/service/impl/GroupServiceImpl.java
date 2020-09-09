package com.paperfly.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.paperfly.dao.GroupDao;
import com.paperfly.entity.PeopleGroupEntity;
import com.paperfly.service.GroupService;
import org.springframework.stereotype.Service;

@Service("groupService")
public class GroupServiceImpl extends ServiceImpl<GroupDao, PeopleGroupEntity> implements GroupService {



}