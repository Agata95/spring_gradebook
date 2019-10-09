package com.javagda25.spring_students2.spring_students2.repositories;

import com.javagda25.spring_students2.spring_students2.model.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GradeRepository extends JpaRepository<Grade, Long> {
}
