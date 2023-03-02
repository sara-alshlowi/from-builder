package com.hibernate.hibernatePlayground.Repo;

import com.hibernate.hibernatePlayground.Entity.Submission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubmissionRepository extends JpaRepository<Submission, Long> {
}
