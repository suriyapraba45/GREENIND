package com.greenind.greenind.repository;

import com.greenind.greenind.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
