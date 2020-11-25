package com.paperfly.classUtils.controller.homework;

import com.paperfly.classUtils.service.UserFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserFileController {
    @Autowired
    UserFileService userFileService;
}
