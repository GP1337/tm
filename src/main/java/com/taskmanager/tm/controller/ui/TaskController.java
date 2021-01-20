package com.taskmanager.tm.controller.ui;

import com.taskmanager.tm.model.Task;
import com.taskmanager.tm.service.TasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    private TasksService tasksService;

    @Autowired
    public void setTasksService(TasksService tasksService) {
        this.tasksService = tasksService;
    }

    @GetMapping
    public String getTasks(Model model){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String username = authentication.getName();

        model.addAttribute("tasks", tasksService.getAllTasksByUser(username));

        return "taskList";

    }

    @GetMapping("/taskDetail")
    public String taskDetail(Model model, @RequestParam(required = false) Long id){

        Task task = null;

        if (id != null){
            task = tasksService.getTaskByID(id);
        }
        if (task == null){
            task = new Task();
        }

        model.addAttribute("task", task);

        return "taskDetails";
    }

    @PostMapping("/savetask")
    public String saveTask(@ModelAttribute(value = "task") Task task){

        try {
            tasksService.saveTask(task);
        } catch (Exception e) {
            return  e.toString();
        }

        return "redirect:/tasks";
    }

   @PostMapping("/completeTask")
    public String completeTask(@RequestParam Long id){

       try {
           tasksService.completeTask(id);
       } catch (Exception e) {
           return e.toString();
       }

       return "redirect:/tasks";

    }

}
