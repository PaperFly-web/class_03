package com.paperfly.classUtils.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import com.paperfly.classUtils.dao.PeopleInfoDao;
import com.paperfly.classUtils.entity.PeopleInfoEntity;
import com.paperfly.classUtils.service.PeopleInfoService;


@Service("peopleInfoService")
public class PeopleInfoServiceImpl extends ServiceImpl<PeopleInfoDao, PeopleInfoEntity> implements PeopleInfoService {



}