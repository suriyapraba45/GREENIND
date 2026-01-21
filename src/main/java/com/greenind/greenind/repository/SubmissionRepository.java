package com.greenind.greenind.repository;

import com.greenind.greenind.model.Submission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubmissionRepository extends JpaRepository<Submission, Long> {

    List<Submission> findByStudentStudentId(Long studentId);

}