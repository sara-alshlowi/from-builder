package com.hibernate.hibernatePlayground.Repo;

import com.hibernate.hibernatePlayground.Entity.Form;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormRepo extends JpaRepository<Form, Long> {
}
