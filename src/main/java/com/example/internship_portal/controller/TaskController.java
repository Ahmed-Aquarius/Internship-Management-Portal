package com.example.internship_portal.controller;


import com.example.internship_portal.dto.TaskDto;
import com.example.internship_portal.service.TaskServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor

public class TaskController {
    private final TaskServiceImpl service;

    @PostMapping
    public ResponseEntity<TaskDto.Response> create(@Valid @RequestBody TaskDto.CreateRequest req){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(req));
    }

    @GetMapping
    public Page<TaskDto.Response> list(
            @RequestParam(required = false) Long internshipId,
            Pageable pageable) {
        return service.list(internshipId, pageable);
    }

    @GetMapping("/{id}")
    public TaskDto.Response get(@PathVariable Long id){ return service.get(id); }

    @PatchMapping("/{id}")
    public TaskDto.Response update(@PathVariable Long id, @RequestBody TaskDto.UpdateRequest req){
        return service.update(id, req);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
}

}
