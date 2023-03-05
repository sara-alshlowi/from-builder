package com.hibernate.hibernatePlayground.Repo;

import com.hibernate.hibernatePlayground.Entity.Field;
import com.hibernate.hibernatePlayground.Entity.Form;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FieldRepository extends JpaRepository<Field, Long> {

    boolean existsByIdAndForm_Id(Long fieldId, Long formId);

}
