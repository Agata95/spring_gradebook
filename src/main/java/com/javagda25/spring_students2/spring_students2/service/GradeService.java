package com.javagda25.spring_students2.spring_students2.service;

import com.javagda25.spring_students2.spring_students2.model.Grade;
import com.javagda25.spring_students2.spring_students2.model.Student;
import com.javagda25.spring_students2.spring_students2.repositories.GradeRepository;
import com.javagda25.spring_students2.spring_students2.repositories.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class GradeService {
    private final GradeRepository gradeRepository;
    private final StudentRepository studentRepository;

//    public void saveGrade(Grade gradeVal, Long studentId) {
//        if (studentRepository.existsById(studentId)) {
//            Student student = studentRepository.getOne(studentId);
//            gradeVal.setStudent(student);
//            gradeVal.getStudent().setId(studentId);
//            gradeRepository.save(gradeVal);
//        } else {
//            throw new EntityNotFoundException("Student not found.");
//        }
//    }

    public void saveGrade(Grade grade, Long studentId) {
        if (studentRepository.existsById(studentId)) {
            Optional<Student> studentOptional = studentRepository.findById(studentId);
            grade.setStudent(studentOptional.get());
            gradeRepository.save(grade);
        } else {
            throw new EntityNotFoundException("Student not found.");
        }
    }

    public List<Grade> listAllGrade() {
        return gradeRepository.findAll();
    }

    public void remove(Long gradeId) {
        gradeRepository.deleteById(gradeId);
    }

    public Optional<Grade> findByGradeId(Long gradeId) {
        return gradeRepository.findById(gradeId);
    }


    public Set<Grade> setAllGrades() {
        return (Set<Grade>) gradeRepository.findAll();
    }
}
