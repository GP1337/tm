package com.taskmanager.tm.repository;

import com.taskmanager.tm.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TasksRepository extends JpaRepository<Task, Long>{

    public List<Task> findByUsernameAndCompletedOrderByCreationDateTimeDesc(String username, boolean completed);

}
