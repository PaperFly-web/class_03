package com.paperfly.classUtils.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.paperfly.classUtils.dao.UserFileDao;
import com.paperfly.classUtils.entity.UserFileEntity;
import com.paperfly.classUtils.service.UserFileService;
import org.springframework.stereotype.Service;

@Service("userFileService")
public class UserFileServiceImpl extends ServiceImpl<UserFileDao, UserFileEntity> implements UserFileService {
}
