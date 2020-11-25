package com.paperfly.classUtils.utils;

import com.paperfly.classUtils.entity.PeopleGroupEntity;
import com.paperfly.classUtils.entity.PeopleInfoEntity;

import java.util.List;

public class PeopleUitl {

    public static PeopleGroupEntity uti(List<PeopleInfoEntity> peopleInfoEntityList){
        PeopleGroupEntity peopleGroupEntity = new PeopleGroupEntity();
        String myName="";
        for (int i = 0; i < peopleInfoEntityList.size(); i++) {
            if(i==0){
                myName=peopleInfoEntityList.get(i).getName();
            }else {
                myName=myName+","+peopleInfoEntityList.get(i).getName();
            }
        }

        String myNo="";
        for (int i = 0; i < peopleInfoEntityList.size(); i++) {
            if(i==0){
                myNo=peopleInfoEntityList.get(i).getNo();
            }else {
                myNo=myNo+","+peopleInfoEntityList.get(i).getNo();
            }
        }
        peopleGroupEntity.setName(myName);
        peopleGroupEntity.setNo(myNo);
        peopleGroupEntity.setOrderTime(System.currentTimeMillis());
        return peopleGroupEntity;
    }
}
