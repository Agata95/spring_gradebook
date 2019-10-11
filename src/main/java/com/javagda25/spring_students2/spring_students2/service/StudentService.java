package com.javagda25.spring_students2.spring_students2.service;

import com.javagda25.spring_students2.spring_students2.model.Student;
import com.javagda25.spring_students2.spring_students2.repositories.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;


    public List<Student> listAllStudents() {
        return studentRepository.findAll();
    }

    public void saveStudent(Student student) {
        studentRepository.save(student);
    }

    public void removeStudentById(Long id) {
        studentRepository.deleteById(id);
    }

    public Optional<Student> findByStudentId(Long id) {
        return studentRepository.findById(id);
    }

    public List<Student> getAll() {
        return studentRepository.findAll();
    }

}
