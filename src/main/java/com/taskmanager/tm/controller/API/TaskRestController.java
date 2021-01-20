package com.taskmanager.tm.controller.API;

import com.taskmanager.tm.model.Task;
import com.taskmanager.tm.service.TasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tasks")
public class TaskRestController {

    private TasksService tasksService;

    @Autowired
    public void setTasksService(TasksService tasksService) {
        this.tasksService = tasksService;
    }

    @GetMapping
    public ResponseEntity getTasks(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String username = authentication.getName();

        ResponseEntity responseEntity = new ResponseEntity(tasksService.getAllTasksByUser(username), HttpStatus.OK);

        return responseEntity;

    }

    @PostMapping
    public ResponseEntity saveTask(@RequestBody Task task){

        ResponseEntity responseEntity = null;

        try {
            responseEntity = new ResponseEntity(tasksService.saveTask(task), HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity(e.toString(), HttpStatus.BAD_REQUEST);
        }

        return responseEntity;
    }

    @PostMapping("/completeTask")
    public ResponseEntity completeTask(@RequestParam Long id){

        ResponseEntity responseEntity = null;

        try {
            responseEntity = new ResponseEntity(tasksService.completeTask(id), HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity(e.toString(), HttpStatus.OK);
        }

        return responseEntity;

    }
}
