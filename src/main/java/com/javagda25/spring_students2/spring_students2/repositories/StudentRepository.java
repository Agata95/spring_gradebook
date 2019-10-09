package com.javagda25.spring_students2.spring_students2.repositories;

import com.javagda25.spring_students2.spring_students2.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
