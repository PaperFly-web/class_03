package com.paperfly.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.Query;
import com.paperfly.utils.PageUtils;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import com.paperfly.dao.PeopleInfoDao;
import com.paperfly.entity.PeopleInfoEntity;
import com.paperfly.service.PeopleInfoService;


@Service("peopleInfoService")
public class PeopleInfoServiceImpl extends ServiceImpl<PeopleInfoDao, PeopleInfoEntity> implements PeopleInfoService {



}