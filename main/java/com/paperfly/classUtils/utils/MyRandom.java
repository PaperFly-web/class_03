package com.paperfly.classUtils.utils;

import cn.hutool.core.util.RandomUtil;

import java.util.ArrayList;
import java.util.List;

public class MyRandom {

    public static List random(List list){
        List randomList=new ArrayList<>(30);
        int size = list.size();
        //随机组合
        for (int i=0;i<size;i++){
            Object o = RandomUtil.randomEle(list);
            list.remove(o);
            randomList.add(o);
        }
        return randomList;
    }
}
