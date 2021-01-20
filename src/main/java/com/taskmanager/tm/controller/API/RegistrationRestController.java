package com.taskmanager.tm.controller.API;

import com.taskmanager.tm.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/registration")
public class RegistrationRestController {

    private RegistrationService registrationService;

    @Autowired
    public void setRegistrationService(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping
    public ResponseEntity register(@RequestParam String username, @RequestParam String password){

        ResponseEntity responseEntity = null;

        try {
            registrationService.add(username, password);
            responseEntity = new ResponseEntity(HttpStatus.OK);
        }
        catch (Exception e){
            responseEntity = new ResponseEntity(e.toString(), HttpStatus.CONFLICT);
        }

        return responseEntity;

    }

}
