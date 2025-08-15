package com.example.internship_portal.entity;

import com.example.internship_portal.entity.users.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Entity
@Table(name = "internships")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Internship {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "title", nullable = false, length = 200)
    @NotBlank(message = "Title is required")
    private String title;
    
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
    
    @Column(name = "skills", columnDefinition = "TEXT")
    private String skills;
    
    @Column(name = "duration", nullable = false)
    @Positive(message = "Duration must be positive")
    private Integer duration;
    
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;
    
    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;
    
    @Column(name = "slots", nullable = false)
    @Positive(message = "Slots must be positive")
    private Integer slots;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private InternshipStatus status = InternshipStatus.OPEN;
    
    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    
    // Relationships
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mentor_id", nullable = false)
    private User mentor;
    
    public enum InternshipStatus {
        OPEN,
        CLOSED,
        IN_PROGRESS,
        COMPLETED
    }
}
