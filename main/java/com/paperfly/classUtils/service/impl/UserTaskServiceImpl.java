package com.paperfly.classUtils.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.paperfly.classUtils.dao.UserTaskDao;
import com.paperfly.classUtils.entity.UserTaskEntity;
import com.paperfly.classUtils.service.UserTaskService;
import org.springframework.stereotype.Service;

@Service("userTaskService")
public class UserTaskServiceImpl extends ServiceImpl<UserTaskDao, UserTaskEntity> implements UserTaskService {
}
