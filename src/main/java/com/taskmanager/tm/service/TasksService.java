package com.taskmanager.tm.service;

import com.taskmanager.tm.model.Task;
import com.taskmanager.tm.repository.TasksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TasksService {

    private TasksRepository tasksRepository;
    private RegistrationService registrationService;

    @Autowired
    public void setUserService(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @Autowired
    public void setTasksRepository(TasksRepository tasksRepository) {
        this.tasksRepository = tasksRepository;
    }

    public List<Task> getAllTasksByUser(String username){

        return tasksRepository.findByUsernameAndCompletedOrderByCreationDateTimeDesc(username, false);

    }

    public Task addTask(String title) throws Exception{

        Task task = new Task(title);

        saveTask(task);

        return task;

    }

    public Task saveTask(Task task) throws Exception{

        if (task.getId() == null){
            task.setCreationDateTime(LocalDateTime.now());
        }

        if (task.getUser() == null) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            String username = authentication.getName();

            task.setUser(registrationService.getByUsername(username));
        }

        tasksRepository.save(task);

        return task;
    }

    public Task completeTask(Long id) throws Exception {

        Optional<Task> optionalTask = tasksRepository.findById(id);

        Task task =  tasksRepository.findById(id).orElseThrow(() -> new Exception("Task with id " + id + " not found"));;

        if (task.isCompleted()){
            throw new Exception("Task with id " + id + " already completed");
        }

        task.setCompleted(true);

        tasksRepository.save(task);

        return task;

    }

    public Task getTaskByID(Long id){
        return tasksRepository.getOne(id);
    }

}
