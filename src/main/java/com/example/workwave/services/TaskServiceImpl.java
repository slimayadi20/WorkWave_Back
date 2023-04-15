package com.example.workwave.services;

import com.example.workwave.entities.Task;
import com.example.workwave.entities.User;
import com.example.workwave.entities.holiday;
import com.example.workwave.repositories.TaskRepository;
import com.example.workwave.repositories.UserRepository;
import com.example.workwave.services.UserServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class TaskServiceImpl {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository employeeRepository;

    public List<Task> GetAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(Long taskId) {
        return taskRepository.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException("Task not found"));
    }

    public Task createTask(Task task) {

        task.setEMP_ID(task.getEMP_ID());
        return taskRepository.save(task);
    }

    public Task updateTask(Long taskId, Task task) {
        Task existingTask = taskRepository.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException("Task not found"));
        existingTask.setTASK_NAME(task.getTASK_NAME());

        existingTask.setEMP_ID(task.getEMP_ID());
        return taskRepository.save(existingTask);
    }

    public void deleteTask(Long taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException("Task not found"));
        taskRepository.delete(task);
    }
}
