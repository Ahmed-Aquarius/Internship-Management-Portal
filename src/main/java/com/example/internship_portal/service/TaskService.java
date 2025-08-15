package com.example.internship_portal.service;

import com.example.internship_portal.dto.TaskDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TaskService {
    TaskDto.Response create(TaskDto.CreateRequest req);
    Page<TaskDto.Response> list(Long internshipId, Pageable pageable);
    TaskDto.Response get(Long id);
    TaskDto.Response update(Long id, TaskDto.UpdateRequest req);
    void delete(Long id);
}
