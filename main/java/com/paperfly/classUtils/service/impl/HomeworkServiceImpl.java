package com.paperfly.classUtils.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.paperfly.classUtils.dao.HomeworkDao;
import com.paperfly.classUtils.entity.HomeworkEntity;
import com.paperfly.classUtils.service.HomeworkService;
import org.springframework.stereotype.Service;

@Service("homeworkService")
public class HomeworkServiceImpl extends ServiceImpl<HomeworkDao, HomeworkEntity> implements HomeworkService {

}
