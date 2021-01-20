package com.taskmanager.tm.controller.ui;

import com.taskmanager.tm.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private RegistrationService registrationService;

    @Autowired
    public void setUserService(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }


}
