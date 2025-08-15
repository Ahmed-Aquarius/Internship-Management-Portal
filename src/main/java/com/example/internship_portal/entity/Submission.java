package com.example.internship_portal.entity;

import com.example.internship_portal.entity.users.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "submissions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Submission {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id", nullable = false)
    @NotNull(message = "Task is required")
    private Task task;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "intern_id", nullable = false)
    @NotNull(message = "Intern is required")
    private User intern;
    
    @Column(name = "content", columnDefinition = "TEXT", nullable = false)
    @NotNull(message = "Submission content is required")
    private String content;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private SubmissionStatus status = SubmissionStatus.SUBMITTED;
    
    @Column(name = "score")
    private Integer score;
    
    @Column(name = "feedback", columnDefinition = "TEXT")
    private String feedback;
    
    @Column(name = "submitted_at")
    private LocalDateTime submittedAt;
    
    @Column(name = "reviewed_at")
    private LocalDateTime reviewedAt;
    
    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;
    
    public enum SubmissionStatus {
        SUBMITTED,
        APPROVED,
        REJECTED,
        NEEDS_REVISION
    }
    
    @PrePersist
    protected void onCreate() {
        if (submittedAt == null) {
            submittedAt = LocalDateTime.now();
        }
    }

    public void setTask(@NotNull(message = "Task is required") Task task) {
        this.task = task;
    }
}
