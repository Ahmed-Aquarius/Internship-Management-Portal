package com.example.internship_portal.service;

import com.example.internship_portal.dto.TaskDto;
import com.example.internship_portal.exception.ResourceNotFoundException;
import com.example.internship_portal.model.Task;
import com.example.internship_portal.repo.InternshipRepository;
import com.example.internship_portal.repo.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepo;
    private final InternshipRepository internshipRepo;

    @Override
    public TaskDto.Response create(TaskDto.CreateRequest req){
        var internship = internshipRepo.findById(req.internshipId())
                .orElseThrow(() -> new ResourceNotFoundException("Internship not found"));
        var t = new Task();
        t.setTitle(req.title());
        t.setDescription(req.description());
        t.setDeadline(req.deadline());
        t.setInternship(internship);
        t.setIsActive(true);
        return toDto(taskRepo.save(t));
    }
    @Override
    public Page<TaskDto.Response> list(Long internshipId, Pageable pageable){
        var page = (internshipId==null)
                ? taskRepo.findByIsActiveTrue(pageable)
                : taskRepo.findByInternshipId(internshipId, pageable);
        return page.map(this::toDto);
    }

    @Override
    public TaskDto.Response get(Long id){
        return toDto(taskRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found")));
    }

    @Override
    public TaskDto.Response update(Long id, TaskDto.UpdateRequest req){
        var t = taskRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found"));
        if (req.title()!=null) t.setTitle(req.title());
        if (req.description()!=null) t.setDescription(req.description());
        if (req.deadline()!=null) t.setDeadline(req.deadline());
        if (req.isActive()!=null) t.setIsActive(req.isActive());
        return toDto(taskRepo.save(t));
    }
    @Override
    public void delete(Long id){
        if(!taskRepo.existsById(id)) throw new ResourceNotFoundException("Task not found");
        taskRepo.deleteById(id);
    }

    private TaskDto.Response toDto(Task t){
        return new TaskDto.Response(
                t.getId(), t.getTitle(), t.getDescription(),
                t.getDeadline(), t.getIsActive(), t.getInternship().getId()
                );
    }


}
