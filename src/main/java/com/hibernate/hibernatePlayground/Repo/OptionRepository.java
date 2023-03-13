package com.hibernate.hibernatePlayground.Repo;

import com.hibernate.hibernatePlayground.Entity.Field;
import com.hibernate.hibernatePlayground.Entity.Options;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OptionRepository extends JpaRepository<Options, Long> {

    List<Options> findAllByField(Field field);
}
