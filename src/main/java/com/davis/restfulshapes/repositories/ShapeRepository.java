package com.davis.restfulshapes.repositories;

import com.davis.restfulshapes.entity.Shape;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

@Repository
public interface ShapeRepository extends JpaRepository<Shape, Integer> {

}
