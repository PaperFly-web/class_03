package com.paperfly.classUtils.entity;

import lombok.Data;

@Data
public class OlaMi {

    String data_type="stt";
    olaMi data=new olaMi();
    @Data
    class olaMi{
        Integer input_type=1;
        String text="我爱你";
    }
}
