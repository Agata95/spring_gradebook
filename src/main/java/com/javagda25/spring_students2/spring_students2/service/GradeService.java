package com.javagda25.spring_students2.spring_students2.service;

import com.javagda25.spring_students2.spring_students2.model.Grade;
import com.javagda25.spring_students2.spring_students2.model.Student;
import com.javagda25.spring_students2.spring_students2.repositories.GradeRepository;
import com.javagda25.spring_students2.spring_students2.repositories.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@AllArgsConstructor
public class GradeService {
    private final GradeRepository gradeRepository;
    private final StudentRepository studentRepository;

    public void saveGrade(Grade grade, Long studentId) {
        if (studentRepository.existsById(studentId)) {
            Student student = studentRepository.getOne(studentId);
            grade.setStudent(student);
            gradeRepository.save(grade);
        } else {
            throw new EntityNotFoundException("Student not found.");
        }
    }

    public List<Grade> listAllGrade() {
        return gradeRepository.findAll();
    }
}
