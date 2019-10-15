package com.javagda25.spring_students2.spring_students2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private GradeSubject subject;

    @CreationTimestamp
    @Column(updatable = false)
//    nie działa:
//    @DateTimeFormat(pattern = "dd-MM-YYYY")
    private LocalDateTime dateAdded;

    private double gradeVal;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    public Grade(GradeSubject subject, double grade) {
        this.subject = subject;
        this.gradeVal = grade;
    }
}
