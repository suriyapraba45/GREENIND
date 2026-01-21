package com.greenind.greenind.repository;

import com.greenind.greenind.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findByStudentIdAndPassword(String studentId, String password);

    Optional<Student> findByStudentId(String studentId);

    Student findByUsername(String username);
}