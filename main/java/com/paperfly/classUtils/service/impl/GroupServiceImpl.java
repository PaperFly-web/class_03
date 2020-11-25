package com.paperfly.classUtils.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.paperfly.classUtils.dao.GroupDao;
import com.paperfly.classUtils.entity.GroupEntity;
import com.paperfly.classUtils.service.GroupService;
import org.springframework.stereotype.Service;

@Service("groupService")
public class GroupServiceImpl   extends ServiceImpl<GroupDao, GroupEntity> implements GroupService {
}
