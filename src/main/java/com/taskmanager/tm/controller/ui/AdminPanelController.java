package com.taskmanager.tm.controller.ui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminPanelController {

    @GetMapping
    public String getAdminPanel(){
        return "redir:ect/tasks";
    }
}
