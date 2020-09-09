package com.paperfly;


import cn.hutool.core.util.RandomUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class ApplicationTests {
    @Test
   public void t1(){
       byte[] bytes = RandomUtil.randomBytes(2);
        for (byte aByte : bytes) {
            System.out.println(aByte);
        }
   }
}
